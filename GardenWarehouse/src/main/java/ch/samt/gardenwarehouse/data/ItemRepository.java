package ch.samt.gardenwarehouse.data;

import ch.samt.gardenwarehouse.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByCode(String code);
}
