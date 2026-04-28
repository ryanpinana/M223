package ch.samt.gardenwarehouse.service;

import ch.samt.gardenwarehouse.data.ItemRepository;
import ch.samt.gardenwarehouse.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public List<Item> findByCode(String code){
        return itemRepository.findByCode(code);
    }

    public List<Item> sellItem(String code){
        List<Item> items = itemRepository.findByCode(code);
        for(Item item : items){
            item.itemCount -= 1;
            itemRepository.save(item);
        }
        return itemRepository.findByCode(code);
    }

    public List<Item> addItem(String code, Integer number){
        List<Item> items = itemRepository.findByCode(code);
        for(Item item : items){
            item.itemCount += number;
            itemRepository.save(item);
        }
        return itemRepository.findByCode(code);
    }

    public void saveItem(Item item){
        itemRepository.save(item);
    }
}
