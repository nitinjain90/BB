package com.mygdx.BB;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;

public class ScoreScreen implements Screen {
	final BB game;
	Texture background;
	final int score;
	OrthographicCamera camera;

	public ScoreScreen(final BB gam, int scor) {
		this.game = gam;
		this.score = scor;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		background = new Texture(Gdx.files.internal("bg1.png"));

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		game.batch.begin();
		game.batch.draw(background, 400, 240);

		game.font.draw(game.batch, "Your score is :" + score, 400, 240);
		game.font.draw(game.batch, "Press anywhere to quit", 200, 240);
		game.batch.end();

		if (Gdx.input.isTouched()) {
			game.dispose();
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
		background.dispose();

	}

}
