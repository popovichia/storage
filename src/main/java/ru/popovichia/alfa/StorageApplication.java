package ru.popovichia.alfa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.popovichia.alfa.services.DataHandlerService;

/**
 *
 * @author Igor Popovich, email: popovichia, phone: +7 913 902 36 36, company:
 * popovichia@gmail.com
 */

@SpringBootApplication
@EnableJpaRepositories
public class StorageApplication {
    
    private static Logger logger = LogManager.getLogger(StorageApplication.class);
    
    public static void main(String[] args) {
        
        String sourceFilePath = getSourceFilePath(args);
        if(sourceFilePath != null) {
            ApplicationContext applicationContext = SpringApplication.run(StorageApplication.class, args);
            DataHandlerService dataHandlerService = applicationContext.getBean(DataHandlerService.class);
            dataHandlerService.createDataStructure(sourceFilePath);
        } else {
            logger.info("\nArgument in command line is not deffined.\n"
                    + "Please, set argument -type:path\n"
                    + "Examples:\n"
                    + "1) - file:input.xml\n"
                    + "2) - classpath:input.xml\n"
                    + "3) - url:file:/input.xml\n");
        }
        
    }
    
    private static String getSourceFilePath(String[] args) {

        String result = null;

        if (args.length == 1
                && args[0].length() > 6
                && args[0].startsWith("-")) {
            
            result = args[0].substring(1);
            
        }

        if (args.length == 2
                && args[0].equals("-")
                && args[1].length() > 5) {
            
            result = args[1];
            
        }

        return result;

    } 

}
