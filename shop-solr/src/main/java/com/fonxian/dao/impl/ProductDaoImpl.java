package com.fonxian.dao.impl;

import com.fonxian.dao.ProductDao;
import com.fonxian.model.ProductModel;
import com.fonxian.model.ResultModel;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * description
 * </p >
 *
 * @author Michael Fang
 * @since 2019-03-23
 */
@Service
public class ProductDaoImpl implements ProductDao {


    @Autowired
    private SolrClient solrClient;

    @Override
    public ResultModel queryProducts(SolrQuery solrQuery) throws Exception {
        //查询并获取查询响应
        QueryResponse queryResponse = solrClient.query(solrQuery);
        //从响应中获取查询结果集
        SolrDocumentList docList = queryResponse.getResults();

        //创建返回结果对象
        ResultModel resultModel = new ResultModel();
        List<ProductModel> productList = new ArrayList<ProductModel>();

        //遍历结果集
        if (docList != null) {
            //获取总记录数
            resultModel.setRecordCount(docList.getNumFound());
            for (SolrDocument doc : docList) {
                ProductModel product = new ProductModel();
                product.setPid(String.valueOf(doc.get("id")));

                //获取高亮
                Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
                if (highlighting != null) {
                    List<String> list = highlighting.get(doc.get("id")).get("pd_name");
                    if (list != null && list.size() > 0) {
                        product.setName(list.get(0));
                    } else {
                        product.setName(String.valueOf(doc.get("pd_name")));
                    }
                } else {
                    product.setName(String.valueOf(doc.get("pd_name")));
                }

                if (doc.get("pd_price") != null && !"".equals(doc.get("pd_price"))) {
                    product.setPrice(Float.valueOf(doc.get("pd_price").toString()));
                }
                product.setCatalog_name(String.valueOf(doc.get("pd_catelog_name")));
                product.setPicture(String.valueOf(doc.get("pd_picture")));
                productList.add(product);
            }
            resultModel.setProductList(productList);
        }
        return resultModel;
    }


}
