package juja.progress.di;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import java.io.IOException;
import javax.inject.Singleton;
import juja.slack.ProgressSlackChannel;

/**
 * User: viktor
 * Date: 1/12/16
 */
public final class SlackModule implements Module {

    @Override
    public void configure(final Binder binder) {
        //Delegated to provider
        binder.bind(ProgressSlackChannel.class);
    }

    @Singleton
    @Provides
    public SlackSession provideSlackSession(
        @Named("slack.bot.token") String token
    ) {
        return SlackSessionFactory.createWebSocketSlackSession(token);
    }


}
