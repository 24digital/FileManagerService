package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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



@Bean
    public FileManager getFileManager(){
    return new FileManager();
}



    public static void main(String... args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        File source = null;
        File target = null;
        System.out.println("Hello to File Manager");

        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("input source folder");
            String s = bufferRead.readLine();
            source = new File(s);
            System.out.println(s+" "+source.exists());

            if(source.isDirectory())
            System.out.println(source.listFiles().toString());

            System.out.println("input target folder");
            String targets = bufferRead.readLine();
            target = new File(targets);
            System.out.println(target+" "+target.exists());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }



        FileManager manager = ctx.getBean(FileManager.class);


        manager.checkTarget(source,target);


    }
}