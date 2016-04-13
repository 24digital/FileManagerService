package Logic;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Marion on 03/29/16.
 */
@Configuration
@Component
public class FileManager implements Runnable {

    Processor processor;
    File sourceFolder;
    File destinationFolder;
    EventBus eventBus;

    public void checkTarget(File sourceFolder, File destinationFolder) throws IOException {

        this.sourceFolder = sourceFolder;
        this.destinationFolder = destinationFolder;
        processor = new Processor();

eventBus = new EventBus();
        //  eventBus.register(new FileManager());
        eventBus.register(new Processor());

        processor.copyFiles(sourceFolder, destinationFolder);
        System.out.println("Post Simple EventBus Example");

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
                for (int i = 0; i < filesArray.size(); i++) {
                    Long current = (Long) dir.get(filesArray.get(i));
                    checkedFiles.add(filesArray.get(i));
                    if (current == null) {


                        dir.put(filesArray.get(i), new Long(filesArray.get(i).lastModified()));


                    } else if (current.longValue() != filesArray.get(i).lastModified()) {
                        // modified file
                        dir.put(filesArray.get(i), new Long(filesArray.get(i).lastModified()));

                        //    changed
                        System.out.println("Modified" + filesArray.get(i));
                        eventBus.post("Simple EventBus Example");
                    }
                }


                Set ref = ((HashMap) dir.clone()).keySet();
                ref.removeAll((Set) checkedFiles);
                Iterator it = ref.iterator();
                while (it.hasNext()) {
                    File deleteFile = (File) it.next();
                    dir.remove(deleteFile);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Monitoring Folder" + dir);
        }
    }
}
