
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ennemis {
    private float x2;
    private float y2;
    private int vitX,vitY;
    private int tailleX;
    private int tailleY;
    private Image img1,img2,img3;
    private  int vies ;
    private boolean dansEcran = false;
   
	public Ennemis(int vies) throws SlickException {
		
		this.x2=(float)( Math.random()*725+25);
		this.y2=(float) (Math.random()*100-200);
		this.vitX=100;
		this.tailleX=65;
		this.tailleY=65;
		this.vitY=100;
	    this.vies=vies;
		img1=new Image("res/ennemi11.png");
		img2=new Image("res/ennemi12.png");
		img3=new Image("res/enemi13.png");
	
	}
	
	public float getX2() {
		return x2;
	}
	public void setX2(float x2) {
		this.x2 = x2;
	}
	
	
	public float getY2() {
		return y2;
	}
	public void setY2(float y2) {
		this.y2 = y2;
	}
	
	//  Methode pour dessiner les Ennemis 
	
	public int getTailleX() {
		return tailleX;
	}

	public void setTailleX(int tailleX) {
		this.tailleX = tailleX;
	}

	public int getTailleY() {
		return tailleY;
	}

	public void setTailleY(int tailleY) {
		this.tailleY = tailleY;
	}
	public int getVies() {
		return vies;
	}

	public void setVies(int vies) {
		this.vies = vies;
	}
	

	public void desssinerEnnemis1(Graphics g) {
			g.drawImage(img1,x2-tailleX/2,y2-tailleY/2);
		
		}
	public void desssinerEnnemis2(Graphics g) {
			g.drawImage(img2,x2-tailleX/2,y2-tailleY/2);
		
		}
	public void desssinerEnnemis3(Graphics g) {
			g.drawImage(img3,x2-tailleX/2,y2-tailleY/2);
		
		}
		
		
	
//  Methode pour deplacer  les Ennemis 
	public void deplacerEnnemis1(int alpha,GameContainer gc) {
		
		if( this.x2+this.tailleX/2 < gc.getWidth() ) {
			
			this.vitX *=-1;
		}
		if(this.x2-this.tailleX/2>0 ) {
			
			this.vitX *=-1;
			
		}
		this.x2=(this.x2+this.vitX*alpha/1000f);
		
		
		/*if( this.y2+this.tailleY/2>gc.getHeight() ) {
		
			this.vitY*=-1;
		}*/
		if(this.y2 + this.tailleY/2 < 0) {
			this.y2=(this.y2+this.vitY*alpha/1000f);
		}
		else {
				
				if(y2-this.tailleY/2 < 1 && dansEcran) {
					this.vitY*=-1;
				}		
				if(this.y2+this.tailleY/2>gc.getHeight()) {
					this.vitY*=-1;
					
					dansEcran = true;
				}
				this.y2=(this.y2+this.vitY*alpha/1000f);
			}
		
		

	}
public void deplacerEnnemis2(int alpha,GameContainer gc) {
	if(this.y2 - this.tailleY/2 < 50) {
		this.y2=(this.y2+this.vitY*alpha/1000f);
	}else {
		if( this.x2+this.tailleX/2 > gc.getWidth() && this.vitX>0 ) {
			
			this.vitX *=-1;
		}
		if(this.x2-this.tailleX/2<0 && this.vitX<0 ) {
			
			this.vitX *=-1;
			
		}
		this.x2=(this.x2+this.vitX*alpha/1000f);
	}
	}
public void deplacerEnnemis3(int alpha,GameContainer gc) {
    
	if( this.y2+this.tailleY/2> gc.getHeight() && this.vitY>0) {
		
		this.vitY *=-1;
	}
	if(this.y2-this.tailleY/2<0 && this.vitY<0) {
		
		this.vitY *=-1;
		
	}
	this.y2=(this.y2+this.vitY*alpha/1000f);
	
}
	
	
//  Methode pour gerer la collision entre les Ennemis 
	public boolean collission(Ennemis e) {
		if(this.x2+ this.tailleX/2<e.getX2()-e.getTailleX()/2 || this.x2-this.tailleX/2>e.getX2()+e.getTailleX()) {
			return false;
		}
		if(this.y2+ this.tailleY<e.getY2()-e.getTailleY()/2 || this.y2-this.tailleY/2>e.getY2()+e.getTailleY()) {
			return false;
		}
		
		return true;
	}
	public float changementdirection(int alpha) {
		this.y2=(this.y2+(-1)*this.vitY*alpha/1000f);
		return y2;
	}
	public boolean collissionVaisseau1(Vaisseau v) {
		if(this.x2+ this.tailleX/2<v.getX()-v.getTaillex()/2 || this.x2-this.tailleX/2>v.getX()+v.getTaillex()) {
			return false;
		}
		if(this.y2+ this.tailleY<v.getY()-v.getTailley()/2 || this.y2-this.tailleY/2>v.getY()+v.getTailley()) {
			return false;
		}
		
		return true;
	}

//   public void DonneVies(ArrayList A){
//	   if (gm.)
//   }

}
