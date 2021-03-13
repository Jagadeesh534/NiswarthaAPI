package com.nishwartha.helpinghands.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activites")
public class Activities {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "imagepath")
	private String imagePath;
	
	@Column(name = "image_src")
	private String imageSrc;
	
	@Column(name = "imagetype")
	private String imagetype;
	
	@Column(name = "title")
	private String title;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImagetype() {
		return imagetype;
	}

	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Activities() {
		super();
	}
	
	

}
