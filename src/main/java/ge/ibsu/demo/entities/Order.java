package ge.ibsu.demo.entities;

import ge.ibsu.demo.entities.enums.OrderStatus;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SHIPPING_PRICE")
    private Double shippingPrice;

    @Column(name = "FULL_PRICE")
    private Double fullPrice;

    @Column(name = "ADDRESS")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVOICE_ID")
    private Invoice invoice;

    @Column(name = "DELIVERY_TIME")
    private Integer deliveryTime;

    @Column(name = "FULL_TIME")
    private Integer fullTime;

    @ManyToMany
    @JoinTable(
            name = "ORDER_DISH",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "DISH_ID")
    )
    private List<Dish> dishes = new ArrayList<Dish>();

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private OrderStatus status = OrderStatus.valueOf("PREPARING");



    public Order() {
        this.dishes = new ArrayList<Dish>();
    }

    public Order(Long id, Double shippingPrice, String address, Customer customer, Integer deliveryTime, List<Dish> dishes, OrderStatus status) {
        this.id = id;
        this.shippingPrice = shippingPrice;
        this.address = address;
        this.customer = customer;
        this.deliveryTime = deliveryTime;
        this.dishes = dishes;
        this.status = status;
        this.calculateFullTime();
        this.calculateFullPrice();
    }

    private void calculateFullPrice() {
        Double fullPrice = 0.0;

        // Iterate through all the dishes in the order
        for (Dish dish : this.getDishes()) {
            fullPrice += dish.getPrice();
        }

        // Add the delivery time of the order
        fullPrice += getShippingPrice();

        this.fullPrice = fullPrice;
    }

    // Calculate the full time based on estimate time to prepare and delivery time
    private void calculateFullTime() {
        Integer fullTime = 0;

        // Iterate through all the dishes in the order
        for (Dish dish : this.getDishes()) {
            Integer estimateTimeToPrepare = dish.getEstimateTimeToPrepare();
            fullTime += estimateTimeToPrepare;
        }

        // Add the delivery time of the order
        fullTime += this.getDeliveryTime();

        this.fullTime = fullTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(Double shippingPrice) {
        this.shippingPrice = shippingPrice;
        this.calculateFullPrice();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
        this.calculateFullTime();
    }

    public Integer getFullTime() {
        return fullTime;
    }


    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
        this.calculateFullPrice();
        this.calculateFullTime();
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getFullPrice() {
        return fullPrice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
