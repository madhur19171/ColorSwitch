import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameState implements Serializable {
	private Obstacle obstacle;
	private Switch switches;
	private Star star;
	private Ball ball;
	private  Scene scene;
	private Stage stage;
	private Group avatarGroup;
	private int obs;
	private ArrayList<Group> avatarGroupArray;
	private HashMap<String,Boolean> currentlyActiveKeys;
	
	///attributes from GamePlay class
	
	private Label score_label;
	private int level;
	private int score;
	private String user_name;
	
	
	GameState(Obstacle obs,Switch swi,Star st,Ball ball,Scene scene,Stage stg,Group avtrGrp,int obs1,ArrayList<Group> list,HashMap<String,Boolean> map,Label scrLbl,int level,int score,String user){
		this.user_name=user;
		this.obstacle=obs;
		this.switches=swi;
		this.star=st;
		this.ball=ball;
		this.scene=scene;
		this.stage=stg;
		this.avatarGroup=avtrGrp;
		this.obs=obs1;
		this.avatarGroupArray=list;
		this.currentlyActiveKeys=map;
		this.score_label=scrLbl;
		this.user_name=user;
	}
	
	public String getUserName() {
		return user_name;
	}
	
	public Obstacle getObstacle() {
		return obstacle;
	}
	
	public Star getStar() {
		return star;
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public Group getAvatarGroup() {
		return avatarGroup;
	}
	
	public int getObs() {
		return obs;
	}
	
	public ArrayList<Group> getAvatarGroupArray(){
		return avatarGroupArray;
	}
	
	public HashMap<String,Boolean> getActiveKeys(){
		return currentlyActiveKeys;
	}
	
	public Label getScoreLabel() {
		return score_label;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getScore() {
		return score;
	}
	
	public Switch getSwitch() {
		return switches;
	}
	
}