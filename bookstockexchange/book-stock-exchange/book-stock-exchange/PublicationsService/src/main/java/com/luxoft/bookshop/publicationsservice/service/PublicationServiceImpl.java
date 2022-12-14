package com.luxoft.bookshop.publicationsservice.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.stock.exchange.exception.ResourceNotFoundException;
import com.luxoft.bookshop.publicationsservice.entity.Publication;
import com.luxoft.bookshop.publicationsservice.repo.PublicationRepository;

@Service
public class PublicationServiceImpl implements PublicationService {

	private static Logger logger = LogManager.getLogger(PublicationServiceImpl.class);

	@Autowired
	private PublicationRepository publicationRepository;

	@Override
	public Publication savePublication(Publication publication) {
		Publication savedPublication = null;
		try {
			savedPublication = publicationRepository.save(publication);
			logger.debug("Publication saved successfully with id: " + savedPublication.getPublicationId());
		} catch (Exception ex) {
			logger.error(new Exception());
			ex.printStackTrace();
		}
		return savedPublication;
	}

	@Override
	public List<Publication> getAllPublications() {
		List<Publication> listOfPublications = publicationRepository.findAll();
		if (listOfPublications == null) {
			throw new ResourceNotFoundException("Publication not found");
		}
		logger.debug("Publication fetched successfully ");
		return listOfPublications;
	}

	@Override
	public Publication updatePublicationById(Publication publication, int publicationId) {
		Publication existingPublication = publicationRepository.findById(publicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Publication not found with id :" + publicationId));
		existingPublication.setPublisherName(publication.getPublisherName());
		existingPublication.setPublishingDate(publication.getPublishingDate());
		Publication updatedPublication = publicationRepository.save(existingPublication);
		logger.debug("Publication updated successfully by id " + publicationId);
		return updatedPublication;
	}

	@Override
	public String deletePublicationById(int publicationId) {
		Publication existingPublication = this.publicationRepository.findById(publicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Publication not found with id :" + publicationId));
		publicationRepository.delete(existingPublication);
		logger.debug("Publication deleted successfully with id " + publicationId);
		return "Deleted Succesfully";
	}

}
