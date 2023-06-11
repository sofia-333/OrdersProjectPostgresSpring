package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.entities.Dish;
import ge.ibsu.demo.entities.Order;
import ge.ibsu.demo.entities.enums.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerAndInvoiceIsNullOrInvoiceStatusEquals(Customer customer, InvoiceStatus invoiceStatus);

    boolean existsByDishesAndInvoiceStatusNot(Dish dish, InvoiceStatus invoiceStatus);
}
