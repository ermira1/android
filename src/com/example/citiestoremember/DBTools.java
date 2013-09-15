package com.example.citiestoremember;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTools extends SQLiteOpenHelper {
	
	public DBTools(Context applicationContext){
		
		super(applicationContext, "citybook.db", null, 2);
		
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		
		String query = "CREATE TABLE cities ( cityId INTEGER PRIMARY KEY, cityName TEXT, " + "countryName TEXT, populationNumber TEXT, traditions TEXT, impressions TEXT, urlImage TEXT)";
		
		database.execSQL(query);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		
		String query = "DROP TABLE IF EXISTS cities";
		
		database.execSQL(query);
		onCreate(database);
		
	}
	
	public void insertCity(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("cityName", queryValues.get("cityName"));
		values.put("countryName", queryValues.get("countryName"));
		values.put("populationNumber", queryValues.get("populationNumber"));
		values.put("traditions", queryValues.get("traditions"));
		values.put("impressions", queryValues.get("impressions"));
		values.put("urlImage", queryValues.get("urlImage"));
		
		database.insert("cities", null, values);
		
		database.close();
		
	}
	
	public int updateCity(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("cityName", queryValues.get("cityName"));
		values.put("countryName", queryValues.get("countryName"));
		values.put("populationNumber", queryValues.get("populationNumber"));
		values.put("traditions", queryValues.get("traditions"));
		values.put("impressions", queryValues.get("impressions"));
		values.put("urlImage", queryValues.get("urlImage"));
		
		return database.update("cities", values, 
				"cityId" + " = ?", new String[] {queryValues.get("cityId") });
		
	}
	
	
	public void deleteCity(String id){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		String deleteQuery = "DELETE FROM cities WHERE cityId='" + id + "'";
		
		database.execSQL(deleteQuery);
		
	}

	
	public ArrayList<HashMap<String, String>> getAllCities(){
		
		ArrayList<HashMap<String, String>> cityArrayList = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT * FROM cities ORDER BY countryName";
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			
			do{
				
				HashMap<String, String> cityMap = new HashMap<String, String>();
				
				cityMap.put("cityId", cursor.getString(0));
				cityMap.put("cityName", cursor.getString(1));
				cityMap.put("countryName", cursor.getString(2));
				cityMap.put("populationNumber", cursor.getString(3));
				cityMap.put("traditions", cursor.getString(4));
				cityMap.put("impressions", cursor.getString(5));
				cityMap.put("urlImage", cursor.getString(6));

				cityArrayList.add(cityMap);
				
			} while(cursor.moveToNext());
			
		}
		
		return cityArrayList;
		
	}
	
	public HashMap<String, String> getCityInfo(String id){
		
		HashMap<String, String> cityMap = new HashMap<String, String>();
		
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM cities WHERE cityId='" + id + "'";
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			
			do{
				
				cityMap.put("cityId", cursor.getString(0));
				cityMap.put("cityName", cursor.getString(1));
				cityMap.put("countryName", cursor.getString(2));
				cityMap.put("populationNumber", cursor.getString(3));
				cityMap.put("traditions", cursor.getString(4));
				cityMap.put("impressions", cursor.getString(5));
				cityMap.put("urlImage", cursor.getString(6));

				
			} while(cursor.moveToNext());
			
		}
		
		return cityMap;
		
	}
	
}






