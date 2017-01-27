package supermarket.products.boundary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import supermarket.products.entity.Product;

@Repository
@Transactional
public class ProductSearch {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    // Spring will inject here the entity manager object
    @PersistenceContext
    private EntityManager entityManager;

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    /**
     * A basic search for the entity User. The search is done by exact match per
     * keywords on fields name, city and email.
     * 
     * @param text
     *            The query text.
     */
    public List<Product> search(String text) {

	FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
		.getFullTextEntityManager(entityManager);
	// get the full text entity manager

	// create the query using Hibernate Search query DSL QueryBuilder
	QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
		.forEntity(Product.class).get();

	// a very basic query by keywords Query query =
	Query query = queryBuilder.keyword().onField("name").matching(text).createQuery();

	// wrap Lucene query in an Hibernate Query object
	FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Product.class);

	// execute search and return results (sorted by relevance as default)
	@SuppressWarnings("unchecked")
	List<Product> results = jpaQuery.getResultList();

	return results;
    } // method search

} // class UserSearch
