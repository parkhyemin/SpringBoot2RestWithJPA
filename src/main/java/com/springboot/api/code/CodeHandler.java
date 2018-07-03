package com.springboot.api.code;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodeHandler {

	@Autowired
	private CodeRepository codeRepository;
	private List<Code> _codeList;
	
	public List<Code> getAllCodeList(){
		if(_codeList == null)
			_codeList = codeRepository.findAllCode();
		return _codeList;
	}
	
	public List<Code> getSidoList(){
		List<Code> result =  new ArrayList<Code>();
		Stream<Code> stream = getAllCodeList().stream();
		stream.filter(c -> c.getSigun() == null).forEach(result::add);
		return result;
	}
	
	public List<Code> Test(){
		List<Code> result =  new ArrayList<Code>();
		Stream<Code> stream = getAllCodeList().stream();
		stream.filter(c -> c.getSido().equals("경기도") && c.getSigun().equals("화성시")).forEach(result::add);
		return result;
	}
	 
}
