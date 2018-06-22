class NPC extends Character{
	private String name;
	private String desc;
	private int scaredness;

	public NPC(String name, String desc){
		super(name, desc);
		scaredness = 100;
	}
	
	
	public void move(){
		//If NPC's scare level is >50, this method will be called
		//If scare level is >100, this will call them to leave the house
	}
	
	public void setFear(int i){
		scaredness = scaredness - 25;
	}java
	
	public void look(){
		for(int i = 0; i < room.items.size(); i++){
			if((room.items.get(i).action == Item.ItemActions.SHAKE) || (room.items.get(i).action == Item.ItemActions.POSSESS) || (room.items.get(i).action == Item.ItemActions.THROW)){
				scaredness += 25;
			}
		}
		//loop through rooms items array, check each items for it actions are being called
		//respond accordingly
		//increase scaredness
		//call move
		//if item is broken, clean it up
		
		//call look every state change? This is basically the last thing you have to implement so get it gud
 	}
	
	public void setState(){
		//This method will allow the NPC's to remove broken items
	}
	
}