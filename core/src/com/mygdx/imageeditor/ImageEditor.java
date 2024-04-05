package com.mygdx.imageeditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	public static ImageEditor Instance;
	public Array<Rec2D> Rectangles = new Array<Rec2D>(5);
	SpriteBatch batch;
    public Vector2 ScreenSize;
    public EditWindow editWindow;

	public void create () {
        Instance = this;
        InputManager inputManager = new InputManager();
        CollisionManager collisionManager = new CollisionManager();
        Gdx.input.setInputProcessor(inputManager);
        batch = new SpriteBatch();
        ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Vector2 editWindowSize = new Vector2(500, ScreenSize.y - 40);
        editWindow = new EditWindow(
        		editWindowSize, new Vector2(ScreenSize.x - editWindowSize.x, 0), Color.GRAY
		);
	}

	public void render () {
        ScreenUtils.clear(0f, 0f, 0f, 1);
        batch.begin();
        Rec2D rec;
        for(int i = 0; i < Rectangles.size; i++) {
	        rec = Rectangles.get(i);
	        batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
        batch.draw(editWindow.DoodleTexture, editWindow.Position.x, editWindow.Position.y, editWindow.Scale.x,
        		editWindow.Scale.y);
        batch.end();
	}
	
	public void dispose () {
		batch.dispose();
	}
}
