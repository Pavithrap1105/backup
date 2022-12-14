package com.luxoft.bookshop.authorservice.controller;

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

import com.luxoft.bookshop.authorservice.entity.Author;
import com.luxoft.bookshop.authorservice.service.AuthorService;

@RestController
public class AuthorController {

	private static Logger logger = LogManager.getLogger(AuthorController.class);

	@Autowired
	private AuthorService authorService;

	@PostMapping("/saveAuthors")
	public Author saveAuthors(@Valid @RequestBody Author author) {
		logger.info("Saving Author details");
		Author savedAuthor = null;

		try {
			logger.info("Inside try block ");
			savedAuthor = authorService.saveAuthor(author);
			logger.debug("Author details Saved Succesfully");
		} catch (Exception e) {
			logger.info("Inside catch block ");
			e.printStackTrace();
		}

		return savedAuthor;
	}

	@GetMapping("/getAllAuthors")
	public List<Author> findAllAuthors() {
		logger.info("Fetching All Author details");
		List<Author> listOfAuthors = null;

		try {
			logger.info("Inside try block ");
			listOfAuthors = authorService.getAllAuthors();
			logger.debug("Author details Fetched Succesfully");
		} catch (Exception e) {
			logger.info("Inside catch block ");
			e.printStackTrace();
		}

		return listOfAuthors;
	}

	@GetMapping("/findAuthorByName/{name}")
	public Author findAuthorByName(@Valid @PathVariable("name") String authorName) {
		logger.info("Fetching Author details by name");
		Author author = authorService.getAuthorByName(authorName);

		logger.debug("Author details Fetched Succesfully");
		return author;
	}

	@GetMapping("/findAuthorById/{id}")
	public Author findAuthorById(@Valid @PathVariable("id") int authorId) {
		logger.info("Fetching Author details by id");
		Author author = authorService.getAuthorById(authorId);

		logger.debug("Author details Fetched Succesfully");
		return author;
	}

	@PutMapping("/updateAuthor/{id}")
	public Author updateAuthor(@Valid @RequestBody Author author, @PathVariable(name = "id") int authorId) {
		logger.info("Updating Author details by id");
		Author updatedAuthor = authorService.updateAuthorById(author, authorId);

		logger.debug("Author details updated Succesfully");
		return updatedAuthor;

	}

	@DeleteMapping("/deleteAuthor/{id}")
	public String deleteAuthor(@Valid @PathVariable(name = "id") int authorId) {
		logger.info("Deleting Author details by id");
		authorService.deleteAuthorById(authorId);

		logger.debug("Author details deleted Succesfully");
		return "Author details deleted Succesfully";
	}

	@PutMapping("/{authorId}/publication/{publicationId}")
	public Author assignPublicationToAuthor(@Valid @PathVariable("authorId") int authorId,
			@PathVariable("publicationId") int publicationId) {
		logger.info("Assigning Author details to publication");
		Author author = authorService.assignPublicationToAuthor(authorId, publicationId);

		logger.debug("Author details assigned Succesfully to publication");
		return author;
	}

}

