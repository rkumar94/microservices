package juja.progress.di;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import juja.google.spreadsheet.api.SpreadSheetReader;
import juja.google.spreadsheet.api.gdata.SpreadsheetServiceProvider;
import juja.google.spreadsheet.dao.GdataProgressDao;
import juja.progress.domain.dao.ProgressDao;
import juja.progress.domain.service.JujacoreProgressService;
import juja.progress.domain.service.ProgressService;

public class GSRApplicationModule implements Module {

    @Override
    public final void configure(final Binder binder) {
        binder.bind(ProgressService.class).to(JujacoreProgressService.class);
        binder.bind(ProgressDao.class).to(GdataProgressDao.class);
        binder.bind(SpreadsheetService.class)
            .toProvider(SpreadsheetServiceProvider.class).in(Singleton.class);
        binder.bind(SpreadSheetReader.class)
            .annotatedWith(Names.named("progress"))
            .toProvider(ProgressSpreadsheetProvider.class);
    }
}