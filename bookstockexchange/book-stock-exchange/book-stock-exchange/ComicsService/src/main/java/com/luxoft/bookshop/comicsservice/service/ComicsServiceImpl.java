package com.luxoft.bookshop.comicsservice.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.stock.exchange.Entities.Author;
import com.books.stock.exchange.Repository.AuthorRepository;
import com.books.stock.exchange.exception.ResourceAlreadyExistException;
import com.books.stock.exchange.exception.ResourceNotFoundException;
import com.luxoft.bookshop.comicsservice.entity.Comics;
import com.luxoft.bookshop.comicsservice.entity.ComicsRequest;
import com.luxoft.bookshop.comicsservice.repo.ComicsRepository;

@Service
public class ComicsServiceImpl implements ComicsService {

	private static Logger logger = LogManager.getLogger(ComicsServiceImpl.class);

	@Autowired
	private ComicsRepository comicsRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Comics saveComic(Comics comics) {
		logger.info("Saving comic details");
		Comics existingComic = this.comicsRepository.findComicByTitle(comics.getComicTitle());

		if (existingComic != null) {
			logger.info("Comic details already exist");
			logger.error(new ResourceAlreadyExistException(comics.getComicTitle() + " Comic already exist"));
			throw new ResourceAlreadyExistException(comics.getComicTitle() + " Comic already exist");
		} else {
			Comics savedComic = comicsRepository.save(comics);
			logger.info("Comic details saved successfully");
			return savedComic;
		}
	}

	@Override
	public List<Comics> getAllComics() {
		logger.info("Fetching All Comics details");
		List<Comics> listOfComics = comicsRepository.findAll();

		if (listOfComics == null) {
			logger.info("Comics not found");
			logger.debug(new ResourceNotFoundException("Comics not found"));
			throw new ResourceNotFoundException("Comics not found");
		}

		logger.debug("All details fetched successfully");
		return listOfComics;
	}

	@Override
	public Comics updateComicById(Comics comics, int comicId) {
		logger.info("Updating Comic detail");

		Comics existingComic = comicsRepository.findById(comicId)
				.orElseThrow(() -> new ResourceNotFoundException("Comic not find with id :" + comicId));
		existingComic.setComicTitle(comics.getComicTitle());
		existingComic.setComicPublishedYear(comics.getComicPublishedYear());
		existingComic.setComicHero(comics.getComicHero());
		Comics updatedComic = comicsRepository.save(existingComic);

		logger.debug("Comic details updated successfully");
		return updatedComic;
	}

	@Override
	public String deleteComicById(int comicId) {
		logger.info("Deleting Comic detail");
		Comics comic = this.comicsRepository.findById(comicId)
				.orElseThrow(() -> new ResourceNotFoundException("Comic not find with id :" + comicId));
		comicsRepository.delete(comic);

		logger.debug("Comic details deleted successfully");
		return "Deleted Succesfully";
	}

	@Override
	public Comics addComic(ComicsRequest request) {
		logger.info("Assigning Comic to the respective Author");
		Author author = authorRepository.findById(request.author_id)
				.orElseThrow(() -> new ResourceNotFoundException("Comic not find with id :" + request.author_id));
		Comics comics = new Comics();
		comics.setComicTitle(request.comicTitle);
		comics.setComicPublishedYear(request.comicPublishedYear);
		comics.setComicHero(request.comicHero);
		comics.setAuthor(author);
		Comics comic = comicsRepository.save(comics);

		logger.debug("Comic assigned to author");
		return comic;
	}

	@Override
	public List<Comics> findComicsByHero(String comicHero) {
		logger.info("Fetching Comic by hero");
		List<Comics> comics = comicsRepository.findComicsByHero(comicHero);
		if (comics == null) {
			logger.info("Comics not found with hero :" + comicHero);
			logger.debug(new ResourceNotFoundException("Comics not found with hero :" + comicHero));
			throw new ResourceNotFoundException("Comics not found with hero :" + comicHero);
		}
		return comics;
	}

}
