package com.sliit.project_elephas.sql;

import android.provider.BaseColumns;

public final class Database_transport {
    public Database_transport() {}

    public static class transportEntry implements BaseColumns {
        public static final String TABLE_TRANSPORT = "transport_cost";
        public static final String COL_1 = "passport";
        public static final String COL_2 = "gross_Mileage";
        public static final String COL_3 = "extra_Mileage";
        public static final String COL_4 = "total_Mileage";
        public static final String COL_5 = "charge_Km";
        public static final String COL_6 = "total_USD";
    }

}
