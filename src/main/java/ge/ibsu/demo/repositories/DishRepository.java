package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.Dish;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    @Query(value = "select * from dish where name like :searchText",
            countQuery = "select * from dish where name like :searchText",
            nativeQuery = true)
    Slice<Dish> search(@Param("searchText") String searchText, Pageable pageable);

}
