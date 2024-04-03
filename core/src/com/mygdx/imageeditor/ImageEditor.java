package com.mygdx.imageeditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	public static ImageEditor Instance;
	SpriteBatch batch;
    Button button1;
    Button button2;
    public Vector2 ScreenSize;

	public void create () {
        batch = new SpriteBatch();
        ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Vector2 rectangleScale = new Vector2(100,50);
        button1 = new Button(
		 rectangleScale,
		 new Vector2(ScreenSize.x / 2f - rectangleScale.x * 2, ScreenSize.y / 2f - rectangleScale.y / 2f),
		 Color.ORANGE);
        button2 = new Button(
		 rectangleScale,
		 new Vector2(ScreenSize.x / 2f + rectangleScale.x, ScreenSize.y / 2f - rectangleScale.y / 2f),
		 Color.GREEN);


        CollisionManager collisionManager = new CollisionManager();
        InputManager inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);
	}

	public void render () {
        ScreenUtils.clear(0f, 0f, 0f, 1);
        batch.begin();
        batch.draw(button1.RecTexture, button1.Position.x, button1.Position.y);
        batch.draw(button2.RecTexture, button2.Position.x, button2.Position.y);
        batch.end();
	}
	
	public void dispose () {
		batch.dispose();
	}
}
