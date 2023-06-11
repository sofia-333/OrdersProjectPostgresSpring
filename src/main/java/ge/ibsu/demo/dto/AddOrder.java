package ge.ibsu.demo.dto;

import ge.ibsu.demo.entities.Dish;
import ge.ibsu.demo.entities.enums.OrderStatus;

import java.util.List;

public class AddOrder {

    private Long id;

    private Double shippingPrice;

    private String address;

    private Long customerId;
    private Long invoiceId;

    private Integer deliveryTime;

    private List<Long> dishIds;


    public List<Long> getDishIds() {
        return dishIds;
    }
    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setDishIds(List<Long> dishIds) {
        this.dishIds = dishIds;
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
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
}
