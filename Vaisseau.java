import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Vaisseau {
	private int Health ;
	private float x;
	private float y;
	private int taillex;
	private int tailley;
	private int vitesse;
	private Image img;
	private ArrayList<Projectile> listP=new ArrayList<Projectile>();
    private float angle;
    private int timer;
     private boolean touch;
	
	public Vaisseau( float x, float y) throws SlickException {
		super();
		Health=50;
		this.x = x;
		this.y = y;
		taillex=65;
	    tailley=70;
		this.vitesse=300;
		img=new Image ("res/vaisseau.png");
		angle=0;
		timer=0;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public boolean isTouch() {
		return touch;
	}
	public void setTouch(boolean touch) {
		this.touch = touch;
	}

	
	public ArrayList<Projectile> getListP() {
		  return listP;
	  }
	public int getHealth() {
		return Health;
	}
	public void setHealth(int health) {
		Health = health;
	}
	public int getTaillex() {
		return taillex;
	}
	public void setTaillex(int taillex) {
		this.taillex = taillex;
	}
	public int getTailley() {
		return tailley;
	}
	public void setTailley(int tailley) {
		this.tailley = tailley;
	} 
	  
	//Methode pour dessiner le Vaisseau 
	
	public void dessinerVaisseau(Graphics g) {
		
		
		
		if (touch) {
			g.drawImage(img, x-taillex/2, y-tailley/2,Color.red);
		}else {
			g.drawImage(img, x-taillex/2, y-tailley/2);
		}
		
	}
	//Methode pour deplacer le vaisseau 
	public void deplacerVaisseau(int alpha,GameContainer gc) {
		Input input =gc.getInput();
		if(input.isKeyDown(input.KEY_RIGHT)&& this.x+this.taillex/2<gc.getWidth()) {
			this.x=this.x+this.vitesse*alpha/1000f;
		}
		if(input.isKeyDown(input.KEY_LEFT)&& this.x-this.taillex/2>0) {
			this.x=this.x-this.vitesse*alpha/1000f;
		}
		if(input.isKeyDown(input.KEY_UP)&& this.y-this.tailley/2>0) {
			this.y=this.y-this.vitesse*alpha/1000f;
		}
		if(input.isKeyDown(input.KEY_DOWN)&& this.y+this.tailley/2<gc.getHeight()) {
			this.y=this.y+this.vitesse*alpha/1000f;
		}
	}
	
	//Methode pour tirer les projectiles .
  public void TireProjectile(Projectile p,GameContainer gc,int alpha) throws SlickException {
	 
		  Input input =gc.getInput();
			if(input.isKeyPressed(input.KEY_SPACE)) {	
				
				listP.add(new Projectile(x,y,200*(float)Math.sin(angle*Math.PI/180), -200*(float)Math.cos(angle*Math.PI/180), angle));
			} 
			for(int i=0;i<listP.size();i++) {
				listP.get(i).deplacerProjectile(alpha);
			}
	  }
  public void TourneVaisseau(GameContainer gc) {
	 
	  Input input =gc.getInput();
	  
	  if(input.isKeyPressed(Input.KEY_D)) {
		  img.rotate((float)45.0);
		  angle += 45;
		  angle %= 360;
	  }
	  else if(input.isKeyPressed(Input.KEY_G)) {
		  img.rotate((float)-45.0);
		  angle -= 45;
		  angle %= 360;
	  }
  }
  public void ManagerAnimation(int alpha) {
	  if(touch) {
		  if(timer<150) {
			  timer+=alpha;
		  }
		
	  else {
			touch=false; 
			timer=0;
		  }
	  }
  }

  }
  
  



