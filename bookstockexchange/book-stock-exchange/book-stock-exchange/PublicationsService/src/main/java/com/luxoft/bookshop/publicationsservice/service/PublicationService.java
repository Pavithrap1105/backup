package com.luxoft.bookshop.publicationsservice.service;

import java.util.List;

import com.luxoft.bookshop.publicationsservice.entity.Publication;

public interface PublicationService {
	
	public Publication savePublication(Publication publication);
	
	public List<Publication> getAllPublications();
	
	public Publication updatePublicationById(Publication publication,int publicationId);
	
	public String deletePublicationById(int publicationId);

}
