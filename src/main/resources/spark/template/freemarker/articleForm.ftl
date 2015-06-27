<form role="form" id="article-create-form" method='POST' <#if article??>action="/admin/article/update/${article.getId()}" <#else>action="/admin/article/create"</#if>>
    
    <!-- Title -->
    <div class="form-group">
        <label for="title">Title: </label>
        <div>
            <input class="form-control" type="text" id="title" name='article-title' placeholder="Enter a new title" <#if article??>value="${article.getTitle()}"</#if> />
        </div>
    </div>

    <!-- Photo -->
    <div class="form-group">
        <label for="photoPath">Photo: </label>
        <div>
            <input class="form-control" type='text' id="photoPath" name='article-photoPath' placeholder="Enter a new photo URL" <#if article??>value="${article.getPhotoPath()}"</#if> />
        </div>
    </div>
    <#if aritcle??>
        <input type='hidden' name='article-id' value="${article.getId()}" />
    </#if>

    <!-- Content -->
    <label for="content">Content</label>
    <textarea class="form-control" name='article-content' id="content" rows="10" cols="50" form="article-create-form" placeholder="Enter article content">
        <#if article??>
            ${article.getContent()}
        </#if>
    </textarea>
    
    <!-- Submit -->
    <input type='submit' <#if article??>value="Update" <#else>value="Publish"</#if> class="btn btn-primary" form="article-create-form" />
</form>