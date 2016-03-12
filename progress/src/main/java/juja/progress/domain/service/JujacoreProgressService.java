package juja.progress.domain.service;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import java.util.Arrays;
import java.util.stream.Collectors;
import juja.progress.domain.dao.ProgressDao;
import juja.progress.domain.model.User;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;
import juja.slack.EmojiCodes;

public final class JujacoreProgressService implements ProgressService {

    private final ProgressDao dao;
    private final Set<String> blacklist;
    private final SlackSession session;

    @Inject
    public JujacoreProgressService(
        final String blacks, final ProgressDao progress,
        final SlackSession session
    ) {
        this.session = session;
        this.blacklist = new HashSet<>(Arrays.asList(blacks.split(";")));
        this.dao = progress;
    }

    @Override
    public Set<String> codes() {
        return this.dao.fetchProgressCodes().stream()
            .filter(code -> !this.blacklist.contains(code))
            .collect(Collectors.toSet());
    }

    @Override
    public void markProgressDone(final User user, final String... codes) {
        this.dao.markProgressForUser(user.getSlackNickName(), codes);
    }

    @Override
    public void reactToUserCodes(
        final String msg, final String chnl
    ) {
        final SlackChannel channel = this.session.findChannelById(chnl);
        this.session.addReactionToMessage(channel, msg,
            EmojiCodes.MARKED.getCode());
    }
}
