package com.springboot.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestApiHelper {
	static final Logger logger = LoggerFactory.getLogger(RestApiHelper.class);	
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
			logger.info(sb.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
}
