package ru.geekbrains.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buyers")
@NamedQueries({
        @NamedQuery(name = "allBuyers",query = "select b from Buyer b")
})
public class Buyer implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "buyer")
    private List<Order> orders;

    public Buyer() {
    }

    public Buyer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getPurchases() {
        return orders;
    }

    public void setPurchases(List<Order> orders) {
        this.orders = orders;
    }
}
