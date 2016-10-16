/**
 * Copyright (c) 2016, juja.com.ua
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * * Neither the name of microservices nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package juja.microservices.gamification.user;

import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User entity.
 * @author Sergii Lisnychyi (ljore@ukr.net)
 * @version $Id$
 * @since 1.0
 */
@Document(collection = "users")
public class User {

    /**
     * Capacity of String object interpretation.
     */
    public static final int TOSTRING_CAPACITY = 50;

    /**
     * Id field.
     */
    @Id
    private String id;

    /**
     * Username field.
     */
    private String username;

    /**
     * List of authorities.
     */
    private Set<String> authorities;

    /**
     * User persistence constructor.
     */
    @PersistenceConstructor
    public User(){}

    /**
     * User persistence constructor.
     * @param username Username
     */
    @PersistenceConstructor
    public User(final String username) {
        this.username = username;
    }

    /**
     * User persistence constructor.
     * @param id Id
     * @param username Username
     * @param authorities Authorities
     */
    @PersistenceConstructor
    public User(final String id, String username, Set<String> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }

    /**
     * Get username.
     * @return Username
     */
    public final String getUsername() {
        return this.username;
    }

    /**
     * Get id.
     * @return Id
     */
    public final String getId() {
        return this.id;
    }

    /**
     * Get authorities.
     * @return List of authorities
     */
    public final Set<String> getAuthorities() {
        return authorities;
    }

    @Override
    public final boolean equals(final Object obj) {
        boolean result;
        if (this == obj) {
            result = true;
        } else if (obj == null || getClass() != obj.getClass()) {
            result = false;
        } else {
            final User user = (User) obj;
            result = Objects.equals(this.id, user.id)
                && Objects.equals(this.username, user.username);
        }
        return result;
    }

    @Override
    public final int hashCode() {
        final int prime = 17;
        final int secprime = 37;
        return new HashCodeBuilder(prime, secprime)
            .append(this.id)
            .append(this.username)
            .toHashCode();
    }

    @Override
    public final String toString() {
        final StringBuilder sbuilder = new StringBuilder(
            User.TOSTRING_CAPACITY
        ).append("User{id='").append(this.id).append('\'')
            .append(", username='").append(this.username).append('\'')
            .append('}');
        return sbuilder.toString();
    }
}
