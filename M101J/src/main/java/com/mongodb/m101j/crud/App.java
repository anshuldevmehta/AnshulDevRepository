/*
 * Copyright 2015 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

public class App {
    public static void main(String[] args) 
    {
    	/**
    	 * Used to set certain mongo client options, refer documentation
    	 * for details
    	 */
    	MongoClientOptions mco = MongoClientOptions.builder().build();
    	//MongoClient is a heavy weight object, do not create more than
    	//necessary
    	MongoClient client = new MongoClient();
    	/*
    	 * MongoDatabase is a light weight object, only contains
    	 * configurations and a few db properties
    	 * 
    	 * MongoDatabase is an immutable object
    	 * With methods can be used to configure, make sure
    	 * that after calling a with we save back to the database
    	 * instance
    	 */
    	MongoDatabase db=client.getDatabase("test");
    	/**
    	 * MongoCollection is the gatway to all CRUD operations.
    	 * 
    	 * Mongo collection is an immutable object, it is actually a
    	 * generic interface.
    	 * 
    	 * MongoCollection is a collection of Documents,we can have the
    	 * generic type as Document or BsonDocument.
    	 */
    	MongoCollection<Document> collection = db.getCollection("test");
    	}
}
