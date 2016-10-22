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

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

/**
 * User custom repository implementation.
 *
 * @author Sergii Lisnychyi (ljore@ukr.net)
 * @version $Id$
 * @since 1.0
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    /**
     * MongoTemplate.
     */
    private final MongoTemplate template;

    @Inject
    public UserRepositoryImpl(final MongoTemplate template) {
        this.template = template;
    }

    @Override
    public final User createUser(final User user) {
        this.template.save(user);
        return user;
    }

    @Override
    public final User getUser(final String id) {
        final Query query = Query.query(Criteria.where("_id").is(id));
        return this.template.findOne(query, User.class);
    }

    @Override
    public final User getUserByUsername(final String username) {
        final Query query = Query.query(Criteria.where("_username").is(username));
        return this.template.findOne(query, User.class);
    }

    @Override
    public final List<User> getUsers() {
        final List<User>  users = this.template.findAll(User.class);
        final List<User> result = new ArrayList<>(users.size());
        result.addAll(users);
        return result;
    }
}
