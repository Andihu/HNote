package com.hdemo.hnote.data;


import android.provider.BaseColumns;

public class FolderTable {
    public static final String TABLE_NAME = "folder";
    public FolderTable() {
    }
    public static final class Columns implements BaseColumns {
        public static final String ID = "id";
        public static final String FOLDER_NAME = "folder_name";

        private Columns() {
        }
    }
}
