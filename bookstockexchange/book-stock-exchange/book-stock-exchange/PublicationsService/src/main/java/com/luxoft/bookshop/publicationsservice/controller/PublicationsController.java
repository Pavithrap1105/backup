package com.luxoft.bookshop.publicationsservice.controller;

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

import com.luxoft.bookshop.publicationsservice.entity.Publication;
import com.luxoft.bookshop.publicationsservice.service.PublicationService;

@RestController
public class PublicationsController {

	private static Logger logger = LogManager.getLogger(PublicationsController.class);

	@Autowired
	private PublicationService publicationService;

	@PostMapping("/savePublication")
	public Publication savePublication(@Valid @RequestBody Publication publication) {
		logger.info("Saving Publication details");
		Publication savedPublication = publicationService.savePublication(publication);

		logger.debug("Saved Publication successfully");
		return savedPublication;
	}

	@GetMapping("/getAllPublications")
	public List<Publication> findAllPublications() {
		logger.info("Fetching All Publication details");
		List<Publication> publications = publicationService.getAllPublications();

		logger.debug("Publication details fetched successfully");
		return publications;
	}

	@PutMapping("/updatePublication/{id}")
	public Publication updatePublication(@Valid @RequestBody Publication publication,
			@PathVariable(name = "id") int publicationId) {
		logger.info("Updating Publication details");
		Publication updatedPublication = publicationService.updatePublicationById(publication, publicationId);
		
		logger.debug("Publication details updated successfully");
		return updatedPublication;
	}

	@DeleteMapping("/deletePublication/{id}")
	public String deletePublication(@Valid @PathVariable(name = "id") int publicationId) {
		logger.info("Deleting Publication details");
		String deleted = publicationService.deletePublicationById(publicationId);
		
		logger.debug("Publication details deleted successfully");
		return deleted;
	}
}
