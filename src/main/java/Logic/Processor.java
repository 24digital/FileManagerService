package Logic;

import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Created by Marion on 03/29/16.
 */
@Service
public class Processor  {

    @Subscribe
    public void task(String s)
    {
        System.out.println("Registered the posted that just came out:Processor");
    }

    public void copyFiles(File sourceFolder, File destinationFolder) throws IOException {
        if (sourceFolder.isDirectory())
        {

            if (!destinationFolder.exists())
            {
                destinationFolder.mkdir();
                System.out.println("Directory created :: " + destinationFolder);
            }

            String files[] = sourceFolder.list();


            for (String file : files)
            {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);

                //Recursive function call
                copyFiles(srcFile, destFile);
            }
        }
        else
        {

            Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied :: " + destinationFolder);
        }

    }
}
