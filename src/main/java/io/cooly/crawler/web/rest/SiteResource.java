package io.cooly.crawler.web.rest;

import io.cooly.crawler.domain.Site;
import io.cooly.crawler.service.SiteService;
import io.cooly.crawler.web.rest.errors.BadRequestAlertException;
import io.cooly.crawler.web.rest.util.HeaderUtil;
import io.cooly.crawler.web.rest.util.PaginationUtil;
import io.github.coolys.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Site.
 */
@RestController
@RequestMapping("/api")
public class SiteResource {

    private final Logger log = LoggerFactory.getLogger(SiteResource.class);

    private static final String ENTITY_NAME = "configurerSite";

    private final SiteService siteService;

    public SiteResource(SiteService siteService) {
        this.siteService = siteService;
    }

    /**
     * POST  /sites : Create a new site.
     *
     * @param site the site to create
     * @return the ResponseEntity with status 201 (Created) and with body the new site, or with status 400 (Bad Request) if the site has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sites")
    public ResponseEntity<Site> createSite(@RequestBody Site site) throws URISyntaxException {
        log.debug("REST request to save Site : {}", site);
        if (site.getId() != null) {
            throw new BadRequestAlertException("A new site cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Site result = siteService.save(site);
        return ResponseEntity.created(new URI("/api/sites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sites : Updates an existing site.
     *
     * @param site the site to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated site,
     * or with status 400 (Bad Request) if the site is not valid,
     * or with status 500 (Internal Server Error) if the site couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sites")
    public ResponseEntity<Site> updateSite(@RequestBody Site site) throws URISyntaxException {
        log.debug("REST request to update Site : {}", site);
        if (site.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Site result = siteService.save(site);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, site.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sites : get all the sites.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sites in body
     */
    @GetMapping("/sites")
    public ResponseEntity<List<Site>> getAllSites(Pageable pageable) {
        log.debug("REST request to get a page of Sites");
        Page<Site> page = siteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sites");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /sites/:id : get the "id" site.
     *
     * @param id the id of the site to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the site, or with status 404 (Not Found)
     */
    @GetMapping("/sites/{id}")
    public ResponseEntity<Site> getSite(@PathVariable String id) {
        log.debug("REST request to get Site : {}", id);
        Optional<Site> site = siteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(site);
    }

    /**
     * DELETE  /sites/:id : delete the "id" site.
     *
     * @param id the id of the site to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sites/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable String id) {
        log.debug("REST request to delete Site : {}", id);
        siteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    /**
     * SEARCH  /_search/sites?query=:query : search for the site corresponding
     * to the query.
     *
     * @param query the query of the site search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/sites")
    public ResponseEntity<List<Site>> searchSites(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Sites for query {}", query);
        Page<Site> page = siteService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/sites");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
