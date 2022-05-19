package com.poseidon.dto;

public class JoinDTO {
	private int age;
	private String id, passwd, name, email, addr, tel;
	
	//생성자 똑같은 이름으로 여러개 만들고 싶어요. = ?
	public JoinDTO() {
		
	}
	public JoinDTO(String id) {
		this.id = id;
	}
	public JoinDTO(int age, String id, String passwd, 
					String name, String email, String addr, String tel) {
		this.age = age;
		this.id = id;
		this.name = name;
		this.passwd = passwd;
		this.email = email;
		this.addr = addr;
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
