package com.hostfully.test.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
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
	
	public Book() {
		
	}

	public Book(Integer property, Date start, Date end) {
		this.property = property;
		this.start = start;
		this.end = end;
	}

	public long getId() {
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