package Utility;

import com.badlogic.gdx.math.Vector2;

public interface IClickable {
	public void onClickDown(Vector2 mousePosition);
	public void onClickUp(Vector2 mousePosition);
	public void onClickDragged(Vector2 clickPosition);
}
