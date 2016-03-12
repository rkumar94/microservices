package juja.progress.domain.service;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import java.util.Arrays;
import java.util.Set;
import juja.progress.domain.dao.ProgressDao;
import juja.progress.domain.model.User;
import juja.slack.EmojiCodes;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;
import org.mockito.Mockito;

public class JujacoreProgressServiceTest {

    @Test
    public void shouldFetchAllProgressCodes() throws Exception {
        //Given
        final ProgressDao dao = Mockito.mock(ProgressDao.class);
        final ProgressService service = new JujacoreProgressService(
            "", dao, Mockito.mock(SlackSession.class)
        );
        Mockito.when(dao.fetchProgressCodes()).thenReturn(Arrays.asList(
            "+code1", "+code2", "+code1"
        ));
        //When
        final Set<String> codes = service.codes();
        //Then
        MatcherAssert.assertThat(
            codes, IsCollectionWithSize.hasSize(2)
        );
        MatcherAssert.assertThat(
            codes, IsCollectionContaining.hasItems(
                "+code1", "+code2"
            )
        );
    }

    @Test
    public void shouldExcludeBlackListedCodes() throws Exception {
        //Given
        final ProgressDao dao = Mockito.mock(ProgressDao.class);
        final ProgressService service = new JujacoreProgressService(
            "+blackListCode1;+blackListCode2", dao,
            Mockito.mock(SlackSession.class)
        );
        Mockito.when(dao.fetchProgressCodes()).thenReturn(Arrays.asList(
            "+code1", "+blackListCode1", "+blackListCode2", "+code2"
        ));
        //When
        final Set<String> actualProgressCodes = service.codes();
        //Then
        MatcherAssert.assertThat(
            actualProgressCodes, IsCollectionWithSize.hasSize(2)
        );
        MatcherAssert.assertThat(
            actualProgressCodes, IsCollectionContaining.hasItems(
                "+code1", "+code2"
            )
        );
    }

    @Test
    public void shouldMarkProgressByCodes() throws Exception {
        //Given
        final ProgressDao dao = Mockito.mock(ProgressDao.class);
        final ProgressService service = new JujacoreProgressService(
            "", dao, Mockito.mock(SlackSession.class)
        );
        final User user = User.create().withSlackNick("slackNick").build();
        //When
        service.markProgressDone(user, "+quiz1", "+quiz2");
        //Then
        Mockito.verify(dao).markProgressForUser(
            "slackNick", "+quiz1", "+quiz2"
        );
    }

    @Test
    public void reactToUserCodesWithWhiteCheckMark() throws Exception {
        final SlackSession session = Mockito.mock(SlackSession.class);
        final SlackChannel channel = Mockito.mock(SlackChannel.class);
        final String chnl = "channelId";
        Mockito.when(session.findChannelById(chnl)).thenReturn(channel);
        final ProgressService service = new JujacoreProgressService(
            "", Mockito.mock(ProgressDao.class), session
        );
        final String msg = "msgId";
        service.reactToUserCodes(msg, chnl);
        Mockito.verify(session).addReactionToMessage(
            channel, msg, EmojiCodes.MARKED.getCode()
        );
    }

    //TODO negative scenario
}