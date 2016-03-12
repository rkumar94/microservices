package juja.slack;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import java.util.Arrays;
import java.util.HashSet;
import juja.progress.domain.model.User;
import juja.progress.domain.service.ProgressService;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public class ProgressSlackChannelTest {

    /**
     * ProgressSlackChannel can mark codes done with service.
     */
    @Test
    public void marksProgressDoneIfMatchesCodes() throws Exception {
        final ProgressService service = Mockito.mock(ProgressService.class);
        final String id = "D0FUQEQUF";
        final ProgressSlackChannel channel = new ProgressSlackChannel(
            id, service
        );
        Mockito.when(service.codes()).thenReturn(
            new HashSet<>(Arrays.asList(
                "+code1", "+code2"
            ))
        );
        final String user = "juja";
        final SlackMessagePosted message = this.createMessageWithUserAndChannel(
            id, user, "+code2\n+code3\n+code1"
        );
        channel.onEvent(message, Mockito.mock(SlackSession.class));
        Mockito.verify(service).markProgressDone(
            new User.UserBuilder().withSlackNick(user).build(),
            "+code2", "+code1"
        );
    }

    /**
     * ProgressSlackChannel can filter channel.
     */
    @Test
    public void filtersChannel() throws Exception {
        final ProgressService service = Mockito.mock(ProgressService.class);
        final ProgressSlackChannel channel = new ProgressSlackChannel(
            "D0FUQEQUF", service
        );
        Mockito.when(service.codes()).thenReturn(
            new HashSet<>(Arrays.asList(
                "+code1", "+code2"
            ))
        );
        final String user = "juja";
        final SlackMessagePosted message = this.createMessageWithUserAndChannel(
            "AnotherChannelId", user, "+code2\n+code3\n+code1"
        );
        channel.onEvent(message, Mockito.mock(SlackSession.class));
        Mockito.verify(service, Mockito.never()).markProgressDone(
            new User.UserBuilder().withSlackNick(user).build(),
            "+code2", "+code1"
        );
    }

    /**
     * ProgressSlackChannel can reacts to user messages.
     */
    @Test
    public void reactsToMessage() throws Exception {
        final ProgressService service = Mockito.mock(ProgressService.class);
        final String chnl = "ProgressChannel";
        final ProgressSlackChannel channel = new ProgressSlackChannel(
            chnl, service
        );
        Mockito.when(service.codes()).thenReturn(
            new HashSet<>(Arrays.asList(
                "+code1", "+code2"
            ))
        );
        final String user = "juja";
        final SlackMessagePosted message = this.createMessageWithUserAndChannel(
            chnl, user, "+code2\n+code3\n+code1"
        );
        final String msg = "messageId";
        Mockito.when(message.getTimeStamp()).thenReturn(msg);
        channel.onEvent(message, Mockito.mock(SlackSession.class));
        Mockito.verify(service).reactToUserCodes(msg, chnl);
    }

    /**
     * ProgressSlackChannel doesn't react to message when no codes.
     */
    @Test
    public void doesNotReactToMessageWhenNoCodes() throws Exception {
        final ProgressService service = Mockito.mock(ProgressService.class);
        final String chnl = "ProgressChannel";
        final ProgressSlackChannel channel = new ProgressSlackChannel(
            chnl, service
        );
        Mockito.when(service.codes()).thenReturn(
            new HashSet<>(Arrays.asList(
                "+code1", "+code2"
            ))
        );
        final String user = "juja";
        final SlackMessagePosted message = this.createMessageWithUserAndChannel(
            chnl, user, "+invalidCode"
        );
        channel.onEvent(message, Mockito.mock(SlackSession.class));
        Mockito.verify(service, Mockito.never()).reactToUserCodes(
            Matchers.anyObject(), Matchers.anyObject()
        );
    }

    private SlackMessagePosted createMessageWithUserAndChannel(
        final String id, final String user, final String content
    ) {
        final SlackMessagePosted message = Mockito.mock(
            SlackMessagePosted.class
        );
        Mockito.when(message.getMessageContent()).thenReturn(content);
        final SlackUser slack = Mockito.mock(SlackUser.class);
        Mockito.when(message.getSender()).thenReturn(slack);
        final SlackChannel channel = Mockito.mock(SlackChannel.class);
        Mockito.when(message.getChannel()).thenReturn(channel);
        Mockito.when(channel.getId()).thenReturn(id);
        Mockito.when(slack.getUserName()).thenReturn(user);
        return message;
    }
}