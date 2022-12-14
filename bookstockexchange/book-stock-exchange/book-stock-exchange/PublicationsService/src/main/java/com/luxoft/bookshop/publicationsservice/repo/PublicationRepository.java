package com.luxoft.bookshop.publicationsservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxoft.bookshop.publicationsservice.entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {

}
