package com.example.android.apothecarystreasures;


import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.apothecarystreasures.data.PotionContract.PotionEntry;

public class DetailsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    // Layout Views
    private ImageView mImage;
    private Spinner mPotion;
    private TextView mQuantity;
    private Spinner mSupplierName;
    private TextView mSupplierPhone;
    private TextView mPrice;

    // Image options
    private int mAnonymitysBreathImg = PotionEntry.IMG_ANONYMITYS_BREATH;
    private int maAssassinsChoiceImg = PotionEntry.IMG_ASSASSINS_CHOICE;
    private int mBeautysDeceitImg = PotionEntry.IMG_BEAUTYS_DECEIT;
    private int mCarousalGiftImg = PotionEntry.IMG_CAROUSAL_GIFT;
    private int mChildsWhisperImg = PotionEntry.IMG_CHILDS_WHISPER;
    private int mElvenRemedyImg = PotionEntry.IMG_ELVEN_REMEDY;
    private int mEyeOfAtlantisImg = PotionEntry.IMG_EYE_OF_ATLANTIS;
    private int mFairysHopeImg = PotionEntry.IMG_FAIRYS_HOPE;
    private int mHydrasHeadImg = PotionEntry.IMG_HYDRAS_HEAD;
    private int mInfernalRootsImg = PotionEntry.IMG_INFERNAL_ROOTS;
    private int mJacksBeanstalkImg = PotionEntry.IMG_JACKS_BEANSTALK;
    private int mRaysOfAStarImg = PotionEntry.IMG_RAYS_OF_A_STAR;
    private int mRegretOfGenesisImg = PotionEntry.IMG_REGRET_OF_GENESIS;
    private int mShaperOfDreamsImg = PotionEntry.IMG_SHAPER_OF_DREAMS;
    private int mShiningTearsImg = PotionEntry.IMG_SHINING_TEARS;
    private int mSilencerOfDoubtsImg = PotionEntry.IMG_SILENCER_OF_DOUBTS;
    private int mTrueLovesMythImg = PotionEntry.IMG_TRUE_LOVES_MYTH;
    private int mVeiledPanaceaImg = PotionEntry.IMG_VEILED_PANACEA;
    private int mWailOfAMermaidImg = PotionEntry.IMG_WAIL_OF_A_MERMAID;
    private int mWallflowersSoulImg = PotionEntry.IMG_WALLFLOWERS_SOUL;

    // Potion options
    private String mAnonymitysBreath = PotionEntry.ANONYMITYS_BREATH;
    private String maAssassinsChoice = PotionEntry.ASSASSINS_CHOICE;
    private String mBeautysDeceit = PotionEntry.BEAUTYS_DECEIT;
    private String mCarousalGift = PotionEntry.CAROUSAL_GIFT;
    private String mChildsWhisper = PotionEntry.CHILDS_WHISPER;
    private String mElvenRemedy = PotionEntry.ELVEN_REMEDY;
    private String mEyeOfAtlantis = PotionEntry.EYE_OF_ATLANTIS;
    private String mFairysHope = PotionEntry.FAIRYS_HOPE;
    private String mHydrasHead = PotionEntry.HYDRAS_HEAD;
    private String mInfernalRoots = PotionEntry.INFERNAL_ROOTS;
    private String mJacksBeanstalk = PotionEntry.JACKS_BEANSTALK;
    private String mRaysOfAStar = PotionEntry.RAYS_OF_A_STAR;
    private String mRegretOfGenesis = PotionEntry.REGRET_OF_GENESIS;
    private String mShaperOfDreams = PotionEntry.SHAPER_OF_DREAMS;
    private String mShiningTears = PotionEntry.SHINING_TEARS;
    private String mSilencerOfDoubts = PotionEntry.SILENCER_OF_DOUBTS;
    private String mTrueLovesMyth = PotionEntry.TRUE_LOVES_MYTH;
    private String mVeiledPanacea = PotionEntry.VEILED_PANACEA;
    private String mWailOfAMermaid = PotionEntry.WAIL_OF_A_MERMAID;
    private String mWallflowersSoul = PotionEntry.WALLFLOWERS_SOUL;

    // Supplier options
    private String mDruidsHut = PotionEntry.DRUIDS_HUT;
    private String mFairyTreehouse = PotionEntry.FAIRY_TREEHOUSE;
    private String mHermitsHideout = PotionEntry.HERMITS_HIDEOUT;
    private String mSunkenShip = PotionEntry.SUNKEN_SHIP;
    private String mWiccasDwelling = PotionEntry.WICCAS_DWELLING;

    // Supplier phone options
    private String mDruidsHutPhone = PotionEntry.DRUIDS_HUT_PHONE;
    private String mFairyTreehousePhone = PotionEntry.FAIRY_TREEHOUSE_PHONE;
    private String mHermitsHideoutPhone = PotionEntry.HERMITS_HIDEOUT_PHONE;
    private String mSunkenShipPhone = PotionEntry.SUNKEN_SHIP_PHONE;
    private String mWiccasDwellingPhone = PotionEntry.WICCAS_DWELLING_PHONE;

    // Database column names
    private String mColumnImage = PotionEntry.COLUMN_IMAGE;
    private String mColumnName = PotionEntry.COLUMN_NAME;
    private String mColumnQuantity = PotionEntry.COLUMN_QUANTITY;
    private String mColumnSupplierName = PotionEntry.COLUMN_SUPPLIER_NAME;
    private String mColumnSupplierPhone = PotionEntry.COLUMN_SUPPLIER_PHONE;
    private String mColumnPrice = PotionEntry.COLUMN_PRICE;

    // Entries for each column
    private int mImageEntry;
    private String mPotionEntry;
    private int mQuantityEntry;
    private String mSupplierNameEntry;
    private String mSupplierPhoneEntry;
    private int mPriceEntry;

    private Uri mPotionUri;
    private String mBestSupplier;
    private int mPotionCost;

    // Check if a potion entry has been modified
    private boolean mPotionWasModified = false;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mPotionWasModified = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Find Views in the layout
        Button mAddition = findViewById(R.id.addition);
        Button mSubtraction = findViewById(R.id.subtraction);
        Button deleteButton = findViewById(R.id.delete_button);
        Button orderButton = findViewById(R.id.order_button);
        mImage = findViewById(R.id.potion_image);
        mPotion = findViewById(R.id.potion);
        mQuantity = findViewById(R.id.quantity);
        mSupplierName = findViewById(R.id.supplier_name);
        mSupplierPhone = findViewById(R.id.supplier_phone);
        mPrice = findViewById(R.id.price);

        mPotion.setOnTouchListener(mTouchListener);
        mSupplierName.setOnTouchListener(mTouchListener);

        // Get the URI of the selected list item from the MainActivity
        Intent intent = getIntent();
        mPotionUri = intent.getData();

        if (mPotionUri == null) {
            setTitle(getString(R.string.add_potion));
            deleteButton.setVisibility(View.GONE);

        } else {
            setTitle(getString(R.string.update_potion));
            getLoaderManager().initLoader(1, null, this);
        }

        // Assign a default value to each entry
        mImageEntry = mAnonymitysBreathImg;
        mPotionEntry = mAnonymitysBreath;
        mQuantityEntry = 0;
        mSupplierNameEntry = mDruidsHut;
        mSupplierPhoneEntry = mDruidsHutPhone;
        mPriceEntry = 0;

        createSpinners();
        handleSpinners();

        mAddition.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                mPotionWasModified = true;

                if (mQuantityEntry < 10) {
                    mQuantityEntry += 1;
                } else {
                    mQuantityEntry = 10;
                    Toast.makeText(DetailsActivity.this,
                            getString(R.string.maximum_quantity), Toast.LENGTH_SHORT).show();
                }
                mQuantity.setText("" + mQuantityEntry);
                calculatePrice();
            }
        });

        mSubtraction.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                mPotionWasModified = true;

                if (mQuantityEntry > 0) {
                    mQuantityEntry -= 1;
                } else {
                    mQuantityEntry = 0;
                    Toast.makeText(DetailsActivity.this,
                            getString(R.string.minimum_quantity), Toast.LENGTH_SHORT).show();
                }
                mQuantity.setText("" + mQuantityEntry);
                calculatePrice();
            }
        });

        // Delete potion entry after asking for confirmation
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show confirmation dialog
                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(DetailsActivity.this);
                deleteDialog.setMessage(getString(R.string.delete_dialog));
                // Delete potion option
                deleteDialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deletePotion();
                    }
                });
                // Don't delete potion option
                deleteDialog.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });
                deleteDialog.create().show();
            }
        });

        // Order potions using the appropriate phone number
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mSupplierPhoneEntry));

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    // Query the data for all the columns on the background
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                PotionEntry._ID,
                PotionEntry.COLUMN_IMAGE,
                PotionEntry.COLUMN_NAME,
                PotionEntry.COLUMN_QUANTITY,
                PotionEntry.COLUMN_SUPPLIER_NAME,
                PotionEntry.COLUMN_SUPPLIER_PHONE,
                PotionEntry.COLUMN_PRICE};

        return new CursorLoader(
                this,
                mPotionUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Check if there are data in the cursor
        if (data == null || data.getCount() < 1) {
            return;
        }

        // Use column indices for the current row to extract the appropriate values
        data.moveToFirst();
        int imageColumnIndex = data.getColumnIndex(PotionEntry.COLUMN_IMAGE);
        int nameColumnIndex = data.getColumnIndex(PotionEntry.COLUMN_NAME);
        int quantityColumnIndex = data.getColumnIndex(PotionEntry.COLUMN_QUANTITY);
        int supplierNameColumnIndex = data.getColumnIndex(PotionEntry.COLUMN_SUPPLIER_NAME);
        int supplierPhoneColumnIndex = data.getColumnIndex(PotionEntry.COLUMN_SUPPLIER_PHONE);
        int priceColumnIndex = data.getColumnIndex(PotionEntry.COLUMN_PRICE);

        int currentImage = data.getInt(imageColumnIndex);
        String currentName = data.getString(nameColumnIndex);
        int currentQuantity = data.getInt(quantityColumnIndex);
        String currentSupplierName = data.getString(supplierNameColumnIndex);
        String currentSupplierPhone = data.getString(supplierPhoneColumnIndex);
        int currentPrice = data.getInt(priceColumnIndex);

        // Update the layout views with the correct data
        mImage.setImageResource(currentImage);
        mQuantity.setText("" + currentQuantity);
        mSupplierPhone.setText(currentSupplierPhone);
        mPrice.setText("" + currentPrice);
        switch (currentName) {
            case PotionEntry.ANONYMITYS_BREATH:
                mPotion.setSelection(0);
                break;
            case PotionEntry.ASSASSINS_CHOICE:
                mPotion.setSelection(1);
                break;
            case PotionEntry.BEAUTYS_DECEIT:
                mPotion.setSelection(2);
                break;
            case PotionEntry.CAROUSAL_GIFT:
                mPotion.setSelection(3);
                break;
            case PotionEntry.CHILDS_WHISPER:
                mPotion.setSelection(4);
                break;
            case PotionEntry.ELVEN_REMEDY:
                mPotion.setSelection(5);
                break;
            case PotionEntry.EYE_OF_ATLANTIS:
                mPotion.setSelection(6);
                break;
            case PotionEntry.FAIRYS_HOPE:
                mPotion.setSelection(7);
                break;
            case PotionEntry.HYDRAS_HEAD:
                mPotion.setSelection(8);
                break;
            case PotionEntry.INFERNAL_ROOTS:
                mPotion.setSelection(9);
                break;
            case PotionEntry.JACKS_BEANSTALK:
                mPotion.setSelection(10);
                break;
            case PotionEntry.RAYS_OF_A_STAR:
                mPotion.setSelection(11);
                break;
            case PotionEntry.REGRET_OF_GENESIS:
                mPotion.setSelection(12);
                break;
            case PotionEntry.SHAPER_OF_DREAMS:
                mPotion.setSelection(13);
                break;
            case PotionEntry.SHINING_TEARS:
                mPotion.setSelection(14);
                break;
            case PotionEntry.SILENCER_OF_DOUBTS:
                mPotion.setSelection(15);
                break;
            case PotionEntry.TRUE_LOVES_MYTH:
                mPotion.setSelection(16);
                break;
            case PotionEntry.VEILED_PANACEA:
                mPotion.setSelection(17);
                break;
            case PotionEntry.WAIL_OF_A_MERMAID:
                mPotion.setSelection(18);
                break;
            case PotionEntry.WALLFLOWERS_SOUL:
                mPotion.setSelection(19);
                break;
        }
        switch (currentSupplierName) {
            case PotionEntry.DRUIDS_HUT:
                mSupplierName.setSelection(0);
                break;
            case PotionEntry.FAIRY_TREEHOUSE:
                mSupplierName.setSelection(1);
                break;
            case PotionEntry.HERMITS_HIDEOUT:
                mSupplierName.setSelection(2);
                break;
            case PotionEntry.SUNKEN_SHIP:
                mSupplierName.setSelection(3);
                break;
            case PotionEntry.WICCAS_DWELLING:
                mSupplierName.setSelection(4);
                break;
        }
    }

    // Display default data
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mImage.setImageResource(0);
        mPotion.setSelection(0);
        mQuantity.setText(String.valueOf(0));
        mSupplierName.setSelection(0);
        mSupplierPhone.setText(mDruidsHutPhone);
        mPrice.setText(String.valueOf(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_potion:
                if (mQuantityEntry > 0) {
                    insertPotion();
                    finish();
                } else {
                    Toast.makeText(this, R.string.zero_potions, Toast.LENGTH_SHORT).show();
                }
                return true;

            case android.R.id.home:
                if (!mPotionWasModified) {
                    NavUtils.navigateUpFromSameTask(this);
                } else {
                    AlertDialog.Builder discardDialog = new AlertDialog.Builder(this);
                    discardDialog.setMessage(getString(R.string.discard_dialog));
                    // Discard changes option
                    discardDialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            NavUtils.navigateUpFromSameTask(DetailsActivity.this);
                        }
                    });
                    // Don't discard changes option
                    discardDialog.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }
                    });
                    discardDialog.create().show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Create an adapter for each Spinner
    private void createSpinners() {
        ArrayAdapter potionAdapter = ArrayAdapter.createFromResource
                (this, R.array.potion_options, android.R.layout.simple_spinner_item);
        ArrayAdapter supplierAdapter = ArrayAdapter.createFromResource
                (this, R.array.supplier_options, android.R.layout.simple_spinner_item);

        potionAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        supplierAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mPotion.setAdapter(potionAdapter);
        mSupplierName.setAdapter(supplierAdapter);
    }

    private void handleSpinners() {

        mPotion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(itemSelected)) {
                    switch (itemSelected) {
                        case PotionEntry.ANONYMITYS_BREATH:
                            mImage.setImageResource(R.drawable.anonymitys_breath);
                            mImageEntry = mAnonymitysBreathImg;
                            mPotionEntry = mAnonymitysBreath;
                            mBestSupplier = mHermitsHideout;
                            mPotionCost = 100;
                            calculatePrice();
                            break;
                        case PotionEntry.ASSASSINS_CHOICE:
                            mImage.setImageResource(maAssassinsChoiceImg);
                            mImageEntry = maAssassinsChoiceImg;
                            mPotionEntry = maAssassinsChoice;
                            mBestSupplier = mHermitsHideout;
                            mPotionCost = 25;
                            calculatePrice();
                            break;
                        case PotionEntry.BEAUTYS_DECEIT:
                            mImage.setImageResource(mBeautysDeceitImg);
                            mImageEntry = mBeautysDeceitImg;
                            mPotionEntry = mBeautysDeceit;
                            mBestSupplier = mSunkenShip;
                            mPotionCost = 50;
                            calculatePrice();
                            break;
                        case PotionEntry.CAROUSAL_GIFT:
                            mImage.setImageResource(mCarousalGiftImg);
                            mImageEntry = mCarousalGiftImg;
                            mPotionEntry = mCarousalGift;
                            mBestSupplier = mFairyTreehouse;
                            mPotionCost = 50;
                            calculatePrice();
                            break;
                        case PotionEntry.CHILDS_WHISPER:
                            mImage.setImageResource(mChildsWhisperImg);
                            mImageEntry = mChildsWhisperImg;
                            mPotionEntry = mChildsWhisper;
                            mBestSupplier = mDruidsHut;
                            mPotionCost = 50;
                            calculatePrice();
                            break;
                        case PotionEntry.ELVEN_REMEDY:
                            mImage.setImageResource(mElvenRemedyImg);
                            mImageEntry = mElvenRemedyImg;
                            mPotionEntry = mElvenRemedy;
                            mBestSupplier = mFairyTreehouse;
                            mPotionCost = 25;
                            calculatePrice();
                            break;
                        case PotionEntry.EYE_OF_ATLANTIS:
                            mImage.setImageResource(mEyeOfAtlantisImg);
                            mImageEntry = mEyeOfAtlantisImg;
                            mPotionEntry = mEyeOfAtlantis;
                            mBestSupplier = mSunkenShip;
                            mPotionCost = 100;
                            calculatePrice();
                            break;
                        case PotionEntry.FAIRYS_HOPE:
                            mImage.setImageResource(mFairysHopeImg);
                            mImageEntry = mFairysHopeImg;
                            mPotionEntry = mFairysHope;
                            mBestSupplier = mFairyTreehouse;
                            mPotionCost = 75;
                            calculatePrice();
                            break;
                        case PotionEntry.HYDRAS_HEAD:
                            mImage.setImageResource(mHydrasHeadImg);
                            mImageEntry = mHydrasHeadImg;
                            mPotionEntry = mHydrasHead;
                            mBestSupplier = mWiccasDwelling;
                            mPotionCost = 25;
                            calculatePrice();
                            break;
                        case PotionEntry.INFERNAL_ROOTS:
                            mImage.setImageResource(mInfernalRootsImg);
                            mImageEntry = mInfernalRootsImg;
                            mPotionEntry = mInfernalRoots;
                            mBestSupplier = mWiccasDwelling;
                            mPotionCost = 100;
                            calculatePrice();
                            break;
                        case PotionEntry.JACKS_BEANSTALK:
                            mImage.setImageResource(mJacksBeanstalkImg);
                            mImageEntry = mJacksBeanstalkImg;
                            mPotionEntry = mJacksBeanstalk;
                            mBestSupplier = mFairyTreehouse;
                            mPotionCost = 100;
                            calculatePrice();
                            break;
                        case PotionEntry.RAYS_OF_A_STAR:
                            mImage.setImageResource(mRaysOfAStarImg);
                            mImageEntry = mRaysOfAStarImg;
                            mPotionEntry = mRaysOfAStar;
                            mBestSupplier = mDruidsHut;
                            mPotionCost = 75;
                            calculatePrice();
                            break;
                        case PotionEntry.REGRET_OF_GENESIS:
                            mImage.setImageResource(mRegretOfGenesisImg);
                            mImageEntry = mRegretOfGenesisImg;
                            mPotionEntry = mRegretOfGenesis;
                            mBestSupplier = mWiccasDwelling;
                            mPotionCost = 75;
                            calculatePrice();
                            break;
                        case PotionEntry.SHAPER_OF_DREAMS:
                            mImage.setImageResource(mShaperOfDreamsImg);
                            mImageEntry = mShaperOfDreamsImg;
                            mPotionEntry = mShaperOfDreams;
                            mBestSupplier = mDruidsHut;
                            mPotionCost = 100;
                            calculatePrice();
                            break;
                        case PotionEntry.SHINING_TEARS:
                            mImage.setImageResource(mShiningTearsImg);
                            mImageEntry = mShiningTearsImg;
                            mPotionEntry = mShiningTears;
                            mBestSupplier = mSunkenShip;
                            mPotionCost = 25;
                            calculatePrice();
                            break;
                        case PotionEntry.SILENCER_OF_DOUBTS:
                            mImage.setImageResource(mSilencerOfDoubtsImg);
                            mImageEntry = mSilencerOfDoubtsImg;
                            mPotionEntry = mSilencerOfDoubts;
                            mBestSupplier = mHermitsHideout;
                            mPotionCost = 75;
                            calculatePrice();
                            break;
                        case PotionEntry.TRUE_LOVES_MYTH:
                            mImage.setImageResource(mTrueLovesMythImg);
                            mImageEntry = mTrueLovesMythImg;
                            mPotionEntry = mTrueLovesMyth;
                            mBestSupplier = mWiccasDwelling;
                            mPotionCost = 50;
                            calculatePrice();
                            break;
                        case PotionEntry.VEILED_PANACEA:
                            mImage.setImageResource(mVeiledPanaceaImg);
                            mImageEntry = mVeiledPanaceaImg;
                            mPotionEntry = mVeiledPanacea;
                            mBestSupplier = mDruidsHut;
                            mPotionCost = 25;
                            calculatePrice();
                            break;
                        case PotionEntry.WAIL_OF_A_MERMAID:
                            mImage.setImageResource(mWailOfAMermaidImg);
                            mImageEntry = mWailOfAMermaidImg;
                            mPotionEntry = mWailOfAMermaid;
                            mBestSupplier = mSunkenShip;
                            mPotionCost = 75;
                            calculatePrice();
                            break;
                        case PotionEntry.WALLFLOWERS_SOUL:
                            mImage.setImageResource(mWallflowersSoulImg);
                            mImageEntry = mWallflowersSoulImg;
                            mPotionEntry = mWallflowersSoul;
                            mBestSupplier = mHermitsHideout;
                            mPotionCost = 50;
                            calculatePrice();
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mImage.setImageResource(R.drawable.anonymitys_breath);
                mPotionEntry = mAnonymitysBreath;
                mBestSupplier = mHermitsHideout;
                mPotionCost = 100;
                calculatePrice();
            }
        });

        mSupplierName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(itemSelected)) {
                    switch (itemSelected) {
                        case PotionEntry.DRUIDS_HUT:
                            mSupplierNameEntry = mDruidsHut;
                            mSupplierPhoneEntry = mDruidsHutPhone;
                            mSupplierPhone.setText(" " + mSupplierPhoneEntry);
                            calculatePrice();
                            break;
                        case PotionEntry.FAIRY_TREEHOUSE:
                            mSupplierNameEntry = mFairyTreehouse;
                            mSupplierPhoneEntry = mFairyTreehousePhone;
                            mSupplierPhone.setText(" " + mSupplierPhoneEntry);
                            calculatePrice();
                            break;
                        case PotionEntry.HERMITS_HIDEOUT:
                            mSupplierNameEntry = mHermitsHideout;
                            mSupplierPhoneEntry = mHermitsHideoutPhone;
                            mSupplierPhone.setText(" " + mSupplierPhoneEntry);
                            calculatePrice();
                            break;
                        case PotionEntry.SUNKEN_SHIP:
                            mSupplierNameEntry = mSunkenShip;
                            mSupplierPhoneEntry = mSunkenShipPhone;
                            mSupplierPhone.setText(" " + mSupplierPhoneEntry);
                            calculatePrice();
                            break;
                        case PotionEntry.WICCAS_DWELLING:
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

    @Override
    public void onBackPressed() {
        if (!mPotionWasModified) {
            super.onBackPressed();
            return;
        } else {
            AlertDialog.Builder discardDialog = new AlertDialog.Builder(this);
            discardDialog.setMessage(getString(R.string.discard_dialog));
            // Discard changes option
            discardDialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            // Don't discard changes option
            discardDialog.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            });
            discardDialog.create().show();
        }
    }

    // Insert a new row of data to the database table according to the chosen values
    private void insertPotion() {
        // Check if the default data were modified
        if (mPotionUri == null && mQuantityEntry == 0 && mPriceEntry == 0 &&
                mImageEntry == mAnonymitysBreathImg && mPotionEntry.equals(mAnonymitysBreath) &&
                mSupplierNameEntry.equals(mDruidsHut) && mSupplierPhoneEntry.equals(mDruidsHutPhone)) {
            return;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(mColumnImage, mImageEntry);
        contentValues.put(mColumnName, mPotionEntry);
        contentValues.put(mColumnQuantity, mQuantityEntry);
        contentValues.put(mColumnSupplierName, mSupplierNameEntry);
        contentValues.put(mColumnSupplierPhone, mSupplierPhoneEntry);
        contentValues.put(mColumnPrice, mPriceEntry);

        // Check if the potion entry is being inserted or updated
        if (mPotionUri == null) {
            // Check if the potion already exists
            if (!hasRepetition(mPotionEntry)) {
                Uri uri = getContentResolver().insert(PotionEntry.CONTENT_URI, contentValues);
                // Try to add a new potion
                if (uri == null) {
                    Toast.makeText(this, getString(R.string.potion_not_added), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.potion_added), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, getString(R.string.already_listed), Toast.LENGTH_SHORT).show();
            }
        } else {
            int updatedRows = getContentResolver()
                    .update(mPotionUri, contentValues, null, null);
            // Try to update an existing potion
            if (updatedRows == 0) {
                Toast.makeText(this, getString(R.string.potion_not_updated), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.potion_updated), Toast.LENGTH_SHORT).show();
            }
        }
    }


    // Called when updating an existing potion
    private void deletePotion() {
        if (mPotionUri != null) {
            int deletedRows = getContentResolver()
                    .delete(mPotionUri, null, null);
            // Try to update an existing potion
            if (deletedRows == 0) {
                Toast.makeText(this, getString(R.string.potion_not_deleted), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.potion_deleted), Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    private void calculatePrice() {
        // Calculate according to Quantity choice
        mQuantityEntry = Integer.valueOf(mQuantity.getText().toString());
        mPriceEntry = mPotionCost * mQuantityEntry;

        // Calculate according to Supplier choice
        if (!mSupplierNameEntry.equals(mBestSupplier)) {
            if (mQuantityEntry > 0) {
                mPriceEntry += 50;
            }
        }
        // Update the price TextView
        mPrice.setText(mPriceEntry + " Coins");
    }

    // Compare the new potion name to the ones in the database to avoid repetition
    private boolean hasRepetition(String newName) {
        boolean isRepetition = false;
        String[] projection = {mColumnName};
        Cursor cursor = getContentResolver().query(PotionEntry.CONTENT_URI, projection,
                null, null, null);

        int nameColumnIndex = cursor.getColumnIndex(mColumnName);

        while (cursor.moveToNext()) {
            String currentName = cursor.getString(nameColumnIndex);
            if (newName.equals(currentName)) {
                isRepetition = true;
            }
        }
        cursor.close();

        return isRepetition;
    }
}