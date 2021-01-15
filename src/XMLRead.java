import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class XMLRead extends DefaultHandler {
    private ArrayList<Category> categories = new ArrayList<Category>();
    private String chars;
    private Note currentNote;

    public ArrayList<Category> getCategories() { return categories; }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
        if (localName.equals("c")) {
            Category c = new Category();
            c.name = atts.getValue("name");
            categories.add(c);
        }
        else if (localName.equals("n")) {
            currentNote = new Note();
            currentNote.id = Integer.parseInt(atts.getValue("id"));
            categories.get(categories.size()-1).notes.add(currentNote);
            chars = "";
        }
    }

    public void characters(char[] ch, int start, int length) {
        chars = chars + new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName) {
        if (localName.equals("text"))
            currentNote.text = chars.trim();
        else if (localName.equals("media"))
            currentNote.media = chars.trim();
        else if (localName.equals("url"))
            currentNote.url = chars.trim();
    }
}

