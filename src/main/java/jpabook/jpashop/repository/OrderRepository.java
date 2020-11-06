package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        //... 검색 로직
        return null;
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o",Order.class)
                .getResultList();
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery(
                "select o from Order o"+
                    " join fetch o.member m"+
                    " join fetch o.delivery d", Order.class)
                    .getResultList();

    }

    public List<Order> findAllWithMemberDelivery(int offset,int limit) {
        return em.createQuery(
                "select o from Order o"+
                        " join fetch o.member m"+
                        " join fetch o.delivery d", Order.class)
                .setFirstResult(offset)
                .setFirstResult(limit)
                .getResultList();

    }

    public List<Order> findAllWithItem() {
        return em.createQuery("select  distinct o from Order o"+
                " join fetch o.member m"+
                " join fetch o.delivery d"+
                " join fetch o.orderItems oi"+
                " join fetch oi.item i", Order.class)
                .getResultList();
    }
}
