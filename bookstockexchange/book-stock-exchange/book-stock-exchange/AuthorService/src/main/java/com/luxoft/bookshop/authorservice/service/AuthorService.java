package com.luxoft.bookshop.authorservice.service;

import java.util.List;

import com.luxoft.bookshop.authorservice.entity.Author;

public interface AuthorService {

	public Author saveAuthor(Author author);

	public List<Author> getAllAuthors();

	public Author getAuthorByName(String authorName);

	public Author getAuthorById(int authorId);

	public Author updateAuthorById(Author author, int authorId);

	public String deleteAuthorById(int authorId);

	public Author assignPublicationToAuthor(int authorId, int publicationId);

}
