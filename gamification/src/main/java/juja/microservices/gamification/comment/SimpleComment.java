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
package juja.microservices.gamification.comment;

import java.util.Date;
import java.util.Objects;

import juja.microservices.gamification.user.User;
import org.springframework.data.annotation.Id;

/**
 * Comment class.
 * @author Sergii Lisnychyi (ljore@ukr.net)
 * @version $Id$
 * @since 1.0
 */
public class SimpleComment implements Comment {

    /**
     * Id field.
     */
    @Id
    private String id;

    /**
     * Author User.
     */
    private User author;

    /**
     * Posted date time.
     */
    private Date time;

    /**
     * Comment text.
     */
    private String text;

    /**
     * Get author.
     * @return User
     */
    public final User getAuthor() {
        return this.author;
    }

    /**
     * Set author.
     * @param setauthor Author.
     */
    public final void setAuthor(final User setauthor) {
        this.author = setauthor;
    }

    /**
     * Get time.
     * @return Time
     */
    public final Date getTime() {
        Date result = null;
        if (this.time != null) {
            result = new Date(this.time.getTime());
        }
        return result;
    }

    /**
     * Set time.
     * @param newtime Time.
     */
    public final void setTime(final Date newtime) {
        Date result = null;
        if (newtime != null) {
            result = new Date(newtime.getTime());
        }
        this.time = result;
    }

    /**
     * Get text.
     * @return Text
     */
    public final String getText() {
        return this.text;
    }

    /**
     * Set text.
     * @param newtext Text.
     */
    public final void setText(final String newtext) {
        this.text = newtext;
    }

    @Override
    public final boolean equals(final Object object) {
        final boolean result;
        if (this == object) {
            result = true;
        } else if (object == null || getClass() != object.getClass()) {
            result = false;
        } else {
            final SimpleComment that = (SimpleComment) object;
            result = Objects.equals(this.id, that.id)
                && Objects.equals(this.author, that.author)
                    && Objects.equals(this.text, that.text)
                    && Objects.equals(this.time, that.time);
        }
        return result;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.id, this.author, this.time, this.text);
    }
}
