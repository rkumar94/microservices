package juja.progress.di;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Names;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public class PropertiesModule implements Module {

    public static final String CONFIG_PROPERTIES = "config.properties";

    @Override
    public void configure(final Binder binder) {
        Properties properties = new Properties();
        InputStream resourceStream = null;
        try {
            resourceStream = this.getClass().getResourceAsStream(
                "/" + CONFIG_PROPERTIES
            );
            if (resourceStream == null) {
                resourceStream = new FileInputStream(CONFIG_PROPERTIES);
            }
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Names.bindProperties(binder, properties);
    }
}
