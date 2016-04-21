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
package juja.microservices.gamification.model.entity;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User entity
 * @author Sergii Lisnychyi (ljore@ukr.net)
 * @version $Id$
 * @since 1.0
 */
@Document(collection = "users")
public class User {

    /**
     * Id field.
     */
    @Id
    private String id;
    /**
     * Uuid field.
     */
    private String uuid;
    /**
     * USername field.
     */
    private String username;

    /**
     * User constructor by default.
     */
    public User() {
    }

    /**
     * User persistence constructor.
     */
    @PersistenceConstructor
    public User(final String username) {
        this.username = username;
        this.uuid = UUID.randomUUID().toString();
    }

    /**
     * Get uuid.
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * Set uuid.
     */
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    /**
     * Get username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Set username.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Set id.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get id.
     */
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return new org.apache.commons.lang.builder.EqualsBuilder()
                .append(id, user.id)
                .append(uuid, user.uuid)
                .append(username, user.username)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang.builder.HashCodeBuilder(17, 37)
                .append(this.id)
                .append(this.uuid)
                .append(this.username)
                .toHashCode();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id='").append(id).append('\'');
        sb.append(", uuid='").append(this.uuid).append('\'');
        sb.append(", username='").append(this.username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
