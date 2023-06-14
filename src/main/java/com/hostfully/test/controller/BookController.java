package com.hostfully.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostfully.test.model.Block;
import com.hostfully.test.model.Book;
import com.hostfully.test.repository.BlockRepository;
import com.hostfully.test.repository.BookRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BookController {
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BlockRepository blockRepository;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
		try {
			List<Book> books = new ArrayList<Book>();

			bookRepository.findAll().forEach(books::add);

			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
		Optional<Book> bookData = bookRepository.findById(id);

		if (bookData.isPresent()) {
			return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		try {
			List<Book> books1 = bookRepository.findByStartBetweenAndProperty(book.getStart(), book.getEnd(), book.getProperty());
			List<Book> books2 = bookRepository.findByEndBetweenAndProperty(book.getStart(), book.getEnd(), book.getProperty());
			List<Block> blocks1 = blockRepository.findByStartBetweenAndProperty(book.getStart(), book.getEnd(), book.getProperty());
			List<Block> blocks2 = blockRepository.findByEndBetweenAndProperty(book.getStart(), book.getEnd(), book.getProperty());
			
			Integer count = books1.size() + books2.size() + blocks1.size() + blocks2.size();
			if (count > 0) return new ResponseEntity<>(HttpStatus.CONFLICT);
			
			Book _book = bookRepository.save(new Book(book.getProperty(), book.getStart(), book.getEnd()));
			return new ResponseEntity<>(_book, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
		Optional<Book> bookData = bookRepository.findById(id);

		if (bookData.isPresent()) {
			List<Book> books1 = bookRepository.findByStartBetweenAndProperty(book.getStart(), book.getEnd(), book.getProperty());
			List<Book> books2 = bookRepository.findByEndBetweenAndProperty(book.getStart(), book.getEnd(), book.getProperty());
			List<Block> blocks1 = blockRepository.findByStartBetweenAndProperty(book.getStart(), book.getEnd(), book.getProperty());
			List<Block> blocks2 = blockRepository.findByEndBetweenAndProperty(book.getStart(), book.getEnd(), book.getProperty());
			
			Integer count = books1.size() + books2.size() + blocks1.size() + blocks2.size();
			if (count > 0) return new ResponseEntity<>(HttpStatus.CONFLICT);
			
			Book _book = bookData.get();
			_book.setStart(book.getStart());
			_book.setEnd(book.getEnd());
			_book.setProperty(book.getProperty());
			return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
		try {
			bookRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
