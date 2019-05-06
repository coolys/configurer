package io.cooly.crawler.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Site.
 */
@Document(collection = "site")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "site")
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @Field("url")
    private String url;

    @Field("domain")
    private String domain;

    // coolybot-needle-entity-add-field - Coolybot will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public Site url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public Site domain(String domain) {
        this.domain = domain;
        return this;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
    // coolybot-needle-entity-add-getters-setters - Coolybot will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Site site = (Site) o;
        if (site.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), site.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Site{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", domain='" + getDomain() + "'" +
            "}";
    }
}
