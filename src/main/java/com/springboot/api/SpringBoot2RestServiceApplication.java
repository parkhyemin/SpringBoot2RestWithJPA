package com.springboot.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.code.Code;
import com.springboot.api.code.CodeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class SpringBoot2RestServiceApplication {

	static final Logger logger = LoggerFactory.getLogger(SpringBoot2RestServiceApplication.class);
	
	private String serviceKey = "AI9qcEoaK35mGSnhjGyfzEBVkfoS14LZFAn7BgBQbI5FwHzxJe1%2BNwPz0GcB%2F0JsMXpFLic28nDyRorftIW8yg%3D%3D";
	
	// 아파트 목록 API
	private String aptListBaseUrl = "http://apis.data.go.kr/1611000/AptListService/getLegaldongAptList";
	
	// 아파트 실거래내역 API
	private String aptTradeListBaseUrl = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade";
	
	// 연립다세대 매매 실거래 API
	private String rhTradeListBaseUrl = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade";
	
	// 단독 주택 매매 실거래 API
	private String shTradeListBaseUrl = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcSHTrade";
	
	@Autowired
	private CodeRepository codeRepository;

	@CrossOrigin("*")
	@RequestMapping("/api/sido")
	public List<Code> sido(@RequestParam Map<String, String> paramMap) {
		return codeRepository.findSiDoCode();
	}
	
	
	@CrossOrigin("*")
	@RequestMapping("/api/sigungu")
	public List<Code> sigungu(@RequestParam Map<String, String> paramMap) {
		return codeRepository.findSigunguCode(paramMap.get("sido"));
	}
	
	@CrossOrigin("*")
	@RequestMapping("/api/dong")
	public List<Code> dong(@RequestParam Map<String, String> paramMap) {
		return codeRepository.findDongCode(paramMap.get("sigungu"));
	}
	
	/**
	 * 아파트 목록 API
	 * @param paramMap
	 * @return
	 */
	@CrossOrigin("*")
	@RequestMapping("/api/getLegaldongAptList")
	public String getLegaldongAptList(@RequestParam Map<String, String> paramMap) {
		return new RestApiHelper()
				.SetBaseUrl(aptListBaseUrl)
				.SetServiceKey(serviceKey)
				.ParameterMap2Url(paramMap)
				.GenerateApiFullUrl()
				.getInputStream();
		
	}
	
	/**
	 * 아파트 실거래내역 API
	 * @param paramMap
	 * @return
	 */
	@CrossOrigin("*")
	@RequestMapping("/api/getRTMSDataSvcAptTrade")
	public String getRTMSDataSvcAptTrade(@RequestParam Map<String, String> paramMap) {
		return new RestApiHelper()
				.SetBaseUrl(aptTradeListBaseUrl)
				.SetServiceKey(serviceKey)
				.ParameterMap2Url(paramMap)
				.GenerateApiFullUrl()
				.getInputStream();
		
	}
	
	/**
	 * 연립다세대 매매 실거래 API
	 * @param paramMap
	 * @return
	 */
	@CrossOrigin("*")
	@RequestMapping("/api/getRTMSDataSvcRHTrade")
	public String getRTMSDataSvcRHTrade(@RequestParam Map<String, String> paramMap) {
		return new RestApiHelper()
				.SetBaseUrl(rhTradeListBaseUrl)
				.SetServiceKey(serviceKey)
				.ParameterMap2Url(paramMap)
				.GenerateApiFullUrl()
				.getInputStream();
		
	}
	
	/**
	 * 단독주택 매매 실거래 API
	 * @param paramMap
	 * @return
	 */
	@CrossOrigin("*")
	@RequestMapping("/api/getRTMSDataSvcSHTrade")
	public String getRTMSDataSvcSHTrade(@RequestParam Map<String, String> paramMap) {
		return new RestApiHelper()
				.SetBaseUrl(shTradeListBaseUrl)
				.SetServiceKey(serviceKey)
				.ParameterMap2Url(paramMap)
				.GenerateApiFullUrl()
				.getInputStream();
		
	}

}