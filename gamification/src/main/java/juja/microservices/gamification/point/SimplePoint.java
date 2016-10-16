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
package juja.microservices.gamification.point;

import java.util.Date;
import java.util.Objects;

import juja.microservices.gamification.user.User;
import org.springframework.data.annotation.Id;

/**
 * Point class.
 * @author Sergii Lisnychyi (ljore@ukr.net)
 * @version $Id$
 * @since 1.0
 */
public class SimplePoint implements Point {

    /**
     * Id field.
     */
    @Id
    private String id;

    /**
     * Active (active Point added recipient the total scores for the user ).
     */
    private boolean active;

    /**
     * Sender User.
     */
    private User sender;

    /**
     * Recipient User.
     */
    private User recipient;

    /**
     * Send time.
     */
    private Date send;

    /**
     * Update time.
     */
    private Date update;

    @Override
    public final boolean equals(final Object object) {
        boolean result = false;
        if (this == object) {
            result = true;
        } else if (object == null || getClass() != object.getClass()) {
            result = false;
        } else {
            final SimplePoint that = (SimplePoint) object;
            result = Objects.equals(this.id, that.id)
                && Objects.equals(this.active, that.active)
                && Objects.equals(this.sender, that.sender)
                    && Objects.equals(this.recipient, that.recipient)
                    && Objects.equals(this.send, that.send)
                    && Objects.equals(this.update, that.update);
        }
        return result;
    }

    @Override
    public final int hashCode() {
        final int one = Objects.hash(this.id, this.active, this.sender);
        final int second = Objects.hash(this.recipient, this.send, this.update);
        return one + second;
    }
}
