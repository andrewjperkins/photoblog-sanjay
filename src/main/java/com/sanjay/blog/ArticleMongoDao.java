package com.sanjay.blog;

import com.mongodb.*;

import java.sql.Date;
import java.util.ArrayList;

public class ArticleMongoDao<T extends Article> implements BlogDbService<T>  
{
    private DBCollection collection;
    
    public ArticleMongoDao()
    {
        try
        {
            MongoClient mongoClient  = new MongoClient("localhost");
            DB db = mongoClient.getDB("samjaydb");
            collection = db.getCollection("Articles");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public Boolean create(T entity)
    {
        BasicDBObject doc = new BasicDBObject("title", entity.getTitle()).
                                       append("id", entity.getId()).
                                       append("content", entity.getContent()).
                                       append("photoPath", entity.getPhotoPath()).
                                       append("deleted", false).
                                       append("createdAt", new Date(new java.util.Date().getTime()));
        
        collection.insert(doc);
        return true;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public T readOne(int id)
    {
        BasicDBObject query = new BasicDBObject("id", id);
        DBCursor cursor = collection.find(query);
        
        try
        {
            if (cursor.hasNext())
            {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                Article entity = new Article(doc.getString("title"),
                                             doc.getString("content"),
                                             doc.getString("photoPath"),
                                             doc.getInt("id"),
                                             doc.getDate("createdAt"),
                                             doc.getBoolean("deleted")
                );
                
                return (T) entity;
            }
            else
            {
                return null;
            }
        }
        finally
        {
            cursor.close();
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<T> readAll()
    {
        // DBCollection::find() without an arguement will default to find all
        DBCursor cursor = collection.find();
        ArrayList<Article> results = (ArrayList<Article>) new ArrayList<T>();
        
        try
        {
            while (cursor.hasNext())
            {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                Article entity = new Article(doc.getString("title"),
                                             doc.getString("content"),
                                             doc.getString("photoPath"),
                                             doc.getInt("id"),
                                             doc.getDate("createdAt"),
                                             doc.getBoolean("deleted")
                );
                
                results.add(entity);
            }
            
            return (ArrayList<T>) results;
        }
        finally
        {
            cursor.close();
        }
    }
    
    @Override
    public Boolean update(int id, String title, String content, String photoPath)
    {
        BasicDBObject query = new BasicDBObject("id", id);
        DBCursor cursor = collection.find(query);
        
        try
        {
            if (cursor.hasNext())
            {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                
                doc.put("title", title);
                doc.put("content", content);
                doc.put("photoPath", photoPath);
                
                collection.save(doc);
                return true;
            }
            else
            {
                return false;
            }
        }
        finally
        {
            cursor.close();
        }
    }
    
    @Override
    public Boolean delete(int id)
    {
        BasicDBObject query = new BasicDBObject("id", id);
        DBCursor cursor = collection.find(query);
        
        try
        {
            if (cursor.hasNext())
            {
                collection.remove(cursor.next());
                return true;
            }
            else
            {
                return false;
            }
        }
        finally
        {
            cursor.close();
        }
    }
}
