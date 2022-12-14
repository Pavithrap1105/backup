package com.luxoft.bookshop.booksservice.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.stock.exchange.Entities.Author;
import com.books.stock.exchange.Entities.Publication;
import com.books.stock.exchange.Repository.AuthorRepository;
import com.books.stock.exchange.Repository.PublicationRepository;
import com.books.stock.exchange.exception.ResourceAlreadyExistException;
import com.books.stock.exchange.exception.ResourceNotFoundException;
import com.luxoft.bookshop.booksservice.entity.Books;
import com.luxoft.bookshop.booksservice.entity.BooksRequest;
import com.luxoft.bookshop.booksservice.repo.BooksRepository;

@Service
public class BooksServiceImpl implements BooksService {

	private static Logger logger = LogManager.getLogger(BooksServiceImpl.class);

	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private PublicationRepository publicationRepository;

	@Override
	public Books saveBook(Books books) {
		Books existingBook = this.booksRepository.findBookByTitle(books.getBookTitle());
		if (existingBook != null) {
			logger.info("Book is already exist with the title :" + books.getBookTitle());
			logger.error(
					new ResourceAlreadyExistException("Book is already exist with the title :" + books.getBookTitle()));
			throw new ResourceAlreadyExistException("Book is already exist with the title :" + books.getBookTitle());
		}
		
		Books savedBook = booksRepository.save(books);
		logger.debug("Book saved successfully with id :" + savedBook.getBookId());
		return savedBook;
	}

	@Override
	public List<Books> getAllBooks() {
		List<Books> listOfBooks = booksRepository.findAll();
		if (listOfBooks == null) {
			logger.info("Books not found");
			logger.error(new ResourceNotFoundException("Books not found"));
			throw new ResourceNotFoundException("Books not found");
		}
		logger.debug("Book fetched successfully");
		return listOfBooks;
	}

	@Override
	public Books updateBookById(Books books, int bookId) {
		Books existingBook = booksRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with id :" + bookId));
		existingBook.setBookTitle(books.getBookTitle());
		existingBook.setBookPublishedYear(books.getBookPublishedYear());
		existingBook.setBookGenre(books.getBookGenre());
		Books updatedBooks = booksRepository.save(existingBook);
		logger.debug("Book updated successfully for id:" + bookId);
		return updatedBooks;
	}

	@Override
	public String deleteBookById(int bookId) {
		Books existingBook = this.booksRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with id :" + bookId));
		booksRepository.delete(existingBook);
		logger.debug("Book deleted successfully for id:" + bookId);
		return "Deleted Succesfully";
	}

	@Override
	public Books addBook(BooksRequest request) {
		Author author = authorRepository.findById(request.author_id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with id :" + request.author_id));
		Publication publications = publicationRepository.findById(request.publication_id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with id :" + request.publication_id));
		Books book = new Books();
		book.setBookTitle(request.bookTitle);
		book.setBookPublishedYear(request.bookPublishedYear);
		book.setBookGenre(request.bookGenre);
		logger.info("Assigning fetched author details by ID to Book");
		book.setAuthor(author);
		logger.info("Assigning fetched publication details by ID to Book");
		book.setPublication(publications);
		Books savedBook = booksRepository.save(book);
		logger.debug("Book,author and publication successfully mapped");
		return savedBook;
	}

	@Override
	public List<Books> findAllBooksByYearAndAuthor(String authorName, String year) {
		List<Books> booksByYearAndAuthor = booksRepository.findAllBooksByYearAndAuthor(authorName, year);
		if (booksByYearAndAuthor==null) {
			logger.info("Book not found");
			logger.error(
					new ResourceNotFoundException("Books not found with author " + authorName + " and year " + year));
			throw new ResourceNotFoundException("Books not found with author " + authorName + " and year " + year);
		}
		logger.debug("Book fetched successfully based on author and published year");
		return booksByYearAndAuthor;
	}

	@Override
	public List<Books> findAllBooksByGenreAndAuthor(String bookGenre, String authorName) {
		List<Books> booksByGenreAndAuthor = booksRepository.findAllBooksByGenreAndAuthor(bookGenre, authorName);
		if (booksByGenreAndAuthor==null) {
			logger.info("Book not found");
			logger.error(new ResourceNotFoundException(
					"Books not found with author " + authorName + " and genre " + bookGenre));
			throw new ResourceNotFoundException(
					"Books not found with author " + authorName + " and genre " + bookGenre);
		}
		logger.debug("Book fetched successfully based on author and book genre");
		return booksByGenreAndAuthor;
	}

}
