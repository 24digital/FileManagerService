package Logic;

import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

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
}
