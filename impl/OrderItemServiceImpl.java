package edu.ncst.mvcweb.service.impl;

import edu.ncst.mvcweb.dao.OrderItemDao;
import edu.ncst.mvcweb.dao.ProductOrderDao;
import edu.ncst.mvcweb.entity.OrderItem;
import edu.ncst.mvcweb.entity.ProductOrder;
import edu.ncst.mvcweb.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private ProductOrderDao productOrderDao;

    @Override
    public List<OrderItem> findAll() {
        return orderItemDao.findAll();
    }

    @Override
    public List<OrderItem> findByOrder(Integer orderId) {
        ProductOrder o = productOrderDao.findById(orderId).orElse(null);
        return orderItemDao.findOrderItemsByOrder(o);
    }

    @Override
    public OrderItem save(OrderItem orderItem) { return orderItemDao.save(orderItem); }
}
