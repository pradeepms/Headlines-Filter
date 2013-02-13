package com.example.daylifesearch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewClass extends Activity {
	WebView webView;
	String URL;
	ProgressDialog dialog;
	ProgressBar pd = null;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view_layout);
		webView = (WebView) findViewById(R.id.webView);
		
		pd = (ProgressBar) findViewById(R.id.progress_bar);

		URL = getIntent().getStringExtra("url");
		Log.d("Inside WebView", URL);
		WebSettings websettings = webView.getSettings();
		websettings.setBuiltInZoomControls(true);
		websettings.setJavaScriptEnabled(true);
		webView.getSettings().setSupportMultipleWindows(true);
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

		webView.setWebViewClient(new CallBack());
		webView.loadUrl(URL);

		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				if (progress < 100 && pd.getVisibility() == ProgressBar.GONE) {
					pd.setVisibility(ProgressBar.VISIBLE);
				}
				pd.setProgress(progress);
				if (progress == 100) {
					pd.setVisibility(ProgressBar.GONE);
				}
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		webView.stopLoading();
	}

	private class CallBack extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			return false;
		}

	}

}
