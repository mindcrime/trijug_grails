import grails.util.Environment

import org.trijug.RssFeed

class BootStrap {

    def init = { servletContext ->
    
		switch( Environment.current )
		{
			case Environment.DEVELOPMENT:
				createSomeFeeds();
				break;
			case Environment.PRODUCTION:
				println "No special configuration required";
				break;
		}
			
	}
    
	def destroy = {
    
		
	}
	
	private void createSomeFeeds()
	{
		RssFeed feedOne = new RssFeed( feedUrl: 'http://feeds.dashes.com/AnilDash' );
		if( feedOne.save() )
		{
			println( "created feedOne" );
		}
		else 
		{
			println( "failed to create feedOne" );
		}	
		
		RssFeed feedTwo = new RssFeed( feedUrl: 'http://www.kmworld.com/rss/default.aspx?Criteria=popularity&days=10&rows=10' );
		if( feedTwo.save() )
		{
			println( "created feedTwo" );
		}
		else 
		{
			println( "failed to create feedTwo" );
		}	

		RssFeed feedThree = new RssFeed( feedUrl: 'http://www.jroller.com/rss' );
		if( feedThree.save() )
		{
			println( "created feedThree" );
		}
		else
		{
			println( "failed to create feedThree" );
		}

		RssFeed feedFour = new RssFeed( feedUrl: 'http://www.linuxjournal.com/news.rss' );
		if( feedFour.save() )
		{
			println( "created feedFour" );
		}
		else
		{
			println( "failed to create feedFour" );
		}

		
	}
	
}
