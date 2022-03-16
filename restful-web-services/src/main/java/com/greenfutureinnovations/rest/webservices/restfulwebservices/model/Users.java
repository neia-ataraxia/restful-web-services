package com.greenfutureinnovations.rest.webservices.restfulwebservices.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_web_gfi_users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String username;
	private String password;
	private boolean status;
	private boolean deleted;
	private boolean level1;
	private boolean level2;
	private boolean level3;
	private boolean level4;
	private boolean level5;
	private Date save_date;
	private Date edit_date;
	private long encoder_id;
	private long editor_id;

	public Users() {
	}
	
	public Users(Long id, String name, String username, String password, boolean status, boolean deleted, boolean level1, boolean level2,
			boolean level3, boolean level4, boolean level5, Date save_date, Date edit_date, long encoder_id,
			long editor_id) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;

		this.password = password;
		this.deleted = deleted;
		this.status = status;
		this.level1 = level1;
		this.level2 = level2;
		this.level3 = level3;
		this.level4 = level4;
		this.level5 = level5;
		this.save_date = save_date;
		this.edit_date = edit_date;
		this.encoder_id = encoder_id;
		this.editor_id = editor_id;
		

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isstatus() {
		return status;
	}

	public void setstatus(boolean status) {
		this.status = status;
	}

	public boolean isdeleted() {
		return deleted;
	}

	public void setdeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public boolean isLevel1() {
		return level1;
	}

	public void setLevel1(boolean level1) {
		this.level1 = level1;
	}

	public boolean isLevel2() {
		return level2;
	}

	public void setLevel2(boolean level2) {
		this.level2 = level2;
	}

	public boolean isLevel3() {
		return level3;
	}

	public void setLevel3(boolean level3) {
		this.level3 = level3;
	}

	public boolean isLevel4() {
		return level4;
	}

	public void setLevel4(boolean level4) {
		this.level4 = level4;
	}

	public boolean isLevel5() {
		return level5;
	}

	public void setLevel5(boolean level5) {
		this.level5 = level5;
	}

	public Date getSave_date() {
		return save_date;
	}

	public void setSave_date(Date save_date) {
		this.save_date = save_date;
	}

	public Date getEdit_date() {
		return edit_date;
	}

	public void setEdit_date(Date edit_date) {
		this.edit_date = edit_date;
	}

	public long getEncoder_id() {
		return encoder_id;
	}

	public void setEncoder_id(long encoder_id) {
		this.encoder_id = encoder_id;
	}

	public long getEditor_id() {
		return editor_id;
	}

	public void setEditor_id(long editor_id) {
		this.editor_id = editor_id;
	}
	
}
