abstract class Character {
	Room room;
	protected String name;
	private String desc;

	public Character(String name, String desc){
		this.name = name;
		this.desc = desc;
	}
	
	@Override
	public String toString(){
		return name + ": " + desc + "\n" +
		name + " is in: " + room.name + ".";
	}
	
	//public abstract void move();
		/*This method will be what all the child classes will use to change their
		"room"(room reference) based upon the return value of getRoomState.
		
		This method and setState will be abstract as each character will implement
		them but in their own appropriate way. 
		*/
	
}