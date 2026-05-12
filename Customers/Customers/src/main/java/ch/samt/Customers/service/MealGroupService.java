package ch.samt.Customers.service;

import ch.samt.Customers.data.MealGroupRepository;
import ch.samt.Customers.model.MealGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealGroupService {
    private MealGroupRepository mealGroupRepository;

    @Autowired
    public MealGroupService(MealGroupRepository mealGroupRepository) {
        this.mealGroupRepository = mealGroupRepository;
    }

    public List<MealGroup> findAll() {
        return mealGroupRepository.findAll();
    }

    public MealGroup findById(Long id) {
        return mealGroupRepository.findById(id).get();
    }
}
