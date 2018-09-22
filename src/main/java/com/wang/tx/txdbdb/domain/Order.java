package com.wang.tx.txdbdb.domain;

import lombok.Data;

/**
 * Created by wxl on 2018/9/22.
 *
 * @author wxl
 */
@Data
public class Order {
    private Long id;
    private Long customerId;
    private String title;
    private Integer amount;
}
