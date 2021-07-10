package ru.geekbrains.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name="getProductsByBuyerId",
                query = "select distinct prd " +
                        "from Order ord " +
                        "join OrderItem oit on oit.order = ord " +
                        "join Product prd on prd = oit.product "+
                        "where ord.buyer.id = :id"),
        @NamedQuery(name="getBuyersByProductId",
                query = "select distinct buy " +
                        "from Order ord " +
                        "join OrderItem oit on oit.order = ord " +
                        "join Buyer buy on ord.buyer = buy " +
                        "where oit.product.id = :id"),
        @NamedQuery(name="allOrders", query = "select o from Order o")
})
public class Order implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Buyer buyer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private List<OrderItem> orderItems;

    public Order(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
