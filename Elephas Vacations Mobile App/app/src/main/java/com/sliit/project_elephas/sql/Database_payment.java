package com.sliit.project_elephas.sql;

import android.provider.BaseColumns;

public final class Database_payment {

    public Database_payment() {}

    public static class paymentEntry implements BaseColumns{
        public static final String TABLE_NAME = "payment";
        public static final String COL_0 = "user_id";
        public static final String COL_1 = "user_email";
        public static final String COL_2 = "name";
        public static final String COL_3 = "address";
        public static final String COL_4 = "nic";
        public static final String COL_5 = "card";
        public static final String COL_6 = "status";
        public static final String COL_7 = "amount";
    }
}
