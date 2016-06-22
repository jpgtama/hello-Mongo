package com.evan.example.Hello_Mongo;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

public class Utils {
	
	public static void print(FindIterable<Document> fi) {
		
		MongoCursor<Document> it = fi.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
