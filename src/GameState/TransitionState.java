package GameState;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

import Entities.Student;

public class TransitionState extends GameState{

	private double x = 640, x2 = 640;
	public Student student1;
	private boolean drawPlayer = false;
	private boolean drawImg2 = false;
	private BufferedImage image;
	private int count = 0;
	private String[] loading = {"Loading", "Loading.", "Loading..", "Loading..."};
	public TransitionState(GameStateManager gsm){
		super(gsm);
		init();
	}

	public void init() {
		if (CharacterState.playerName == "Player 1") {
			student1 = new Student("/Animations/MikeAnimation.png");
		} else {
			student1 = new Student("/Animations/AakashAnimation.png");
		}
		
		student1.setLocation(GamePanel.WIDTH/2 - 16, 100);
		student1.setMoveSpeed(0);
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/blackBack.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		x -= 1;

		if(x<=0){
			x = 0;
			drawPlayer = true;
		}
		
		if(drawPlayer == true){
			student1.update();
			count++;
		}
		
		if(count >= 1000){
			if(GameStateManager.getPreviousState() == 3){
				GameState.startMusic("/Resources/Music/5thStreet.wav");
				gsm.setState(GameStateManager.LEVEL1STATE);
			}
			if(GameStateManager.getPreviousState() == 5){
				GameState.startMusic("/Resources/Music/FlyingAces.wav");
				gsm.setState(GameStateManager.LEVEL2STATE);
			}
			if(GameStateManager.getPreviousState() == 6){
				GameState.startMusic("/Resources/Music/GameOver.wav");
				gsm.setState(GameStateManager.GAMEOVERSTATE);
			}
			
			count = 0;
			drawPlayer = false;
			x = 640;

			
		}

	}
	
	public void draw(Graphics2D g) {
		
		if(drawPlayer == false){
			g.setColor(new Color(0, 0, 0));
			g.drawRect((int)x, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
		
		if(drawPlayer == true){
			g.drawImage(image, 0, 0, null);
			student1.draw(g);
			
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.setStroke(new BasicStroke(2));
			int len = (int) g.getFontMetrics().getStringBounds(loading[0], g).getWidth();
			int start = GamePanel.WIDTH / 2 - len / 2;
			g.drawString(loading[0], start, 200);
		}

	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}
	

}
