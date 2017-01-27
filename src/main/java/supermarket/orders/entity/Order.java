package supermarket.orders.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST })
    List<OrderLine> orderLines;

    String idBill;

    String customer;

    public Order(ArrayList<OrderLine> lines) {
	this.orderLines = lines;
    }

    public Order() {

    }

    public Order(String idBill, String customer) {
	this.idBill = idBill;
	this.customer = customer;
	orderLines = new ArrayList<OrderLine>();
    }
}
