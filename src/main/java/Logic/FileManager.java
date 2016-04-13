package Logic;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * Created by Marion on 03/29/16.
 */
@Configuration
@Component
public class FileManager implements Runnable  {

 Processor processor;
    File sourceFolder;
    File destinationFolder;

    public void checkTarget(File sourceFolder, File destinationFolder) throws IOException {

    this.sourceFolder =sourceFolder;
        this.destinationFolder = destinationFolder;
    processor = new Processor();


        EventBus eventBus = new EventBus();
        //  eventBus.register(new FileManager());
        eventBus.register(new Processor());

        processor.copyFiles(sourceFolder,destinationFolder);
        System.out.println("Post Simple EventBus Example");
        eventBus.post("Simple EventBus Example");
        run();
    }


    @Override
    public void run() {
        ArrayList<File> filesArray = new ArrayList<>();
        HashMap dir = new HashMap();

        for (; ; ) {
            try {
                Thread.sleep(2000);

                HashSet checkedFiles = new HashSet();



                    filesArray.addAll(Arrays.asList(sourceFolder.listFiles()));



                // scan the files and check for modification/addition
                for(int i = 0; i < filesArray.size(); i++) {
                    Long current = (Long)dir.get(filesArray.get(i));
                    checkedFiles.add(filesArray.get(i));
                    if (current == null) {


                        dir.put(filesArray.get(i), new Long(filesArray.get(i).lastModified()));


                    }
                    else if (current.longValue() != filesArray.get(i).lastModified()){
                        // modified file
                        dir.put(filesArray.get(i), new Long(filesArray.get(i).lastModified()));

                   //    changed
                        System.out.println("Modified" + filesArray.get(i));
                    }
                }


                Set ref = ((HashMap)dir.clone()).keySet();
                ref.removeAll((Set)checkedFiles);
                Iterator it = ref.iterator();
                while (it.hasNext()) {
                    File deleteFile = (File)it.next();
                    dir.remove(deleteFile);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Monitoring Folder" + dir);
        }
    }
}
