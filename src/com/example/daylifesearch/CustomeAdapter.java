package com.example.daylifesearch;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomeAdapter extends ArrayAdapter<Articles> {
	Context context;
	ArrayList<Articles> articles;
	int textViewResourceId;
	public CustomeAdapter(Context context, int textViewResourceId,
			List<Articles> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		
		this.articles = (ArrayList<Articles>) objects;
		this.context=context;
		this.textViewResourceId=textViewResourceId;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View row = convertView;
        WeatherHolder holder = null;
		if (row==null) {
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(textViewResourceId, parent, false);
//            row.setMinimumHeight(70);
            holder = new WeatherHolder();
            
            holder.txtTitle = (TextView)row.findViewById(R.id.rowText);
            row.setTag(holder);
		} else
        {
            holder = (WeatherHolder)row.getTag();
        }
        
        holder.txtTitle.setText(articles.get(position).headLine);

		return row;
	}
	static class WeatherHolder{
		TextView txtTitle;
	}

}
