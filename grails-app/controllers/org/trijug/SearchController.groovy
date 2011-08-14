package org.trijug

import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpException
import org.apache.commons.httpclient.HttpMethod
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.queryParser.MultiFieldQueryParser
import org.apache.lucene.queryParser.QueryParser
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.search.Query
import org.apache.lucene.search.ScoreDoc
import org.apache.lucene.search.TopDocs
import org.apache.lucene.store.Directory
import org.apache.lucene.store.FSDirectory
import org.apache.lucene.store.NIOFSDirectory
import org.apache.lucene.util.Version
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.parser.Parser
import org.apache.tika.sax.BodyContentHandler

class SearchController {

	def index = 
	{
		[];	
	}
	
	def doSearch = 
	{	
	
		String queryString = params.queryString;
		println "searching Users, queryString: ${queryString}";
		
		String indexDirLocation = "/tmp/trijug_grails_index";
		File indexDir = new File( indexDirLocation );
		Directory fsDir = FSDirectory.open( indexDir );
		
		IndexSearcher searcher = new IndexSearcher( fsDir );
	
		String[] searchFields = ['title', 'url', 'content'];
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_30, searchFields, new StandardAnalyzer(Version.LUCENE_30));
		Query query = queryParser.parse(queryString);
		
		TopDocs hits = searcher.search(query, 40);
		
		def entries = new ArrayList<Entry>();
		ScoreDoc[] docs = hits.scoreDocs;
		for( ScoreDoc doc : docs )
		{
			Document result = searcher.doc( doc.doc );
			
			
			String id = result.get("id")
			println( id + " " + result.get("title"));
		
			entries.add( Entry.findById(id));
		
		}
		
		println "found some entries: ${entries.size()}";
		
		render( view:'displaySearchResults', model:[allEntries:entries]);
	}
	
	
	def rebuildIndex =
	{	
		println "doing rebuildIndex";
		List<Entry> allEntries = Entry.list();
			
		// add document to index
		String indexDirLocation = "/tmp/trijug_grails_index";
		Directory indexDir = new NIOFSDirectory( new java.io.File( indexDirLocation ) );
		IndexWriter writer = new IndexWriter( indexDir, new StandardAnalyzer(Version.LUCENE_30), true, IndexWriter.MaxFieldLength.UNLIMITED);
		
		try
		{
			writer.setUseCompoundFile(true);
	
			println "about to process all entries";
		
			for( Entry entry : allEntries )
			{
				println "processing entry with id: ${entry.id} and title: ${entry.title}";
				
				Document doc = new Document();
			
				doc.add( new Field( "id", Long.toString(entry.id), Field.Store.YES, Field.Index.NOT_ANALYZED ) );
				doc.add( new Field( "url", entry.url, Field.Store.YES, Field.Index.NOT_ANALYZED ) );
				doc.add( new Field( "title", entry.title, Field.Store.YES, Field.Index.ANALYZED ) );
				
				
				/* TODO: use HttpClient to load the page, then extract the content and index it.
				 * We'll assume HTTP only links for now... */
	
				
				HttpClient client = new HttpClient();
				 println "establishing httpClient object to download content for indexing";
				 
				//establish a connection within 10 seconds
				client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
				String url = entry.url;
				HttpMethod method = new GetMethod(url);
	
				String responseBody = null;
				try{
					println "executing http request";
					client.executeMethod(method);
					// responseBody = method.getResponseBodyAsString();
				} catch (HttpException he) {
					System.err.println("Http error connecting to '" + url + "'");
					System.err.println(he.getMessage());
					continue;
				} catch (IOException ioe){
					ioe.printStackTrace();
					System.err.println("Unable to connect to '" + url + "'");
					continue;
				}
	
				// println "responseBody: \n ${responseBody}"
				
				// extract text with Tika
				InputStream input = method.getResponseBodyAsStream();
				org.xml.sax.ContentHandler textHandler = new BodyContentHandler(-1);
				Metadata metadata = new Metadata();
				// PDFParser parser = new PDFParser();
				Parser parser = new AutoDetectParser();
				parser.parse(input, textHandler, metadata);
				
				// println("Title: " + metadata.get("title"));
				// println("Author: " + metadata.get("Author"));
				// println("content: " + textHandler.toString());
				String content = textHandler.toString();
				doc.add( new Field( "content", content, Field.Store.NO, Field.Index.ANALYZED, Field.TermVector.YES ) );
				
				
				println "adding document to writer";
				writer.addDocument( doc );
	
			}
		
			println "optimizing index";
			writer.optimize();
		}
		finally
		{
			
			try
			{
				if( input != null )
				{
					input.close();
				}
			}
			catch( Exception e )
			{
				// ignore this for now, but add a log message at least
			}
		
			try
			{
				if( client != null )
				{
					println "calling connectionManager.shutdown()";
					client.getConnectionManager().shutdown();
				}
			}
			catch( Exception e )
			{
				// ignore this for now, but add a log message at least
			}
			
			try
			{
				if( writer != null )
				{
					writer.close();
				}
			}
			catch( Exception e ) {
				// ignore this for now, but add a log message at least
			}
			
			try
			{
				if( indexDir != null )
				{
					indexDir.close();
				}
			}
			catch( Exception e )
			{
				// ignore this for now, but add a log message at least
			}
		}
					
	}
}
