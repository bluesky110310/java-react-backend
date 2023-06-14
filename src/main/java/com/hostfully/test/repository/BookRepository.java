package com.hostfully.test.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostfully.test.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByStartBetweenAndProperty(Date start, Date end, Integer property);
	List<Book> findByEndBetweenAndProperty(Date start, Date end, Integer property);
}
