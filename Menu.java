import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import java.awt.Font;

public class Menu {
	public static int var=0;
	private Image imgmenu, imgButton1, imgButton2,welcom;
	
	public Menu() throws SlickException {
		this.imgmenu=new Image("res/imagemenu.jpg");
		this.imgButton1=new Image("res/startGame.png");
		this.imgButton2=new Image("res/exitGame.png");
		this.welcom=new Image("res/welcomefav.png");
	}
	public void dessiner(Graphics g) {

		
		imgmenu.draw(0, 0, 800, 600);
		welcom.draw(0, 0, 200, 250);
		g.drawImage(imgButton1, 800/4, 260);
		g.drawImage(imgButton2, 800/4, 400);
		//g.setColor(Color.green);
		//g.drawString("MENU", 1500/3, 100);	
	}
	public int clicButton(int x, int y) {
		int ici=-1;
			if(y>260 && y<=260+139 && x>800/4 && x<800/4+320) 
				ici=0;
			if(y>=400 && y<=300+153 && x>800/4 && x<800/4+320) {
				ici=1;
			
			}

		
		return ici;
	}
	//selection permet de passer d'une fentre a l'autre
	public void selection(int x, int y) {
		int n=clicButton(x,y);
		if(n!=-1) {
			var=n+1;
		}
	}
}
