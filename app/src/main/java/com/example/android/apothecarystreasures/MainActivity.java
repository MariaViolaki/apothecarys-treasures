package com.example.android.apothecarystreasures;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.apothecarystreasures.data.PotionContract.PotionEntry;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    PotionCursorAdapter potionCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind the cursor adapter to the list view
        ListView listView = findViewById(R.id.list_view);

        View defaultView = findViewById(R.id.default_view);
        listView.setEmptyView(defaultView);

        potionCursorAdapter = new PotionCursorAdapter(this, null);
        listView.setAdapter(potionCursorAdapter);

        // Get the ID from the clicked item and send the complete URI to the DetailsActivity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                Uri potionUri = ContentUris.withAppendedId(PotionEntry.CONTENT_URI, id);
                intent.setData(potionUri);
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(1, null, this);
    }

    // Query the data for the requested columns on the background
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                PotionEntry._ID,
                PotionEntry.COLUMN_IMAGE,
                PotionEntry.COLUMN_NAME,
                PotionEntry.COLUMN_PRICE,
                PotionEntry.COLUMN_QUANTITY};

        return new CursorLoader(
                this,
                PotionEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    // Update the cursor with new data
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        potionCursorAdapter.swapCursor(data);
    }

    // Reset the cursor by deleting its data
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        potionCursorAdapter.swapCursor(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_potions:
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
                return true;
            case R.id.delete_inventory:
                getContentResolver().delete(PotionEntry.CONTENT_URI, null, null);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}