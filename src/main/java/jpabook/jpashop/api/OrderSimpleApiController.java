package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    
    private final OrderRepository orderRepository;
    
    @GetMapping("/api/v2/simple-orders")
    public Result orderV2(){
        List<Order> orders =  orderRepository.findAll();
        List<SimpleOrderDto> result = orders.stream()
                .map(o ->new SimpleOrderDto(o))
                .collect(toList());

        return new Result(result);
    }

    @GetMapping("/api/v3/simple-orders")
    public Result ordersV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result =orders.stream()
                .map(o-> new SimpleOrderDto(o))
                .collect(toList());
        return new Result(result);
    }

    @Data
    @AllArgsConstructor
    class Result<T>{
        private T data;
    }
    @Data
    static class SimpleOrderDto{

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }
}
