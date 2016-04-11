package GUI;

import java.io.File;

import Logic.FileManager;

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

        System.out.println("Hello to File Manager");





        FileManager manager = ctx.getBean(FileManager.class);


        manager.checkTarget(new File("c:\\Users\\Marion\\Downloads\\hwk2.txt").toPath(),new File("C:\\Users\\Marion\\Downloads\\TurtleBeach\\test.txt").toPath());


    }
}