package com.mygdx.BB;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class StartScreen implements Screen {

	final BB game;

	
	Texture logo;
	Texture background;

	OrthographicCamera camera;

	public StartScreen(final BB gam) {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		this.game = gam;

		logo = new Texture(Gdx.files.internal("logo.png"));
		background = new Texture(Gdx.files.internal("bg1.png"));
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		game.batch.begin();
		game.batch.draw(background, 0, 0);
		game.batch.draw(logo, 200, 270);
		game.font.draw(game.batch, "Click Anywhere To Begin", 100, 150);
		game.batch.end();
		
		if(Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
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
       logo.dispose();
       background.dispose();
	}

}
