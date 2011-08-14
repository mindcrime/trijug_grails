package org.trijug


class HomeController {

	def index = {
	
		List<Entry> allEntries = new ArrayList<Entry>();

		def page = params.page;
		if( !page )
		{
			page = 1;
		}
		else 
		{
			page = Integer.parseInt( page );	
		}
				
		// get the entries via query now...
		List<Entry> queryResults =
		Entry.executeQuery( "select entry from Entry as entry order by entry.dateCreated desc",
			[], [max: page * 10 ]);

		allEntries.addAll( queryResults );
		
		[allEntries: allEntries];	
	}
	
	def getEntriesHtml = {
	
		List<Entry> allEntries = new ArrayList<Entry>();

		def page = params.page;
		if( !page )
		{
			page = 1;
		}
		else 
		{
			page = Integer.parseInt( page );	
		}
				
		// get the entries via query now...
		List<Entry> queryResults =
		Entry.executeQuery( "select entry from Entry as entry order by entry.dateCreated desc",
			[], [max: page * 10 ]);

		allEntries.addAll( queryResults );
			
		render(template:"/allEntries",model:[allEntries:allEntries]);
		
	}
	
}