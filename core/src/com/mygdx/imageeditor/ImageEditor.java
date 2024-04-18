package com.mygdx.imageeditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	public static ImageEditor Instance;
    public Vector2 ScreenSize;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
    public EditWindow _editWindow;
	SpriteBatch batch;
	
	public void filesImported(String[] filePaths) {
		Pixmap map = ImageInputOutput.Instance.loadImage(filePaths[0]);
		if(map == null) return;
		_editWindow.RecTexture = new Texture(map);
	}

	public void create () {
		Util.testIntToSignedBytes();
        Instance = this;
        InputManager inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);
        
        new ImageInputOutput();
        
        batch = new SpriteBatch();
        ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Vector2 _editWindowSize = new Vector2(500, ScreenSize.y - 40);
        _editWindow = new EditWindow(
        		_editWindowSize, new Vector2(ScreenSize.x - _editWindowSize.x, 0)
		);
        Button b = new Button(new Vector2(50,50), Vector2.Zero, Color.GOLD);
        CollisionManager collisionManager = new CollisionManager();
	}

	public void render () {
        ScreenUtils.clear(0f, 0f, 0f, 1);
        batch.begin();
        Rec2D rec;
        for(int i = 0; i < Rectangles.size; i++) {
	        rec = Rectangles.get(i);
	        batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
        batch.draw(_editWindow.DoodleTexture, _editWindow.Position.x, _editWindow.Position.y, _editWindow.Scale.x,
        		_editWindow.Scale.y);
        batch.end();
	}
	
	public void dispose () {
		batch.dispose();
	}
}
