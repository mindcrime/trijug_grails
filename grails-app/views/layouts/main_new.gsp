<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
		<link rel="stylesheet" type="text/css" href="/css/reset-min.css" />
        <link rel="stylesheet" type="text/css" href="/css/cssbase/base-min.css" />
        <link rel="stylesheet" type="text/css" href="/css/fonts-min.css" />
        <link rel="stylesheet" type="text/css" href="/css/grids-min.css" />
        <g:javascript library="jquery-1.4" />
        <g:javascript library="jquery.timers-1.2" />
       	<script type="text/javascript">
        	<g:render template="/javascript/application.js"/>
    	</script>
                
        <g:layoutHead />
    </head>
    <body>

      	<div id="body" class="yui3-g">
            
			<div class="yui3-u-5-24">
 
 				<p style="font-weight:bold;float:left;margin-left:45px;margin-top:190px;">
					<ul>
						<li><a href="#">Left Item One</a></li>
						<li><a href="#">Left Item Two</a></li>
						<li><a href="#">Left Item Three</a></li>
						<li><a href="#">Left Item Four</a></li>
					</ul>    		
	    		</p>
 				
        	</div>
        	
        	<div class="yui3-u-7-12">
        	
	    		<!-- layout main content area -->
	    	   	<g:layoutBody /> 
 
        	</div>
    	
    		<div class="yui3-u-5-24">
    		
	    		<p style="font-weight:bold;float:left;margin-left:45px;margin-top:190px;">
					<ul>
						<li><a href="#">Right Item One</a></li>
						<li><a href="#">Right Item Two</a></li>
						<li><a href="#">Right Item Three</a></li>
						<li><a href="#">Right Item Four</a></li>
					</ul>    		
	    		</p>
    		</div>
    	</div> <!--  "body" -->
               
       	<div id="footer">
                    
            <!-- footer -->
            <div>
                 <center>FOOTER</center>
           	</div>
       	</div>  


    </body>
</html>