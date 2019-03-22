package com.fonxian.controller;

import com.fonxian.model.ResultModel;
import com.fonxian.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping({"", "index", "list"})
    public ModelAndView list(@RequestParam(required = false, defaultValue = "") String queryString,
                             @RequestParam(required = false, defaultValue = "") String catalog_name,
                             @RequestParam(required = false, defaultValue = "") String price,
                             @RequestParam(required = false, defaultValue = "") String sort,
                             @RequestParam(required = false, defaultValue = "1") Integer page, Model model) throws Exception {

        ResultModel result = productService.query(queryString, catalog_name, price, sort, page);

        ModelAndView view = new ModelAndView();
        //返回查询结果
        view.addObject("result", result);
        view.addObject("queryString", queryString);
        view.addObject("catalog_name", catalog_name);
        view.addObject("price", price);
        view.addObject("sort", sort);

        view.setViewName("product");

        return view;
    }

}
