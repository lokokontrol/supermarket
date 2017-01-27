package supermarket.products.control;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import supermarket.products.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    // Sin JOIN
    // @Query("SELECT P.name , C.category FROM Product P, ProductCategoryType C
    // WHERE P.category = C.id")

    // Con Join
    @Query("SELECT P.name , C.category FROM Product P JOIN P.category C WHERE P.category = C.id")
    public List<Object> personalQuery();

}