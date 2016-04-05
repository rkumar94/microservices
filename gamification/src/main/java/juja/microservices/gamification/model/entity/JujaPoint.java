package juja.microservices.gamification.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jujapoints")
public class JujaPoint {
    private final String fromUserUUID;
    private final String toUserUUID;
    private final int number;
    private final String date;
    private final String description;

    public JujaPoint(String fromUserUUID, String toUserUUID, int number, String date, String description) {
        this.fromUserUUID = fromUserUUID;
        this.toUserUUID = toUserUUID;
        this.number = number;
        this.date = date;
        this.description = description;
    }

    public String getFromUserUUID() {
        return fromUserUUID;
    }

    public String getToUserUUID() {
        return toUserUUID;
    }

    public int getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JujaPoint jujaPoint = (JujaPoint) o;

        if (number != jujaPoint.number) return false;
        if (fromUserUUID != null ? !fromUserUUID.equals(jujaPoint.fromUserUUID) : jujaPoint.fromUserUUID != null)
            return false;
        if (toUserUUID != null ? !toUserUUID.equals(jujaPoint.toUserUUID) : jujaPoint.toUserUUID != null) return false;
        if (date != null ? !date.equals(jujaPoint.date) : jujaPoint.date != null) return false;
        return !(description != null ? !description.equals(jujaPoint.description) : jujaPoint.description != null);

    }

    @Override
    public int hashCode() {
        int result = fromUserUUID != null ? fromUserUUID.hashCode() : 0;
        result = 31 * result + (toUserUUID != null ? toUserUUID.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
