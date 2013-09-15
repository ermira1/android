package com.example.citiestoremember;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;


public class NewCity extends Activity{

	// The EditText objects

	EditText cityName;
	EditText countryName;
	EditText populationNumber;
	EditText traditions;
	EditText impressions;
	EditText urlImage;

	DBTools dbTools = new DBTools(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_new_city);

		cityName = (EditText) findViewById(R.id.cityName);
		countryName = (EditText) findViewById(R.id.countryName);
		populationNumber = (EditText) findViewById(R.id.populationNumber);
		traditions = (EditText) findViewById(R.id.traditions);
		impressions = (EditText) findViewById(R.id.impressions);
		urlImage = (EditText) findViewById(R.id.urlImage);
		
	}
	public void addNewCity(View view) {
		
		HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();

		queryValuesMap.put("cityName", cityName.getText().toString());
		queryValuesMap.put("countryName", countryName.getText().toString());
		queryValuesMap.put("populationNumber", populationNumber.getText().toString());
		queryValuesMap.put("traditions", traditions.getText().toString());
		queryValuesMap.put("impressions", impressions.getText().toString());
		queryValuesMap.put("urlImage", urlImage.getText().toString());

		dbTools.insertCity(queryValuesMap);
		
		this.callMainActivity(view);
		
	}
	

	public void callMainActivity(View view){
		  
		  Intent objIntent = new Intent(getApplication(), MainActivity.class);
		  finish();
		  startActivity(objIntent);
		  
		 }
	
	  public void onBackPressed() {
	      
	      
		Intent objIntent = new Intent(getApplication(), MainActivity.class);
		finish();
		  startActivity(objIntent);
		  

	      }
	 
}
