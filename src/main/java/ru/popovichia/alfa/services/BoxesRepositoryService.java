package ru.popovichia.alfa.services;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.popovichia.alfa.data.Box;
import ru.popovichia.alfa.repositories.BoxesRepository;

/**
 *
 * @author Igor Popovich, email: popovichia, phone: +7 913 902 36 36, company:
 * popovichia@gmail.com
 */
@Service
public class BoxesRepositoryService {
    
    private static Logger logger = LogManager.getLogger(BoxesRepositoryService.class);
    
    @Autowired
    private BoxesRepository boxesRepository;
    
    public boolean saveBox(Box box) {
        
        boolean result = false;
        
        if (box != null) {
            
            boxesRepository.save(box);
            result = true;
            
            logger.info("Box data saved: " + box);
            
        }
        
        return result;
        
    }
    
    public Box findById(int id) {
        return boxesRepository.findById(id);
    }
    
    public List<Integer> findListChildBoxesIdsByListParentBoxesIds(List<Integer>listParentBoxesIds) {
        
        return boxesRepository.findListChildBoxesIdsByListParentBoxesIds(listParentBoxesIds);
        
    }
    
}
