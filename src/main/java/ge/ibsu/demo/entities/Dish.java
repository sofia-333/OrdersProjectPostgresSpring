package ge.ibsu.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "DISH")
public class Dish {

    @Id
    @SequenceGenerator(name = "DISH_SEQ", sequenceName = "DISH_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISH_SEQ")
    @Column(name = "DISH_ID")
    private Long dishId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ESTIMATE_TIME_TO_PREPARE")
    private Integer estimateTimeToPrepare;

    @Column(name = "PRICE")
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEstimateTimeToPrepare() {
        return estimateTimeToPrepare;
    }

    public void setEstimateTimeToPrepare(Integer estimateTimeToPrepare) {
        this.estimateTimeToPrepare = estimateTimeToPrepare;
    }

}
