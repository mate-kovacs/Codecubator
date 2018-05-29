package com.codecool.poop.ORM;

import com.codecool.poop.model.User;

public class TestHibernate {

    public static void main(String[] args) {

        // Example for test the database
        User user = new User("valaki", "pass", "email");
        DataHandler db = DataHandler.getInstance();
        db.addItemToDB(user);

    }
}
