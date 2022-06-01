package edu.ncst.mvcweb.controller;

import edu.ncst.mvcweb.entity.OrderItem;
import edu.ncst.mvcweb.entity.Product;
import edu.ncst.mvcweb.entity.ProductOrder;
import edu.ncst.mvcweb.entity.User;
import edu.ncst.mvcweb.pojo.ShopCartItem;
import edu.ncst.mvcweb.service.ProductOrderService;
import edu.ncst.mvcweb.service.ProductService;
import edu.ncst.mvcweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/shopcart")
public class ShopCartController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductOrderService productOrderService;
    @Autowired
    UserService userService;
    @GetMapping(value = "/list")
    public List<ShopCartItem> list(HttpSession session) {
        return getShopCart(session);
    }


    @PostMapping("/order")
    public Map<String,Object> save(Integer userId, HttpSession session){
        Map<String,Object> map = new HashMap<String,Object>();
        User user = userService.queryById(userId);
        List<ShopCartItem> shopCartItems = getShopCart(session);
        if (shopCartItems.size() == 0) {
            map.put("status", "fail");
            map.put("message", "购物车中没有商品");
        }
        else {
            ProductOrder order = new ProductOrder();
            order.setOrderUser(user);
            order.setOrderTime(new Date());
            float total = 0.0f;
            List<OrderItem> items = new ArrayList<>();
            for (ShopCartItem item : shopCartItems) {
                Product p = productService.queryById(item.getProductId());
                if (p == null)
                    continue;

                OrderItem oi = new OrderItem();
                oi.setOrder(order);
                oi.setProduct(p);
                oi.setNumber(item.getNumber());
                oi.setPrice(p.getPrice());
                total += oi.getPrice().floatValue() * oi.getNumber().floatValue();
                items.add(oi);
            }
            order.setTotalMoney(total);
            productOrderService.save(order, items);
            shopCartItems.clear();
            map.put("status", "success");
        }
        return map;
    }

    @PostMapping("deleteByList")
    public Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList, HttpSession session){

        List<ShopCartItem> shopCartItems = getShopCart(session);
        for (Integer id : idList) {
            for (int i = 0; i < shopCartItems.size(); i++) {
                if (id.equals(shopCartItems.get(i).getProductId())) {
                    shopCartItems.remove(i);
                    break;
                }
            }
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status","success");
        return map;
    }

    @PostMapping("add")
    public Map<String,Object> save(@RequestParam("idList[]") List<Integer> idList, HttpSession session){
        for (Integer id : idList) {
            boolean a = false;
            Product product = productService.queryById(id);
            if (product != null) {
                List<ShopCartItem> shopCartItems = getShopCart(session);
                for (ShopCartItem shopCartItem : shopCartItems) {
                    if(shopCartItem.getProductId().equals(product.getId())) {
                        shopCartItem.setNumber(shopCartItem.getNumber()+1);
                        a = true;
                    }
                }
                if(!a){
                    ShopCartItem item = new ShopCartItem();
                    item.setProductId(product.getId());
                    item.setProductName(product.getProductName());
                    item.setPrice(product.getPrice());
                    item.setNumber(1);
                    shopCartItems.add(item);
                }

            }
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status", "success");
        return map;
    }

    private List<ShopCartItem> getShopCart(HttpSession session) {
        List<ShopCartItem> result = null;
        if (session.getAttribute("shopcart") == null) {
            result = new ArrayList<>();
            session.setAttribute("shopcart", result);
        }
        else
            result = (List<ShopCartItem>)session.getAttribute("shopcart");
        return result;
    }
}