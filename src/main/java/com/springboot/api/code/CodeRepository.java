package com.springboot.api.code;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CrudRepository<Code, Integer> {
	@Query(nativeQuery = true, value=
			"select *\r\n" + 
			"from TBL_JSCODE\r\n" + 
			"where SIGUN is  null\r\n" + 
			"order by CODE")
	List<Code> findSidoCode();
	
	@Query(nativeQuery = true, value=
			"select * \r\n" + 
			"from tbl_jscode\r\n" + 
			"where sido = ?1 \r\n" + 
			"and sigun is not null\r\n" + 
			"and dong is null\r\n" + 
			"order by sigun")
	List<Code> findSiGunGuCode(String sido);
	
}
