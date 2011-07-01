import java.util.Vector;

import org.htmlparser.Attribute;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.nodes.RemarkNode;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;



public class htmlParse {
	 void processMyNodes (Node node)
	 {
	     if (node instanceof TextNode)
	     {
	         // downcast to TextNode
	         TextNode text = (TextNode)node;
	         // do whatever processing you want with the text
	         //System.out.println (text.getText ());
	     }
	     if (node instanceof RemarkNode)
	     {
	         // downcast to RemarkNode
	         RemarkNode remark = (RemarkNode)node;
	         // do whatever processing you want with the comment
	         //if(remark.getText() == "title"){
//	           System.out.println (remark.getText());
	         //}  
	     }
	     else if (node instanceof TagNode)
	     {
	         // downcast to TagNode
	         TagNode tag = (TagNode)node;
	         
	         
	         //System.out.println (tag.getText());
	         //if(tag.getTagName().equals("DIV")){ 
	            //if(tag.getAttributeEx("class").getRawValue().equals("pi movie96")){
			if(tag.getAttributeEx("class") != null){
					if(tag.getAttributeEx("class").getRawValue().equals("\"fl oh\"")){
				         NodeList nl = tag.getChildren();
				         if (null != nl)
							try {
								for (NodeIterator i = nl.elements(); i.hasMoreNodes(); ){
									Node n = i.nextNode();
									if(n instanceof TagNode){
									TagNode t = (TagNode) n; 
									System.out.println(t.getAttributeEx("title").getRawValue());
									}
								}
							} catch (ParserException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
			}
	            //}
	        // Attribute att = tag.getAttributeEx("class");
	         //System.out.println(att.getRawValue());
	         //}  
	         // do whatever processing you want with the tag itself
	         // ...
	         // process recursively (nodes within nodes) via getChildren()
	         NodeList nl = tag.getChildren();
	         if (null != nl)
				try {
					for (NodeIterator i = nl.elements (); i.hasMoreNodes (); )
					     processMyNodes (i.nextNode ());
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     }
	 }
	 
	 public static void main (String[] args){
		 
		 Parser parser = null;
		try {
			parser = new Parser ("http://www.cinemaki.com.br/shows");
		} catch (ParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 htmlParse process = new htmlParse();
		 
		 try {
			for (NodeIterator i = parser.elements (); i.hasMoreNodes (); )
			     process.processMyNodes (i.nextNode ());
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 
	 }
}
