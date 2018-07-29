package com.example.android.apothecarystreasures;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.apothecarystreasures.data.PotionContract.PotionEntry;

public class PotionCursorAdapter extends CursorAdapter {

    public PotionCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // Create an empty list view layout
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    // Update each View of the layout with data from the current row
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find Views in the layout
        ImageView imageView = view.findViewById(R.id.image_view);
        TextView nameView = view.findViewById(R.id.name_view);
        TextView priceView = view.findViewById(R.id.price_view);
        TextView quantityView = view.findViewById(R.id.quantity_view);
        Button buttonView = view.findViewById(R.id.button_view);

        // Use column indices for the current row to extract the appropriate values
        int idColumnIndex = cursor.getColumnIndex(PotionEntry._ID);
        int imageColumnIndex = cursor.getColumnIndex(PotionEntry.COLUMN_IMAGE);
        int nameColumnIndex = cursor.getColumnIndex(PotionEntry.COLUMN_NAME);
        int priceColumnIndex = cursor.getColumnIndex(PotionEntry.COLUMN_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(PotionEntry.COLUMN_QUANTITY);

        final int potionId = cursor.getInt(idColumnIndex);
        int potionImage = cursor.getInt(imageColumnIndex);
        final String potionName = cursor.getString(nameColumnIndex);
        final int potionPrice = cursor.getInt(priceColumnIndex);
        final int potionQuantity = cursor.getInt(quantityColumnIndex);

        imageView.setImageResource(potionImage);
        nameView.setText(potionName);
        priceView.setText(potionPrice + " Coins to Earn");
        if (potionQuantity == 1) {
            quantityView.setText(potionQuantity + " Piece");
        } else {
            quantityView.setText(potionQuantity + " Pieces");
        }

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (potionQuantity > 0) {
                    // Decrease the quantity by 1
                    int newQuantity = potionQuantity - 1;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(PotionEntry.COLUMN_QUANTITY, newQuantity);
                    // Decrease the price accordingly
                    int newPrice = updatePrice(newQuantity, potionPrice, potionName);
                    contentValues.put(PotionEntry.COLUMN_PRICE, newPrice);

                    Uri potionUri = ContentUris.withAppendedId(PotionEntry.CONTENT_URI, potionId);
                    context.getContentResolver()
                            .update(potionUri, contentValues, null, null);
                } else {
                    Toast.makeText(context, R.string.out_of_stock, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Decrease quantity according to the potion's name
    private int updatePrice(int quantity, int price, String name) {
        if (quantity == 0) {
            price = 0;
        } else {
            if (name.equals(PotionEntry.ANONYMITYS_BREATH)
                    || name.equals(PotionEntry.EYE_OF_ATLANTIS) || name.equals(PotionEntry.INFERNAL_ROOTS)
                    || name.equals(PotionEntry.JACKS_BEANSTALK) || name.equals(PotionEntry.SHAPER_OF_DREAMS)) {
                price -= 100;
            } else if (name.equals(PotionEntry.FAIRYS_HOPE)
                    || name.equals(PotionEntry.RAYS_OF_A_STAR) || name.equals(PotionEntry.REGRET_OF_GENESIS)
                    || name.equals(PotionEntry.SILENCER_OF_DOUBTS) || name.equals(PotionEntry.WAIL_OF_A_MERMAID)) {
                price -= 75;
            } else if (name.equals(PotionEntry.BEAUTYS_DECEIT)
                    || name.equals(PotionEntry.CAROUSAL_GIFT) || name.equals(PotionEntry.CHILDS_WHISPER)
                    || name.equals(PotionEntry.TRUE_LOVES_MYTH) || name.equals(PotionEntry.WALLFLOWERS_SOUL)) {
                price -= 50;
            } else {
                price -= 25;
            }
        }
        return price;
    }
}