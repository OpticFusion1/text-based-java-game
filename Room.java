import java.util.ArrayList;

class Room implements Comparable<Room>{
	//private Character[] characterArray;
	private LinkedList<Character> characterArray = new LinkedList<Character>();
	protected String name;
	String desc;
	public Room North, South, East, West;
	public ArrayList<Item> items;
	
	@Override
	public int compareTo(Room r){
		if(this.name.compareTo(r.name) > 0) return 1;
		if(this.name.compareTo(r.name) < 0) return -1;
		else return 0;
	}
	
	public void setDesc(String desc){
		this.desc = desc;
	}
	
	public Room(String name, String desc){
		this.name = name;
		this.desc = desc;
		items = new ArrayList<>();
	}
	
	public void addCharacter(Character a){
		
		if(characterArray.size() < 5){
			characterArray.append(a);
			a.room = this;
		}
		else {
			System.out.println(a.name + " can not join, " + "Room is full!\n");
		}
	}
	
	
	public void addItem(Item i){
		items.add(i);
	}
	
	public LinkedList<Character> getCharacters(){
		return characterArray;
	}
	
	
	public String getItems() {
		String str = "\n";
		
		for(int i = 0; i < items.size(); i++){
			if (items.get(i) == null) {
				str += "";
			}
			else {
				str +=  items.get(i).toString() + "\n";
			}
		}
		return str;

	}
	
	
	public String getNeighbors(){
		String str = "\n";
		
		if(North != null){
			str += " North: " + North.name + "\n";
		}
		else {
			str += " North: None" + "\n";
		}
		
		if(South != null){
			str += " South: " + South.name + "\n";
		}
		else {
			str += " South: None" + "\n";
		}
		
		if(East != null){
			str += " East: " + East.name + "\n";
		}
		else {
			str += " East: None" + "\n";
		}
		
		if(West != null){
			str += " West: " + West.name + "\n";
		}
		else {
			str += " West: None" + "\n";
		}
		
		return str;
	}
	
	@Override
	public String toString(){
		return "Room: " + name + "\n" +
		"Description: " + desc + "\n" +
		"Neighbors: " + getNeighbors() + "\n" +
		"Contents:" + getCharacters() + "\nItems: " + getItems();
		

	}
	
	
}