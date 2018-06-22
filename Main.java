import java.util.Scanner;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Timer;

public class Main {
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter XML file: ");
		String iFile = input.nextLine();
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		RoomParser sxp = new RoomParser();
		
        try {
			InputStream xmlInput  = new FileInputStream("input_sp18.xml");
            SAXParser saxParser = spf.newSAXParser();
         
            saxParser.parse(iFile, sxp);
        }
        catch(SAXException|ParserConfigurationException|IOException e){
			System.out.println("Not a valid file name.");
        }
		
		HashMap<String, Room> rooms = sxp.getRooms();

		Player player = RoomParser.globalP;
		player.initTimer(60);
		System.out.println(player.getTime());
		System.out.println("add time: to add more time, time: to get time \n");
		System.out.println("Enter 'help' for a list of commands: ");
		
		
		
		while(player.player(input)){
		}
	}
	
	
}