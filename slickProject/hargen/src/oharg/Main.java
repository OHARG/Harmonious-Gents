/**
 * The main or starting class of the game.
 * 
 * Initial help from TheBinaryAddiction on Youtube.com
 * https://www.youtube.com/playlist?list=PL5A34D064E4500D18
 * Warning! The series is intended for excessively novice programmers!
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
    private int reallySlowTick;
    private int count;
    
    private long now;
    private long preAverage;
    
    private String upsMessage;

    public Main(String title) {
        super(title);
        
        lineAnimY = 0;
        lineAnimX = 0;
        anim = 0;
        slowTick = 0;
        reallySlowTick = 0;
        count = 0;
        
        now = System.nanoTime();
        preAverage = 0;
        
        upsMessage = "0";
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
        
        g.setColor(Color.white);
        g.drawString(upsMessage, 55, 25);
        
    }

    @Override
    public void init(GameContainer arg0) throws SlickException {
        image = new Image(/*Game.TEXTURES + */"harmonious-gents.jpg");
        
    }

    @Override
    public void update(GameContainer gc, int msDelta) throws SlickException {
        
        // base UdatesPerSecond (UPS)
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
        
        // UPS / 10
        this.anim = ++anim % 1000;
        if(anim % 10 == 5) {
            this.slowTick = ++slowTick % 80;
            
            // UPS / 100
            if(slowTick % 10 == 8) {
                this.reallySlowTick = ++reallySlowTick % 10;
                
                // UPS / 200
                if(reallySlowTick % 2 == 0) {
                    upsMessage = "" + preAverage / 200;
                    this.preAverage = 0;
                }
            }
        }
        this.now = System.nanoTime() % 10000;
        this.preAverage += now;
        
    }

} // End Main class