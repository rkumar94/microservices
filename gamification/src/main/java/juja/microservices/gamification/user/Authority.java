package juja.microservices.gamification.user;

import java.util.List;

/**
 * Authority entity.
 * @author olga kulykova email o.kulykova@gmail.com
 * @version $Id$
 * @since 1.0
 */
public class Authority {

    /**
     * Id field.
     */
    private String id;

    /**
     * AuthorityName field.
     */
    private AuthorityName authorityName;

    /**
     * List of users.
     */
    private List<User> users;

    /**
     * Get id
     * @return Id
     */
    public String getId() {
        return id;
    }

    /**
     * Set id
     * @param id Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get authority name.
     * @return AuthorityName
     */
    public AuthorityName getAuthorityName() {
        return authorityName;
    }

    /**
     * Set authority name.
     * @param authorityName AuthorityName
     */
    public void setAuthorityName(AuthorityName authorityName) {
        this.authorityName = authorityName;
    }

    /**
     * Get users.
     * @return List of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Set users.
     * @param users List of users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
