package edu.ncst.mvcweb.controller;

import edu.ncst.mvcweb.dao.OrderItemDao;
import edu.ncst.mvcweb.entity.OrderItem;
import edu.ncst.mvcweb.entity.ProductOrder;
import edu.ncst.mvcweb.service.OrderItemService;
import edu.ncst.mvcweb.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class ProductOrderController {
    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping(value = "/list")
    public Map<String, Object> list(int pageSize, int pageNumber) {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        PageRequest page = PageRequest.of(pageNumber, pageSize, Sort.by(order));
        return productOrderService.findByPage(page);
    }

    @GetMapping("/queryOrderById")
    public ProductOrder queryUserById(Integer id){
        return productOrderService.queryById(id);
    }

    @GetMapping("/findItemsByOrderId")
    public List<OrderItem> findItemsByOrderId(Integer id){
        if (id == null)
            return new ArrayList<>();
        return orderItemService.findByOrder(id);
    }
}
/*@RestController
@RequestMapping("/order")
public class ProductOrderController {
    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/list")
    public Map<String, Object> list(int pageSize,int pageNumber) {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"id");
        PageRequest page = PageRequest.of(pageNumber,pageSize,Sort.by(order));
        return productOrderService.findByPage(page);
    }

    @GetMapping("queryUserById")
    public ProductOrder queryUserById(Integer id) {
        return productOrderService.queryById(id);
    }

    @GetMapping("/queryItemsByOrderId")
    public List<OrderItem> queryItemsByOrderId(Integer id) {
        id(id==null)
            return new ArrayList<>();
        return orderItemService.findByOrder(id);
    }
}*/

