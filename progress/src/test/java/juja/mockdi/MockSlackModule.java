package juja.mockdi;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.ullink.slack.simpleslackapi.SlackSession;
import org.mockito.Mockito;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public final class MockSlackModule implements Module {
    @Override
    public void configure(final Binder binder) {
        binder.bind(SlackSession.class).toInstance(
            Mockito.mock(SlackSession.class)
        );
    }
}
