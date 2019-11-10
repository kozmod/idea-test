package ru.idea.test.db.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.junit.Test;

public class MongoTest {
    @Test
    public void name() {
        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase db = mongo.getDatabase("mydb");
        MongoCollection table = db.getCollection("cities");

        System.out.println(table.find().first());
    }
}
