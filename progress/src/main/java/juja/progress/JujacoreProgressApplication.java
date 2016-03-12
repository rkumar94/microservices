package juja.progress;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ullink.slack.simpleslackapi.SlackSession;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import juja.progress.di.GSRApplicationModule;
import juja.progress.di.PropertiesModule;
import juja.progress.di.SlackModule;
import juja.slack.ProgressSlackChannel;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public final class JujacoreProgressApplication
    implements Application {

    private final SlackSession slack;
    private final ProgressSlackChannel progress;

    public JujacoreProgressApplication(final Injector injector) {
        this.slack = injector.getInstance(SlackSession.class);
        this.progress = injector.getInstance(ProgressSlackChannel.class);
        this.init();
    }

    private void init() {
        this.slack.addMessagePostedListener(this.progress);
    }

    @Override
    public void start() throws IOException {
        this.slack.connect();
    }

    @Override
    public void stop() throws IOException {
        this.slack.disconnect();
    }

    public static void main(final String... args) throws IOException {
        final Injector injector = Guice.createInjector(
            new PropertiesModule(), new SlackModule(),
            new GSRApplicationModule()
        );
        final Application app = new JujacoreProgressApplication(injector);
        try {
            app.start();
        } catch (final IOException e) {
            System.out.println("Filed to start application with exception");
            e.printStackTrace();
            app.stop();
        }
    }
}
