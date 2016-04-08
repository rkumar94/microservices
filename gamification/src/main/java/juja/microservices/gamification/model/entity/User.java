package juja.microservices.gamification.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String uuid;
    private String userName;

    public User() {
    }

    @PersistenceConstructor
    public User(String userName) {
        this.userName = userName;
        this.uuid = generateUUID();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String generateUUID(){
        return UUID.randomUUID().toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {


        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (uuid != null ? !uuid.equals(user.uuid) : user.uuid != null) return false;
        return userName != null ? userName.equals(user.userName) : user.userName == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", uuid='" + uuid + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
