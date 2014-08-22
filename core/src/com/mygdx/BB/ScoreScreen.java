package com.mygdx.BB;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ScoreScreen implements Screen {
	final BB game;

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private BitmapFont font;
	private TextButton buttonExit, buttonShare, buttonStart;
	private Sound click;

	final int score;

	public ScoreScreen(final BB gam, int scor) {
		this.game = gam;
		this.score = scor;

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

		buttonExit = new TextButton("EXIT", style);
		buttonShare = new TextButton("FB SHARE", style);
		buttonStart = new TextButton("MAIN MENU", style);
		table.row();
		table.add(buttonStart);
		table.row();
		table.add(buttonShare);
		table.row();
		table.add(buttonExit);

		table.debug();
		stage.addActor(table);
		click = Gdx.audio.newSound(Gdx.files.internal("Click.wav"));

		buttonExit.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				click.play();
				game.dispose();
			}

		});

		buttonShare.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				click.play();
			}

		});

		buttonStart.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				game.setScreen(new StartScreen(game));
				click.play();
			}

		});

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(255.0f, 255.0f, 255.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		game.batch.begin();

		font.draw(game.batch, "Your score is :" + score, 400, 420);
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
		// TODO Auto-generated method stub
		stage.dispose();
		table.clear();
		font.dispose();
		atlas.dispose();
		skin.dispose();
		click.dispose();
		game.batch.dispose();
	}

}
