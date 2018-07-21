package com.example.android.apothecarystreasures;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.apothecarystreasures.data.TreasureContract.TreasureEntry;
import com.example.android.apothecarystreasures.data.TreasureDbHelper;


public class TreasureActivity extends AppCompatActivity {

    // Layout Views
    private Spinner mProduct;
    private TextView mQuantity;
    private Spinner mSupplierName;
    private TextView mSupplierPhone;
    private TextView mPrice;
    private TextView mData;
    private ImageView mProductImage;

    // Product options
    private String mAnonymitysBreath = TreasureEntry.ANONYMITYS_BREATH;
    private String maAssassinsChoice = TreasureEntry.ASSASSINS_CHOICE;
    private String mBeautysDeceit = TreasureEntry.BEAUTYS_DECEIT;
    private String mCarousalGift = TreasureEntry.CAROUSAL_GIFT;
    private String mChildsWhisper = TreasureEntry.CHILDS_WHISPER;
    private String mElvenRemedy = TreasureEntry.ELVEN_REMEDY;
    private String mEyeOfAtlantis = TreasureEntry.EYE_OF_ATLANTIS;
    private String mFairysHope = TreasureEntry.FAIRYS_HOPE;
    private String mHydrasHead = TreasureEntry.HYDRAS_HEAD;
    private String mInfernalRoots = TreasureEntry.INFERNAL_ROOTS;
    private String mJacksBeanstalk = TreasureEntry.JACKS_BEANSTALK;
    private String mRaysOfAStar = TreasureEntry.RAYS_OF_A_STAR;
    private String mRegretOfGenesis = TreasureEntry.REGRET_OF_GENESIS;
    private String mShaperOfDreams = TreasureEntry.SHAPER_OF_DREAMS;
    private String mShiningTears = TreasureEntry.SHINING_TEARS;
    private String mSilencerOfDoubts = TreasureEntry.SILENCER_OF_DOUBTS;
    private String mTrueLovesMyth = TreasureEntry.TRUE_LOVES_MYTH;
    private String mVeiledPanacea = TreasureEntry.VEILED_PANACEA;
    private String mWailOfAMermaid = TreasureEntry.WAIL_OF_A_MERMAID;
    private String mWallflowersSoul = TreasureEntry.WALLFLOWERS_SOUL;

    // Supplier options
    private String mDruidsHut = TreasureEntry.DRUIDS_HUT;
    private String mFairyTreehouse = TreasureEntry.FAIRY_TREEHOUSE;
    private String mHermitsHideout = TreasureEntry.HERMITS_HIDEOUT;
    private String mSunkenShip = TreasureEntry.SUNKEN_SHIP;
    private String mWiccasDwelling = TreasureEntry.WICCAS_DWELLING;

    // Supplier phone options
    private String mDruidsHutPhone = TreasureEntry.DRUIDS_HUT_PHONE;
    private String mFairyTreehousePhone = TreasureEntry.FAIRY_TREEHOUSE_PHONE;
    private String mHermitsHideoutPhone = TreasureEntry.HERMITS_HIDEOUT_PHONE;
    private String mSunkenShipPhone = TreasureEntry.SUNKEN_SHIP_PHONE;
    private String mWiccasDwellingPhone = TreasureEntry.WICCAS_DWELLING_PHONE;

    // Database column names
    private String mColumnName = TreasureEntry.COLUMN_NAME;
    private String mColumnQuantity = TreasureEntry.COLUMN_QUANTITY;
    private String mColumnSupplierName = TreasureEntry.COLUMN_SUPPLIER_NAME;
    private String mColumnSupplierPhone = TreasureEntry.COLUMN_SUPPLIER_PHONE;
    private String mColumnPrice = TreasureEntry.COLUMN_PRICE;

    // Entries for each column
    private String mProductEntry;
    private int mQuantityEntry;
    private String mSupplierNameEntry;
    private String mSupplierPhoneEntry;
    private int mPriceEntry;

    private String mBestSupplier;
    private int mProductCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasure);

        mProduct = findViewById(R.id.product);
        mQuantity = findViewById(R.id.quantity);
        mSupplierName = findViewById(R.id.supplier_name);
        mSupplierPhone = findViewById(R.id.supplier_phone);
        mPrice = findViewById(R.id.price);
        mData = findViewById(R.id.data);
        mProductImage = findViewById(R.id.product_image);

        createSpinners();
        handleSpinners();

        // Assign a default value to each entry
        mProductEntry = mAnonymitysBreath;
        mQuantityEntry = 0;
        mSupplierNameEntry = mDruidsHut;
        mSupplierPhoneEntry = mDruidsHutPhone;
        mPriceEntry = 0;

        // Handle button clicks to increase or decrease the Quantity
        Button addition = findViewById(R.id.addition);
        Button subtraction = findViewById(R.id.subtraction);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuantityEntry < 10) {
                    mQuantityEntry += 1;
                } else {
                    mQuantityEntry = 10;
                }
                mQuantity.setText("" + mQuantityEntry);
                calculatePrice();
            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuantityEntry > 0) {
                    mQuantityEntry -= 1;
                } else {
                    mQuantityEntry = 0;
                }
                mQuantity.setText("" + mQuantityEntry);
                calculatePrice();
            }
        });

        Button confirmationButton = findViewById(R.id.confirmation_button);

        confirmationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuantityEntry > 0) {
                    LinearLayout dataLayout = findViewById(R.id.data_layout);
                    dataLayout.setVisibility(View.VISIBLE);
                    dataLayout.requestFocus();

                    insertData();
                    queryData();
                } else {
                    showToast();
                }
            }
        });
    }

    private void calculatePrice() {
        // Calculate according to Quantity choice
        mQuantityEntry = Integer.valueOf(mQuantity.getText().toString());
        mPriceEntry = mProductCost * mQuantityEntry;

        // Calculate according to Supplier choice
        if (mSupplierNameEntry.equals(mBestSupplier)) {
        } else {
            if (mQuantityEntry > 0) {
                mPriceEntry += 50;
            }
        }
        // Update the price TextView
        mPrice.setText(mPriceEntry + " Coins");
    }

    // Create an adapter for each Spinner
    private void createSpinners() {
        ArrayAdapter productAdapter = ArrayAdapter.createFromResource
                (this, R.array.product_options, android.R.layout.simple_spinner_item);
        ArrayAdapter supplierAdapter = ArrayAdapter.createFromResource
                (this, R.array.supplier_options, android.R.layout.simple_spinner_item);

        productAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        supplierAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mProduct.setAdapter(productAdapter);
        mSupplierName.setAdapter(supplierAdapter);
    }

    private void handleSpinners() {

        mProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(itemSelected)) {
                    switch (itemSelected) {
                        case TreasureEntry.ANONYMITYS_BREATH:
                            mProductImage.setImageResource(R.drawable.anonymitys_breath);
                            mProductEntry = mAnonymitysBreath;
                            mBestSupplier = mHermitsHideout;
                            mProductCost = 100;
                            calculatePrice();
                            break;
                        case TreasureEntry.ASSASSINS_CHOICE:
                            mProductImage.setImageResource(R.drawable.assassins_choice);
                            mProductEntry = maAssassinsChoice;
                            mBestSupplier = mHermitsHideout;
                            mProductCost = 25;
                            calculatePrice();
                            break;
                        case TreasureEntry.BEAUTYS_DECEIT:
                            mProductImage.setImageResource(R.drawable.beautys_deceit);
                            mProductEntry = mBeautysDeceit;
                            mBestSupplier = mSunkenShip;
                            mProductCost = 50;
                            calculatePrice();
                            break;
                        case TreasureEntry.CAROUSAL_GIFT:
                            mProductImage.setImageResource(R.drawable.carousal_gift);
                            mProductEntry = mCarousalGift;
                            mBestSupplier = mFairyTreehouse;
                            mProductCost = 50;
                            calculatePrice();
                            break;
                        case TreasureEntry.CHILDS_WHISPER:
                            mProductImage.setImageResource(R.drawable.childs_whisper);
                            mProductEntry = mChildsWhisper;
                            mBestSupplier = mDruidsHut;
                            mProductCost = 50;
                            calculatePrice();
                            break;
                        case TreasureEntry.ELVEN_REMEDY:
                            mProductImage.setImageResource(R.drawable.elven_remedy);
                            mProductEntry = mElvenRemedy;
                            mBestSupplier = mFairyTreehouse;
                            mProductCost = 25;
                            calculatePrice();
                            break;
                        case TreasureEntry.EYE_OF_ATLANTIS:
                            mProductImage.setImageResource(R.drawable.eye_of_atlantis);
                            mProductEntry = mEyeOfAtlantis;
                            mBestSupplier = mSunkenShip;
                            mProductCost = 100;
                            calculatePrice();
                            break;
                        case TreasureEntry.FAIRYS_HOPE:
                            mProductImage.setImageResource(R.drawable.fairys_hope);
                            mProductEntry = mFairysHope;
                            mBestSupplier = mFairyTreehouse;
                            mProductCost = 75;
                            calculatePrice();
                            break;
                        case TreasureEntry.HYDRAS_HEAD:
                            mProductImage.setImageResource(R.drawable.hydras_head);
                            mProductEntry = mHydrasHead;
                            mBestSupplier = mWiccasDwelling;
                            mProductCost = 25;
                            calculatePrice();
                            break;
                        case TreasureEntry.INFERNAL_ROOTS:
                            mProductImage.setImageResource(R.drawable.infernal_roots);
                            mProductEntry = mInfernalRoots;
                            mBestSupplier = mWiccasDwelling;
                            mProductCost = 100;
                            calculatePrice();
                            break;
                        case TreasureEntry.JACKS_BEANSTALK:
                            mProductImage.setImageResource(R.drawable.jacks_beanstalk);
                            mProductEntry = mJacksBeanstalk;
                            mBestSupplier = mFairyTreehouse;
                            mProductCost = 100;
                            calculatePrice();
                            break;
                        case TreasureEntry.RAYS_OF_A_STAR:
                            mProductImage.setImageResource(R.drawable.rays_of_a_star);
                            mProductEntry = mRaysOfAStar;
                            mBestSupplier = mDruidsHut;
                            mProductCost = 75;
                            calculatePrice();
                            break;
                        case TreasureEntry.REGRET_OF_GENESIS:
                            mProductImage.setImageResource(R.drawable.regret_of_genesis);
                            mProductEntry = mRegretOfGenesis;
                            mBestSupplier = mWiccasDwelling;
                            mProductCost = 75;
                            calculatePrice();
                            break;
                        case TreasureEntry.SHAPER_OF_DREAMS:
                            mProductImage.setImageResource(R.drawable.shaper_of_dreams);
                            mProductEntry = mShaperOfDreams;
                            mBestSupplier = mDruidsHut;
                            mProductCost = 100;
                            calculatePrice();
                            break;
                        case TreasureEntry.SHINING_TEARS:
                            mProductImage.setImageResource(R.drawable.shining_tears);
                            mProductEntry = mShiningTears;
                            mBestSupplier = mSunkenShip;
                            mProductCost = 25;
                            calculatePrice();
                            break;
                        case TreasureEntry.SILENCER_OF_DOUBTS:
                            mProductImage.setImageResource(R.drawable.silencer_of_doubts);
                            mProductEntry = mSilencerOfDoubts;
                            mBestSupplier = mHermitsHideout;
                            mProductCost = 75;
                            calculatePrice();
                            break;
                        case TreasureEntry.TRUE_LOVES_MYTH:
                            mProductImage.setImageResource(R.drawable.true_loves_myth);
                            mProductEntry = mTrueLovesMyth;
                            mBestSupplier = mWiccasDwelling;
                            mProductCost = 50;
                            calculatePrice();
                            break;
                        case TreasureEntry.VEILED_PANACEA:
                            mProductImage.setImageResource(R.drawable.veiled_panacea);
                            mProductEntry = mVeiledPanacea;
                            mBestSupplier = mDruidsHut;
                            mProductCost = 25;
                            calculatePrice();
                            break;
                        case TreasureEntry.WAIL_OF_A_MERMAID:
                            mProductImage.setImageResource(R.drawable.wail_of_a_mermaid);
                            mProductEntry = mWailOfAMermaid;
                            mBestSupplier = mSunkenShip;
                            mProductCost = 75;
                            calculatePrice();
                            break;
                        case TreasureEntry.WALLFLOWERS_SOUL:
                            mProductImage.setImageResource(R.drawable.wallflowers_soul);
                            mProductEntry = mWallflowersSoul;
                            mBestSupplier = mHermitsHideout;
                            mProductCost = 50;
                            calculatePrice();
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mProductImage.setImageResource(R.drawable.anonymitys_breath);
                mProductEntry = mAnonymitysBreath;
                mBestSupplier = mHermitsHideout;
                mProductCost = 100;
                calculatePrice();
            }
        });

        mSupplierName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(itemSelected)) {
                    switch (itemSelected) {
                        case TreasureEntry.DRUIDS_HUT:
                            mSupplierNameEntry = mDruidsHut;
                            mSupplierPhoneEntry = mDruidsHutPhone;
                            mSupplierPhone.setText(" " + mSupplierPhoneEntry);
                            calculatePrice();
                            break;
                        case TreasureEntry.FAIRY_TREEHOUSE:
                            mSupplierNameEntry = mFairyTreehouse;
                            mSupplierPhoneEntry = mFairyTreehousePhone;
                            mSupplierPhone.setText(" " + mSupplierPhoneEntry);
                            calculatePrice();
                            break;
                        case TreasureEntry.HERMITS_HIDEOUT:
                            mSupplierNameEntry = mHermitsHideout;
                            mSupplierPhoneEntry = mHermitsHideoutPhone;
                            mSupplierPhone.setText(" " + mSupplierPhoneEntry);
                            calculatePrice();
                            break;
                        case TreasureEntry.SUNKEN_SHIP:
                            mSupplierNameEntry = mSunkenShip;
                            mSupplierPhoneEntry = mSunkenShipPhone;
                            mSupplierPhone.setText(" " + mSupplierPhoneEntry);
                            calculatePrice();
                            break;
                        case TreasureEntry.WICCAS_DWELLING:
                            mSupplierNameEntry = mWiccasDwelling;
                            mSupplierPhoneEntry = mWiccasDwellingPhone;
                            mSupplierPhone.setText(" " + mSupplierPhoneEntry);
                            calculatePrice();
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSupplierNameEntry = mDruidsHut;
                mSupplierPhoneEntry = mDruidsHutPhone;
                mSupplierPhone.setText(" " + mSupplierPhoneEntry);
                calculatePrice();
            }
        });
    }

    // Insert a new row of data to the database table according to the chosen values
    private void insertData() {
        TreasureDbHelper dbHelper = new TreasureDbHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(mColumnName, mProductEntry);
        contentValues.put(mColumnQuantity, mQuantityEntry);
        contentValues.put(mColumnSupplierName, mSupplierNameEntry);
        contentValues.put(mColumnSupplierPhone, mSupplierPhoneEntry);
        contentValues.put(mColumnPrice, mPriceEntry);

        sqLiteDatabase.insert(TreasureEntry.TABLE_NAME, null, contentValues);
    }

    // Request data about all columns of the database table
    private void queryData() {
        TreasureDbHelper dbHelper = new TreasureDbHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        // The names of columns to be requested
        String[] projection = {
                mColumnName,
                mColumnQuantity,
                mColumnSupplierName,
                mColumnSupplierPhone,
                mColumnPrice};

        Cursor cursor = sqLiteDatabase.query(
                TreasureEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        int nameColumnIndex = cursor.getColumnIndex(mColumnName);
        int quantityColumnIndex = cursor.getColumnIndex(mColumnQuantity);
        int supplierNameColumnIndex = cursor.getColumnIndex(mColumnSupplierName);
        int supplierPhoneColumnIndex = cursor.getColumnIndex(mColumnSupplierPhone);
        int priceColumnIndex = cursor.getColumnIndex(mColumnPrice);

        // Extract the appropriate data for each row
        while (cursor.moveToNext()) {
            String currentName = cursor.getString(nameColumnIndex);
            int currentQuantity = cursor.getInt(quantityColumnIndex);
            String currentSupplierName = cursor.getString(supplierNameColumnIndex);
            String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);
            int currentPrice = cursor.getInt(priceColumnIndex);

            // Display the data for the current product
            if (currentQuantity == 1) {
                mData.append(currentQuantity + " piece of " +
                        currentName + " for " +
                        currentPrice + " Coins.\nSupplier: " +
                        currentSupplierName + "\nPhone Number: " +
                        currentSupplierPhone + "\n\n");
            } else {
                mData.append(currentQuantity + " pieces of " +
                        currentName + " for " +
                        currentPrice + " Coins.\nSupplier: " +
                        currentSupplierName + "\nPhone Number: " +
                        currentSupplierPhone + "\n\n");
            }
        }
        cursor.close();
    }

    private void showToast() {
        Toast.makeText(this, R.string.minimum_quantity, Toast.LENGTH_SHORT).show();
    }
}