package com.springboot.api.code;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CrudRepository<Code, Integer> {
	@Query(nativeQuery = true, value=
			"select * from TBL_JSCODE")
	List<Code> findAllCode();
	
	@Query(nativeQuery = true, value=
			"select * from TBL_JSCODE where SIGUN is null order by CODE")
	List<Code> findSiDoCode();
	
	@Query(nativeQuery = true, value=
			"select *											\r\n" + 
			"from TBL_JSCODE									\r\n" + 
			"where substr(CODE,0,2) = substr(?,0,2)	\r\n" + 
			"and SIGUN is not null								\r\n" + 
			"and DONG is null									\r\n" + 
			"order by SIGUN")
	List<Code> findSigunguCode(String sidoCode);
	
	@Query(nativeQuery = true, value=
			"select *											\r\n" + 
			"from TBL_JSCODE									\r\n" + 
			"where substr(CODE,0,4) = substr(?,0,4)				\r\n" + 
			"and DONG is not null								\r\n" + 
			"order by DONG")
	List<Code> findDongCode(String sigunguCode);
}
