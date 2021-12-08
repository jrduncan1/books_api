package com.jduncan.booksapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jduncan.booksapi.models.Book;
import com.jduncan.booksapi.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	// returns all books
	public List<Book> allBooks() {
		return bookRepository.findAll();
	}
	
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    
    // updates book
    public Book updateBook(Long id, String title, String description, String language, Integer pages) {
    	Book updatedBook = this.findBook(id);
    	
    	updatedBook.setTitle(title);
    	updatedBook.setDescription(description);
    	updatedBook.setLanguage(language);
    	updatedBook.setNumberOfPages(pages);
    	
    	return bookRepository.save(updatedBook);
    }
    
    // deletes book
    public void deleteBook(Long id) {
    	bookRepository.deleteById(id);
    }

}
