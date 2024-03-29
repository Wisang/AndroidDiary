package com.lge.scc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExerciseData {

	static final int VERSION = 1;
	static final String DATABASE = "exercise.db";
	static final String TABLE = "exercise";
	
	static final String C_DATE = "date";
	static final String C_EXERCISE = "exer";
	static final String C_SET = "sets";
	static final String C_REPEAT = "repeat";
	static final String C_WEIGHT = "weight";
			
	static final String GET_ALL_ORDER_BY = C_DATE + " DESC";
	
	class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context) {
			super(context, DATABASE, null, VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table " + TABLE + " (" + C_DATE + " text, " + C_EXERCISE + " int, "
					+ C_SET + " int, " + C_REPEAT + " int, " + C_WEIGHT + " int)");			
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table " + TABLE);
			this.onCreate(db);
		}
	}
	
	private final DbHelper dbHelper;
	
	public ExerciseData(Context context) {
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

	public Cursor getExercises() {
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		
		return db.query(TABLE, null, null, null, null, null, GET_ALL_ORDER_BY);
	}
}
