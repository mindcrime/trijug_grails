<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
		<link rel="stylesheet" type="text/css" href="css/reset-min.css" />
        <link rel="stylesheet" type="text/css" href="css/cssbase/base-min.css" />
        <link rel="stylesheet" type="text/css" href="css/fonts-min.css" />
        <link rel="stylesheet" type="text/css" href="css/grids-min.css" />        
        <g:layoutHead />
    </head>
    <body>

      	<div id="body" class="yui3-g">
            
			<div class="yui3-u-5-24">
 
 				<p style="font-weight:bold;float:right;margin-right:45px;">
 					<g:render template="/leftSidebar" />				
 				</p>
 				
        	</div>
        	
        	<div class="yui3-u-7-12">
        	
	    		<!-- layout main content area -->
	    	   	<g:layoutBody /> 
 
        	</div>
    	
    		<div class="yui3-u-5-24">
    		
	    		<p style="font-weight:bold;float:left;margin-left:45px;">
	    			<g:render template="/rightSidebar" />	    		
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