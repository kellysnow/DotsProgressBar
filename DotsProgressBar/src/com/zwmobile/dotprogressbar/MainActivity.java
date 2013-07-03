package com.zwmobile.dotprogressbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.zwmobile.dotsprogressbar.R;

public class MainActivity extends Activity {
	private TextView textView;
	private DotsProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initView() {
		Button button = (Button) findViewById(R.id.btn_start);
		button.setOnClickListener(mClickListener);
		progressBar = (DotsProgressBar) findViewById(R.id.dotsProgressBar);
		progressBar.setDotsCount(4);
		textView = (TextView) findViewById(R.id.textView);
		textView.setVisibility(View.INVISIBLE);
		DownloadTask task = new DownloadTask();
		task.execute();
	}

	private OnClickListener mClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			DownloadTask task = new DownloadTask();
			task.execute();

		}

	};

	class DownloadTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			progressBar.setVisibility(View.VISIBLE);
			textView.setVisibility(View.INVISIBLE);
			progressBar.start();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {

				e.printStackTrace();
				return false;
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				progressBar.stop();
				progressBar.setVisibility(View.INVISIBLE);
				textView.setVisibility(View.VISIBLE);
			}
		}

	}
}
