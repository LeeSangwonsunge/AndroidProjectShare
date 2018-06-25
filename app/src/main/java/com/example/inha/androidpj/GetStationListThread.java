package com.example.inha.androidpj;

import android.os.Handler;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;

/**
 * 미세먼지 측정소 가져오는 스레드
 * @author Ans
 *
 */

class GetStationListThread extends Thread {	//station thread
    static public boolean active=false; //var of thread active
    int data=0;			//array count
    public boolean isreceiver;
    String sTotalCount;	//info count
    String[] sStationName;	//array of station name
    boolean bStationName,bTotalCount;	//bool var
    boolean tResponse;	//bool var for response
    String sidoName;
    Handler handler;
    String Servicekey="ServiceKey=lJb07fNtjJUSbinL919L84uRAxnwp3YEH4Ot%2B4NNzvs4BEC4lPRac4zSuTTDD1gzU1KZPMnBAEH8Q2zrLVui7A%3D%3D";
    String getInfo="http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/";
    String getStationFindDust="getMsrstnList?";

    String addr="addr=";
    String infoCnt="numOfRows=50";

    public GetStationListThread(boolean receiver, String sido){

        Log.w("시도 이름", sido);
        handler=new Handler();
        isreceiver=receiver;
        sidoName=sido;


        bStationName=false;	//init
    }
    public void run(){

        if(active){
            try{
                sStationName=new String[100];	//station array
                data=0;
                XmlPullParserFactory factory= XmlPullParserFactory.newInstance();	//create pull parser factory instance
                factory.setNamespaceAware(true);									//factory set
                XmlPullParser xpp=factory.newPullParser();							//create pull parser
                String dustUrl=getInfo+getStationFindDust+addr+sidoName+"&"+infoCnt+"&"+Servicekey;
                Log.w("url", dustUrl);
                URL url=new URL(dustUrl);		//URL
                InputStream is=url.openStream();
                xpp.setInput(is,"UTF-8");			//utf-8 setting
                int eventType=xpp.getEventType();

                while(eventType!= XmlPullParser.END_DOCUMENT){	//not end tag
                    switch(eventType){
                        case XmlPullParser.START_TAG:	//'<' tag parsing

                            if(xpp.getName().equals("stationName")){	//station name
                                bStationName=true;

                            }if(xpp.getName().equals("totalCount")){	//station count
                            bTotalCount=true;

                        }

                            break;

                        case XmlPullParser.TEXT:	//paring value

                            if(bStationName){				//bool var of station name
                                sStationName[data]=xpp.getText();
                                bStationName=false;
                            }if(bTotalCount){               //bool var of station count
                            sTotalCount=xpp.getText();
                            bTotalCount=false;
                        }
                            break;

                        case XmlPullParser.END_TAG:		//'</' tag parsing

                            if(xpp.getName().equals("response")){	//end of parsing
                                tResponse=true;
                                view_text();
                            }if(xpp.getName().equals("dmY")){	//1 array finished
                            data++;
                        }
                            break;
                    }
                    eventType=xpp.next();	//next parsing
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }



    }

    /**
     * parsing response
     * @author Ans
     */
    private void view_text(){

        handler.post(new Runnable() {	//기본 ?�들?�니�?handler.post?�면??
            @Override
            public void run() {

                active=false;
                if(tResponse){		//response
                    tResponse=false;
                    Log.e("station cnt",""+data);

                    DustActivity.StationListThreadResponse(data, sStationName);
                    data=0;		//
                }


            }
        });
    }
}
