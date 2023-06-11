package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.dto.EditInvoice;
import ge.ibsu.demo.dto.GenerateInvoice;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.entities.Invoice;
import ge.ibsu.demo.entities.Order;
import ge.ibsu.demo.entities.enums.InvoiceStatus;
import ge.ibsu.demo.exceptions.PreConditionException;
import ge.ibsu.demo.repositories.InvoiceRepository;
import ge.ibsu.demo.repositories.OrderRepository;
import ge.ibsu.demo.utils.GeneralUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    @Transactional
    public Invoice generateInvoicesForCustomer(GenerateInvoice generateInvoice) throws Exception {
        // Generate invoices for the eligible orders
        Invoice invoice = new Invoice();
        Customer customer = customerService.getById(generateInvoice.getCustomerId());
        System.out.println(customer);
        invoice.setCustomer(customer);
        // Retrieve orders for the customer without invoices or with cancelled invoices
        List<Order> orders = orderRepository.findByCustomerAndInvoiceIsNullOrInvoiceStatusEquals(customer, InvoiceStatus.CANCELLED);
        if (orders.isEmpty()) {
            throw new PreConditionException("Customer has no orders to generate invoice");
        }
        System.out.println(orders);
//        invoice.setOrders(orders);
        double fullAmount = 0.0;
        for (Order order : orders) {
            order.setInvoice(invoice);
            fullAmount += order.getFullPrice();
        }
        invoice.setAmount(fullAmount);
        invoice.setStatus(InvoiceStatus.valueOf("UNPAID"));
        System.out.println(invoice);
        invoiceRepository.save(invoice);
        for (Order order : orders) {
            order.setInvoice(invoice);
            orderRepository.save(order);
        }
        return invoice;
    }

    @Transactional
    public Invoice edit(Long id, EditInvoice editInvoice) throws Exception {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("INVOICE_NOT_FOUND"));
        invoice.setStatus(editInvoice.getStatus());
        return invoiceRepository.save(invoice);
    }
}
