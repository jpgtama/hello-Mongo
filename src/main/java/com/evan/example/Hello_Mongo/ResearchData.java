package com.evan.example.Hello_Mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ResearchData {
	
	private static MongoClient mongoClient;
	
	private static MongoDatabase testDB;
	
	private static MongoCollection<Document> researchDataCollection;
	
	static {
		init();
	}
	
	/**
	 * init
	 */
	private static void init() {
		mongoClient = new MongoClient("localhost");
		
		testDB = mongoClient.getDatabase("test");
		
		researchDataCollection = testDB.getCollection("researchData");
	}
}
