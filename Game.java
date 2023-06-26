import java.util.ArrayList;

import org.newdawn.slick.BasicGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Game extends BasicGame {
	private Image img, gameOver,level,gagner;
	private int collisionTimer;
	private Music music,gameOverMusic;

	private Menu menu;
	private Vaisseau v;
	private Projectile p1;
	private int nbrEnnemi,nB1, nB2, nB3,score;

	private boolean jeufini;

	//private ArrayList<obstacles> o = new ArrayList<obstacles>();
	// liste des ennemis
	private ArrayList<Ennemis>listE1;
	private ArrayList<Ennemis>listE2;
	private ArrayList<Ennemis>listE3;
	// liste des Projectiles des ennemis
	private ArrayList<Projectile>balennemi1; 
	private ArrayList<Projectile>balennemi2 ;
	private ArrayList<Projectile>balennemi3 ;
	private int temps1 ,temps2 ,temps3 , t2, t3,t1;
	private float t;
	private int levelTime;
	private int vieRestante;

	boolean gagne;
	private int tempsJeu;
	private boolean afficher;
	public Game(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//		// TODO Auto-generated method stub
		//		Input input =gc.getInput();
		//		if(input.isKeyPressed(input.KEY_SPACE)) {
		//			if (gc.isPaused()) {
		//				gc.resume();
		//				
		//			}else {
		//				gc.pause();
		//			}
		//			
		//		}
		//		
		//		 int bgCounter = -1;
		//	        for (Image bg : img) {
		//	            g.drawImage(bg, bgCounter*500  + x, 0);
		//	            bgCounter++;
		//	        }
		//	       
		//	        g.drawString("Shoot'em up", 100, 150);
		//	        g.drawString("1. Play", 100, 170);
		//	        g.drawString("2. Options", 100, 190);
		//g.drawImage(img, 0, 0);
		if(Menu.var==0)
			menu.dessiner(g);
		if(Menu.var==1) {
			g.drawImage(img, 0,0);

			defilement(g, img, t);
		
			v.dessinerVaisseau(g);

			

			//dessiner les projectiles de la liste de projectlie vaisseau
			for(int i = 0; i < v.getListP().size(); i++) {
				v.getListP().get(i).dessinerProjectile1(g);
			}
			//dessiner les ennemis de la liste des ennemis 
			for(int i=listE1.size()-1; i>=0;i--) {
				listE1.get(i).desssinerEnnemis1(g);
			}

			for(int i=listE2.size()-1; i>=0;i--) {
				listE2.get(i).desssinerEnnemis2(g);
			}
			for(int i=listE3.size()-1; i>=0;i--) {
				listE3.get(i).desssinerEnnemis3(g);
			}
			//dessiner les projectiles de la liste de projectlie ennemis
			for(int i=balennemi1.size()-1; i>=0;i--) {
				balennemi1.get(i).dessinerProjectile2(g);
			}
			for(int i=balennemi2.size()-1; i>=0;i--) {
				balennemi2.get(i).dessinerProjectile2(g);
			}
			for(int i=balennemi3.size()-1; i>=0;i--) {
				balennemi3.get(i).dessinerProjectile2(g);
			}
			// dessine les score et les vies du vaisseau
			g.drawString("score:"+score, 0004, 006);
			g.drawString("Vies:"+v.getHealth(), 0004, 30);

			//dessine la page gameOver si le joueur perd
			if( v.getHealth()==0) {
				
				gameOver.draw(0, 0, 800, 600);
			}
			//dessine la page Next level
    	  	if(afficher)
				level.draw(0, 250, 800, 100);
			}
		//dessine la page Victory
		  if (gagne) {
			  gagner.draw(0, 0, 800, 600);
		  }
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		menu=new Menu();
		img = new Image("res/fond.gif");
		gameOver=new Image("res/gameOverfav.jpg");
		//TODO Auto-generated method stub
		music =new Music("res/music.ogg");
		music.setVolume(0.5f);
		music.loop();
		v=new Vaisseau(400,300);
		listE1=new ArrayList<Ennemis>();
		listE2=new ArrayList<Ennemis>();
		listE3=new ArrayList<Ennemis>();
		balennemi1 = new ArrayList<Projectile>();
		balennemi2 = new ArrayList<Projectile>();
		balennemi3 = new ArrayList<Projectile>();
		
	    level=new Image("res/level.jpg");
	    gagner=new Image("res/gagner2.png");
		gameOverMusic=new Music("res/gameOverSong.wav");
		Menu.var = 0;
		levelTime = 0;
		tempsJeu = 0;
		temps1 = 0;temps2 = 0;temps3 = 0; t2=0; t3=0;t1=0;
		t=0;
		afficher=false;
		gagne=false;
		gc.setTargetFrameRate(100);
		collisionTimer = 0;
		nbrEnnemi=0;nB1=0; nB2=0; nB3=0;
		//score=0;
		jeufini=true;
     
	}

	@Override
	public void update(GameContainer gc, int alpha) throws SlickException {
		// TODO Auto-generated method stub

		Input inp = gc.getInput();
		if(Menu.var==0) {
			//Quand la souris n'est pas encore cliqué Menu.var=0;
			if((inp.isMousePressed(Input.MOUSE_LEFT_BUTTON))||(inp.isKeyPressed(Input.KEY_ENTER)))
				menu.selection(inp.getMouseX(),inp.getMouseY());	
		}
		//quand la souris est deja clique le jeu commence 
		if(Menu.var==1 && v.getHealth() > 0) {	
			tempsJeu += alpha;
			temps1 += alpha;
			temps2 += alpha;
			temps3 += alpha;
// methode pour ajouter les ennemis
			if (nbrEnnemi<6&&temps1 >1000) {
				temps1 = 0;
				listE1.add(new Ennemis(3));
				nbrEnnemi++;
			}
			if (nbrEnnemi<8&& temps2 > 1800) {
				temps2 = 0;
				listE2.add(new Ennemis(2));
				nbrEnnemi++;

			}

			if ( nbrEnnemi<10&&temps3 > 2200) {
				temps3 = 0;
				listE3 .add(new Ennemis(4));
				nbrEnnemi++;

			}
//methodes pour deplacer et tourner le vaisseau
			v.deplacerVaisseau(alpha,gc);
			v.TourneVaisseau(gc);
			// Verifie si les projectiles de la liste projectiles vaisseau sont hors-écran
			for(int j = v.getListP().size() - 1; j >= 0; j--) {
				if(v.getListP().get(j).horsEcran()) {
					//le supprime s'il est hors-écran
					v.getListP().remove(j);
				}

			}
			
			//tire les projectiles de la liste de projectle vaisseau
			v.TireProjectile(p1, gc,alpha);


			//deplace les ennemis qui sont dans la liste des ennemis.
			for(int i=0;i < listE1.size();i++) {
				listE1.get(i).deplacerEnnemis1(alpha, gc);
			}
			for(int i=0;i < listE2.size();i++) {
				listE2.get(i).deplacerEnnemis2(alpha, gc);
			}
			for(int i=0;i < listE3.size();i++) {
				listE3.get(i).deplacerEnnemis3(alpha, gc);
			}
			// Verifie la collision entre les projectile du vaisseau de la liste projectiles vaisseau et les ennemis de la liste ennemis 
			for(int j = v.getListP().size() - 1; j >= 0; j--) {
				for(int i=listE1.size() - 1;i>=0;i--) {
					
					if (v.getListP().get(j).collissionEnnemi(listE1.get(i)) && v.getHealth()!=0) {
//						 supprime le projectile et l'ennemi s'il y a collision
						v.getListP().remove(j);
				listE1.get(i).setVies(listE1.get(i).getVies()-1);
					
						if( listE1.get(i).getVies()==0) {
							listE1.remove(i);
							score+=3;	
						}
						break;
					}
					} 
					
				
			}

			for(int j1 = v.getListP().size() - 1; j1 >= 0; j1--) {
				for(int i1=listE2.size() - 1;i1>=0;i1--) {
					if (v.getListP().get(j1).collissionEnnemi(listE2.get(i1)) && v.getHealth() !=0) {
						v.getListP().remove(j1);
						
				listE2.get(i1).setVies(listE2.get(i1).getVies()-1);
				
						if( listE2.get(i1).getVies()==0) {
						listE2.remove(i1);
						score+=5;
						
						}
						break;
					} 
				}
			}
			for(int j2 = v.getListP().size() - 1; j2 >= 0; j2--) {

				for(int i2=listE3.size() - 1;i2>=0;i2--) {

					if (v.getListP().get(j2).collissionEnnemi(listE3.get(i2)) && v.getHealth()!=0) {
                           
						v.getListP().remove(j2);
						listE3.get(i2).setVies(listE3.get(i2).getVies()-1);
						if( listE3.get(i2).getVies()==0) {
						listE3.remove(i2);
						
						score+=4;
						
						}
						break;
					} 
				}
			}
			
			// Verifie si les projectiles de la liste balennemis sont hors-écran
			for(int k=balennemi1.size() - 1;k >= 0;k--) {
				if(balennemi1.get(k).horsEcran()) {
					//supprime s'ils sont hors écran
					balennemi1.remove(k);
				}
			}
			for(int k=balennemi2.size() - 1;k >= 0;k--) {
				if(balennemi2.get(k).horsEcran()) {
					//supprime s'ils sont hors écran
					balennemi2.remove(k);
				}
			}
			for(int k=balennemi3.size() - 1;k >= 0;k--) {
				if(balennemi3.get(k).horsEcran()) {
					//supprime s'ils sont hors écran
					balennemi3.remove(k);
				}
			}
			//ajoute les balennemis dans la liste balennemis  chaque fois que la liste balennemis est vide

		
			if(balennemi1.size() == 0) 
				nB1=0;
			t1++;
			if(nB1<2 && t1>20) {

				for(int j=listE1.size()-1; j>=0; j--)
					balennemi1.add(new Projectile(listE1.get(j).getX2(), listE1.get(j).getY2(), 0, 300, 180));
				nB1++;
				t1=0;
			}


			if(balennemi2.size() == 0) 
				nB2=0;
			t2++;
			if(nB2<3 && t2>75) {

				for(int j=listE2.size()-1; j>=0; j--)
					balennemi2.add(new Projectile(listE2.get(j).getX2(), listE2.get(j).getY2(), 0, 300, 180));
				nB2++;
				t2=0;
			}


			if(balennemi3.size() == 0) 
				nB3=0;
			t3++;
			if(nB3<4 && t3>60 )    {

				for(int j=listE3.size()-1; j>=0; j--) 
					balennemi3.add(new Projectile(listE3.get(j).getX2(), listE3.get(j).getY2(), 0, 300, 180));
				nB3++;
				t3=0;
			}
			//deplacer les projectiles ennemis

			for(int i = 0; i < balennemi1.size(); i++) {
				balennemi1.get(i).deplacerProjectile1(alpha);
			}
			for(int i = 0; i < balennemi2.size(); i++) {
				balennemi2.get(i).deplacerProjectile2(alpha);
			}
			for(int i = 0; i < balennemi3.size(); i++) {
				balennemi3.get(i).deplacerProjectile3(alpha);
			}
			// Verifie la collision entre les balennemis de la liste projectiles ennnemis et le vaisseau 
			v.ManagerAnimation(alpha);
			for(int j =0; j <balennemi1.size(); j++) {

				if (balennemi1.get(j).collissionVaisseau(v)  && v.getHealth()!=0) {
					//supprime s'il y a collison.
					balennemi1.remove(j);
					v.setTouch(true);
					if(v.getHealth()>0) {
					v.setHealth(v.getHealth()-1);
					
					}
				} 
			}
			for(int j =0; j <balennemi2.size(); j++) {

				if (balennemi2.get(j).collissionVaisseau(v)  && v.getHealth()!=0) {
					//supprime s'il y a collison.
					balennemi2.remove(j);
					v.setTouch(true);
					if(v.getHealth()>0) {
					v.setHealth(v.getHealth()-1);
					//System.out.println(v.getHealth());
                   
					}
				} 
			}
			
			for(int j =0; j <balennemi3.size(); j++) {

				if (balennemi3.get(j).collissionVaisseau(v)  && v.getHealth()!=0) {
					//supprime s'il y a collison.
					balennemi3.remove(j);
					v.setTouch(true);
					if(v.getHealth()>0){
					v.setHealth(v.getHealth()-1);
					//System.out.println(v.getHealth());
                  
					}
				} 
			}

			//Collision entre les ennemis et le Vaisseau
			for (int i=0;i<listE1.size();i++) {
				if (listE1.get(i).collissionVaisseau1(v)) {
									//listE1.remove(i);
					v.setTouch(true);
					//System.out.println(v.getHealth());
					collisionTimer += alpha;
					if(collisionTimer > 500) {
						
						v.setHealth(v.getHealth()-1);
						collisionTimer = 0;
					}
				
					

					//				if (v.getHealth()==0) {
					//					v.setX(0);
					//					v.setY(0);
					 
				}
			}
			for (int i=0;i<listE2.size();i++) {
				if (listE2.get(i).collissionVaisseau1(v)) {
									 
					collisionTimer += alpha;
					v.setTouch(true);
					if(collisionTimer > 500) {
						
						v.setHealth(v.getHealth()-1);
						collisionTimer = 0;
					}
									 
				}
			}
			for (int i=0;i<listE3.size();i++) {
				if (listE3.get(i).collissionVaisseau1(v)) {
					collisionTimer += alpha;
					v.setTouch(true);
					if(collisionTimer > 500) {
						
						v.setHealth(v.getHealth()-1);
						collisionTimer = 0;
					}
								 
				}
			}
			// la frequence de defilement
			double frequence = 25;
			t += frequence * alpha/1000f;
			if(t>=500)
				t=0;
			
			// methode qui permet de verifier et d'afficher si tu as gagné 
			if(tempsJeu > 3000 && listE1.size()==0 &&listE2.size()==0&&listE3.size()==0) {
				//gc.pause();
				gagne= true;
				levelTime += alpha;	
				if(levelTime > 5000) {
					gagne = false;
					
					
				}
				
			}
			
			//methode qui permet d'afficher le Next level
			if(tempsJeu > 3800 && listE1.size()==0 &&listE2.size()==0&&listE3.size()==0) {
				if(levelTime > 5000)
					afficher = true;
				levelTime += alpha;	
				if(levelTime > 8000) {
					afficher = false;
					//gc.resume();
					vieRestante = v.getHealth();
					init(gc);
					v.setHealth(vieRestante);
					Menu.var=1;
				}
				
			}
			
		}
		
		if(jeufini==true && v.getHealth()==0) {
			levelTime += alpha;
			if(levelTime>1000) {
			music.stop();
			gameOverMusic.play();
			 jeufini=false;
			 init(gc);	
			 
			}
			
		}
	} 
	public static void defilement(Graphics g, Image img, float t) {
		g.drawImage(img.getScaledCopy(800,700),0, t-700);
		g.drawImage(img.getScaledCopy(800,700),0, t);
	}
}









