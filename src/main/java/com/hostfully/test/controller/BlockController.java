package com.hostfully.test.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostfully.test.model.Block;
import com.hostfully.test.repository.BlockRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BlockController {
	@Autowired
	BlockRepository blockRepository;
	
	@PostMapping("/blocks")
	public ResponseEntity<Block> createBook(@RequestBody Block book) {
		try {
			Block _block = blockRepository.save(new Block(book.getProperty(), book.getStart(), book.getEnd()));
			return new ResponseEntity<>(_block, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/blocks/{id}")
	public ResponseEntity<Block> updateBlock(@PathVariable("id") long id, @RequestBody Block book) {
		Optional<Block> blockData = blockRepository.findById(id);

		if (blockData.isPresent()) {
			Block _block = blockData.get();
			_block.setStart(book.getStart());
			_block.setEnd(book.getEnd());
			_block.setProperty(book.getProperty());
			return new ResponseEntity<>(blockRepository.save(_block), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/blocks/{id}")
	public ResponseEntity<HttpStatus> deleteBlock(@PathVariable("id") long id) {
		try {
			blockRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
