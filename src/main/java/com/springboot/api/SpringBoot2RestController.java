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

@CrossOrigin("*")
@RestController
public class SpringBoot2RestController {

	static final Logger logger = LoggerFactory.getLogger(SpringBoot2RestController.class);
	
	private String serviceKey = "AI9qcEoaK35mGSnhjGyfzEBVkfoS14LZFAn7BgBQbI5FwHzxJe1%2BNwPz0GcB%2F0JsMXpFLic28nDyRorftIW8yg%3D%3D";
	
	
	// 실거래 내역 API 기본 URL
	private String apiBaseUrl = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc";
	private String aptTradeUrl = "/getRTMSDataSvcAptTrade";	// 아파트
	private String rhTradeUrl = "/getRTMSDataSvcRHTrade";	// 연립, 다세대
	private String shTradeUrl = "/getRTMSDataSvcSHTrade";	// 단독, 다가구 주택
	
	// 카카오 주소-> 경도 API 기본 URL
	private String apiKakakoBaseUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=";
	private String apiKey = "4bdbeb2a97c3db7eb425753b4acafb9e";
	
	@Autowired
	private CodeRepository codeRepository;

	@RequestMapping("/api/sido")
	public List<Code> sido(@RequestParam Map<String, String> paramMap) {
		return codeRepository.findSiDoCode();
	}
	
	@RequestMapping("/api/sigungu")
	public List<Code> sigungu(@RequestParam Map<String, String> paramMap) {
		return codeRepository.findSigunguCode(paramMap.get("sido"));
	}
	
	@RequestMapping("/api/dong")
	public List<Code> dong(@RequestParam Map<String, String> paramMap) {
		return codeRepository.findDongCode(paramMap.get("sigungu"));
	}
	
	/**
	 * 아파트 실거래내역 API
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/api/getRTMSDataSvcAptTrade")
	public String getRTMSDataSvcAptTrade(@RequestParam Map<String, String> paramMap) {
		return new RestApiHelper()
				.SetBaseUrl(apiBaseUrl + aptTradeUrl)
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
	@RequestMapping("/api/getRTMSDataSvcRHTrade")
	public String getRTMSDataSvcRHTrade(@RequestParam Map<String, String> paramMap) {
		return new RestApiHelper()
				.SetBaseUrl(apiBaseUrl + rhTradeUrl)
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
	@RequestMapping("/api/getRTMSDataSvcSHTrade")
	public String getRTMSDataSvcSHTrade(@RequestParam Map<String, String> paramMap) {
		return new RestApiHelper()
				.SetBaseUrl(apiBaseUrl + shTradeUrl)
				.SetServiceKey(serviceKey)
				.ParameterMap2Url(paramMap)
				.GenerateApiFullUrl()
				.getInputStream();
		
	}
	
	/**
	 * 다음카카오API
	 *  시 + 구 + 동 + 번지로 경도 구하기
	 *  ex)https://dapi.kakao.com/v2/local/search/address.json?query=서울특별시 중랑구 망우동 506-8
	 *  주의 : 해더값(Authorization) 추가 필요 
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/api/getAddress")
	public String getAddress(@RequestParam Map<String, String> paramMap) {
		return new RestApiHelper()
				.SetBaseUrl(apiKakakoBaseUrl)
				.SetServiceKey(apiKey)
				.SetParameter(paramMap.get("query"))
				.getInputStreamKakao();
		
	}

}