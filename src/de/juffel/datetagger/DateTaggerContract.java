package de.juffel.datetagger;

import android.provider.BaseColumns;

public final class DateTaggerContract {
	
	public static abstract class TagEntry implements BaseColumns {
		public static final String TABLE_NAME = "tag";
		public static final String COLUMN_NAME_ID = "id";
		public static final String COLUMN_NAME_CONTENT = "content";
		public static final String COLUMN_NAME_DATE = "date";
	}
	
	public static abstract class DaterangeEntry implements BaseColumns {
		public static final String TABLE_NAME = "datetime_range";
		public static final String COLUMN_NAME_ID = "id";
		public static final String COLUMN_NAME_FROM = "from";
		public static final String COLUMN_NAME_TILL = "till";
	}
	public static abstract class JoinTagRangeEntry implements BaseColumns {
		public static final String TABLE_NAME = "tag_daterange";
		public static final String COLUMN_NAME_TAG_ID = "tag_id";
		public static final String COLUMN_NAME_DATERANGE_ID = "daterange_id";
	}

    public static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + TagEntry.TABLE_NAME + " (" +
        TagEntry.COLUMN_NAME_ID + " TEXT PRIMARY_KEY, " +
        TagEntry.COLUMN_NAME_CONTENT + " TEXT, " +
        TagEntry.COLUMN_NAME_DATE + " TEXT);" +
        "CREATE TABLE " + DaterangeEntry.TABLE_NAME + " (" +
        DaterangeEntry.COLUMN_NAME_ID + " TEXT PRIMARY_KEY, " +
        DaterangeEntry.COLUMN_NAME_FROM + " TEXT, " +
        DaterangeEntry.COLUMN_NAME_TILL + " TEXT);" +
        "CREATE TABLE " + JoinTagRangeEntry.TABLE_NAME + " (" +
        JoinTagRangeEntry.COLUMN_NAME_TAG_ID + " TEXT, " +
        JoinTagRangeEntry.COLUMN_NAME_DATERANGE_ID + " TEXT);";

    public static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + TagEntry.TABLE_NAME + "\n" +
        "DROP TABLE IF EXISTS " + DaterangeEntry.TABLE_NAME + "\n" +
        "DROP TABLE IF EXISTS " + JoinTagRangeEntry.TABLE_NAME;
}
