package com.lge.scc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Contents {
	static final int VERSION = 2;
	static final String DATABASE = "diary.db";
	static final String TABLE = "food";
	
	static final String C_DATE = "date";
	static final String C_BREAKFAST = "breakfast";
	static final String C_LUNCH = "lunch";
	static final String C_DINNER = "dinner";
	static final String C_EXTRAMEAL = "extrameal";
	static final String C_SQUAT = "squat";
	static final String C_DEADLIFT = "deadlift";
	static final String C_BENCHPRESS = "benchpress";
	
	static final String GET_ALL_ORDER_BY = C_DATE + " DESC";
		
	class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context) {
			super(context, DATABASE, null, VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table " + TABLE + " (" 
					+ C_DATE + " text, " 
					+ C_BREAKFAST + " text, "
					+ C_LUNCH + " text, "
					+ C_DINNER + " text, "
					+ C_EXTRAMEAL + " text, "
					+ C_SQUAT + " text, "
					+ C_DEADLIFT + " text, "
					+ C_BENCHPRESS + " text)");			
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table " + TABLE);
			this.onCreate(db);
		}
	}
	
	private final DbHelper dbHelper;
	
	public Contents(Context context) {
		this.dbHelper = new DbHelper(context);
	}
	
	public void close() {
		this.dbHelper.close();
	}
	
	public void insert(ContentValues values) {
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		
		db.insertOrThrow(TABLE, null, values);
		db.close();
	}

	public Cursor getMeals() {
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		
		return db.query(TABLE, null, null, null, null, null, GET_ALL_ORDER_BY);
	}
}
