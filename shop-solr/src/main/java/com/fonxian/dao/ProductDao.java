package com.fonxian.dao;

import com.fonxian.model.ResultModel;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * <p>
 * description
 * </p >
 *
 * @author Michael Fang
 * @since 2019-03-23
 */
public interface ProductDao {

    ResultModel queryProducts(SolrQuery solrQuery) throws Exception;


}
