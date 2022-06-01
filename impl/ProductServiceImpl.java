package edu.ncst.mvcweb.service.impl;

import edu.ncst.mvcweb.dao.ProductDao;
import edu.ncst.mvcweb.entity.Product;
import edu.ncst.mvcweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /*@Override
    public Product findByProductName(String productName) {
        return productDao.findByProductName(productName);
    }*/

    @Override
    public List<Product> findAll() {

        //return productDao.findAll();
        Iterable<Product> products = productDao.findAll();
        List<Product> list = new ArrayList<>();
        products.forEach(single->{list.add(single);});
        return list;
    }

    @Override
    public Map<String, Object> findByPage(Pageable pageable) {
        Page<Product> page = productDao.findAll(pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotalElements());
        map.put("rows", page.toList());
        return map;
    }

    @Override
    public void delete(Integer id) { productDao.deleteById(id);}

    @Override
    public void delete(List<Integer> idList) {
        for (Integer id : idList)
            productDao.deleteById(id);
    }

    @Override
    public Product queryById(Integer id) { return productDao.findById(id).orElse(null); }

    @Override
    public Product save(Product product) { return productDao.save(product); }


}