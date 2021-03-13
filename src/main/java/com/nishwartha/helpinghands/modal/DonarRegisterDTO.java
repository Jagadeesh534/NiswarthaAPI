package com.nishwartha.helpinghands.modal;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.nishwartha.helpinghands.entity.DonarRegister;

public class DonarRegisterDTO {

	private String id;
	
	private String name;
	
	private String bloodgroup;
	
	private Double age;
	
	private Double weight;
	
	private String email;
	
	private String address;
	
	private String mobilenumber;
	
	private String gender;
	
	private Date lastblooddonateddate;
	
	private Date createddate;
	
	private String image;
	
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public Date getLastblooddonateddate() {
		return lastblooddonateddate;
	}

	public void setLastblooddonateddate(Date lastblooddonateddate) {
		this.lastblooddonateddate = lastblooddonateddate;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public DonarRegisterDTO() {
		super();
	}
	public DonarRegisterDTO(DonarRegister d) {
		this.address = d.getAddress();
		this.age = d.getAge();
		this.bloodgroup = d.getBloodgroup();
		this.createddate = d.getCreateddate();
		this.email = d.getEmail();
		this.lastblooddonateddate = d.getLastblooddonateddate();
		this.mobilenumber = d.getMobilenumber();
		this.name = d.getName();
		this.weight = d.getWeight();
		this.gender = d.getGender();
	}
	
}
