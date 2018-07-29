package com.example.android.apothecarystreasures.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.android.apothecarystreasures.data.PotionContract.PotionEntry;

public class PotionProvider extends ContentProvider {

    private PotionDbHelper mDbHelper;

    // Integer constants to be used by the URI Matcher
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int POTIONS = 0;
    private static final int POTION_ID = 1;

    // URI patterns to provide access to a single row or the entire table
    static {
        uriMatcher.addURI(PotionContract.CONTENT_AUTHORITY, PotionContract.POTIONS_PATH, POTIONS);
        uriMatcher.addURI(PotionContract.CONTENT_AUTHORITY, PotionContract.POTIONS_PATH + "/#", POTION_ID);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new PotionDbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case POTIONS:
                return PotionEntry.DIRECTORY_LISTING_TYPE;
            case POTION_ID:
                return PotionEntry.SINGLE_ITEM_TYPE;
            default:
                throw new IllegalStateException(uri + " could not be matched");
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        SQLiteDatabase sqLiteDatabase = mDbHelper.getReadableDatabase();

        // Depending on the given URI pattern, query one or more rows
        switch (uriMatcher.match(uri)) {
            case POTIONS:
                cursor = sqLiteDatabase.query(PotionEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case POTION_ID:
                selection = PotionEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(PotionEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Could not query " + uri);
        }

        // Update the cursor according to which URI was used for modifications
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case POTIONS:
                SQLiteDatabase sqLiteDatabase = mDbHelper.getWritableDatabase();
                // Insert a new row to the database successfully unless the ID is equal to -1
                long id = sqLiteDatabase.insert(PotionEntry.TABLE_NAME, null, values);
                if (id == -1) {
                    return null;
                }
                // Notify listeners that the row was updated
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);

            default:
                throw new IllegalArgumentException("Could not insert " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // Don't update the database if there are no new data
        if (values.size() == 0) {
            return 0;
        }
        int updatedRows;
        SQLiteDatabase sqLiteDatabase = mDbHelper.getWritableDatabase();

        // Update a row depending on the given URI pattern
        switch (uriMatcher.match(uri)) {
            case POTION_ID:
                selection = PotionEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                updatedRows = sqLiteDatabase.update(PotionEntry.TABLE_NAME, values, selection, selectionArgs);
                // Notify listeners that the row was updated
                if (updatedRows == 1) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return updatedRows;
            default:
                throw new IllegalArgumentException("Could not update " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deletedRows;
        SQLiteDatabase sqLiteDatabase = mDbHelper.getWritableDatabase();

        // Depending on the given URI pattern, delete one or more rows
        switch (uriMatcher.match(uri)) {
            case POTIONS:
                deletedRows = sqLiteDatabase.delete(PotionEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case POTION_ID:
                selection = PotionEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                deletedRows = sqLiteDatabase.delete(PotionEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Could not delete " + uri);
        }
        // Notify listeners that the rows were updated
        if (deletedRows != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deletedRows;
    }
}