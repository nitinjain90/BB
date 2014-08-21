package com.mygdx.BB;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StartScreen implements Screen {

	final BB game;
	Texture logo;

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private BitmapFont font;
	private TextButton buttonPlay, buttonLeaderboard;
	private Sound click;

	

	public StartScreen(final BB gam) {
		this.game = gam;

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("buttons.pack");
		skin = new Skin(atlas);
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		font = new BitmapFont(Gdx.files.internal("font.fnt"), false);
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("Button");
		style.down = skin.getDrawable("Button");
		style.font = font;

		buttonPlay = new TextButton("PLAY", style);
		buttonLeaderboard = new TextButton("LEADERBOARD", style);
		table.add(buttonPlay);
		table.add(buttonLeaderboard);
		table.debug();
		stage.addActor(table);
		click = Gdx.audio.newSound(Gdx.files.internal("Click.wav"));

		buttonPlay.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				click.play();
				game.setScreen(new GameScreen(game));

			}

		});

		buttonLeaderboard.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				click.play();
				game.setScreen(new ScoreScreen(game, 0));

			}

		});

		logo = new Texture(Gdx.files.internal("logo.png"));

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		game.batch.begin();
		game.batch.draw(logo, 200, 250);
		game.batch.end();

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
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
		logo.dispose();
		stage.dispose();
		font.dispose();
		atlas.dispose();
		skin.dispose();
		click.dispose();

	}

}
