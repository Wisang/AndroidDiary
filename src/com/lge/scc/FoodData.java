package com.lge.scc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodData {

	static final int VERSION = 1;
	static final String DATABASE = "diary.db";
	static final String TABLE = "food";
	
	static final String C_DATE = "date";
	static final String C_WHEN = "time";
	static final String C_NAME = "name";
	
	static final String GET_ALL_ORDER_BY = C_DATE + " DESC";
		
	class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context) {
			super(context, DATABASE, null, VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table " + TABLE + " (" + C_DATE + " text, " + C_WHEN + " int, "
					+ C_NAME + " text)");			
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table " + TABLE);
			this.onCreate(db);
		}
	}
	
	private final DbHelper dbHelper;
	
	public FoodData(Context context) {
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
