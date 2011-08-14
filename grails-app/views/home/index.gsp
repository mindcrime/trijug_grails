<html>
    <head>
        <title>Welcome to Tri-JUG</title>
        <meta name="layout" content="main_new" />
    </head>
    <body>
 		<h2>Feeds for Tri-JUG</h2>
 		<p />
 		<g:each in="${allEntries}" var="entry">
 			<div style="margin-top:10px; padding-top:7px;padding-bottom:7px;">
 				<a href="${entry.url}">${entry.title}</a>
 			</div>
 		</g:each>
    </body>
</html>