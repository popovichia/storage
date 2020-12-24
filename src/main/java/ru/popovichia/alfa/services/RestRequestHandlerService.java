package ru.popovichia.alfa.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.popovichia.alfa.data.RestRequestItems;

/**
 *
 * @author Igor Popovich, email: popovichia, phone: +7 913 902 36 36, company:
 * popovichia@gmail.com
 */
@Service
public interface RestRequestHandlerService {
    
    List<Integer> getItemsByBoxIdAndItemColor(RestRequestItems restRequestItems);
    
}
