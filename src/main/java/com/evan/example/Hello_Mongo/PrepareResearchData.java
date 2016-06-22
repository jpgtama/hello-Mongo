package com.evan.example.Hello_Mongo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PrepareResearchData {
	
	public static List<Document> getData() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("C:/Users/310199253/Documents/Philips/Sql Server Export", "researchData2.txt"));
		
		// result document
		List<Document> researchDataList = new ArrayList<Document>();
		
		Map<String, Document> researchDataMap = new LinkedHashMap();
		
		ObjectMapper om = new ObjectMapper();
		// skip first line - header
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			
			// split by '\t'
			String[] fields = line.split("\t");
			
			String researchDataId = fields[0];
			String researchDefId = fields[1];
			String patientId = fields[2];
			String formDataId = fields[3];
			String formDefId = fields[4];
			String formData = fields[5];
			
			// add research info
			if (researchDataMap.get(researchDataId) == null) {
				researchDataMap.put(researchDataId, new Document());
				researchDataMap.get(researchDataId).put("_id", researchDataId);
				researchDataMap.get(researchDataId).put("defId", researchDefId);
				researchDataMap.get(researchDataId).put("patientId", patientId);
			}
			
			// add form data
			researchDataMap.get(researchDataId).put(formDataId, new Document());
			((Map) researchDataMap.get(researchDataId).get(formDataId)).put("_id", formDataId);
			((Map) researchDataMap.get(researchDataId).get(formDataId)).put("defId", formDefId);
			// get form data map
			Map formDataDoc = om.readValue(formData, Document.class);
			((Map) researchDataMap.get(researchDataId).get(formDataId)).put("data", formDataDoc);
			
		}
		
		for (Document v : researchDataMap.values()) {
			researchDataList.add(v);
			System.out.println(v);
		}
		
		return researchDataList;
		
	}
	
	private static void old() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("C:/Users/310199253/Documents/Philips/Sql Server Export", "researchData2.txt"));
		
		// result document
		List<Map> researchDataList = new ArrayList<Map>();
		
		Map<String, Map> researchDataMap = new LinkedHashMap();
		
		ObjectMapper om = new ObjectMapper();
		// skip first line - header
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			
			// split by '\t'
			String[] fields = line.split("\t");
			
			String researchDataId = fields[0];
			String researchDefId = fields[1];
			String patientId = fields[2];
			String formDataId = fields[3];
			String formDefId = fields[4];
			String formData = fields[5];
			
			// add research info
			if (researchDataMap.get(researchDataId) == null) {
				researchDataMap.put(researchDataId, new LinkedHashMap());
				researchDataMap.get(researchDataId).put("_id", researchDataId);
				researchDataMap.get(researchDataId).put("defId", researchDefId);
				researchDataMap.get(researchDataId).put("patientId", patientId);
			}
			
			// add form data
			researchDataMap.get(researchDataId).put(formDataId, new LinkedHashMap());
			((Map) researchDataMap.get(researchDataId).get(formDataId)).put("_id", formDataId);
			((Map) researchDataMap.get(researchDataId).get(formDataId)).put("defId", formDefId);
			// get form data map
			Map formDataDoc = om.readValue(formData, Map.class);
			((Map) researchDataMap.get(researchDataId).get(formDataId)).put("data", formDataDoc);
			
		}
		
		// for (Object v : researchDataMap.values()) {
		// researchDataList.add((Document) v);
		// System.out.println(v);
		// }
		
		System.out.println(om.writeValueAsString(researchDataList));
		
	}
}
