package dhbw.karlsruhe.it.solar.core.ai.events;

import java.util.EventListener;

/**
 * Created by argannor on 20.02.15.
 */
public interface TargetReachedListener extends EventListener{
    public void handle(TargetReachedEvent event);
}
