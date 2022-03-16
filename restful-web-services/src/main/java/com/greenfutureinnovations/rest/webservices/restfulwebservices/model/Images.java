package com.greenfutureinnovations.rest.webservices.restfulwebservices.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_web_gfi_images")
public class Images {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private long ref;
	private int type;
	private String path;
	private Date save_date;
	private Date edit_date;
	private long encoder_id;
	private long editor_id;

	public Images() {
	}

	public Images(Long id, long ref, int type, String path, Date save_date, Date edit_date, long encoder_id,
			long editor_id) {
		super();
		this.id = id;
		this.ref = ref;
		this.type = type;
		this.path = path;
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

	public long getRef() {
		return ref;
	}

	public void setRef(long ref) {
		this.ref = ref;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
