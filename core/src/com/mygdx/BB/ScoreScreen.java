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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

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

	OrthographicCamera camera;

	public ScoreScreen(final BB gam, int scor) {
		this.game = gam;
		this.score = scor;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
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
		table.add(buttonExit);
		table.add(buttonShare);
		table.add(buttonStart);
		table.debug();
		stage.addActor(table);
		click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
		buttonExit.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				click.play();
				game.dispose();
			}

		});
		buttonShare.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
			    click.play();  	
			}
        	 
         });
		buttonStart.addListener(new ChangeListener(){

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
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		stage.draw();
		game.batch.begin();
		
		game.font.draw(game.batch, "Your score is :" +score, 400, 340);
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
		// TODO Auto-generated method stub
	   stage.dispose();
	   font.dispose();
	   atlas.dispose();
	   skin.dispose();

	}

}
