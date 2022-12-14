package com.luxoft.bookshop.booksservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.bookshop.booksservice.entity.Books;
import com.luxoft.bookshop.booksservice.entity.BooksRequest;
import com.luxoft.bookshop.booksservice.service.BooksService;

@RestController
public class BooksController {

	private static Logger logger = LogManager.getLogger(BooksController.class);

	@Autowired
	private BooksService booksService;

	@PostMapping("/saveBooks")
	public Books saveBooks(@Valid @RequestBody Books books) {
		Books savedBook = booksService.saveBook(books);
		logger.debug("Book with id " + books.getBookId() + " saved successfully");
		return savedBook;
	}

	@GetMapping("/getAllBooks")
	public List<Books> findAllBooks() {
		List<Books> allBooks = booksService.getAllBooks();
		logger.debug("Books fetched successfully");
		return allBooks;
	}

	@PutMapping("/updateBook/{id}")
	public Books updateBook(@Valid @RequestBody Books books, @PathVariable(name = "id") int bookId) {
		Books updatedBookById = booksService.updateBookById(books, bookId);
		logger.debug("Book with id " + bookId + " updated successfully");
		return updatedBookById;
	}

	@DeleteMapping("/deleteBook/{id}")
	public String deleteBook(@Valid @PathVariable(name = "id") int bookId) {
		String deletedBookById = booksService.deleteBookById(bookId);
		logger.debug("Book with id " + bookId + " deleted successfully");
		return deletedBookById;
	}

	// For Mapping between books,author and publications
	@PostMapping("/assignBook")
	public Books assignBook(@Valid @RequestBody BooksRequest request) {
		Books addedBook = booksService.addBook(request);
		logger.debug("Book assigned to author and publication successfully");
		return addedBook;
	}

	@GetMapping("/getBooksByYearAndAuthor/{author}/{year}")
	public List<Books> getBooksByYearAndAuthor(@Valid @PathVariable("author") String authorName,
			@PathVariable("year") String bookPublishedYear) {
		List<Books> booksByYearAndAuthor = booksService.findAllBooksByYearAndAuthor(authorName, bookPublishedYear);
		logger.debug("Books list fetched successfully by author and published year");
		return booksByYearAndAuthor;
	}

	@GetMapping("/findAllBooksByGenreAndAuthor/{genre}/{author}")
	public List<Books> findAllBooksByGenreAndAuthor(@Valid @PathVariable("genre") String bookGenre,
			@PathVariable("author") String authorName) {
		List<Books> booksByGenreAndAuthor = booksService.findAllBooksByGenreAndAuthor(bookGenre, authorName);
		logger.debug("Books list fetched successfully by author and book genre");
		return booksByGenreAndAuthor;
	}
}
