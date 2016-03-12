package juja.progress.domain.model;

import com.google.common.base.Objects;

public final class User {
    private String slackNickName;

    public String getSlackNickName() {
        return this.slackNickName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final User user = (User) o;
        return Objects.equal(this.slackNickName, user.slackNickName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.slackNickName);
    }

    public static UserBuilder create() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private User user = new User();

        public UserBuilder withSlackNick(String slackNick) {
            user.slackNickName = slackNick;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
