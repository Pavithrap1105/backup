package com.luxoft.bookshop.authorservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luxoft.bookshop.authorservice.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

	@Query("from Author where authorName=:name")
	public Author getAuthorByName(@Param("name") String authorName);


}
