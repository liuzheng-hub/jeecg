package edu.ncst.mvcweb.controller;

import edu.ncst.mvcweb.entity.Product;
import edu.ncst.mvcweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /*@GetMapping(value = "/getOne")
    public Product getProduct(String productName) {
        return productService.findByProductName(productName);
    }*/

    @GetMapping(value = "/list")
    public Map<String, Object> list(int pageSize, int pageNumber) {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        PageRequest page = PageRequest.of(pageNumber, pageSize, Sort.by(order));
        return productService.findByPage(page);
    }

    @GetMapping(value = "/findAll")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping("deleteByList")
    public Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList){
        productService.delete(idList);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status","success");
        return map;
    }

    @GetMapping("queryProductById")
    public Product queryProductById(Integer id){
        Product product = productService.queryById(id);
        return product;
    }

    @PostMapping("save")
    public Map<String,Object> save(Product product){
        productService.save(product);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status", "success");
        return map;
    }
}