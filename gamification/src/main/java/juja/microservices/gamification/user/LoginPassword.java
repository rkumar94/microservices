package juja.microservices.gamification.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@ToString(of = {"login", "password"})
@EqualsAndHashCode(of = {"login", "password"})
public final class LoginPassword {

    private String login;
    private String password;

    @JsonCreator
    public LoginPassword(@JsonProperty("login") final String login, @JsonProperty("pwd") final String password) {
        this.login = login;
        this.password = password;
    }

    public String login() {
        return login;
    }

    public String password() {
        return password;
    }
}
