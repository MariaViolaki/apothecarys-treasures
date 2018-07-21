package com.example.android.apothecarystreasures.data;

import android.provider.BaseColumns;

public final class TreasureContract {

    private TreasureContract() {
    }

    // Defines constant variables for each treasure of the database
    public static final class TreasureEntry implements BaseColumns {

        // Name of the database table
        public static final String TABLE_NAME = "treasures";

        // Constants for each column
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";

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