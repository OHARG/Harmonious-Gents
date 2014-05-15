/** 
 * The core class game.
 */
package com.oharg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Main extends ApplicationAdapter {
	Texture dropImage;
	Texture bucketImage;
	Sound dropSound;
	Music rainMusic;
	
	Rectangle bucket;
	
	OrthographicCamera camera;
	SpriteBatch batch;
	
	@Override
	public void create () {
        dropImage = new Texture(Gdx.files.internal("droptest/droplet.png"));
        bucketImage = new Texture(Gdx.files.internal("droptest/bucket.png"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("droptest/drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("droptest/rain.mp3"));
        
        rainMusic.setLooping(true);
        rainMusic.play();
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480); // TODO try w/ getHeight/Width methods
        
        batch = new SpriteBatch();
        
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1); //RGBA, [0, 1] float
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		if(Gdx.input.isTouched()) {
		    Vector3 touchPos = new Vector3();
		    touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		    camera.unproject(touchPos);
		    bucket.x = touchPos.x - 64 / 2;
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
		
		if(bucket.x < 0) bucket.x = 0;
		if(bucket.x > 800 - 64) bucket.x = 800 - 64;
		
		// render
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bucketImage, bucket.x, bucket.y);
        batch.end();
	}
}
