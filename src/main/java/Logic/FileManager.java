package Logic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by Marion on 03/29/16.
 */
@EnableScheduling
@EnableAsync
@Configuration
@Component
public class FileManager {

    @Scheduled(fixedRate = 500)
    public void checkTarger()
    {
        System.out.println("Hello ");
    }

}
