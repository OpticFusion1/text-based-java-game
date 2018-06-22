import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

class Player extends Character{
	private Timer timer;
	private AtomicInteger timeLeft;
	private boolean cheats = false;
	
	public Player(String name, String desc){
		super(name, desc);
	}
	
	public boolean player(Scanner s){
		String input = s.nextLine();
		input = input.toLowerCase();
		String str = "";
		String str2 = "";
		if(input.contains(":")){
			String[] newInput = input.split(":");
			str += newInput[0];
			str2 += newInput[1];
		}
		
		if(input.equals("help")){
			System.out.println("Full list of commands: \n" + 
			"Look: view the room you are in and the characters/items in it.\n" +				"North, East, South, West: Directional movements the player may take.\n" +
			"shake:[itemname], possess:[itemname], throw:[itemname] - these commands allow manipulating the objects in the room, causing a reaction from the other Characters.\n" +
			"'Exit' to quit the game.\n" +
			"'cheatmode' - enables cheats.\n");
			return true;
		}			
		else if(input.equals("look")){
			System.out.println(room.toString());
			return true;
		}
		else if(input.equals("north")){
			if(room.North != null){
				System.out.println("You head north.");
				room = room.North;
				System.out.println(getTime() + " Seconds left.");
			}
			else {
				System.out.println("There is no room there!");
			}
			return true;
		}
		else if(input.equals("south")){
			if(room.South != null){
				System.out.println("You head south.");
				room = room.South;
				System.out.println(getTime() + " Seconds left.");
			}
			else {
				System.out.println("There is no room there!");
			}
			return true;
		}
		else if(input.equals("east")){
			if(room.East != null){
				System.out.println("You head East.");
				room = room.East;
				System.out.println(getTime() + " Seconds left.");
			}
			else {
				System.out.println("There is no room there!");
			}
			return true;
		}
		else if(input.equals("west")){
			if(room.West != null){
				System.out.println("You head West.");
				room = room.West;
				System.out.println(getTime() + " Seconds left.");
			}
			else {
				System.out.println("There is no room there!");
			}
			return true;
		}
		else if(str.equals("shake")){
			setAction(str, str2);
			return true;
		}
		else if(str.equals("possess")){
			setAction(str, str2);
			return true;
		}
		else if(str.equals("throw")){
			setAction(str, str2);
			return true;
		}
		else if(input.equals("exit")){
			return false;
		}
		else if(input.equals("quit")){
			return false;
		}
		else if(input.equals("time")){
			System.out.println("You have: " + getTime() + " left.");
			return true; 
		}
		else if(input.equals("add time")){
			setTime(30);
			System.out.println(getTime() + " Seconds left.");
			return true;
		}
		else if(input.equals("cheatmode")){
			cheats = true;
			System.out.println("You have activated cheatmode! Here are your options: \n");
			System.out.println("look:[roomname] - to see a rooms contents. \n");
			System.out.println("look:all - to see every rooms content. \n");
			return true;
		}
		else if(input.equals("look:all")){
			if(cheats) {
				RoomParser.roomTree.printInOrder();
			}
			return true;
		}
		else if(str.equals("look")){
			if(cheats) {
				System.out.println(RoomParser.roomTree.search(new Room(str2, "")));
				
			}
			return true;
		}
		else if(input.equals("nocheatmode")){
			cheats = false;
			System.out.println("Cheats have been disabled.");
			return true;
		}
		else {
			System.out.println("That is not a valid command. \n");
			return true;
		}
	}
	
	
	
	public void initTimer(int secs){
		timeLeft = new AtomicInteger(secs);
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				int tl = timeLeft.decrementAndGet();
				if(tl == 0){
					System.out.println("You failed to spook everyone in time! You lose!");
					System.exit(0);
				}
			}
		};
		timer = new Timer();
		timer.schedule(task, 0, 1000);
	}
	
	public void setTime(int t){
		timeLeft.addAndGet(t);
	}
	
	public int getTime(){
		return timeLeft.get();
	}
	
	

	
	//Method responsible for finding items in the players room, and their available actions
	public void setAction(String action, String itemName) {
		
		for(int i = 0; i < room.items.size(); i++){
			if(room.items.get(i).name.equals(itemName)){
				if(action.equals("shake")){
					for(int j = 0; j < room.items.get(i).actions.length; j++){
						if(room.items.get(i).actions[j] == Item.ItemActions.SHAKE){
							room.items.get(i).action = Item.ItemActions.SHAKE;
							room.items.get(i).breakCount++;
							if(room.items.get(i).breakCount == 3){
								room.items.get(i).broken = true;
								room.items.get(i).actions[j] = Item.ItemActions.BROKEN;
								System.out.println("The item is broken!");
							}
							else {
								System.out.print("You shake the item!\n");
							}
							break;
						}
						else {
							System.out.print("This action doesn't apply to this item\n");
							break;
						}
					}
				}
				else if(action.equals("possess")){
					for(int j = 0; j < room.items.get(i).actions.length; j++){
						if(room.items.get(i).actions[j] == Item.ItemActions.POSSESS){
							room.items.get(i).action = Item.ItemActions.POSSESS;
							room.items.get(i).breakCount++;
							if(room.items.get(i).breakCount == 3){
								room.items.get(i).broken = true;
								room.items.get(i).actions[j] = Item.ItemActions.BROKEN;
								System.out.println("The item is broken!");
							}
							else {
								System.out.print("You possess the item!\n");
							}
							break;
						}
						else {
							System.out.print("This action doesn't apply to this item\n");
							break;
						}
					}
				}
				else if(action.equals("throw")){
					for(int j = 0; j < room.items.get(i).actions.length; j++){
						if(room.items.get(i).actions[j] == Item.ItemActions.THROW){
							room.items.get(i).action = Item.ItemActions.THROW;
							room.items.get(i).breakCount++;
							if(room.items.get(i).breakCount == 3){
								room.items.get(i).broken = true;
								room.items.get(i).actions[j] = Item.ItemActions.BROKEN;
								System.out.println("The item is broken!");
							}
							else {
								System.out.print("You throw the item!\n");
							}
							break;
						}
						else {
							System.out.print("This action doesn't apply to this item\n");
							break;
						}
					}
				}
				}
			}
		}
		

}	

