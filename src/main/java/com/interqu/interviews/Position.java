package com.interqu.interviews;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("positions")
public class Position {
    String position;

    public Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return position;
    }

}
