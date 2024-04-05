package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EditWindow extends Rec2D implements IClickable {
	public Texture DoodleTexture;
	private Pixmap _doodleMap;

	public EditWindow(Vector2 scale, Vector2 position, Color backgroundColor) {
		super(scale, position, backgroundColor);
		_doodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
		_doodleMap.setColor(Color.ORANGE);
		DoodleTexture = new Texture(_doodleMap);

	}
	public void onClickDown(Vector2 position) {
		System.out.println("Clicked on the Edit Window");
		_doodleMap.drawPixel((int) (position.x),(int) (position.y));
		DoodleTexture = new Texture(_doodleMap);
	}
	@Override
	public void onClickUp(Vector2 mousePosition) {
		
	}
}
