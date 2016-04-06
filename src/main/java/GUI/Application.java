package GUI;

import java.io.File;
import java.nio.IntBuffer;
import java.nio.file.FileSystem;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import Logic.FileManager;
import Logic.Processor;
import com.google.common.eventbus.EventBus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Application   {
    private static final int NUMBER_OF_QUOTES = 10;


@Bean
    public FileManager getFileManager(){
    return new FileManager();
}



    public static void main(String... args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        FileManager manager = ctx.getBean(FileManager.class);

File k = new File("c");

        manager.checkTarger(new File("c:\\Users\\Marion\\Downloads\\hwk2.txt").toPath(),new File("C:\\Users\\Marion\\Downloads\\TurtleBeach\\test.txt").toPath());


    }
}