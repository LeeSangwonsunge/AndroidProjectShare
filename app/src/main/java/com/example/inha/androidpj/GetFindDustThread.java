package com.example.inha.androidpj;

import android.os.Handler;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;

class GetFindDustThread extends Thread {	//thread of getting finddust
    static public boolean active=false; //var of thread active
    int data=0;			//array count
    public boolean isreceiver;
    String sTotalCount;	//info count
    String[] sDate,sSo2Value,sCoValue,sO3Value,sNo2Value,sPm10Value,sKhaiValue,sKhaiGrade,sSo2Grade,sCoGrade,sO3Grade,sNo2Grade,sPm10Grade;	//array var of grades
    boolean bTotalCount,bDate,bSo2Value,bCoValue,bO3Value,bNo2Value,bPm10Value,bKhaiValue,bKhaiGrade,bSo2Grade,bCoGrade,bO3Grade,bNo2Grade,bPm10Grade;	//bool var of grades
    boolean tResponse;	//var of checking for parsing
    String dongName;
    Handler handler;	//handler
    String Servicekey="ServiceKey=lJb07fNtjJUSbinL919L84uRAxnwp3YEH4Ot%2B4NNzvs4BEC4lPRac4zSuTTDD1gzU1KZPMnBAEH8Q2zrLVui7A%3D%3D";
    String getInfo="http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/";
    String getStationFindDust="getMsrstnAcctoRltmMesureDnsty?";
    String searchDate="dataTerm=daily";
    String station="stationName=";
    String infoCnt="numOfRows=50";

    public GetFindDustThread(boolean receiver,String dong){

        Log.w("dong name", dong);
        handler=new Handler();
        isreceiver=receiver;
        dongName=dong;


        bTotalCount=bDate=bSo2Value=bCoValue=bO3Value=bNo2Value=bPm10Value=bKhaiValue=bKhaiGrade=bSo2Grade=bCoGrade=bO3Grade=bNo2Grade=bPm10Grade=false;	//init var value
    }
    public void run(){

        if(active){
            try{
                sDate=new String[100];	//init date
                sSo2Value=new String[100];	//init
                sCoValue=new String[100];	//init
                sO3Value=new String[100];	//init
                sNo2Value=new String[100];	//init
                sPm10Value=new String[100];	//init
                sKhaiValue=new String[100];	//init
                sKhaiGrade=new String[100]; //init
                sSo2Grade=new String[100];	//init
                sCoGrade=new String[100];	//init
                sO3Grade=new String[100];	//init
                sNo2Grade=new String[100];	//init
                sPm10Grade=new String[100];	//init
                data=0;
                XmlPullParserFactory factory= XmlPullParserFactory.newInstance();	//create pull parser factory instance
                factory.setNamespaceAware(true);									//factory set
                XmlPullParser xpp=factory.newPullParser();							//create pull parser
                String dustUrl=getInfo+getStationFindDust+station+dongName+"&"+infoCnt+"&"+searchDate+"&"+Servicekey;
                Log.w("url check ", dustUrl);
                URL url=new URL(dustUrl);		//URL
                InputStream is=url.openStream();
                xpp.setInput(is,"UTF-8");			//utf-8 setting
                int eventType=xpp.getEventType();

                while(eventType!= XmlPullParser.END_DOCUMENT){	//not end tag
                    switch(eventType){
                        case XmlPullParser.START_TAG:	//  '<' tag parsing

                            if(xpp.getName().equals("dataTime")){	//date
                                bDate=true;

                            } if(xpp.getName().equals("so2Value")){		//SO2 value
                            bSo2Value=true;

                        } if(xpp.getName().equals("coValue")){		//CO value
                            bCoValue=true;

                        } if(xpp.getName().equals("o3Value")){		//O3 value
                            bO3Value=true;

                        } if(xpp.getName().equals("no2Value")){		//NO2 value
                            bNo2Value=true;

                        } if(xpp.getName().equals("pm10Value")){	//Pm10 value
                            bPm10Value=true;

                        } if(xpp.getName().equals("khaiValue")){		//Khai value
                            bKhaiValue=true;

                        } if(xpp.getName().equals("khaiGrade")){	//Khai grade
                            bKhaiGrade=true;

                        }if(xpp.getName().equals("so2Grade")){	//SO2 grade
                            bSo2Grade=true;

                        }if(xpp.getName().equals("coGrade")){	//CO grade
                            bCoGrade=true;

                        }if(xpp.getName().equals("o3Grade")){	//O3 grade
                            bO3Grade=true;

                        }if(xpp.getName().equals("no2Grade")){	//NO2 grade
                            bNo2Grade=true;

                        }if(xpp.getName().equals("pm10Grade")){	//Pm10 grade
                            bPm10Grade=true;

                        }if(xpp.getName().equals("totalCount")){	//total count
                            bTotalCount=true;

                        }

                            break;

                        case XmlPullParser.TEXT:	//parsing value

                            if(bDate){				//date
                                sDate[data]=xpp.getText();
                                bDate=false;
                            } if(bSo2Value){					//SO2 value
                            sSo2Value[data]=xpp.getText();
                            bSo2Value=false;
                        }  if(bCoValue){				//Co value
                            sCoValue[data]=xpp.getText();
                            bCoValue=false;
                        }  if(bO3Value){				//O3 value
                            sO3Value[data]=xpp.getText();
                            bO3Value=false;
                        }  if(bNo2Value){				//NO2 value
                            sNo2Value[data]=xpp.getText();
                            bNo2Value=false;
                        }  if(bPm10Value){				//Pm10 value
                            sPm10Value[data]=xpp.getText();
                            bPm10Value=false;
                        }  if(bKhaiValue){				//Khai value
                            sKhaiValue[data]=xpp.getText();
                            bKhaiValue=false;
                        } if(bKhaiGrade){				//Khai grade
                            sKhaiGrade[data]=xpp.getText();
                            bKhaiGrade=false;
                        }if(bSo2Grade){				//SO2 grade
                            sSo2Grade[data]=xpp.getText();
                            bSo2Grade=false;
                        }if(bCoGrade){				//CO grade
                            sCoGrade[data]=xpp.getText();
                            bCoGrade=false;
                        }if(bO3Grade){				//O3 grade
                            sO3Grade[data]=xpp.getText();
                            bO3Grade=false;
                        }if(bNo2Grade){				//NO2 grade
                            sNo2Grade[data]=xpp.getText();
                            bNo2Grade=false;
                        }if(bPm10Grade){				//Pm10 grade
                            sPm10Grade[data]=xpp.getText();
                            bPm10Grade=false;
                        }if(bTotalCount){               //total count
                            sTotalCount=xpp.getText();
                            bTotalCount=false;
                        }
                            break;

                        case XmlPullParser.END_TAG:		//'</' tag parsing

                            if(xpp.getName().equals("response")){	//parsing finished
                                tResponse=true;
                                view_text();
                            }if(xpp.getName().equals("item")){	//1 array finished
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
     * response result
     * @author Ans
     */
    private void view_text(){

        handler.post(new Runnable() {
            @Override
            public void run() {

                active=false;
                if(tResponse){
                    tResponse=false;
                    data=0;		//
                    DustActivity.FindDustThreadResponse(sTotalCount,sDate,sSo2Value,sCoValue,sO3Value,sNo2Value,sPm10Value,sKhaiValue,sKhaiGrade,sSo2Grade,sNo2Grade,sCoGrade,sO3Grade,sPm10Grade);

                }


            }
        });
    }
}
