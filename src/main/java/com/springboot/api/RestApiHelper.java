package com.springboot.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RestApiHelper {
		
	/*"http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade?ServiceKey=AI9qcEoaK35mGSnhjGyfzEBVkfoS14LZFAn7BgBQbI5FwHzxJe1%2BNwPz0GcB%2F0JsMXpFLic28nDyRorftIW8yg%3D%3D&LAWD_CD=11110&DEAL_YMD=201512"*/
	private String _baseUrl;
	private String _serviceKey;
	private String _lawdCd;
	private String _dealYmd;
	private String _fullUrl;
	
	public RestApiHelper SetBaseUrl(String baseUrl) {
		this._baseUrl = baseUrl;
		return this;
	}
	
	public RestApiHelper SetServiceKey(String serviceKey) {
		this._serviceKey = serviceKey;
		return this;
	}
	
	public RestApiHelper SetLawdCd(String lawdCd) {
		this._lawdCd = lawdCd;
		return this;
	}
	
	public RestApiHelper SetDealYmd(String dealYmd) {
		this._dealYmd = dealYmd;
		return this;
	}
	
	public RestApiHelper GenerateFullUrl() {
		this._fullUrl = _baseUrl + "?ServiceKey=" + this._serviceKey + "&LAWD_CD=" + this._lawdCd + "&DEAL_YMD=" + this._dealYmd;
		return this;
	}

	public String getXmlInformation()  {
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
