package io.cooly.crawler.service.impl;

import io.cooly.crawler.domain.Site;
import io.cooly.crawler.repository.SiteRepository;
import io.cooly.crawler.repository.search.SiteSearchRepository;
import io.cooly.crawler.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing Site.
 */
@Service
public class SiteServiceImpl implements SiteService {

    private final Logger log = LoggerFactory.getLogger(SiteServiceImpl.class);

    private final SiteRepository siteRepository;

    private final SiteSearchRepository siteSearchRepository;

    public SiteServiceImpl(SiteRepository siteRepository, SiteSearchRepository siteSearchRepository) {
        this.siteRepository = siteRepository;
        this.siteSearchRepository = siteSearchRepository;
    }

    /**
     * Save a site.
     *
     * @param site the entity to save
     * @return the persisted entity
     */
    @Override
    public Site save(Site site) {
        log.debug("Request to save Site : {}", site);
        Site result = siteRepository.save(site);
        siteSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the sites.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Site> findAll(Pageable pageable) {
        log.debug("Request to get all Sites");
        return siteRepository.findAll(pageable);
    }


    /**
     * Get one site by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Site> findOne(String id) {
        log.debug("Request to get Site : {}", id);
        return siteRepository.findById(id);
    }

    /**
     * Delete the site by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Site : {}", id);
        siteRepository.deleteById(id);
        siteSearchRepository.deleteById(id);
    }

    /**
     * Search for the site corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Site> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Sites for query {}", query);
        return siteSearchRepository.search(queryStringQuery(query), pageable);    }
}
