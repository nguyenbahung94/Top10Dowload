package com.example.android.udemytop10dowloadmasterandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button btnparse;
    private ListView listapp;
    private  String mFileContents;
    private TextView editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnparse=(Button)findViewById(R.id.btn_parse);
       // listapp=(ListView)findViewById(R.id.xmllistview);
        editText=(TextView)findViewById(R.id.edt_hienthi);

        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             // ParseApplication parseApplication=new ParseApplication(mFileContents);
                editText.setText(mFileContents);


            }
        });


        DowloadData dowloadData=new DowloadData();
        dowloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml");
    }
    private  class DowloadData extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            mFileContents=DowloadXMLFile(params[0]);
            if(mFileContents==null){
                Log.i("DowloaData","Error Dowloading");
            }
            return mFileContents;
        }
        private String DowloadXMLFile(String urlPath){
            StringBuilder temBuilder=new StringBuilder();
             try {
                 URL url=new URL(urlPath);
                 HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                 int response=urlConnection.getResponseCode();
                 Log.d("DowloadData","The response code was"+response);
                 InputStream is=urlConnection.getInputStream();
                 InputStreamReader reader=new InputStreamReader(is);
                 int charRead;
                 char[] inputBuffer=new char[500];
                 while (true){
                     charRead=reader.read(inputBuffer);
                     if(charRead<=0){
                         break;
                     }
                     temBuilder.append(String.copyValueOf(inputBuffer,0,charRead));
                 }
                 return  temBuilder.toString();

             }catch (Exception e){
                 Log.d("DowloadData","IO Exception reading data"+e.getMessage());
             }
            return urlPath;
        }
    }
}
