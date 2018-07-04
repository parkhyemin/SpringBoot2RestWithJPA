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
	// 아파트 실거래내역 API
	private String baseUrl = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade";
	private String serviceKey = "AI9qcEoaK35mGSnhjGyfzEBVkfoS14LZFAn7BgBQbI5FwHzxJe1%2BNwPz0GcB%2F0JsMXpFLic28nDyRorftIW8yg%3D%3D";
	
	// 아파트 목록 API
	private String aptListBaseUrl = "http://apis.data.go.kr/1611000/AptListService/getLegaldongAptList";
	private String aptListServiceKey = "AI9qcEoaK35mGSnhjGyfzEBVkfoS14LZFAn7BgBQbI5FwHzxJe1%2BNwPz0GcB%2F0JsMXpFLic28nDyRorftIW8yg%3D%3D";
	
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
		List<Code> list = codeRepository.findSigunguCode(paramMap.get("sido"));
		return list;
	}
	
	@CrossOrigin("*")
	@RequestMapping("/api/dong")
	public List<Code> dong(@RequestParam Map<String, String> paramMap) {
		List<Code> list = codeRepository.findDongCode(paramMap.get("sigungu"));
		return list;
	}
	
	@CrossOrigin("*")
	@RequestMapping("/api/data")
	public String data(@RequestParam Map<String, String> paramMap) {
		return new RestApiHelper()
				.SetBaseUrl(baseUrl)
				.SetServiceKey(aptListServiceKey)
				.ParameterMap2Url(paramMap)
				.GenerateApiFullUrl()
				.getInputStream();
		
	}
	
	@CrossOrigin("*")
	@RequestMapping("/api/aptList")
	public String aptList(@RequestParam Map<String, String> paramMap) {
		return new RestApiHelper()
				.SetBaseUrl(aptListBaseUrl)
				.SetServiceKey(serviceKey)
				.ParameterMap2Url(paramMap)
				.GenerateApiFullUrl()
				.getInputStream();
		
	}

}