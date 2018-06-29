package com.springboot.api;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.api.code.Code;
import com.springboot.api.code.CodeRepository;

@Controller
public class SpringBoot2RestServiceApplication {

	@Autowired
	 private CodeRepository codeRepository;
	
	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

//	@RequestMapping("/")
//	public String welcome(Map<String, Object> model) {
//		model.put("message", this.message);
//		return "welcome";
//	}
	
	@RequestMapping("/api/hello")
	public @ResponseBody String hello() {
		return "Hello, Spring Boot! change!2";
	}
	
	@CrossOrigin("*")
	@RequestMapping("/api/sample")
	@ResponseBody
	public String sample(@RequestParam Map<String, String> paramMap) {
		List<Code> list = codeRepository.findAllCode();
		Stream<Code> stream = list.stream();
		stream.filter(c -> c.getSido().equals("제주도")).forEach(System.out::println);
				
//		Iterator<Code> ic = codeRepository.findAll().iterator();
//		System.out.println(ic.hasNext());
		return "aa";
	}

}