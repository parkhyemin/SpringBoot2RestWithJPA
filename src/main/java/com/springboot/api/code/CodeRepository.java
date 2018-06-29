package com.springboot.api.code;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CrudRepository<Code, Integer> {
	@Query(nativeQuery = true, value=
			"select substr(code,0,5) as code\r\n" + 
			"        , max(sido) as sido\r\n" + 
			"        , max(sigun) as sigun\r\n" + 
			"        , '' as dong\r\n" + 
			"from TBL_JSCODE\r\n" + 
			"group by substr(code,0,5)\r\n" + 
			"order by 1")
	List<Code> findAllCode();
	
}
