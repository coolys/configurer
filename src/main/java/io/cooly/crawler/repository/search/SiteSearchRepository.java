package io.cooly.crawler.repository.search;

import io.cooly.crawler.domain.Site;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Site entity.
 */
public interface SiteSearchRepository extends ElasticsearchRepository<Site, String> {
}
