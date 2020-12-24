package ru.popovichia.alfa.storage.restcontrollers;

import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.popovichia.alfa.data.RestRequestItems;
import ru.popovichia.alfa.services.RestRequestHandlerService;

/**
 *
 * @author Igor Popovich, email: popovichia, phone: +7 913 902 36 36, company:
 * popovichia@gmail.com
 */

@RestController
@RequestMapping(path = "/")
public class StorageRestController {
    
    private static Logger logger = LogManager.getFormatterLogger(StorageRestController.class);
    
    @Autowired
    private RestRequestHandlerService restRequestHandlerService;
    
    @PostMapping(path = "/items",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Integer> getItems(@Valid @RequestBody RestRequestItems restRequestItems) {
        
        logger.info("Rest service recieved JSON request body: " + restRequestItems);
        
        return restRequestHandlerService.getItemsByBoxIdAndItemColor(restRequestItems);
        
    }
    
}
