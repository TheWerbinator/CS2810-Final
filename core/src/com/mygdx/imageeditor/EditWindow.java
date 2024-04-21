package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import Utility.IClickable;
import Utility.InputManager;

public class EditWindow extends Rec2D implements IClickable{
	public static EditWindow Instance;
	public Texture DoodleTexture;
	public Pixmap DoodleMap;
	private Vector2 _previousPaintPosition;
	public Color DrawColor;

	public EditWindow(Vector2 scale, Vector2 position) {
		super(scale, position, Color.SLATE);
		Instance = this;
		DoodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
		DrawColor = Color.ORANGE;
		DoodleMap.setColor(DrawColor);
		DoodleTexture = new Texture(DoodleMap);
		InputManager.Instance.Clickables.add(this);
	}
	private void paintAtPosition(Vector2 worldPosition) {
		Vector2 paintPosition = new Vector2(worldPosition.x - Position.x,Scale.y - worldPosition.y);
		int startX = (int) _previousPaintPosition.x;
		int startY = (int)_previousPaintPosition.y;
		int endX = (int) paintPosition.x;
		int endY = (int) paintPosition.y;
		DoodleMap.drawLine(startX, startY, endX, endY);
		DoodleMap.drawLine(startX + 1, startY, endX + 1, endY);
		DoodleMap.drawLine(startX - 1, startY, endX - 1, endY);
		DoodleMap.drawLine(startX, startY + 1, endX, endY + 1);
		DoodleMap.drawLine(startX, startY - 1, endX, endY - 1);
		DoodleTexture = new Texture(DoodleMap);
		_previousPaintPosition = paintPosition;
	}
	public void onClickDragged(Vector2 clickPosition) {
		if(_previousPaintPosition == null)
			_previousPaintPosition = new Vector2(clickPosition.x - Position.x,Scale.y - clickPosition.y);
		paintAtPosition(clickPosition);
	}
	public void onClickDown(Vector2 clickPosition) {
		if(_previousPaintPosition == null)
			_previousPaintPosition = new Vector2(clickPosition.x - Position.x,Scale.y - clickPosition.y);
		paintAtPosition(clickPosition);
	}
	
	public void onClickUp(Vector2 mousePosition) { _previousPaintPosition = null; }
}
