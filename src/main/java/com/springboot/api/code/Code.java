package com.springboot.api.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_JSCODE")
public class Code {
	
	@Id
	@Column(name="code")
	private String code;
	
	@Column(name="sido")
	private String sido;
	
	@Column(name="sigun")
	private String sigun;
	
	@Column(name="dong")
	private String dong;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getSigun() {
		return sigun;
	}

	public void setSigun(String sigun) {
		this.sigun = sigun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	@Override
	public String toString() {
		return "Code [code=" + code + ", sido=" + sido + ", sigun=" + sigun + ", dong=" + dong + "]";
	}
	
}
