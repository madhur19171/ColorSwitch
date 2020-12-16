import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;


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
		
		ArrayList<GameState> list=read();
		if(list.size()==0) {
			System.out.println("No records Mister!");
		}
		else {
			for(GameState x:list) {
				if(x.getUserName().equals(user_name))
					return x;
			}
		}

		return null;
	}
	
	public void write(ArrayList<GameState> list) {
		try {
			FileOutputStream fileOut=new FileOutputStream("src/Records.txt");
			ObjectOutputStream out=new ObjectOutputStream(fileOut);
			
			list.add(game);
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
	
	
	public ArrayList<GameState> read() throws ClassNotFoundException{
		
		ArrayList<GameState> list=new ArrayList<GameState>();
		//Properties pro=new Properties();
		try {
			File fin=new File("src/Records.txt");
			if(fin.length()==0) {
				System.out.println("file size: "+fin.length());
				return new ArrayList<GameState>();
			}
			FileInputStream fileIn=new FileInputStream("src/Records.txt");
			ObjectInputStream in=new ObjectInputStream(fileIn);

			list=(ArrayList<GameState>) in.readObject();

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
