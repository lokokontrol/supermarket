package supermarket.products.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import supermarket.prices.Price;

@Entity
@Indexed
@Data
@Builder
@AllArgsConstructor

// Para que compare los objeos por el campo id.
@EqualsAndHashCode(of = { "id" })

@AnalyzerDef(name = "Analyzer_Products", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
	@TokenFilterDef(factory = LowerCaseFilterFactory.class),
	@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
		@Parameter(name = "language", value = "Spanish") }) })

@Analyzer
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    private Long id;

    @Field
    @Analyzer(definition = "Analyzer_Products")
    private String name;

    @Embedded
    private Price price;

    @Field
    private String offerName;

    @ManyToOne(cascade = { CascadeType.PERSIST })
    private ProductCategoryType category;

    public Product() {

    }

}