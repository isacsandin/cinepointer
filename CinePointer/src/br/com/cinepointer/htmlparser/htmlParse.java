package br.com.cinepointer.htmlparser;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;


public class htmlParse {

	static String url = "http://www.cinemaki.com.br/shows";
	//static String nextPage = "/shows";

	void processMyNodes (Node node)
	{

		if (node instanceof TagNode)
		{
			// downcast to TagNode
			TagNode tag = (TagNode)node;
			
			if(tag.getAttributeEx("class") != null){
				if(tag.getAttributeEx("class").getRawValue().equals("\"fl oh\"")){
					processFilmDiv(tag);
				}
				if(tag.getAttributeEx("class").getRawValue().equals("\"newpager\"")){
					url = processNextPageDiv(tag);
				}
			}
			NodeList nl = tag.getChildren();
			if (null != nl)
				try {
					for (NodeIterator i = nl.elements (); i.hasMoreNodes (); )
						processMyNodes (i.nextNode ());
				} catch (ParserException e) {
					e.printStackTrace();
				}
		}
	}

	private String processNextPageDiv(TagNode tag) {
		NodeList nl = tag.getChildren();
		if (null != nl){
			try {
				for (NodeIterator i = nl.elements(); i.hasMoreNodes(); ){
					Node n = i.nextNode();
					if(n instanceof TagNode){
						TagNode t = (TagNode) n;
						if(t.getAttributeEx("class") != null){
							
							//esse avanca para a proxima pagina
							if(t.getAttributeEx("class").getRawValue().equals("\"fr\"")){
								Node[] nodes = t.getChildren().toNodeArray();
								for (Node tmp : nodes) {
									if(tmp instanceof LinkTag){
										LinkTag lt = (LinkTag) tmp; 
										return lt.getLink();
									}
								}

							}
						}									
					}
				}
			} catch (ParserException e) {
				return null;
			}
		}	
		return null;
	}

	private void processFilmDiv(TagNode tag) {
		NodeList nl = tag.getChildren();
		if (null != nl)
			try {
				for (NodeIterator i = nl.elements(); i.hasMoreNodes(); ){
					Node n = i.nextNode();
					if(n instanceof TagNode){
						TagNode t = (TagNode) n; 
						System.out.println("\n"+t.getAttributeEx("title").getRawValue());
					}
					if(n instanceof LinkTag){
						LinkTag t = (LinkTag) n; 
						System.out.println(t.getLink());
						Node[] nl1 = t.getChildrenAsNodeArray();
						for (Node bla : nl1) {
							if(bla instanceof ImageTag){
								ImageTag it = (ImageTag) bla; 
								System.out.println(it.getImageURL());
							}
						}

					}

				}
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void main (String[] args){

		Parser parser = null;
		htmlParse process = new htmlParse();

		while (url !=null){
			System.out.println("===================================================");
			System.out.println(url);
			System.out.println("===================================================");
			try {
				parser = new Parser (url);
			} catch (ParserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				for (NodeIterator i = parser.elements (); i.hasMoreNodes (); )
					process.processMyNodes (i.nextNode ());
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("url e nulo");
	}

}
