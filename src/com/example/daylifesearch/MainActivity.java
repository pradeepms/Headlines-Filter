package com.example.daylifesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener {
	

	EditText search;
	Button go;
	ListView resultList;
	JSONArray jarry;
	JSONObject jobj;
	ProgressDialog dialog;
	ArrayList<Articles> articleList;
	CustomeAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		search = (EditText) findViewById(R.id.search);
		go = (Button) findViewById(R.id.go);
		resultList = (ListView) findViewById(R.id.searchResults);
		
		go.setOnClickListener(this);
		resultList.setOnItemClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String query = search.getText().toString();
		/*if(query==""){
			Toast.makeText(getApplicationContext(), "No input", Toast.LENGTH_SHORT);
			return;
		}*/
		
		dialog = ProgressDialog.show(this, "", "Please wait", true);
		
		SignatureClass obj = new SignatureClass();
		String signature = obj.signatureCall(query);
		
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query=query.replaceAll("\\+", "%20");
		Log.d("Qurery Value" ,query);
		
		System.out.println("Within MainActivty" + signature);
	
		new readJSONTask()
				.execute("http://freeapi.daylife.com/jsonrest/publicapi/4.10/search_getRelatedArticles?query="
						+ query
						+ "&limit=20&accesskey=55b84e19f67f128dc4a0cd74cc413471&signature="
						+ signature);
		
	}

	public class readJSONTask extends AsyncTask<String, Void, String> {

		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			return readJSON(params[0]);
		}

		protected void onPostExecute(String result) {
			articleList = new ArrayList<Articles>();
		
			try {
				JSONObject root = new JSONObject(result);
				JSONObject response = root.getJSONObject("response");

				String attributeId = response.getString("message");
				System.out.println(attributeId);

				JSONObject payload = response.getJSONObject("payload");
				JSONArray article = payload.getJSONArray("article");
				
				

				for (int i = 0; i < article.length(); i++) {
					JSONObject c = article.getJSONObject(i);
					String headline = c.getString("headline");
					String url = c.getString("url");
					System.out.println(url);
					System.out.println(headline);
					//strings.add(c.getString("headline").toString());
					
					
					Articles articles = new Articles();
					
					articles.setHeadline(c.getString("headline").toString());
					articles.setURL(c.getString("url").toString());
					articleList.add(articles);
					
				}
				adapter = new CustomeAdapter(MainActivity.this, R.layout.row_item, articleList);
				
				resultList.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				dialog.dismiss();
				/*for(int i=0;i<articleList.size();i++){ 
				strings.add(articleList.get(i).getHeadline());
				
				}*/
				/*
				arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
						android.R.layout.simple_list_item_1, strings);

				resultList.setAdapter(arrayAdapter);
				adapter.notifyDataSetChanged();
				adapter.notifyDataSetInvalidated();*/				
				
				
			

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public String readJSON(String URL) {
			// TODO Auto-generated method stub
			StringBuilder sb = new StringBuilder();
			HttpGet httpGet = new HttpGet(URL);
			HttpClient client = new DefaultHttpClient();

			try {
				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;

					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}
				} else {
					Log.e("JSON", "Couldn't find JSON file");
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return sb.toString();
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		String url = articleList.get(position).getURL();
        Intent i = new Intent(MainActivity.this,   WebViewClass.class);
        i.putExtra("url", url);
        startActivity(i);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		
		super.onPause();
	}
}