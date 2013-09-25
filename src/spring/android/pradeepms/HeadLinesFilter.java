package spring.android.pradeepms;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;


import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

import spring.android.pradeepms.R;
import spring.android.pradeepms.Payload.Article;
import spring.android.pradeepms.signature.SignatureClass;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HeadLinesFilter extends SherlockActivity implements OnClickListener,
		OnItemClickListener {

	protected static String TAG = HeadLinesFilter.class.getSimpleName();
	private EditText search;
	private ImageButton go;
	private ListView list;
	private List<Article> articles;
	String signature;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
		
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getSupportActionBar().setCustomView(R.layout.ab_title);
		TextView headline = (TextView) findViewById(R.id.headline);
		headline.setTypeface(myTypeface);
		
		
		search = (EditText) findViewById(R.id.search);
		go = (ImageButton) findViewById(R.id.go);
		list = (ListView) findViewById(R.id.searchResults);
		
		if(!isNetworkAvailable()){
			Toast.makeText(this, "Enable Data Connection", Toast.LENGTH_LONG)
			.show();
		}else{
			signature = new SignatureClass().signatureCall(" ");
			/*
			 * 
			 * Register in Daylife.com to get accesskey and sharedsecret
			 * 
			 * 
			 * Add your accesskey in the below URL
			 * 
			 */
			new readJSONTask()
			.execute("http://freeapi.daylife.com/jsonrest/publicapi/4.10/search_getRelatedArticles?query="
					
					+ "&limit=20&accesskey=your_access_key_here&signature="
					+ signature);
			
			
		}
		go.setOnClickListener(this);
		list.setOnItemClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String query = search.getText().toString();

		signature = new SignatureClass().signatureCall(query);

		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = query.replaceAll("\\+", "%20");
		Log.d("Qurery Value", query);

		if (!isNetworkAvailable()) {
			Toast.makeText(this, "Enable Data Connection", Toast.LENGTH_LONG)
					.show();
		} else {
			new readJSONTask()
					.execute("http://freeapi.daylife.com/jsonrest/publicapi/4.10/search_getRelatedArticles?query="
							+ query
							+ "&limit=20&accesskey=55b84e19f67f128dc4a0cd74cc413471&signature="
							+ signature);
		}
	}

	public class readJSONTask extends AsyncTask<String, Void, DayLifeJSON> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			dialog = new ProgressDialog(HeadLinesFilter.this);
			dialog.setMessage("Loading..");
			dialog.show();
		}

		@Override
		protected DayLifeJSON doInBackground(String... params) {
			// TODO Auto-generated method stub

			URI url = URI.create(params[0]);

			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(
					new StringHttpMessageConverter());
			restTemplate.getMessageConverters().add(
					new MappingJackson2HttpMessageConverter());

			// Perform the HTTP GET request
			DayLifeJSON responseEntity = restTemplate.getForObject(url,
					DayLifeJSON.class);
			System.out.println("########## "+responseEntity.getResponse().getCode());
			System.out.println(responseEntity.getResponse().getPayload()
					.getArticle().size());

			return responseEntity;
		}

		protected void onPostExecute(DayLifeJSON response) {
			dialog.dismiss();
			refreshResults(response);

		}

	}

	private void refreshResults(DayLifeJSON response) {
		if (response.getResponse().getPayload().getArticle().size() == 0) {
			Toast.makeText(this, "No result", Toast.LENGTH_LONG).show();
			return;
		}

		this.articles = response.getResponse().getPayload().getArticle();
		String[] val = response.getResponse().getPayload().getArticle().get(1).getTimestamp().split(" ");
		String frst = val[0];
		Log.d(TAG+" Time stamp", frst.toString());
		list.setAdapter(new CustomAdapter(this, articles));
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub

		String url = this.articles.get(position).getUrl();
		Intent i = new Intent(HeadLinesFilter.this, WebViewClass.class);
		i.putExtra("url", url);
		startActivity(i);

	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

}