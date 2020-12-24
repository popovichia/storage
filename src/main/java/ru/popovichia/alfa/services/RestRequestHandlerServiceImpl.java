package ru.popovichia.alfa.services;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.popovichia.alfa.data.RestRequestItems;

@Service
public class RestRequestHandlerServiceImpl implements RestRequestHandlerService {
    
    private static Logger logger = LogManager.getLogger(RestRequestHandlerServiceImpl.class);
    
    @Autowired
    private ItemsRepositoryService itemsRepositoryService;
    
    @Autowired
    private BoxesRepositoryService boxesRepositoryService;
    
    private List<Integer> listIncludedBoxesIds;
    
    public List<Integer> getItemsByBoxIdAndItemColor(RestRequestItems restRequestItems) {
        
        Integer boxId = restRequestItems.getBox();
        String color = restRequestItems.getColor();
        
        listIncludedBoxesIds = new ArrayList<>();
        listIncludedBoxesIds.add(boxId);

        findIncludedBoxes(listIncludedBoxesIds);
        
        logger.info("Searching items in boxes: " + listIncludedBoxesIds + ", color: " + color + " --->");
        List<Integer> resultItemsList = itemsRepositoryService.findItemsByBoxesIdsAndItemColor(listIncludedBoxesIds, color);
        logger.info("<--- Found items: " + resultItemsList);        
        
        return resultItemsList;

    }
    
    private void findIncludedBoxes(List<Integer> listParentBoxesIds) {
        
        List<Integer> listChildBoxesIds = boxesRepositoryService.findListChildBoxesIdsByListParentBoxesIds(listParentBoxesIds);
        if (listChildBoxesIds != null 
                && !listChildBoxesIds.isEmpty()) {

            listIncludedBoxesIds.addAll(listChildBoxesIds);
            findIncludedBoxes(listChildBoxesIds);
            
        }
        
    }
    
}
