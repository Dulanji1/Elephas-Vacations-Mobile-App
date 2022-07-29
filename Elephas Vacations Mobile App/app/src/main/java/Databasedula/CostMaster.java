package com.example.sightseeen.Database;

import android.provider.BaseColumns;

public final class CostMaster {

    // Constructor
    private CostMaster() {
    };

    // Inner class that defines the table contents
    public static class Costs implements BaseColumns {
        public static final String TABLE_NAME = "costs";
        public static final String COLUMN_NAME_PackageNo = "packageNo";
        public static final String COLUMN_NAME_PackageName = "packageName";
        public static final String COLUMN_NAME_PackageDiscription = "packageDisription";
        public static final String COLUMN_NAME_PackagePrice = "packagePrice";

        public static String getCOLUMN_NAME_PackageNo() {
            return COLUMN_NAME_PackageNo;
        }

        public static String getCOLUMN_NAME_PackageName() {
            return COLUMN_NAME_PackageName;
        }

        public static String getCOLUMN_NAME_PackageDiscription() {
            return COLUMN_NAME_PackageDiscription;
        }

        public static String getCOLUMN_NAME_PackagePrice() {
            return COLUMN_NAME_PackagePrice;
        }
    }

}

