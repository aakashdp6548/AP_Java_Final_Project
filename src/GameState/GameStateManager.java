package GameState;

import java.util.ArrayList;

public class GameStateManager {

	private static ArrayList<GameState> gameStates;
	private static int currentState, previousState;

	public static final int NAMEANIMATIONSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int HELPSTATE = 2;
	public static final int CHARACTERSTATE = 3;
	public static final int TRANSITIONSTATE = 4;
	public static final int LEVEL1STATE = 5;
	public static final int LEVEL2STATE = 6;
	public static final int GAMEOVERSTATE = 7;
	public static final int RESTARTSTATE = 8;

	public GameStateManager() {

		gameStates = new ArrayList<GameState>();

		currentState = 0;
		gameStates.add(new NameAnimationState(this));
		gameStates.add(new MenuState(this));
		gameStates.add(new HelpState(this));
		gameStates.add(new CharacterState(this));
		gameStates.add(new TransitionState(this));
		gameStates.add(new Level1State(this));
		gameStates.add(new Level2State(this));
		gameStates.add(new GameOverState(this));
		gameStates.add(new RestartState(this));
	}

	public void setState(int state) {
		previousState = currentState;
		currentState = state;
		gameStates.get(currentState).init();
	}

	public void update() {
		gameStates.get(currentState).update();
	}

	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}

	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}

	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
	
	public static GameState getState(){
		return gameStates.get(currentState);
	}
	
	public static int getPreviousState(){
		return previousState;
	}
}
