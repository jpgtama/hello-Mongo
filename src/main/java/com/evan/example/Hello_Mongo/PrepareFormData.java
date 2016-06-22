package com.evan.example.Hello_Mongo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PrepareFormData {
	
	public static void main(String[] args) {
		getData();
	}
	
	public static List<Document> getData() {
		
		List<Document> dataList = new ArrayList<Document>();
		
		ObjectMapper om = new ObjectMapper();
		try {
			for (String line : Files.readAllLines(Paths.get("C:/Users/310199253/Documents/Philips/Sql Server Export", "formdata2.txt"))) {
				// split by '\t'
				String[] fields = line.split("\t");
				
				String oid = fields[0];
				String defId = fields[1];
				String pid = fields[2];
				String data = fields[3];
				String createBy = fields[4];
				String updateBy = fields[5];
				
				// result map
				Document resultDocument = new Document();
				
				// add info
				resultDocument.put("_id", oid);
				resultDocument.put("defId", defId);
				resultDocument.put("pId", pid);
				resultDocument.put("createBy", createBy);
				resultDocument.put("updateBy", updateBy);
				
				// get data map
				Document dataDoc = om.readValue(data, Document.class);
				
				// add data map
				resultDocument.put("data", dataDoc);
				
				System.out.println(resultDocument);
				
				dataList.add(resultDocument);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataList;
	}
	
}
