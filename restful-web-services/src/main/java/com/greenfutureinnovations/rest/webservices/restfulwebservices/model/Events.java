package com.greenfutureinnovations.rest.webservices.restfulwebservices.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_web_gfi_events")
public class Events {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String heading;
	private String sub_heading;
	private String content;
	private boolean status;
	private Date save_date;
	private Date edit_date;
	private long encoder_id;
	private long editor_id;

	public Events() {
	}

	public Events(Long id, String heading, String sub_heading, String content, boolean status, Date save_date,
			Date edit_date, long encoder_id, long editor_id) {
		super();
		this.id = id;
		this.heading = heading;
		this.sub_heading = sub_heading;
		this.content = content;
		this.status = status;
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

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getSub_heading() {
		return sub_heading;
	}

	public void setSub_heading(String sub_heading) {
		this.sub_heading = sub_heading;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
