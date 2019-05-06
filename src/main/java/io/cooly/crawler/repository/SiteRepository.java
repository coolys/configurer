package io.cooly.crawler.repository;

import io.cooly.crawler.domain.Site;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Site entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SiteRepository extends MongoRepository<Site, String> {

}
