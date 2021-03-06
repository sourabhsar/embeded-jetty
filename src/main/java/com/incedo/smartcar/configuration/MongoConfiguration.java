package com.incedo.smartcar.configuration;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.UnknownHostException;

/**
 * Created by Sourabh on 7/21/2016.
 */
@Configuration
@EnableWebMvc
@PropertySource(value = { "classpath:smartcar-application.properties" })
public class MongoConfiguration {

    @Bean
    public MongoClient mongoClient(
            @Value("${smartcar.dbconnection.mongoHostName}") String mongoHostName,
            @Value("${smartcar.dbconnection.mongoPort}") String mongoPort) throws UnknownHostException {
        return new MongoClient(mongoHostName,Integer.valueOf(mongoPort));
    }

    @Bean
    public DB db(MongoClient mongoClient,@Value("${smartcar.dbconnection.collection}") String collection) {
        return mongoClient.getDB(collection);
    }

    @Bean
    public DBCollection dbCollection(DB db,@Value("${smartcar.dbconnection.tablename}") String tablename) {
        return db.getCollection(tablename);
    }
}
