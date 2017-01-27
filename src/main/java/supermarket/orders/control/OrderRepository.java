package supermarket.orders.control;

import org.springframework.data.repository.PagingAndSortingRepository;

import supermarket.orders.entity.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

}
