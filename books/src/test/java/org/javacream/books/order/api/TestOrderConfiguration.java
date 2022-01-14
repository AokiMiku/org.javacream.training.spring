package org.javacream.books.order.api;

import org.javacream.books.order.api.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("test")
public class TestOrderConfiguration {
    @Bean
    @Qualifier("orders")
    public Map<Long, Order> orders(){
        Map<Long, Order> orders = new HashMap<>();
        Order order1 = new Order(1001l, "Isbn1001", 1, 19.99, "meier", Order.OrderStatus.OK);
        Order order2 = new Order(1002l, "Isbn1002", 1, 9.99, "meier", Order.OrderStatus.OK);
        Order order3 = new Order(1003l, "Isbn1003", 1, 39.99, "metzger", Order.OrderStatus.PENDING);
        Order order4 = new Order(1004l, "Isbn1001", 1, 0d, "meier", Order.OrderStatus.UNAVAILABLE);
        Order order5 = new Order(1005l, "Isbn1002", 1, 444.99, "schneider", Order.OrderStatus.PENDING);
        Order order6 = new Order(1006l, "Isbn1002", 1, 0d, "metzger", Order.OrderStatus.UNAVAILABLE);
        orders.put(order1.getOrderId(), order1);
        orders.put(order2.getOrderId(), order2);
        orders.put(order3.getOrderId(), order3);
        orders.put(order4.getOrderId(), order4);
        orders.put(order5.getOrderId(), order5);
        orders.put(order6.getOrderId(), order6);
        return orders;
    }

}
