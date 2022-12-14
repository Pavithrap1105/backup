package com.luxoft.bookshop.comicsservice.entity;

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

/**
 * 
 * @author Pavithra Prakash
 *
 */
/**
 * 
 * Comics class is considered as an entity bean so that spring creates an object
 * and manages all other things
 * 
 * @Table tells to create table with the name as Comics
 * @Data represents using lombok library so that to generate
 *       getter,setters,etc..,
 */
@Entity
@Table(name = "Comics")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comics {
	/**
	 * @Id represents publicationId will be primary key in the database
	 * @GeneratedValue represents that id should be generated automatically
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comicId;

	@NotNull(message = "Comic title should not be null")
	@NotEmpty(message = "Comic title should not be empty")
	private String comicTitle;

	@NotEmpty(message = "Comic published year should not be empty")
	@Digits(integer = 4, fraction = 0)
	private String comicPublishedYear;

	@NotNull(message = "Comic hero should not be null")
	@NotEmpty(message = "Comic hero should not be empty")
	private String comicHero;

	public Comics(int comicId,
			@NotNull(message = "Comic title should not be null") @NotEmpty(message = "Comic title should not be empty") String comicTitle,
			@NotEmpty(message = "Comic published year should not be empty") @Digits(integer = 4, fraction = 0) String comicPublishedYear,
			@NotNull(message = "Comic hero should not be null") @NotEmpty(message = "Comic hero should not be empty") String comicHero) {
		super();
		this.comicId = comicId;
		this.comicTitle = comicTitle;
		this.comicPublishedYear = comicPublishedYear;
		this.comicHero = comicHero;
	}

	@JsonIgnore
	@ManyToOne
	private Author author;

}
