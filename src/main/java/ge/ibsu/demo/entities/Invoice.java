package ge.ibsu.demo.entities;

import ge.ibsu.demo.entities.enums.InvoiceStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "INVOICES")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INVOICE_ID")
    private Long invoiceId;

    @Column(name = "AMOUNT")
    private Double amount;

//    @OneToMany()
//    @JoinColumn(name = "INVOICE_ID")
//    private List<Order> orders;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private InvoiceStatus status = InvoiceStatus.UNPAID;

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }
}
