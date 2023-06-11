package ge.ibsu.demo.dto;

import jakarta.persistence.Column;

public class AddDish {
    private Long dishId;

    private String name;

    private String description;

    private Integer estimateTimeToPrepare;
    private Double price;

    public Long getDishId() {
        return dishId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
