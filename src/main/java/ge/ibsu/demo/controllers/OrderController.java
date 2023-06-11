package ge.ibsu.demo.controllers;

import ge.ibsu.demo.utils.GeneralUtil;
import ge.ibsu.demo.dto.AddOrder;
import ge.ibsu.demo.entities.Order;
import ge.ibsu.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "api/orders")
@PreAuthorize("hasRole('ADMIN')")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Order getById(@PathVariable Long id) throws Exception {
        return orderService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Order add(@RequestBody AddOrder addOrder) throws Exception {
        GeneralUtil.checkRequiredProperties(addOrder, Arrays.asList("customerId", "shippingPrice", "deliveryTime", "shippingPrice" ));

        return orderService.add(addOrder);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Order edit(@PathVariable Long id, @RequestBody AddOrder addOrder) throws Exception {
        GeneralUtil.checkRequiredProperties(addOrder, Arrays.asList("customerId", "shippingPrice", "deliveryTime", "shippingPrice"));
        return orderService.edit(id, addOrder);
    }


}
