package supermarket.products.control;

import org.springframework.data.repository.PagingAndSortingRepository;

import supermarket.products.entity.ProductCategoryType;

public interface ProductCategoryTypeRepository extends PagingAndSortingRepository<ProductCategoryType, Long> {

}