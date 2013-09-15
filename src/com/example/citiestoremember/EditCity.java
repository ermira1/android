package com.example.citiestoremember;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditCity extends Activity{
	
	EditText cityName;
	EditText countryName;
	EditText populationNumber;
	EditText traditions;
	EditText impressions;
	EditText urlImage;
	String url_image;

	DBTools dbTools = new DBTools(this);
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_city);
	
		cityName = (EditText) findViewById(R.id.cityName);
		countryName = (EditText) findViewById(R.id.countryName);
		populationNumber = (EditText) findViewById(R.id.populationNumber);
		traditions = (EditText) findViewById(R.id.traditions);
		impressions = (EditText) findViewById(R.id.impressions);
		urlImage = (EditText) findViewById(R.id.urlImage);

		Intent theIntent = getIntent();
		
		String cityId = theIntent.getStringExtra("cityId");
		
		
		HashMap<String, String> cityList = dbTools.getCityInfo(cityId);
		
		if(cityList.size() != 0){
			
			cityName.setText(cityList.get("cityName"));
			countryName.setText(cityList.get("countryName"));
			populationNumber.setText(cityList.get("populationNumber"));
			traditions.setText(cityList.get("traditions"));
			impressions.setText(cityList.get("impressions"));
			urlImage.setText(cityList.get("urlImage"));

		}
		url_image= urlImage.getText().toString();
	}
	
	
	
	public void removeCity(View view){
		
		Intent theIntent = getIntent();
		
		String cityId = theIntent.getStringExtra("cityId");
		
		dbTools.deleteCity(cityId);
		
		this.callMainActivity(view);
		
	}
	
	public void editCity(View view){
		
		HashMap<String, String> queryValuesMap = new HashMap<String, String>();
		
		cityName = (EditText) findViewById(R.id.cityName);
		countryName = (EditText) findViewById(R.id.countryName);
		populationNumber = (EditText) findViewById(R.id.populationNumber);
		traditions = (EditText) findViewById(R.id.traditions);
		impressions = (EditText) findViewById(R.id.impressions);
		urlImage = (EditText) findViewById(R.id.urlImage);

		Intent theIntent = getIntent();
		
		String cityId = theIntent.getStringExtra("cityId");
		
		queryValuesMap.put("cityId", cityId);
		queryValuesMap.put("cityName", cityName.getText().toString());
		queryValuesMap.put("countryName", countryName.getText().toString());
		queryValuesMap.put("populationNumber", populationNumber.getText().toString());
		queryValuesMap.put("traditions", traditions.getText().toString());
		queryValuesMap.put("impressions", impressions.getText().toString());
		queryValuesMap.put("urlImage", urlImage.getText().toString());

		dbTools.updateCity(queryValuesMap);
		
		this.callMainActivity(view);
		
	}
	
	public void displayCity(View view){
		
		Intent theIntent = new Intent(getApplicationContext(), DisplayImage.class);
		theIntent.putExtra("urlImage", url_image); 
		
		startActivity(theIntent);
		
	}
	public void callMainActivity(View view){
		  
		  Intent objIntent = new Intent(getApplication(), MainActivity.class);
		  finish();
		  startActivity(objIntent);
		  
		 }
	@Override
	  public void onBackPressed() {
	      
	      
		Intent objIntent = new Intent(getApplication(), MainActivity.class);
		finish();
		  startActivity(objIntent);
		  

	      }
	 
	  

	
}