package com.sanjay.blog;

import java.util.ArrayList;

public interface BlogDbService<T> 
{
    public Boolean create(T entity);
    public T readOne(int id);
    public ArrayList<T> readAll();
    public Boolean update(int id, String title, String content, String photoPath);
    public Boolean delete(int id);
}

