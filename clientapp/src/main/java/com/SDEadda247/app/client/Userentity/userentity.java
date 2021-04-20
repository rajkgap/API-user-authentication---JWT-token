package com.SDEadda247.app.client.Userentity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="User")
public class userentity implements Serializable {

	private static final long serialVersionUID = -9009140655131902736L;
	@Id
	@GeneratedValue
	private long id;
	
	
	@Column(nullable=false)
	private String first_name;
	
	@Column(nullable=false)
	private String last_name;
	
	@Column(nullable=false,unique=true)
	private String email;
	
	
	@Column(nullable=false,unique=true)
	private String userid;
	
	@Column(nullable=false,unique=true)
	private String encryptedpassword;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEncryptedpassword() {
		return encryptedpassword;
	}

	public void setEncryptedpassword(String encryptedpassword) {
		this.encryptedpassword = encryptedpassword;
	}

}
