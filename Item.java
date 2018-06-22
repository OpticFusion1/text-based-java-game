class Item {
	String name;
	private String desc;
	public ItemActions[] actions;
	ItemActions action;
	int breakCount;
	boolean broken;
	
	public enum ItemActions { 
		POSSESS, SHAKE, THROW, BROKEN
	}
	
	public Item(String name, String desc){
		this.name = name;
		this. desc = desc;
		actions = new ItemActions[3];
		breakCount = 0;
		broken  = false;
	}
	
	public void addAction(ItemActions a){
		for(int i = 0; i < actions.length; i++){
			if(actions[i] == null){
				actions[i] = a;
				break;
			}
			else if(actions[i] == a){
				System.out.println("This action already exists.");
				break;
			}
			else if(actions[2] != null){
				System.out.println("Item action array is full.");
				break;
			}
		}
	}
	
	public String getActions(){
		String str = "\n";
			for(int i = 0; i < actions.length; i++){
				str += actions[i] + "\n";
			}
		return str;
	}
	
	public String toString(){
		return name + ": " + desc;
	}
	
}