package com.sanjay.blog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article 
{
    private String title;
    private String photoPath;
    private String content;
    private final Date createdAt;
    private final Integer id;
    private Boolean deleted;
 
    public Article(String title, String content, String photoPath, Integer size) 
    {
        this.title = title;
        this.content = content;
        this.photoPath = photoPath;
        this.createdAt = new Date();
        this.id = size;
        this.deleted = false;
    }
    
    public Article(String title, String content, String photoPath, Integer id, Date createdAt, Boolean deleted)
    {
        this.title = title;
        this.content = content;
        this.photoPath = photoPath;
        this.createdAt = createdAt;
        this.id = id;
        this.deleted = deleted;
    }

    public String getTitle() 
    {
        return title;
    }

    public String getContent() 
    {
        return content;
    }
    
    public String getPhotoPath()
    {
        return photoPath;
    }
    
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }
    
     public void setPhotoPath(String photoPath)
    {
        this.photoPath = photoPath;
    }

    public Integer getId() 
    {
        return id;
    }

    public void delete() 
    {
        this.deleted = true;
    }

    public Boolean readable() 
    {
        return !this.deleted;
    }

    public String getCreatedAt() 
    {
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(this.createdAt);
    }

    public String getEditLink() 
    {
        return "<a href='/admin/article/update/" + this.id + "'>Edit</a>";
    }

    public String getDeleteLink() 
    {
        return "<a href='/admin/article/delete/" + this.id + "'>Delete</a>";
    }
}
