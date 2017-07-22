package com.dacheng.mes.sfdcs.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
		private final String USER_AGENT = "Mozilla/5.0";
		public static void main(String[] args) throws Exception {
			String url = "http://127.0.0.1:8800/sfdcs-service/rest/SFDCSConfigRestService/queryLocationInfo/0/25";
	       Test http = new Test();
	        System.out.println("Testing 1 - Send Http GET request");
	        http.sendGet(url);
		}

		// HTTP GET请求
		private void sendGet(String url) {
			  Map<String, String> map = new HashMap<String, String>();
		    try{
		    	URL obj = new URL(url);
			    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			    //默认值我GET
			    con.setRequestMethod("GET");
			    //添加请求头
			    con.setRequestProperty("User-Agent", USER_AGENT);
			    int responseCode = con.getResponseCode();
			    System.out.println("\nSending 'GET' request to URL : " + url);
			    System.out.println("Response Code : " + responseCode);
			    if (responseCode == 200){
			    	map.put("result", "OK");
			    }else if (responseCode == 404) {
			    	map.put("result", "make sure you send the true data!");
				}
			    BufferedReader in = new BufferedReader(
			            new InputStreamReader(con.getInputStream()));
			    String inputLine;
			    StringBuffer response = new StringBuffer();
			
			    while ((inputLine = in.readLine()) != null) {
			        response.append(inputLine);
			    }
			    in.close();
			    
			    map.put("data", response.toString());
		//	    System.out.println(map);
			    
		    }catch (java.net.ConnectException  e) {
				// TODO: handle exception
		    	map.put("result", "connection fail !");
			}catch (Exception e) {
				// TODO: handle exception
			}
		    Object data = JSONObject.fromObject(map).get("data"); 
		 //   JSONArray jsonArray = JSONArray.fromObject(data);
		    System.out.println(data);
		//    System.out.println(map.toString());
		}
}

