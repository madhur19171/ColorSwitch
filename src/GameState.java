import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameState implements Serializable {
	private int level;
	private int score;
	private String user_name;
	private ArrayList<Integer> obsArrayList;
	private double ballY;
	private GamePlay gamePlay;
	public GameState(GamePlay gamePlay, int level, int score, String user_name, ArrayList<Integer> obsArrayList,double ballY) {
		this.gamePlay = gamePlay;
		this.level = level;
		this.score = score;
		this.user_name = user_name;
		this.obsArrayList = obsArrayList;
		this.ballY=ballY;
	}


	public int getLevel() {
		return level;
	}

	public int getScore() {
		return score;
	}

	public String getUser_name() {
		return user_name;
	}

	public ArrayList<Integer> getObsArrayList() {
		return obsArrayList;
	}

	public double getBallY() {
		return ballY;
	}

	public GamePlay getGamePlay() {
		return gamePlay;
	}
}