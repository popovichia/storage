package ru.popovichia.alfa.services;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.popovichia.alfa.data.Item;
import ru.popovichia.alfa.repositories.ItemsRepository;

/**
 *
 * @author Igor Popovich, email: popovichia, phone: +7 913 902 36 36, company:
 * popovichia@gmail.com
 */
@Service
public class ItemsRepositoryService {

    private static Logger logger = LogManager.getLogger(ItemsRepositoryService.class);
    
    @Autowired
    private ItemsRepository itemsRepository;
    
    public boolean saveItem(Item item) {
        
        boolean result = false;
        
        if (item != null) {
            
            itemsRepository.save(item);
            result = true;
            
            logger.info("Item data saved: " + item);
            
        }
        
        return result;
        
    }
    
    public List<Integer> findItemsByBoxesIdsAndItemColor(List<Integer> listBoxesIds, String color) {
        return itemsRepository.findItemsByBoxesIdsAndItemColor(listBoxesIds, color);
    }
    
}
