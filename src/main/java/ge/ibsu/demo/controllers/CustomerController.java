package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.services.CustomerService;
import ge.ibsu.demo.utils.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/customers")
@PreAuthorize("hasRole('ADMIN')")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Customer add(@RequestBody AddCustomer addCustomer) throws Exception {
        GeneralUtil.checkRequiredProperties(addCustomer, Arrays.asList("firstName", "lastName", "phoneNumber", "email", "address"));
        return customerService.add(addCustomer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Customer getById(@PathVariable Long id) throws Exception {
        return customerService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Customer edit(@PathVariable Long id, @RequestBody AddCustomer addCustomer) throws Exception {
        GeneralUtil.checkRequiredProperties(addCustomer, Arrays.asList("firstName", "lastName", "phoneNumber", "email", "address"));
        return customerService.edit(id, addCustomer);
    }

}
