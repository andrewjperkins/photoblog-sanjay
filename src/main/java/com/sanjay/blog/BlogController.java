package com.sanjay.blog;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;

public class BlogController 
{
    private static final String PASSWORD = "thepathwithin";
    private static final String SESSION_NAME = "admin";
    
    private static BlogDbService<Article> articleDbService = new ArticleMongoDao<>();
    
    public BlogController()
    {
        initAllHandlers();
    }
    
    private void initAllHandlers()
    {
        // ----------------------------------------------------------
        //                     PUBLIC HANDLERS
        // ----------------------------------------------------------
        
        // Blog Page
        get("/", (request, response) -> {
            Map<String, Object> viewObjects = new HashMap<>();
            ArrayList<Article> articles = articleDbService.readAll();
            
            if (articles.isEmpty())
            {
                viewObjects.put("hasNoArticles", "There are no articles to display at this time.");
            }
            else
            {
                Deque<Article> showArticles = new ArrayDeque<>();
                
                for (Article article : articles)
                {
                    if (article.readable())
                    {
                        showArticles.addFirst(article);
                    }
                }
                
                viewObjects.put("articles", showArticles);
            }
            
            viewObjects.put("templateName", "articleListPublic.ftl");
            
            return new ModelAndView(viewObjects, "layoutPublic.ftl");
        }, new FreeMarkerEngine());
        
        
        // Login Page
        get("/login", (request, response) -> {
            Map<String, Object> viewObjects = new HashMap<>();
            
            return new ModelAndView(viewObjects, "login.ftl");
        }, new FreeMarkerEngine());
        
        post("/login", (request, response) -> {
            boolean authenticated = request.queryParams("password").equals(PASSWORD);
                    
            if (!authenticated)
            {
                return "Incorrect password. Please <a href='/login'>try again</a>";
            }
            else
            { 
                request.session(true);
                request.session().attribute(SESSION_NAME, "sanjay");
                
                response.status(200);
                response.redirect("/admin/home");
                return "";
            }
        });
        
        // ----------------------------------------------------------
        //                      ADMIN HANDLERS
        // ----------------------------------------------------------
        
        // Blog Page (Admin)
        get("/admin/home", (request, response) -> {
            Map<String, Object> viewObjects = new HashMap<>();
            ArrayList<Article> articles = articleDbService.readAll();
            
            if (articles.isEmpty())
            {
                viewObjects.put("hasNoArticles", "There are no articles to display at this time.");
            }
            else
            {
                Deque<Article> showArticles = new ArrayDeque<>();
                
                for (Article article : articles)
                {
                    if (article.readable())
                    {
                        showArticles.addFirst(article);
                    }
                }
                
                viewObjects.put("articles", showArticles);
            }
            
            viewObjects.put("templateName", "articleList.ftl");
            
            return new ModelAndView(viewObjects, "layout.ftl");
        }, new FreeMarkerEngine());
        
        // Create
        get("/admin/article/create", (request, response) -> {
            Map<String, Object> viewObjects = new HashMap<>();
            viewObjects.put("templateName", "articleForm.ftl");
            
            return new ModelAndView(viewObjects, "layout.ftl");
        }, new FreeMarkerEngine());
        
        post("/admin/article/create", (request, response) -> {
            String title = request.queryParams("article-title");
            String content = request.queryParams("article-content");
            String photoPath = request.queryParams("article-photoPath");       
            Article article = new Article(title, content, photoPath, articleDbService.readAll().size());
            
            articleDbService.create(article);
            
            response.status(201);
            response.redirect("/admin/home");
            return "";
        });
        
        // Read
        // TODO: this will be determined when Page object is created. Will limit number of articles viewed per page
        
        // Update
        get("/admin/article/update/:id", (request, response) -> {
            Integer id = Integer.parseInt(request.params(":id"));
            Map<String, Object> viewObjects = new HashMap<>();
            
            viewObjects.put("templateName", "articleForm.ftl");
            viewObjects.put("article", articleDbService.readOne(id));
            
            return new ModelAndView(viewObjects, "layout.ftl");
        }, new FreeMarkerEngine());
        
        post("/admin/article/update/:id", (request, response) -> {
            Integer id = Integer.parseInt(request.params(":id")); // TODO: This is not working. 
            String title = request.queryParams("article-title");
            String photoPath = request.queryParams("article-photoPath");
            String content = request.queryParams("article-content");
            
            articleDbService.update(id, title, content, photoPath);
            
            response.status(200);
            response.redirect("/admin/home");
            return "";
        });
        
        // Delete
        get("/admin/article/delete/:id", (request, response) -> {
            Integer id = Integer.parseInt(request.params(":id"));
            
            articleDbService.delete(id);
            
            response.status(200);
            response.redirect("/admin/home");
            return "";
        });
        
        get("admin/logout", (request, response) -> {
            request.session().removeAttribute(SESSION_NAME);
            
            response.status(200);
            return "You have successfully logged out. Back to <a href='/login'>login page</a>";
        });
        
        // ----------------------------------------------------------
        //                         FILTERS
        // ----------------------------------------------------------
        
        before("/admin/*", (request, response) -> {
           String sessionName = request.session().attribute(SESSION_NAME);
           
           if (sessionName == null)
           {
               halt(401, "401: You are not authorized to view this page."); 
           }
        });
    }
}
