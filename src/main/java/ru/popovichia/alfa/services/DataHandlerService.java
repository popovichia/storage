package ru.popovichia.alfa.services;

import org.springframework.stereotype.Service;

/**
 *
 * @author Igor Popovich, email: popovichia, phone: +7 913 902 36 36, company:
 * popovichia@gmail.com
 */
@Service
public interface DataHandlerService {
    
    boolean createDataStructure(String sourceFilePath);
    
}
