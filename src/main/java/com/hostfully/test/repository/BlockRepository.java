package com.hostfully.test.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostfully.test.model.Block;

public interface BlockRepository extends JpaRepository<Block, Long> {
	List<Block> findByStartBetweenAndProperty(Date start, Date end, Integer property);
	List<Block> findByEndBetweenAndProperty(Date start, Date end, Integer property);
}
