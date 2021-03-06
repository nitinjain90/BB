package com.mygdx.BB;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
	final BB game;
	private BitmapFont font;
	private static final int no_of_frames = 2;
	Texture ballonFrames;
	TextureRegion[] burstFrames = new TextureRegion[no_of_frames];
	Animation burstAnimation;
	Array<Rectangle> ballons;
     TextureRegion currentFrame;
	long lastBallonTime;
	int ballonBursted;
	OrthographicCamera camera;
	int ballonMissed;

	Sound ballonBursting;
     
	
	public GameScreen(final BB gam) {
		this.game = gam;
		
		ballonFrames = new Texture(Gdx.files.internal("ballon_burst.png"));
        font = new BitmapFont(Gdx.files.internal("font.fnt") , false);  
        ballonBursting = Gdx.audio.newSound(Gdx.files.internal("BallonBursting.wav"));
		TextureRegion[][] tmp = TextureRegion.split(ballonFrames, ballonFrames.getWidth()/2, ballonFrames.getHeight());
        burstFrames[0] = tmp[0][0];
        burstFrames[1] = tmp[0][1];
		
        burstAnimation = new Animation(3.0f , burstFrames );
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

	private boolean ballonBursted(Rectangle ballon) {
		Vector2 touch = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		if (touch.x >= ballon.x && touch.x < ballon.x + ballon.width
				&& touch.y > ballon.y && touch.y < ballon.y + ballon.height)
			return true;
		else
			return false;
	}
     
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
		font.draw(game.batch, "Ballon Bursted :" + ballonBursted, 0, 460);
		font.draw(game.batch, "Ballon Missed:"+ballonMissed, 400, 460);
		for (Rectangle ballon : ballons) {
			game.batch.draw(burstFrames[0], ballon.x, ballon.y);
		}
		

		if (TimeUtils.nanoTime() - lastBallonTime > 1000000000) {
			spawnBallon(); // a ballon every second
		}

		Iterator<Rectangle> iter = ballons.iterator();
		while (iter.hasNext()) {
			Rectangle ballon = iter.next();
			ballon.y = ballon.y + 100 * Gdx.graphics.getDeltaTime();
			if (ballon.y + 64 > 480) {
				iter.remove();
				ballonMissed++;
			}
			if (ballonBursted(ballon) == true) {
				ballonBursted++;
				game.batch.draw(burstFrames[1] , ballon.x , ballon.y);
				ballonBursting.play();
				iter.remove();
			}
		}
		
		if (ballonMissed > 5) {
			game.setScreen(new ScoreScreen(game, ballonBursted));
		}
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
      
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {


	}

	@Override
	public void dispose() {
		ballonFrames.dispose();
		ballonBursting.dispose();
		game.batch.dispose();
		

	}

}
