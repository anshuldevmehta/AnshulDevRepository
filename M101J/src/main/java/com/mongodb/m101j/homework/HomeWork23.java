package com.mongodb.m101j.homework;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.m101j.util.Helpers.printJson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class HomeWork23 {
	
	public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("students");
        MongoCollection<Document> collection = database.getCollection("grades");

        //collection.drop();

        // insert 10 documents with two random integers

//        Bson filter = new Document("x", 0)
//        .append("y", new Document("$gt", 10).append("$lt", 90));

        Bson filter = eq("type", "homework");
        Bson sort = descending("student_id","score");
        int student_id_head=0;
        int student_id=0;
        List<Document> all = collection.find(filter).sort(sort).into(new ArrayList<Document>());
        System.out.println("Size of homework scores list:"+all.size());
        for (Document cur : all)
        {
        	student_id=cur.getInteger("student_id");
        	if(student_id_head==0)
        	student_id_head=cur.getInteger("student_id");
        	
        	if(student_id!=student_id_head)
        	{
        		removeRecord(student_id_head,collection);
        		//System.out.println("Going to remove student ID:"+student_id_head);
        		student_id_head=student_id;
        	}
        	//System.out.println("Student ID:"+cur.getInteger("student_id"));
        	//System.out.println("Student Score:"+cur.getDouble("score"));


        	
        	//printJson(cur);
        }

//        long count = collection.count(filter);
//        System.out.println();
//        System.out.println(count);
    }

	private static void removeRecord(int student_id,MongoCollection<Document> collection)
	{
		Bson sort = ascending("score");
		Bson filter = and(eq("student_id", student_id),eq("type","homework"));
		System.out.println("Student id in remove record:"+student_id);
		//collection.find(filter).sort(sort).first().;
		 Document documentToBeRemoved = collection.find(filter).sort(sort).first();
		 int studentIdToBeRemoved=documentToBeRemoved.getInteger("student_id");
		 String scoreTypeToBeRemoved=documentToBeRemoved.getString("type");
		 Double scoreToBeRemoved=documentToBeRemoved.getDouble("score");
		 
		 //System.out.println("Record meeting following criteria will be removed:");
		 //System.out.println("Student ID:"+studentIdToBeRemoved+ " Score Type : " +scoreTypeToBeRemoved + "Score:"+ scoreToBeRemoved);
		 
		 collection.deleteOne(and (eq("student_id",studentIdToBeRemoved),eq("type",scoreTypeToBeRemoved),eq("score",scoreToBeRemoved)));
		
	}
	
	//This worked but here is the actual solution:
	
//	/*
//	 * Copyright 2015 MongoDB, Inc.
//	 *
//	 * Licensed under the Apache License, Version 2.0 (the "License");
//	 * you may not use this file except in compliance with the License.
//	 * You may obtain a copy of the License at
//	 *
//	 *  http://www.apache.org/licenses/LICENSE-2.0
//	 *
//	 * Unless required by applicable law or agreed to in writing, software
//	 * distributed under the License is distributed on an "AS IS" BASIS,
//	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//	 * See the License for the specific language governing permissions and
//	 * limitations under the License.
//	 */
//
//	package course.homework.week2;
//
//	import com.mongodb.MongoClient;
//	import com.mongodb.MongoClientURI;
//	import com.mongodb.client.MongoCollection;
//	import com.mongodb.client.MongoCursor;
//	import com.mongodb.client.MongoDatabase;
//	import org.bson.Document;
//
//	import java.io.IOException;
//
//	import static com.mongodb.client.model.Filters.eq;
//	import static com.mongodb.client.model.Sorts.ascending;
//
//	public class RemoveLowest {
//	    public static void main(final String[] args) {
//	        MongoClient client = new MongoClient();
//	        MongoDatabase numbersDB = client.getDatabase("students");
//	        MongoCollection<Document> grades = numbersDB.getCollection("grades");
//
//	        MongoCursor<Document> cursor = grades.find(eq("type", "homework"))
//	                                .sort(ascending("student_id", "score")).iterator();
//
//	        Object studentId = -1;
//	        try {
//	            while (cursor.hasNext()) {
//	                Document entry = cursor.next();
//	                if (!entry.get("student_id").equals(studentId)) {
//	                    System.out.println("Removing: " + entry);
//	                    Object id = entry.get("_id");
//	                    grades.deleteOne(eq("_id", id));
//
//	                }
//	                studentId = entry.get("student_id");
//	           }
//	        } finally {
//	            cursor.close();
//	        }
//	    }
//	}
}
