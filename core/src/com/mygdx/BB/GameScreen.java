package com.mygdx.BB;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
	final BB game;
	Texture background;
	Texture ballonImage;

	Array<Rectangle> ballons;
	Array<Rectangle> heart;
	long lastBallonTime;
	int ballonBursted;
	OrthographicCamera camera;
	int ballonMissed;

	public GameScreen(final BB gam) {
		this.game = gam;

		background = new Texture(Gdx.files.internal("background.jpg"));
		ballonImage = new Texture(Gdx.files.internal("ballon.png"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		ballons = new Array<Rectangle>();

		spawnBallon();

	}

	private void spawnBallon() {
		Rectangle ballon = new Rectangle();
		ballon.x = MathUtils.random(0, 800 - 64); //
		ballon.y = 0;
		ballon.width = 40;
		ballon.height = 80;
		ballons.add(ballon);
		lastBallonTime = TimeUtils.nanoTime();
	}
	
	private boolean ballonBursted(Rectangle ballon){
		Vector2 touch = new Vector2(Gdx.input.getX() , Gdx.input.getY());
		if(touch.x>=ballon.x && touch.x<ballon.x+ballon.width && touch.y>ballon.y&&touch.y<ballon.y+ballon.height)
		return true;
		else 
			return false;
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        game.batch.begin();
        game.font.draw(game.batch, "Ballon Bursted :", 0, 460);
        for(Rectangle ballon : ballons){
        	game.batch.draw(ballonImage, ballon.x, ballon.y);
        }
        game.batch.end();
        
        if(TimeUtils.nanoTime() - lastBallonTime > 1000000000){
        	spawnBallon(); //a ballon every second
        }
        
        Iterator<Rectangle> iter = ballons.iterator();
        while(iter.hasNext()) {
        	Rectangle ballon = iter.next();
        	ballon.y = ballon.y + 100*Gdx.graphics.getDeltaTime();
        	if(ballon.y+64 >480){
        		iter.remove();
        		ballonMissed++;
        	}if(ballonBursted(ballon)== true){ 
        		ballonBursted++;
  	            iter.remove();
        	}
        }
        if(ballonMissed>5){
        	game.setScreen(new ScoreScreen(game,ballonMissed));
        }
        
 	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
        ballonImage.dispose();
        background.dispose();
	}

}
