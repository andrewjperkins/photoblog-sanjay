<#if hasNoArticles??>
	<h3>${hasNoArticles}</h3>
<#else>
	<#list articles as article>
		<h4>${article.getTitle()}</h4>
		<p>${article.getCreatedAt()}</p>
		<a href="${article.getPhotoPath()}" target="blank">
			<img src="${article.getPhotoPath()}" class="img-responsive" alt="Responsive image">
		</a>
		<p>${article.getContent()}</p>
		<p>${article.getEditLink()} | ${article.getDeleteLink()}</p>
		<hr>
	</#list>
</#if>