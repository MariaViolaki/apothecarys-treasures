package com.example.android.apothecarystreasures.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.android.apothecarystreasures.R;

public final class PotionContract {

    private PotionContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.example.android.apothecarystreasures";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String POTIONS_PATH = "apothecarystreasures";


    // Defines constant variables for each treasure of the database
    public static final class PotionEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(BASE_CONTENT_URI, POTIONS_PATH);

        // MIME types for a single potion and for the full list
        public static final String SINGLE_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + POTIONS_PATH;
        public static final String DIRECTORY_LISTING_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + POTIONS_PATH;

        // Name of the database table
        public static final String TABLE_NAME = "potions";

        // Constants for each column
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";

        // Constants for images
        public static final int IMG_ANONYMITYS_BREATH = R.drawable.anonymitys_breath;
        public static final int IMG_ASSASSINS_CHOICE = R.drawable.assassins_choice;
        public static final int IMG_BEAUTYS_DECEIT = R.drawable.beautys_deceit;
        public static final int IMG_CAROUSAL_GIFT = R.drawable.carousal_gift;
        public static final int IMG_CHILDS_WHISPER = R.drawable.childs_whisper;
        public static final int IMG_ELVEN_REMEDY = R.drawable.elven_remedy;
        public static final int IMG_EYE_OF_ATLANTIS = R.drawable.eye_of_atlantis;
        public static final int IMG_FAIRYS_HOPE = R.drawable.fairys_hope;
        public static final int IMG_HYDRAS_HEAD = R.drawable.hydras_head;
        public static final int IMG_INFERNAL_ROOTS = R.drawable.infernal_roots;
        public static final int IMG_JACKS_BEANSTALK = R.drawable.jacks_beanstalk;
        public static final int IMG_RAYS_OF_A_STAR = R.drawable.rays_of_a_star;
        public static final int IMG_REGRET_OF_GENESIS = R.drawable.regret_of_genesis;
        public static final int IMG_SHAPER_OF_DREAMS = R.drawable.shaper_of_dreams;
        public static final int IMG_SHINING_TEARS = R.drawable.shining_tears;
        public static final int IMG_SILENCER_OF_DOUBTS = R.drawable.silencer_of_doubts;
        public static final int IMG_TRUE_LOVES_MYTH = R.drawable.true_loves_myth;
        public static final int IMG_VEILED_PANACEA = R.drawable.veiled_panacea;
        public static final int IMG_WAIL_OF_A_MERMAID = R.drawable.wail_of_a_mermaid;
        public static final int IMG_WALLFLOWERS_SOUL = R.drawable.wallflowers_soul;

        // Constants for products
        public static final String ANONYMITYS_BREATH = "Anonymity's Breath";
        public static final String ASSASSINS_CHOICE = "Assassin's Choice";
        public static final String BEAUTYS_DECEIT = "Beauty's Deceit";
        public static final String CAROUSAL_GIFT = "Carousal Gift";
        public static final String CHILDS_WHISPER = "Child's Whisper";
        public static final String ELVEN_REMEDY = "Elven Remedy";
        public static final String EYE_OF_ATLANTIS = "Eye of Atlantis";
        public static final String FAIRYS_HOPE = "Fairy's Hope";
        public static final String HYDRAS_HEAD = "Hydra's Head";
        public static final String INFERNAL_ROOTS = "Infernal Roots";
        public static final String JACKS_BEANSTALK = "Jack's Beanstalk";
        public static final String RAYS_OF_A_STAR = "Rays of a Star";
        public static final String REGRET_OF_GENESIS = "Regret of Genesis";
        public static final String SHAPER_OF_DREAMS = "Shaper of Dreams";
        public static final String SHINING_TEARS = "Shining Tears";
        public static final String SILENCER_OF_DOUBTS = "Silencer of Doubts";
        public static final String TRUE_LOVES_MYTH = "True Love's Myth";
        public static final String VEILED_PANACEA = "Veiled Panacea";
        public static final String WAIL_OF_A_MERMAID = "Wail of a Mermaid";
        public static final String WALLFLOWERS_SOUL = "Wallflower's Soul";

        // Constants for suppliers
        public static final String DRUIDS_HUT = "Druid's Hut";
        public static final String FAIRY_TREEHOUSE = "Fairy Treehouse";
        public static final String HERMITS_HIDEOUT = "Hermit's Hideout";
        public static final String SUNKEN_SHIP = "Sunken Ship";
        public static final String WICCAS_DWELLING = "Wicca's Dwelling";

        // Constants for suppliers' phone numbers
        public static final String DRUIDS_HUT_PHONE = "01000100";
        public static final String FAIRY_TREEHOUSE_PHONE = "01000110";
        public static final String HERMITS_HIDEOUT_PHONE = "01001000";
        public static final String SUNKEN_SHIP_PHONE = "01010011";
        public static final String WICCAS_DWELLING_PHONE = "01010111";
    }
}