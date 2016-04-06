package Logic;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Created by Marion on 03/29/16.
 */
@EnableScheduling
@EnableAsync
@Configuration
@Component
public class FileManager  {

    @Scheduled(fixedRate = 500)
    public void checkTarger(Path file, Path target) throws IOException {

            System.out.println("File has been found");
            Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);

        EventBus eventBus = new EventBus();
        //  eventBus.register(new FileManager());
        eventBus.register(new Processor());
        System.out.println("Post Simple EventBus Example");
        eventBus.post("Simple EventBus Example");
    }




}
