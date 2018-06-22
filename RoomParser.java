
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
 
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
 
import java.util.ArrayList;
import java.util.HashMap;
 
public class RoomParser extends DefaultHandler {
	private HashMap<String, Room> rooms = new HashMap<String, Room>();
	static Player globalP;
	private int n = 0; 
	private int e = 1; 
	private int s = 2;
	private int w = 3;
	private String name;
	private String desc;
	private Room currRoom;
	private String actions;
	private int count = 0;
	
	static public BST<Room> roomTree = new BST<>();
	
	
	public HashMap<String, Room> getRooms(){
		return rooms;
	}
	
	public Room getRoom(String name){
		return rooms.get(name);
	}
	

	
	public void assignDirection(int dir, String r){
		if(!rooms.containsKey(r)){
				if(dir == 0){
					Room nr = new Room(r, "");
					rooms.put(r, nr);
					currRoom.North = nr;
				}
				else if(dir == 2){
					Room nr = new Room(r, "");
					rooms.put(r, nr);
					currRoom.South = nr;
				}
				else if(dir == 1){
					Room nr = new Room(r, "");
					rooms.put(r, nr);
					currRoom.East = nr;
				}
				else if(dir == 3){
					Room nr = new Room(r, "");
					rooms.put(r, nr);
					currRoom.West = nr;
				}
			}
		else {
				if(dir == 0){
					currRoom.North = rooms.get(r);
				}
				else if(dir == 2){
					currRoom.South = rooms.get(r);
				}
				else if(dir == 1){
					currRoom.East = rooms.get(r);
				}
				else if(dir == 3){
					currRoom.West = rooms.get(r);
				}
			}
		}
	
	public void startDocument() throws SAXException {
		
    }
	
	public void startElement(String namespaceURI,
                             String localName,
                             String qName, 
                             Attributes atts) throws SAXException {
								 
		if(qName.equals("room")){
			name = atts.getValue("name");
			desc = atts.getValue("description");
				
			
			if(!rooms.containsKey(name)){
				Room r = new Room(name.toLowerCase(), desc);
				currRoom = r;
				rooms.put(name, currRoom);
			}
			else {
				currRoom = rooms.get(name);
				currRoom.setDesc(desc);
			}
			
			
			if(atts.getValue("north") != null){
				assignDirection(n, atts.getValue("north")); 
			}
			if(atts.getValue("east") != null){
				assignDirection(e, atts.getValue("east")); 
			}
			if(atts.getValue("west") != null){
				assignDirection(w, atts.getValue("west")); 
			}
			if(atts.getValue("south") != null){
				assignDirection(s, atts.getValue("south")); 
			}
			
		}
		
		if(qName.equals("child")){
			name = atts.getValue("name");
			desc = atts.getValue("description");
			
			Character c = new Child(name, desc);
			currRoom.addCharacter(c);
		}
		
		if(qName.equals("adult")){
			name = atts.getValue("name");
			desc = atts.getValue("description");
			
			Character a = new Adult(name, desc);
			currRoom.addCharacter(a);
		}
		
		if(qName.equals("player")){
			name = atts.getValue("name");
			desc = atts.getValue("description");
			
			Player p = new Player(name, desc);
			p.room = currRoom;
			currRoom.addCharacter(p);
			globalP = p;
		}
		
		if(qName.equals("item")){
			name = atts.getValue("name").toLowerCase();;
			desc = atts.getValue("description");
			actions = atts.getValue("actions");
			String[] action2 = actions.split(",");
			
			Item i = new Item(name, desc);
			for(int j = 0; j < action2.length; j++){
				if(action2[j].equals("shake")){
					i.addAction(Item.ItemActions.SHAKE);
				}
				else if(action2[j].equals("throw")){
					i.addAction(Item.ItemActions.THROW);
				}
				else if(action2[j].equals("possess")){
					i.addAction(Item.ItemActions.POSSESS);
				}
			}
			currRoom.addItem(i);
			
		}
		
	}
	
	public void endElement(String namespaceURI,
                           String localName,
                           String qName) throws SAXException {
				  
		roomTree.insert(currRoom);
		
		n = 0; 
		e = 1; 
		s = 2;
		w = 3;
		String name = "";
		String desc = "";
		
		
    }
 
    public void endDocument() throws SAXException {
	
    }
 
    public void characters(char ch[], int start, int length)
        throws SAXException {
    }
	
	
	
		

	public static void main(String[] args){
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            InputStream xmlInput  = new FileInputStream("input_sp18.xml");
            SAXParser saxParser = spf.newSAXParser();
            RoomParser sxp = new RoomParser();
            saxParser.parse(xmlInput, sxp);
            //HashMap<String, Room> rooms = sxp.getRooms();
        }
        catch(SAXException|ParserConfigurationException|IOException e){
            e.printStackTrace();
        }
    }
    
}

	