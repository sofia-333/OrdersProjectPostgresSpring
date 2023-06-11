package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.AddDish;
import ge.ibsu.demo.entities.Dish;
import ge.ibsu.demo.entities.enums.InvoiceStatus;
import ge.ibsu.demo.repositories.DishRepository;
import ge.ibsu.demo.repositories.OrderRepository;
import ge.ibsu.demo.utils.GeneralUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public Dish getById(Long id) throws Exception {
        return dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DISH_NOT_FOUND"));
    }


    public List<Dish> getByIds(List<Long> ids) throws Exception {
        return ids.stream()
                .map(id -> dishRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Transactional
    public Dish add(AddDish addDish) throws Exception {
        Dish dish = new Dish();
        GeneralUtil.getCopyOf(addDish, dish);

        return dishRepository.save(dish);
    }

    @Transactional
    public Dish edit(Long id, AddDish addDish) throws Exception {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DISH_NOT_FOUND"));
        GeneralUtil.getCopyOf(addDish, dish);
        return dishRepository.save(dish);
    }

    @Transactional
    public void delete(Long dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("DISH_NOT_FOUND"));

        // Check if the dish is associated with any non-cancelled orders
        boolean hasNonCancelledOrders = orderRepository.existsByDishesAndInvoiceStatusNot(dish, InvoiceStatus.CANCELLED);

        if (hasNonCancelledOrders) {
            throw new IllegalStateException("Cannot delete dish. It is associated with non-cancelled orders.");
        }

        dishRepository.delete(dish);
    }
}
