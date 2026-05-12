package ch.samt.Customers.data;

import ch.samt.Customers.model.MealGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealGroupRepository extends JpaRepository<MealGroup, Long> {
}
