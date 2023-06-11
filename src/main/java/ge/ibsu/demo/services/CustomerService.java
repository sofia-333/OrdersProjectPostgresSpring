package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.CustomerRepository;
import ge.ibsu.demo.utils.GeneralUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer add(AddCustomer addCustomer) throws Exception {
        Customer customer = new Customer();
        GeneralUtil.getCopyOf(addCustomer, customer);

        return customerRepository.save(customer);
    }

    public Customer getById(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CUSTOMER_NOT_FOUND"));
    }


    @Transactional
    public Customer edit(Long id, AddCustomer addCustomer) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CUSTOMER_NOT_FOUND"));
        //update customer data
        GeneralUtil.getCopyOf(addCustomer, customer);
        return customerRepository.save(customer);
    }


}
