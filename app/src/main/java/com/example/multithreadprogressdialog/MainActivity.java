package com.example.multithreadprogressdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    private void initViews(){
        btnDownload=findViewById(R.id.btnDownload);
    }
    private void initListeners(){
        btnDownload.setOnClickListener(new BtnDownloadListeners());
    }

    private  class BtnDownloadListeners implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            new DownloadThread().execute((String) null);
        }
    }

    class DownloadThread extends AsyncTask<String,Integer,Float>{
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("HAMMER");
            progressDialog.setMessage("Downloading..");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }
        @Override
        protected Float doInBackground(String... strings) {
            for(int i=1;i<=100;i++){
                progressDialog.setProgress(i);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Float aFloat) {
            super.onPostExecute(aFloat);
            progressDialog.dismiss();
        }
    }


}