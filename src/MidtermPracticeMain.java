import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;


/*
 * Prompt Write a program that graphically walks through the contents of a JSON or XML file
Both can be found in the Sessions folder: stats.json, notes.xml
The program should be able to scroll through items one-by-one (slideshow) or search based on whatever you decide
It should show images of some kind (again, you decide)
 */

public class MidtermPracticeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlrdr = saxParser.getXMLReader();
            XMLRead noterdr = new XMLRead();
            xmlrdr.setContentHandler(noterdr);
            xmlrdr.parse("http://www.textmuse.com/admin/notes.php?app=23456&sponsor=6");
            
            try {
            xmlrdr.parse("notes.xml");
            }catch (Exception ex) { System.out.printf("Failed with error %s\n", ex.getLocalizedMessage());}
            
            ArrayList<Category> cs = noterdr.getCategories();
            for (Category c: cs) {
                System.out.printf("%s\n", c.name);
                for (Note n: c.notes) {
                    System.out.printf("\t%s\n", n.text);
                }
            }
        }
        catch (Exception ex) { System.out.printf("Failed with error %s\n", ex.getLocalizedMessage());}
    }

	}


