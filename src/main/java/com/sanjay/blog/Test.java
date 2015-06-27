package com.sanjay.blog;

import static spark.SparkBase.staticFileLocation;

public class Test 
{   
    public static void main(String[] args)
    {  
        staticFileLocation("/public");
        new BlogController();
    }
}