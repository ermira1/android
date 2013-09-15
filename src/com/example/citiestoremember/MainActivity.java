package com.example.citiestoremember;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.citiestoremember.DBTools;
import com.example.citiestoremember.NewCity;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;


public class MainActivity extends ListActivity  {

	
	Intent intent;
	TextView cityId;

	DBTools dbTools = new DBTools(this);

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<HashMap<String, String>> cityList =  dbTools.getAllCities();

		if(cityList.size()!=0) {
			
					
			ListView listView = getListView();
			listView.setOnItemClickListener(new OnItemClickListener() {
				
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					
					cityId = (TextView) view.findViewById(R.id.cityId);
					
					String cityIdValue = cityId.getText().toString();	
					
					Intent  theIndent = new Intent(getApplication(), EditCity.class);
					
					theIndent.putExtra("cityId", cityIdValue); 
					
					finish();
					
					startActivity(theIndent); 
				}
			}); 
			
			ListAdapter adapter = new SimpleAdapter( MainActivity.this,cityList, R.layout.city_entry, new String[] { "cityId", "cityName"}, new int[] {R.id.cityId , R.id.cityName});
			setListAdapter(adapter);
		}
	}
	
	
	
	public void showAddCity(View view) {
		Intent theIntent = new Intent(getApplicationContext(), NewCity.class);
		finish();
		startActivity(theIntent);
	}
	
	public void showMap(View view)
	{Intent theIntent = new Intent(getApplicationContext(), Google.class);
	startActivity(theIntent);
		
	}
	
	public void showVenice (View view)
	{
		Intent intent = new Intent (getApplicationContext(), Venice.class);
		
		startActivity(intent);
	}
	
	@Override
	  public void onBackPressed() {
	      new AlertDialog.Builder(this)
	          .setIcon(android.R.drawable.ic_dialog_alert)
	          .setTitle("Closing Application")
	          .setMessage("Are you sure you want to close this application?")
	          .setPositiveButton("Yes", new DialogInterface.OnClickListener()
	      {
	          @Override
	          public void onClick(DialogInterface dialog, int which) {
	              finish();    
	          }

	      })
	      .setNegativeButton("No", null)
	      .show();
	  }
	 
}
