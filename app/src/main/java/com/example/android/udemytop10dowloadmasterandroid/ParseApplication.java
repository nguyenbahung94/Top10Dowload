package com.example.android.udemytop10dowloadmasterandroid;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by android on 2/22/2016.
 */
public class ParseApplication {
    private String xmlData;
    private ArrayList<Application> applications;

    public ParseApplication(String data) {
        this.xmlData = data;
        applications=new ArrayList<Application>();
    }

    public ArrayList<Application> getApplications() {
        return applications;
    }
    public boolean process(){
        boolean status=true;
        Application currentrecord;
        boolean isEntry=false;
        String textValue="";
        try {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp=factory.newPullParser();
            xpp.setInput(new StringReader(this.xmlData));
            int eventType=xpp.getEventType();
            while (eventType!=XmlPullParser.END_DOCUMENT){
                String tagName=xpp.getName();
                switch (eventType){
                    case  XmlPullParser.START_TAG:
                        Log.d("parseApplication","starting tag for"+tagName);
                        if (tagName.equals("entry")){
                            isEntry=true;
                            currentrecord=new Application();
                            break;
                        }
                    case XmlPullParser.END_TAG:
                        Log.d("parseApplication","Ending tag for"+tagName);
                        break;
                    default:
                }
                eventType=xpp.next();
            }
        }catch (Exception e){
            status=false;
            e.printStackTrace();
        }
        return  true;

    }
}
