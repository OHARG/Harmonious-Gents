/**
 * The main class and embodiment of the game.
 * 
 * @author Christopher Murphy
 * @version 1.0a May 5, 2014
 */

package oharg;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame {
    
    // TODO move Game class to separate file
    static class Game {
        public static int width = 1024;
        public static int height = (int)(width * 3 / 4.0);
        public static boolean fullScreen = false;
    }

    public Main(String title) {
        super(title);
    }

    public static void main(String[] args) throws SlickException {
        
        AppGameContainer app = new AppGameContainer(new Main("Slick2D Engine | Testing"));
        
        // TODO implement pop-up dialogue prior to app.start() to query fields in Game class
        
        app.setDisplayMode(Game.width, Game.height, Game.fullScreen);
        app.start();
        
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        
        String message = "Aprox. Middle\n  of Screen";
        Font f = g.getFont();
        int strWidth = f.getWidth(message);
        int strHeight = f.getHeight(message);
        int strX = Game.width / 2 - strWidth / 2;
        int strY = Game.height / 2 - strHeight;
        int buffer = 25; // padding in pixels
        
        g.setColor(Color.darkGray);
        g.fillRoundRect(strX - 2 * buffer, strY - 2 * buffer, strWidth + 4 * buffer, strHeight + 4 * buffer, 4 * buffer);
        g.setColor(Color.white);
        g.fillRoundRect(strX - buffer, strY - buffer, strWidth + 2 * buffer, strHeight + 2 * buffer, 2 * buffer);
        g.setColor(Color.black);
        g.drawString(message, strX, strY);
    }

    @Override
    public void init(GameContainer arg0) throws SlickException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(GameContainer arg0, int arg1) throws SlickException {
        // TODO Auto-generated method stub
        
    }

} // End Main class