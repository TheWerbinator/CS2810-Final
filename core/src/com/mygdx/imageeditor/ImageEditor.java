package com.mygdx.imageeditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.buttons.ClearDoodleButton;
import com.mygdx.buttons.ColorButton;
import com.mygdx.buttons.ExitButton;
import com.mygdx.buttons.SaveButton;

import Utility.CollisionManager;
import Utility.ImageInputOutput;
import Utility.InputManager;

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
		Instance = this;
		initializeUtilityClasses();
		createGraphicalElements();
	}
	private BitmapFont _font;
	private void initializeUtilityClasses() {
		new CollisionManager();
		new ImageInputOutput();
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		_font = new BitmapFont();
	}

	private void createGraphicalElements() {
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Vector2 editWindowSize = new Vector2(500, ScreenSize.y - 25);
		ColorButton whiteButton = new ColorButton(new Vector2(42, 42), new Vector2(0, 168), Color.WHITE);
		ColorButton blackButton = new ColorButton(new Vector2(42, 42), new Vector2(42, 168), Color.BLACK);
		ColorButton brownButton = new ColorButton(new Vector2(42, 42), new Vector2(0, 126), Color.BROWN);
		ColorButton grayButton = new ColorButton(new Vector2(42, 42), new Vector2(42, 126), Color.GRAY);
		ColorButton blueButton = new ColorButton(new Vector2(42, 42), new Vector2(0, 84), Color.BLUE);
		ColorButton redButton = new ColorButton(new Vector2(42, 42), new Vector2(42, 84), Color.RED);
		ColorButton goldButton = new ColorButton(new Vector2(42, 42), new Vector2(0, 42), Color.YELLOW);
		ColorButton purpleButton = new ColorButton(new Vector2(42, 42), new Vector2(42, 42), Color.PURPLE);
		ColorButton orangeButton = new ColorButton(new Vector2(42, 42), Vector2.Zero, Color.ORANGE);
		ColorButton greenButton = new ColorButton(new Vector2(42, 42), new Vector2(42, 0), Color.GREEN);
		// size (width, height), left, bottom
		SaveButton saveButton = new SaveButton(new Vector2(76,25), new Vector2(4, ScreenSize.y - 25), Color.GRAY);
		ExitButton exitButton = new ExitButton(new Vector2(76,25), new Vector2(84, ScreenSize.y - 25), Color.GRAY);
		ClearDoodleButton clearButton = new ClearDoodleButton(new Vector2(76,25), new Vector2(164, ScreenSize.y - 25), Color.GRAY);
		batch = new SpriteBatch();
		_editWindow = new EditWindow(editWindowSize, new Vector2(ScreenSize.x - editWindowSize.x, 0));

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
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.Outline.OutlineTex, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
			if(rec instanceof SaveButton) {
				SaveButton button = (SaveButton) rec;
				if(button.ButtonText == null) continue;
				_font.draw(batch, button.ButtonText, button.Position.x, button.Position.y + button.Scale.y * 0.75f,
				 button.Scale.x, Align.center, false);
			}
			if(rec instanceof ExitButton) {
				ExitButton button = (ExitButton) rec;
				if(button.ButtonText == null) continue;
				_font.draw(batch, button.ButtonText, button.Position.x, button.Position.y + button.Scale.y * 0.75f,
				 button.Scale.x, Align.center, false);
			}
			if(rec instanceof ClearDoodleButton) {
				ClearDoodleButton button = (ClearDoodleButton) rec;
				if(button.ButtonText == null) continue;
				_font.draw(batch, button.ButtonText, button.Position.x, button.Position.y + button.Scale.y * 0.75f,
				 button.Scale.x, Align.center, false);
			}
		}

        batch.end();
	}
	
	public void dispose () {
		batch.dispose();
	}
}
