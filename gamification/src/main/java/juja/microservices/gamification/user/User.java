package juja.microservices.gamification.user;

/**
 * User interface.
 * @author Sergii Lisnychyi (ljore@ukr.net)
 * @version $Id$
 * @since 1.0
 */
public interface User {

    /**
     * Capacity of String object interpretation.
     */
    int TOSTRING_CAPACITY = 50;

    /**
     * Get username.
     * @return Username
     */
    String getUsername();

}
