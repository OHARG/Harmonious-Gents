/**
 * The main or starting class of the game.
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
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame {
    
    Image image;
    
    // testing render() using basic animation/iteration
    private int lineAnimY;
    private int lineAnimX;
    private int anim;
    private int slowTick;
    private int count;
    
    // TODO move Game class to separate file
    public static class Game {
        public static int width = 960;
        public static int height = (int)(width * 9 / 16.0);
        public static boolean fullScreen = false;
        public static int ups = 200;
    }

    public Main(String title) {
        super(title);
        
        lineAnimY = 0;
        lineAnimX = 0;
        anim = 0;
        slowTick = 0;
        count = 0;
    }

    public static void main(String[] args) throws SlickException {
        
        AppGameContainer app = new AppGameContainer(new Main("Harmonious-Gents | hargen"));
        
        // TODO implement pop-up dialogue prior to app.start() to query fields in Game class
        
        app.setDisplayMode(Game.width, Game.height, Game.fullScreen);
        
        app.start();
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        
        g.setColor(Color.white);
        g.drawLine(0, lineAnimY, Game.width, Game.height - lineAnimY);
        g.drawLine(Game.width - lineAnimX, 0, lineAnimX, Game.height);
        
        String message = "Aprox. Middle\n  of Screen";
        Font f = g.getFont();
        int strWidth = f.getWidth(message);
        int strHeight = f.getHeight(message);
        int strX = Game.width / 2 - strWidth / 2;
        int strY = Game.height / 2 - strHeight;
        
        int buffer = 20; // max padding in pixels for rounding rectangle edges
        int animA = slowTick % (2 * buffer);
        int round = animA > buffer ? 2 * buffer - animA : animA; // rounding 0 to buffer, then back to 0
        round += 10; // min buffer
        
        g.setColor(new Color(anim % 256, 255 - anim % 256, anim % 128));
        g.fillRoundRect(strX - 2 * round, strY - 2 * round, strWidth + 4 * round, strHeight + 4 * round, 4 * round);
        g.setColor(Color.white);
        g.fillRoundRect(strX - round, strY - round, strWidth + 2 * round, strHeight + 2 * round, 2 * round);
        g.setColor(Color.black);
        g.drawString(message, strX, strY);

        g.drawImage(image, Game.width / 2 - image.getWidth() / 2, 40);
        
    }

    @Override
    public void init(GameContainer arg0) throws SlickException {
        image = new Image("harmonious-gents.jpg");
        
    }

    @Override
    public void update(GameContainer gc, int msDelta) throws SlickException {
        
        count += msDelta;
        if(count < 1e3 / Game.ups) {
            return;
        }
        count = 0; // reset

        int skipFrames = 2;
        for(int i = 0; i < skipFrames; i++) {
            lineAnimX = ++lineAnimX % Game.width;
            lineAnimY = (int)((double)lineAnimX / Game.width * Game.height); // y coordinate is a percentage of the total x distance traveled
        }
        
        anim = ++anim % 1000;
        if(anim % 20 == 0) {
            slowTick++;
        }
        
    }

} // End Main class