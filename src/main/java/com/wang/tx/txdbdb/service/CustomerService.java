package com.wang.tx.txdbdb.service;

import com.wang.tx.txdbdb.domain.Customer;
import com.wang.tx.txdbdb.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wxl on 2018/9/22.
 *
 * @author wxl
 */
@Service
public class CustomerService {

    @Autowired
    @Qualifier("userJdbcTemplate")
    private JdbcTemplate userJdbcTemplate;
    @Autowired
    @Qualifier("orderJdbcTemplate")
    private JdbcTemplate orderJdbcTemplate;

    private static final String SQL_UPDATE_DEPOSIT = "update t_user set deposit = deposit- ? where id = ?";

    private static final String SQL_CREATE_ORDER = "insert into t_order (customer_id,title,amount) values(?,?,?)";

    @Transactional
    public void createOrder(Order order){
        userJdbcTemplate.update(SQL_UPDATE_DEPOSIT,order.getAmount(),order.getCustomerId());
        if(order.getTitle().contains("error1")){
            throw new RuntimeException("error1");
        }
        orderJdbcTemplate.update(SQL_CREATE_ORDER,order.getCustomerId(),order.getTitle(),order.getAmount());
        if(order.getTitle().contains("error2")){
            throw new RuntimeException("error2");
        }
    }


    public Map userInfo(String customerId){
        Map customer = null;
        try{
            customer= userJdbcTemplate.queryForMap("select * from t_user where id= "+customerId);
        }catch (EmptyResultDataAccessException e){

        }
        List orders = orderJdbcTemplate.queryForList("select * from t_order where customer_id ="+customerId);
        Map r = new HashMap();
        r.put("customer",customer);
        r.put("orders",orders);
        return r;
    }




}
