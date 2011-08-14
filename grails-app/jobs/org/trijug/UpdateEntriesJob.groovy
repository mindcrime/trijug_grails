package org.trijug

import com.sun.syndication.feed.synd.SyndEntry
import com.sun.syndication.feed.synd.SyndFeed
import com.sun.syndication.io.SyndFeedInput
import com.sun.syndication.io.XmlReader

class UpdateEntriesJob 
{
	static triggers = {
		simple name: 'mySimpleTrigger', startDelay: 30000, repeatInterval: 30000
	  }
	
	def execute()
	{
		println "Executing UpdateEntriesJob";
		
		List<RssFeed> feeds = RssFeed.list();
		
		for( RssFeed rssFeed in feeds )
		{
			// lookup the feed, and get the FeedUrl
			String url = rssFeed.feedUrl;
			println "Loading from url: ${url}";

			
			// load the feed, and create an Entry for each link in the RssFeed
			URL feedUrl = new URL(url);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = null;
			XmlReader reader = null;
			try
			{
				reader = new XmlReader(feedUrl)
				feed = input.build(reader);
				println "Feed: ${feed.getTitle()}"
				
				List<SyndEntry> entries = feed.getEntries();
				
				for( SyndEntry entry in entries )
				{
					String linkUrl = entry.getLink();
					String linkTitle = entry.getTitle();
										
					println "creating and adding entry for link: ${linkUrl} with title: ${linkTitle}"
				
					Entry existingEntry = Entry.findByUrl( linkUrl );
					if( existingEntry )
					{
						println( "Entry already exists, skipping..." );
						continue;	
					}
					else
					{
						Entry anEntry = new Entry( title: linkTitle, url: linkUrl );
						anEntry.save();
					}
				}
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
			finally
			{
				if( reader != null )
				{
					reader.close();
				}
			}

			
		}
		
		
	}
}
