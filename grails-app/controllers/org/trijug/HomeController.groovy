package org.trijug

class HomeController {

	def index = {
	
		List<Entry> allEntries = new ArrayList<Entry>();
		
		Entry entry = new Entry( title: "Whatever", url: "http://www.example.com" );
		allEntries.add( entry );
		
		[allEntries: allEntries];	
	}
}
