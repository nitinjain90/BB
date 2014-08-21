package com.mygdx.BB;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ScoreScreen implements Screen {
	final BB game;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
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
		game.batch.draw(background, 0, 0);

		game.font.draw(game.batch, "Your score is :" + score, 400, 240);
		game.font.draw(game.batch, "Press anywhere to quit", 200, 240);
		game.batch.end();

		if (Gdx.input.isTouched()) {
			game.dispose();
		}

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
		// TODO Auto-generated method stub
		background.dispose();

	}

}
