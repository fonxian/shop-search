package com.fonxian.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * description
 * </p >
 *
 * @author Michael Fang
 * @since 2019-03-23
 */
@Configuration
public class SolrServerConfig {

    @Bean
    public SolrClient buildSolrClient() {
        return new HttpSolrClient.Builder("http://cheng1:8889/solr/mydata_shard").withConnectionTimeout(10000).build();
    }


}
