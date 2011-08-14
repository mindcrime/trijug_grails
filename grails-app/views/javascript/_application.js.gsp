    
	$(document).ready( 
			function()
		 	{
				$('#loadMoreLink').data("page", 1 );
				// setup an onclick handler for the status submit button
				// and serialize the associated form along with it...
				
				$('#loadMoreLink').bind( 'click', function() {
					
					var page = $('#loadMoreLink').data("page");
					page = page +1;
					$('#loadMoreLink').data("page", page );
					
					if( page > 1 )
					{
						// load more content from the server, pass the page so it knows how much
						// to return us...
						
						$('#allEntries').load( "${ createLink(controller:'home', action:'getEntriesHtml')}" + "?page=" + page );
					}
					
					return false;
				} );
				
				
			} );    