package com.springboot.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class RestApiHelper {
		
	/*"http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade?ServiceKey=AI9qcEoaK35mGSnhjGyfzEBVkfoS14LZFAn7BgBQbI5FwHzxJe1%2BNwPz0GcB%2F0JsMXpFLic28nDyRorftIW8yg%3D%3D&LAWD_CD=11110&DEAL_YMD=201512"*/
	private String _baseUrl;
	private String _serviceKey;
	private String _parameter = new String();
	private String _fullUrl;
	
	public RestApiHelper SetBaseUrl(String baseUrl) {
		this._baseUrl = baseUrl;
		return this;
	}
	
	public RestApiHelper SetServiceKey(String serviceKey) {
		this._serviceKey = serviceKey;
		return this;
	}
	
	
	public RestApiHelper ParameterMap2Url(Map<String, String> paramMap) {
		for(String key : paramMap.keySet()) {
			this._parameter += "&" + key + "=" + paramMap.get(key);
		}
		return this;
	}
	
	public RestApiHelper GenerateApiFullUrl() {
		this._fullUrl = _baseUrl + "?ServiceKey=" + this._serviceKey + this._parameter;
		return this;
	}

	public String getInputStream()  {
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(_fullUrl);
			URLConnection yc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream(),"UTF-8"));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine).append("\n");
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
}
