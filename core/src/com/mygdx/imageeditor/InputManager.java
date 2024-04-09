package com.mygdx.imageeditor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {
    public static InputManager Instance;
	public Array<IClickable> Clickables = new Array<IClickable>();
	public Array<IHoverable> Hoverables = new Array<IHoverable>();
	private IClickable _currentlyClicked;
	private IHoverable _currentlyHovered;
	public InputManager() {
		Instance = this;
	}

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	Vector2 worldPosition = new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY);
    	IClickable collision = CollisionManager.Instance.getClicked(worldPosition);
    	if(collision != null) collision.onClickDown(worldPosition);
    	_currentlyClicked = collision;
        return true;
    }
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    	if(_currentlyClicked != null) _currentlyClicked.onClickUp(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
    	return false;
    }
    public boolean touchDragged(int screenX, int screenY, int pointer) {
    	mouseMoved(screenX, screenY);
    	if(_currentlyClicked != null)
    		 _currentlyClicked.onClickDragged(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
    	return false;
    }
    public boolean mouseMoved(int screenX, int screenY) {
    	IHoverable collision = CollisionManager.Instance.getHovered(
    			new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
    	if(collision != _currentlyHovered && _currentlyHovered != null) _currentlyHovered.onHoverExit();
    	if(collision != null) collision.onHover();
    	_currentlyHovered = collision;
        return true;
    }
    
    public boolean keyDown(int keycode) {return false;}
    public boolean keyUp(int keycode) {return false;}
    public boolean keyTyped(char character) {return false;}
    public boolean scrolled(float amountX, float amountY) {return false;}
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {return false;}
}
