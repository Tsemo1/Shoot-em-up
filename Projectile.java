
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile {
   private float x1;
   private float y1;
   private int taillep;
   private float vitesseX;
   private float vitesseY;
   private Image img1,img2;
   private  float timer;
   
public Projectile(float x1, float y1,float  vitesseX,float vitesseY, float angle) throws SlickException {
	this.x1 = x1;
	this.y1 = y1;
	this.vitesseX = vitesseX;
	this.vitesseY = vitesseY;
	this.taillep=16;
	img2=new Image("res/projectile1.jpg");
	img1 =new Image("res/missile.png");
	img1.rotate(angle);
	timer=50;
	
}

public float getX1() {
	return x1;
}

public void setX1(float x1) {
	this.x1 = x1;
}

public float getY1() {
	return y1;
}

public void setY1(float y1) {
	this.y1 = y1;
}
public int getTaillep() {
	return taillep;
}

public void setTaillep(int taillep) {
	this.taillep = taillep;
}

//Methode pour dessiner les projectiles 
public void dessinerProjectile1(Graphics g) {
	//g.setColor(Color.orange);

	g.drawImage(img1, x1-taillep/2, y1-taillep/2);
}
public void dessinerProjectile2(Graphics g) {
	//g.setColor(Color.orange);

	g.drawImage(img2, x1-taillep/2, y1-taillep/2);
}

//Methode pour deplacer les projectiles 
public void deplacerProjectile(int alpha) {
	
	y1 +=  vitesseY*alpha/1000f;;
	x1+=vitesseX*alpha/1000f;;
}
	public void deplacerProjectile1(int alpha) {
		float frequence = 1.5f;
		timer = frequence * alpha/1000f;
		y1 +=  vitesseY*timer;
		x1+=vitesseX*timer;
	}
	public void deplacerProjectile2(int alpha) {
		float frequence = 2f;
		timer = frequence * alpha/1000f;
		y1 +=  vitesseY*timer ;
		x1+=vitesseX*timer;
	}
	public void deplacerProjectile3(int alpha) {
		float frequence =1f;
		timer = frequence * alpha/1000f;
		y1 +=  vitesseY*timer ;
		x1+=vitesseX*timer ;
	}
	
//  Methode pour gerer la collision entre le projectile et les Ennemis 
	public boolean collissionEnnemi(Ennemis e) {
		if(this.x1<e.getX2()-e.getTailleX()/2 || this.x1>e.getX2()+e.getTailleX()/2) {
			
			return false;
			
		}
		if(this.y1<e.getY2()-e.getTailleY()/2 || this.y1>e.getY2()+e.getTailleY()/2) {
			
			return false;
		}
		return true;
	}
//  Methode pour gerer la collision entre le projectile et le Vaisseau 
	public boolean collissionVaisseau(Vaisseau v) {
		if(this.x1+taillep/2<v.getX()-v.getTaillex()/2 || this.x1-taillep/2>v.getX()+v.getTaillex()/2) {
			
			return false;
			
		}
		if(this.y1+taillep/2<v.getY()-v.getTailley()/2 || this.y1-taillep/2>v.getY()+v.getTailley()/2) {
			
			return false;
		}
		return true;
	}
	
//  Methode pour verifier si les projectiles sont hors écran
	public boolean horsEcran() {
		if (this.y1<0) {
			return true;
		}
		else if(y1 > 600) {
			return true;
		}
		if (this.x1<0) {
			return true;
		}
		else if(x1 > 800) {
			return true;
		}
		
		return false;
		
	}

	
}
