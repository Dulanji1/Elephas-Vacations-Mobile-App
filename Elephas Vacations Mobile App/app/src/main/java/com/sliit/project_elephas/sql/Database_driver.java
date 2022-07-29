package com.sliit.project_elephas.sql;

import android.provider.BaseColumns;

public class Database_driver {

    public Database_driver() { }

    public static class driverEntry implements BaseColumns {
        public static final String TABLE_DRIVER = "driver_guide";
        public static final String COL_1 = "passport";
        public static final String COL_2 = "driver_cost";
        public static final String COL_3 = "guide_cost";
        public static final String COL_4 = "total_usd";
    }


}
