package com.fonxian.controller;

import com.fonxian.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * description
 * </p >
 *
 * @author Michael Fang
 * @since 2019-03-22
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

}
