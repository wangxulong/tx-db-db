package com.wang.tx.txdbdb.web;

import com.wang.tx.txdbdb.domain.Order;
import com.wang.tx.txdbdb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by wxl on 2018/9/22.
 *
 * @author wxl
 */
@RestController
@RequestMapping("/api/customer")
public class CustomApi {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "add")
    public String createOrder(Order order) {
        customerService.createOrder(order);
        return "OK";
    }

    @RequestMapping(value = "userInfo")
    public Map getUserInfo(String userId) {
        return customerService.userInfo(userId);
    }

}
