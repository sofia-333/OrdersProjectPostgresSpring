package ge.ibsu.demo.services;

import ge.ibsu.demo.entities.enums.InvoiceStatus;
import ge.ibsu.demo.entities.enums.OrderStatus;
import ge.ibsu.demo.exceptions.PreConditionException;
import ge.ibsu.demo.utils.GeneralUtil;
import ge.ibsu.demo.dto.AddOrder;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.entities.Dish;
import ge.ibsu.demo.entities.Order;
import ge.ibsu.demo.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private DishService dishService;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Long id) throws Exception {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ORDER_NOT_FOUND"));
    }

    @Transactional
    public Order add(AddOrder addOrder) throws Exception {
        Order order = new Order();
        GeneralUtil.getCopyOf(addOrder, order);

        Customer customer = customerService.getById(addOrder.getCustomerId());
        order.setCustomer(customer);

        System.out.println(addOrder.getDishIds());
        List<Dish> dishes = dishService.getByIds(addOrder.getDishIds());
        order.setDishes(dishes);

        //set customer address if order has no address
        if (addOrder.getAddress() == null) {
            order.setAddress(customer.getAddress());
        }

        return orderRepository.save(order);
    }


    @Transactional
    public Order edit(Long id, AddOrder addOrder) throws Exception {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ORDER_NOT_FOUND"));

        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.DELIVERED) {
            throw new PreConditionException("Can't update order with statuses DELIVERED or CANCELLED");
        }

        if (order.getInvoice() != null && order.getInvoice().getStatus() != InvoiceStatus.CANCELLED) {
            throw new PreConditionException("Can't update order with invoices. Cancel invoice to edit the order.");
        }

        //update order data
        GeneralUtil.getCopyOf(addOrder, order);
        if (addOrder.getCustomerId() != null) {
            Customer customer = customerService.getById(addOrder.getCustomerId());
            if (!addOrder.getCustomerId().equals(order.getCustomer().getCustomerId())) {
                order.setCustomer(customer);
            }
            //set customer address if order has no address
            if (addOrder.getAddress() == null) {
                order.setAddress(customer.getAddress());
            }
        }
        List<Dish> dishes = dishService.getByIds(addOrder.getDishIds());
        order.setDishes(dishes);

        return orderRepository.save(order);
    }



}
