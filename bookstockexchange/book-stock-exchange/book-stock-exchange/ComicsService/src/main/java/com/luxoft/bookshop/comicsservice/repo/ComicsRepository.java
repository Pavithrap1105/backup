package com.luxoft.bookshop.comicsservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luxoft.bookshop.comicsservice.entity.Comics;

public interface ComicsRepository extends JpaRepository<Comics, Integer> {

	@Query("from Comics where comicHero=:hero ")
	public List<Comics> findComicsByHero(@Param("hero") String comicHero);

	@Query("from Comics where comicTitle=:title")
	public Comics findComicByTitle(@Param("title") String comicTitle);

}
