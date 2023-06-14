package com.hostfully.test.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "block")
public class Block {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "`PROPERTY`")
	private Integer property;

	@Column(name = "`START`")
	@Temporal(TemporalType.DATE)
	private Date start;

	@Column(name = "`END`")
	@Temporal(TemporalType.DATE)
	private Date end;

	public Block() {

	}

	public Block(Integer property, Date start, Date end) {
		this.property = property;
		this.start = start;
		this.end = end;
	}
	
	public Long getId() {
		return id;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}
