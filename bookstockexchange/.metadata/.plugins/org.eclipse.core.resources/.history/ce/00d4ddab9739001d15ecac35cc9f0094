package com.books.stock.exchange.Services;

import java.util.List;

import com.books.stock.exchange.Entities.Books;
import com.books.stock.exchange.Entities.BooksRequest;

public interface BooksService {
	
	public Books saveBook(Books books);
	
	public List<Books> getAllBooks();
	
	public Books updateBookById(Books books,int bookId);
	
	public String deleteBookById(int bookId);

	public Books addBook(BooksRequest request);
	
	public List<Books> findAllBooksByYearAndAuthor(String authorName,String year);
	
	public List<Books> findAllBooksByGenreAndAuthor(String bookGenre,String authorName);


}
