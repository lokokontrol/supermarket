package supermarket.orders.control;

import org.springframework.data.repository.PagingAndSortingRepository;

import supermarket.orders.entity.OrderLine;

public interface OrderLineRepository extends PagingAndSortingRepository<OrderLine, Long> {

}
