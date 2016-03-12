package juja.slack;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.inject.Named;
import juja.progress.domain.model.User;
import juja.progress.domain.service.ProgressService;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public final class ProgressSlackChannel implements SlackMessagePostedListener {

    private final String channel;

    private final ProgressService progress;

    @Inject
    public ProgressSlackChannel(
        @Named("progress.channel") final String chnl,
        final ProgressService prgrs
    ) {
        this.channel = chnl;
        this.progress = prgrs;
    }

    @Override
    public void onEvent(
        final SlackMessagePosted event, final SlackSession session
    ) {
        final String channel = event.getChannel().getId();
        if (this.channel.equals(channel)) {
            final User user = new User.UserBuilder().withSlackNick(
                event.getSender().getUserName()
            ).build();
            final List<String> codes = Stream.of(
                event.getMessageContent().split("\n")
            ).filter(
                code -> this.progress.codes().contains(code)
            ).collect(Collectors.toList());
            if (!codes.isEmpty()) {
                this.progress.markProgressDone(user, codes.toArray(
                    new String[codes.size()])
                );
                this.progress.reactToUserCodes(event.getTimeStamp(), channel);
            }
        }
    }
}
