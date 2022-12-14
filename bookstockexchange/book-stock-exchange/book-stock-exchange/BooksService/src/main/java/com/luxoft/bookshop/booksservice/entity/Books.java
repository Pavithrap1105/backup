package com.luxoft.bookshop.booksservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;

	@NotNull(message = "Book title should not be null")
	@NotEmpty(message = "Book title should not be empty")
	private String bookTitle;

	@Digits(integer = 4, fraction = 0)
	private String bookPublishedYear;

	@NotNull(message = "Book title should not be null")
	@NotEmpty(message = "Book title should not be empty")
	private String bookGenre;

	public Books(int bookId,
			@NotNull(message = "Book title should not be null") @NotEmpty(message = "Book title should not be empty") String bookTitle,
			@Digits(integer = 4, fraction = 0) String bookPublishedYear,
			@NotNull(message = "Book title should not be null") @NotEmpty(message = "Book title should not be empty") String bookGenre) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookPublishedYear = bookPublishedYear;
		this.bookGenre = bookGenre;
	}

	@JsonIgnore
	@ManyToOne
	private Author author;

	@JsonIgnore
	@ManyToOne
	private Publication publication;

}
