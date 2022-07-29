package com.example.sightseeen.Database;

import android.provider.BaseColumns;

public class SightseenMaster {
    // Constructor
    private SightseenMaster() {
    };

    // Inner class that defines the table contents
    public static class Sightseen implements BaseColumns {
        public static final String TABLE_NAME_SightseenTabele = "SightseenTicketPrices";
        public static final String COLUMN_NAME_SightNo = "sightNo";
        public static final String COLUMN_NAME_SightName = "sightName";
        public static final String COLUMN_NAME_Child_Ticket_Price = "childTicPrice";
        public static final String COLUMN_NAME_Adult_Ticket_Price = "adultTicPrice";

        public static String getTableName() {
            return TABLE_NAME_SightseenTabele ;
        }

        public static String getCOLUMN_NAME_SightNo() {
            return COLUMN_NAME_SightNo;
        }

        public static String getCOLUMN_NAME_SightName() {
            return COLUMN_NAME_SightName;
        }

        public static String getCOLUMN_NAME_Child_Ticket_Price() {
            return COLUMN_NAME_Child_Ticket_Price;
        }

        public static String getCOLUMN_NAME_Adult_Ticket_Price() {
            return COLUMN_NAME_Adult_Ticket_Price;
        }
    }


}
