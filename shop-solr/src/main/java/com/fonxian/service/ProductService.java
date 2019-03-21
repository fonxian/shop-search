package com.fonxian.service;

import com.fonxian.model.ResultModel;

/**
 * <p>
 * description
 * </p >
 *
 * @author Michael Fang
 * @since 2019-03-22
 */
public interface ProductService {

    ResultModel query(String queryString, String catalog_name, String price,
                      String sort, Integer page) throws Exception;

}
