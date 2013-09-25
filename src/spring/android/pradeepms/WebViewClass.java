package spring.android.pradeepms;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WebViewClass extends SherlockActivity {
	WebView webView;
	String URL;
	ProgressDialog dialog;
	ProgressBar pd = null;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view_layout);
		Typeface myTypeface = Typeface.createFromAsset(getAssets(),
				"Roboto-Regular.ttf");

		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(R.layout.ab_title);
		TextView headline = (TextView) findViewById(R.id.headline);
		headline.setTypeface(myTypeface);

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
