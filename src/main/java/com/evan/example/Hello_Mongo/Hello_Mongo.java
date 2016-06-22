package com.evan.example.Hello_Mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

public class Hello_Mongo {
	
	private static MongoClient mongoClient;
	
	private static MongoDatabase testDB;
	
	private static MongoCollection<Document> formDataCollection;
	
	static {
		init();
	}
	
	/**
	 * init
	 */
	private static void init() {
		mongoClient = new MongoClient("localhost");
		
		testDB = mongoClient.getDatabase("test");
		
		formDataCollection = testDB.getCollection("formData");
	}
	
	/**
	 * prepare
	 */
	private static void prepare() {
		
		for (Document d : PrepareFormData.getData()) {
			formDataCollection.insertOne(d);
		}
		
		// formDataCollection.insertOne(PrepareData.getData().get(0));
		
		System.out.println("end.");
		
	}
	
	/**
	 * find
	 */
	private static void find() {
		
		print(formDataCollection.find());
		
	}
	
	private static void print(FindIterable<Document> fi) {
		
		MongoCursor<Document> it = fi.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	private static void findFirst() {
		// 51794274
		System.out.println(formDataCollection.find().first());
	}
	
	private static void findById(Long id) {
		System.out.println(formDataCollection.find(Filters.eq("_id", id.toString())).first());
	}
	
	private static void select() {
		// TODO Auto-generated method stub
		print(formDataCollection.find().sort(Sorts.descending("data.mrn")).projection(Projections.include("data.patientName", "data.mrn", "data.gender"))
				.limit(10));
	}
	
	private static void sort() {
		// TODO Auto-generated method stub
		print(formDataCollection.find().sort(Sorts.descending("data.mrn")).limit(10));
	}
	
	public static void main(String[] args) {
		// prepare();
		// find();
		// findFirst();
		// findById(51794274L);
		// sort();
		
		select();
		
		// filter();
		
		dataExport();
	}
	
	private static void dataExport() {
		// patientName as 病患姓名, mrn -> 病患编号, gender -> 病患性别
		
	}
	
	private static void filter() {
		print(formDataCollection.find().filter(Filters.regex("data.mrn", "^asdf")));
	}
}
