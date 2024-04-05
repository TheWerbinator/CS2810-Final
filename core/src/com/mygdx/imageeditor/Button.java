package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button extends Rec2D implements IClickable, IHoverable{
	private Color _startColor;
	public enum ButtonState{Clicked, Hovered, None};
	private ButtonState _currentState;

	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		_startColor = recColor;
		InputManager.Instance.Buttons.add(this);
		_currentState = ButtonState.None;
	}
	
	public void onHovered() {
		if(_currentState == ButtonState.Clicked) return;
		_recColor = new Color(_startColor.r/2f, _startColor.g/2f, _startColor.b/2f, 1);
		_currentState = ButtonState.Hovered;
		generateTexture();
	}
	
	public void onHoverExit() {
		_currentState = ButtonState.None;
		_recColor = new Color(_startColor);
		generateTexture();
	}

	@Override
	public void onClickDown(Vector2 mousePosition) {
		_currentState = ButtonState.Clicked;
		_recColor = new Color(_startColor.r/4f, _startColor.g/4f, _startColor.b/4f, 1);
		generateTexture();
	}

	@Override
	public void onClickUp(Vector2 mousePosition) {
		_currentState = ButtonState.Hovered;
		_recColor = new Color(_startColor.r/2f, _startColor.g/2f, _startColor.b/2f, 1);
		generateTexture();
	}
}
