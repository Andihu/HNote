package com.hdemo.hnote.data;


import android.provider.BaseColumns;

public class NoteTable {
    public static final String TABLE_NAME = "note";
    public NoteTable() {
    }
    public static final class Columns implements BaseColumns {
        public static final String ID = "id";
        public static final String TIME = "time";
        public static final String CONTENT = "content";
        public static final String  END_TIME= "end_time";
        public static final String SUBJECT = "subject";
        public static final String FOLDER_ID = "folder_id";
        public static final String FAVORITE = "favorite";

        private Columns() {
        }
    }
}
