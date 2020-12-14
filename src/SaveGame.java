import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;


public class SaveGame implements Serializable{
	private GameState game;
	
	SaveGame(){
		
	}
	SaveGame(GameState game){
		this.game=game;
	}
	
	public void saveIntoFile() throws ClassNotFoundException {
		write(read());
	}
	
	public GameState loadGame(String user_name) throws ClassNotFoundException {
		
		HashMap<String,GameState> list=read();
		if(list.isEmpty()||!list.containsKey(user_name))
			System.out.println("No records Found!!");
		else
			return list.get(user_name);
		return null;
	}
	
	public void write(HashMap<String,GameState> list) {
		try {
			FileOutputStream fileOut=new FileOutputStream("src/Records.txt");
			ObjectOutputStream out=new ObjectOutputStream(fileOut);
			
			if(list.containsKey(game.getUserName())) {
				list.replace(game.getUserName(),game);
			}
			out.writeObject(list);
				
			out.close();
			fileOut.close();
			System.out.println("serialisation successful!!!");
		}catch(FileNotFoundException e) {
			System.out.println("File Not Found");
		}catch(IOException e) {
			System.out.println("Some problem in writing the object");
			e.printStackTrace();
		}
	}
	
	
	private HashMap<String,GameState> read() throws ClassNotFoundException{
		
		HashMap<String,GameState> list=new HashMap<String,GameState>();
		try {
			FileInputStream fileIn=new FileInputStream("src/Records.txt");
			ObjectInputStream in=new ObjectInputStream(fileIn);
			list=(HashMap<String,GameState>) in.readObject();
			in.close();
			fileIn.close();
		}catch(FileNotFoundException e) {
			System.out.println("File Not found while tryin to read");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
