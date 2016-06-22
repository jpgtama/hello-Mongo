package com.evan.example.Hello_Mongo;

import org.bson.Document;

import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Test_DBRef {
	
	private static MongoClient mongoClient;
	
	private static MongoDatabase testDB;
	
	// private static MongoCollection<Document> researchDataCollection;
	
	static {
		init();
	}
	
	/**
	 * init
	 */
	private static void init() {
		mongoClient = new MongoClient("localhost");
		
		testDB = mongoClient.getDatabase("test");
		
		// researchDataCollection = testDB.getCollection("researchData");
	}
	
	public static void main(String[] args) {
		
		MongoCollection<Document> prodCollection = testDB.getCollection("prod");
		
		// Utils.print(prodCollection.find());
		
		MongoCursor<Document> it = prodCollection.find().iterator();
		
		while (it.hasNext()) {
			Document prod = it.next();
			
			System.out.println(prod);
			
			DBRef inventory = prod.get("inventory", DBRef.class);
			
		}
		
		System.out.println("===========================");
		
		Utils.print(prodCollection.find(Filters.eq("inventory.$id", "52350353b2eff1353b349de9")));
	}
	
}
