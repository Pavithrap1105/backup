package com.luxoft.bookshop.comicsservice.service;

import java.util.List;

import com.luxoft.bookshop.comicsservice.entity.Comics;
import com.luxoft.bookshop.comicsservice.entity.ComicsRequest;

public interface ComicsService {
	
	public Comics saveComic(Comics comics);
	
	public List<Comics> getAllComics();
	
	public Comics updateComicById(Comics comics,int comicId);
	
	public String deleteComicById(int comicId);

	public Comics addComic(ComicsRequest request);
	
	public List<Comics> findComicsByHero(String comicHero);

}
