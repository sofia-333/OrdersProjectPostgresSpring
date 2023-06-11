package ge.ibsu.demo.controllers;

import ge.ibsu.demo.utils.GeneralUtil;
import ge.ibsu.demo.dto.AddDish;
import ge.ibsu.demo.entities.Dish;
import ge.ibsu.demo.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "api/dishes")
@PreAuthorize("hasRole('ADMIN')")
public class DishController {

    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Dish> getAll() {
        return dishService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Dish getById(@PathVariable Long id) throws Exception {
        return dishService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Dish add(@RequestBody AddDish addDish) throws Exception {
        GeneralUtil.checkRequiredProperties(addDish, Arrays.asList("name", "estimateTimeToPrepare", "price"));
        return dishService.add(addDish);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Dish edit(@PathVariable Long id, @RequestBody AddDish addDish) throws Exception {
        GeneralUtil.checkRequiredProperties(addDish, Arrays.asList("name", "estimateTimeToPrepare", "price"));
        return dishService.edit(id, addDish);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    public void edit(@PathVariable Long id) throws Exception {
        dishService.delete(id);
    }

}
