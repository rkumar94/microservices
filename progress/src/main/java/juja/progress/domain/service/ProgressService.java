package juja.progress.domain.service;

import juja.progress.domain.model.User;

import java.util.Set;

public interface ProgressService {

    Set<String> codes();

    void markProgressDone(User user, String... codes);

    void reactToUserCodes(String messageId, final String channelId);

}
