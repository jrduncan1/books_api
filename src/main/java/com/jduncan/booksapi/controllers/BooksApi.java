package com.jduncan.booksapi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jduncan.booksapi.models.Book;
import com.jduncan.booksapi.services.BookService;

@RestController
public class BooksApi {
	
	private final BookService bookService;
	
	public BooksApi(BookService bookService) {
		this.bookService = bookService;
	}
	
	@RequestMapping("/api/books")
	public List<Book> index() {
		return bookService.allBooks();
	}
	
    @PostMapping("/api/books")
    public Book create(@RequestParam(value="title") String title, 
    				@RequestParam(value="description") String description, 
    				@RequestParam(value="language") String language, 
    				@RequestParam(value="pages") Integer pages) {
        Book book = new Book(title, description, language, pages);
        return bookService.createBook(book);
    }
    
    @RequestMapping("/api/books/{id}")
    public Book show(@PathVariable("id") Long id) {
        Book book = bookService.findBook(id);
        return book;
    }
    
    @PutMapping("/api/books/{id}")
    public Book update(
    		@PathVariable("id") Long id,
    		@RequestParam("title") String title,
    		@RequestParam("description") String description,
    		@RequestParam("language") String language,
    		@RequestParam("pages") Integer pages
    		) {
    	Book book = bookService.updateBook(id, title, description, language, pages);
    	return book;
    }
    
    @DeleteMapping("/api/books/{id}")
    public void delete(@PathVariable("id") Long id) {
    	bookService.deleteBook(id);
    }

}
