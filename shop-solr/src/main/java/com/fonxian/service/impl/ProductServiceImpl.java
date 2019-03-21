package com.fonxian.service.impl;

import com.fonxian.model.ResultModel;
import com.fonxian.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * description
 * </p >
 *
 * @author Michael Fang
 * @since 2019-03-22
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public ResultModel query(String queryString, String catalog_name, String price, String sort, Integer page) throws Exception {
        return null;
    }

}
