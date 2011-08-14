<html>
    <head>
        <title>Welcome to Tri-JUG</title>
        <meta name="layout" content="main_new" />
    </head>
    <body>
 		<h2>Message Sender</h2>
 		<p />
		<g:form action="submitMessage" controller="message" method="POST">
			<g:textArea name="messageBody"></g:textArea>
			<g:submitButton name="Send Message"></g:submitButton>
		</g:form>	
		
    </body>
</html>