package com.luxoft.bookshop.booksservice.service;

import java.util.List;

import com.luxoft.bookshop.booksservice.entity.Books;
import com.luxoft.bookshop.booksservice.entity.BooksRequest;

public interface BooksService {
	
	public Books saveBook(Books books);
	
	public List<Books> getAllBooks();
	
	public Books updateBookById(Books books,int bookId);
	
	public String deleteBookById(int bookId);

	public Books addBook(BooksRequest request);
	
	public List<Books> findAllBooksByYearAndAuthor(String authorName,String year);
	
	public List<Books> findAllBooksByGenreAndAuthor(String bookGenre,String authorName);


}
