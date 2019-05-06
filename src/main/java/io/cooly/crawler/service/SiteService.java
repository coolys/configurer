package io.cooly.crawler.service;

import io.cooly.crawler.domain.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Site.
 */
public interface SiteService {

    /**
     * Save a site.
     *
     * @param site the entity to save
     * @return the persisted entity
     */
    Site save(Site site);

    /**
     * Get all the sites.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Site> findAll(Pageable pageable);


    /**
     * Get the "id" site.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Site> findOne(String id);

    /**
     * Delete the "id" site.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    /**
     * Search for the site corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Site> search(String query, Pageable pageable);
}
