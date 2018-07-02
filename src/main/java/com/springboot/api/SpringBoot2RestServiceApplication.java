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

@RestController
public class SpringBoot2RestServiceApplication {

	private String baseUrl = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade";
	private String serviceKey = "AI9qcEoaK35mGSnhjGyfzEBVkfoS14LZFAn7BgBQbI5FwHzxJe1%2BNwPz0GcB%2F0JsMXpFLic28nDyRorftIW8yg%3D%3D";
	
	@Autowired
	private CodeRepository codeRepository;
	

	@CrossOrigin("*")
	@RequestMapping("/api/sido")
	public List<Code> sido(@RequestParam Map<String, String> paramMap) {
		List<Code> list = codeRepository.findSidoCode();
/*		List<Code> result = new ArrayList<Code>();
		Stream<Code> stream = list.stream();
		stream.filter(c -> c.getSido().equals("제주도")).forEach(result::add);*/
		return list;
	}
	
	@CrossOrigin("*")
	@RequestMapping("/api/sigungu")
	public List<Code> sigungu(@RequestParam Map<String, String> paramMap) {
		List<Code> list = codeRepository.findSiGunGuCode(paramMap.get("sido"));
		return list;
	}
	
	@CrossOrigin("*")
	@RequestMapping("/api/data")
	public String data(@RequestParam Map<String, String> paramMap) {
		return new RestApiHelper()
				.SetBaseUrl(baseUrl)
				.SetServiceKey(serviceKey)
				.SetLawdCd(paramMap.get("lawdCd"))
				.SetDealYmd(paramMap.get("dealYmd"))
				.GenerateFullUrl()
				.getXmlInformation();
		
	}

}