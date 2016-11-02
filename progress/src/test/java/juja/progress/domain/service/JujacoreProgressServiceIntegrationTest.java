package juja.progress.domain.service;

import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import java.util.Set;

import juja.google.spreadsheet.api.Cell;
import juja.google.spreadsheet.api.SpreadSheetReader;
import juja.google.spreadsheet.api.gdata.GdataCell;
import juja.mockdi.MockSlackModule;
import juja.progress.di.GSRApplicationModule;
import juja.progress.di.PropertiesModule;
import juja.progress.domain.model.User;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsCollectionContaining;
import org.hamcrest.core.IsNot;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class JujacoreProgressServiceIntegrationTest {

    private static Injector injector;

    @BeforeClass
    public static void createInjector() {
        JujacoreProgressServiceIntegrationTest.injector = Guice.createInjector(
            new PropertiesModule(),
            new GSRApplicationModule(),
            new MockSlackModule()
        );
    }

    @Ignore
    @Test
    public void fetchCodesFromRealSpreadsheet() throws Exception {
        final ProgressService service = JujacoreProgressServiceIntegrationTest
            .injector.getInstance(ProgressService.class);
        final Set<String> codes = service.codes();
        MatcherAssert.assertThat(
            codes, IsCollectionWithSize.hasSize(251)
        );
        MatcherAssert.assertThat(codes, IsNot.not(
            IsCollectionContaining.hasItem(""))
        );
    }

    @Ignore
    @Test
    public void markProgressForUser() throws Exception {
        final ProgressService service = JujacoreProgressServiceIntegrationTest
            .injector.getInstance(ProgressService.class);
        final SpreadSheetReader spreadsheet =
            JujacoreProgressServiceIntegrationTest.injector.getInstance(
                Key.get(SpreadSheetReader.class, Names.named("progress"))
            );
        Cell cell = new GdataCell(
            spreadsheet, "viktorkuchyn", "log-код", "+lms"
        );
        cell.update("");
        service.markProgressDone(
            User.create().withSlackNick("viktorkuchyn").build(), "+lms"
        );
        cell = new GdataCell(spreadsheet, "viktorkuchyn", "log-код", "+lms");
        MatcherAssert.assertThat(cell.value(), Is.is("DONE"));
    }

    @Ignore
    @Test
    public void createsNewColumnForUserId() throws Exception {
        final SpreadSheetReader spreadsheet =
            JujacoreProgressServiceIntegrationTest.injector.getInstance(
                Key.get(SpreadSheetReader.class, Names.named("progress"))
            );
        final String title = "TEST_HEADER";
        final ListEntry header = spreadsheet.findRowByColumnValue(title, "");
        header.update();
        final CellEntry cell = spreadsheet.createNewHeader(title);
        MatcherAssert.assertThat(cell.getCell().getInputValue(), Is.is(title));
    }

}