package com.fonxian.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * description
 * </p >
 *
 * @author Michael Fang
 * @since 2019-03-21
 */
public class SolrTest {

    //连接Solr服务端
    private static HttpSolrClient client = new HttpSolrClient.Builder("http://cheng1:8889/solr/mydata_shard").withConnectionTimeout(10000).build();


    @Test
    public void testAdd() {
        // 创建文档doc
        SolrInputDocument doc = new SolrInputDocument();

        // 添加内容
        doc.addField("id", "test002");
        doc.addField("pd_name", "美国短尾猫");
        doc.addField("pd_price", "2000.5");
        doc.addField("pd_description", "一只美国短尾猫");

        // 添加到client，并且提交(commit)
        try {
            client.add(doc);
            client.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() throws IOException, SolrServerException {

//        client.deleteById("test002");
        client.deleteByQuery("pd_name:短尾");
        client.commit();

    }

    @Test
    public void testQuery() throws IOException, SolrServerException {

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("pd_name:天下");
        //设置过滤查询
        solrQuery.setFilterQueries("pd_price:[1 TO 1000]");
        //设置排序
        solrQuery.setSort("pd_price", SolrQuery.ORDER.asc);
        //设置起始条数、行数
//        solrQuery.setStart(20);
//        solrQuery.setRows(30);
        //设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("pd_name");
        solrQuery.setHighlightSimplePre("<em>");
        solrQuery.setHighlightSimplePost("</em>");

        QueryResponse queryResponse = client.query(solrQuery);
        SolrDocumentList results = queryResponse.getResults();

        //获取总共查找个数
        System.out.println(results.getNumFound());
        for (SolrDocument doc : results) {
            System.out.println("----------------------");
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            List<String> list = null;
            if (highlighting.get(doc.get("id")) != null) {
                list = highlighting.get(doc.get("id")).get("pd_name");
            }
            if (list != null && list.size() > 0) {
                System.out.println(list.get(0));
            }

            System.out.println(doc.get("pd_name"));
            System.out.println(doc.get("pd_price"));
            System.out.println("----------------------");
        }

    }


}
