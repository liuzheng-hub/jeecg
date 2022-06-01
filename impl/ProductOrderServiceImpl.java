package edu.ncst.mvcweb.service.impl;

import edu.ncst.mvcweb.dao.OrderItemDao;
import edu.ncst.mvcweb.dao.ProductOrderDao;
import edu.ncst.mvcweb.entity.OrderItem;
import edu.ncst.mvcweb.entity.ProductOrder;
import edu.ncst.mvcweb.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private ProductOrderDao productOrderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public Map<String, Object> findByPage(Pageable pageable) {
        Page<ProductOrder> page = productOrderDao.findAll(pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotalElements());
        map.put("rows", page.toList());
        return map;
    }

    @Override
    public ProductOrder queryById(Integer id) { return productOrderDao.findById(id).orElse(null); }

    @Override
    public ProductOrder save(ProductOrder order, List<OrderItem> orderItems) {
        productOrderDao.save(order);
        for (OrderItem item : orderItems) {
            orderItemDao.save(item);
        }
        return order;
    }
}
/*@Service
@Transactional
public class ProductOrderService implements ProductOrderDao {
    @Autowired
    private ProductOrderDao productOrderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public Map<String, Object> findByPage(Pageable pageable) {
        Page<ProductOrder> page = productOrderDao.findAll(pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("total",page.getItemElements());
        map.put("rows",page.toList());
        return map;
    }

    @Override
    public ProductOrder queryById(Integer id) {
        return productOrderDao.findById(id).orElse(null);

    }

    @Override
    public ProductOrder save(ProductOrder order, List<OrderItem> orderItems) {
        productOrderDao.save(order);
        for(OrderItem item: orderItems){
            OrderItemDao.save(item);
        }
        return order;
    }
}*/



