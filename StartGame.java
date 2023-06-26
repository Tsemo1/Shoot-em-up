import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class StartGame {

	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		   AppGameContainer app = new AppGameContainer(new Game("Shoot'em up"));
		   // donner les dimmensions à la fenetre
	        app.setDisplayMode(800, 600, false);
	      
	        //cacher le Fps qui s'affiche sur la fenetre
	        app.setShowFPS(false);
	        app.start();
	}

}
