package org.sujinde.beforenood;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseUtil extends SQLiteOpenHelper {

	final String CREATE_PERSON="create table person ("
			+"id integer primary key autoincrement,"
			+"name text,"
			+"sex text,"
			+"age int )";
	
	Context mContext;
	
	public MyDatabaseUtil(Context context, String name, CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		mContext=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_PERSON);
		Log.d("fuck", "create table person succeed");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
db.execSQL("drop table if exists sujinde");
	}

}
