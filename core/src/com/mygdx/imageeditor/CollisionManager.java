package com.mygdx.imageeditor;

import com.badlogic.gdx.math.Vector2;

public class CollisionManager {
    public static CollisionManager Instance;
    public CollisionManager() {
        Instance = this;
    }
    public Button getCollision(Vector2 coordinates) {
    	Button iteratingButton;
        for(int i = 0; i < InputManager.Instance.Buttons.size; i++) {
        	iteratingButton = InputManager.Instance.Buttons.get(i);
	        //Check to see if we collided with the rectangle instance
	        if(coordinates.x >= iteratingButton.Position.x && coordinates.x <= iteratingButton.Position.x + iteratingButton.Scale.x) {
	            if(coordinates.y >= iteratingButton.Position.y && coordinates.y < iteratingButton.Position.y + iteratingButton.Scale.y) {
	                return iteratingButton;
	            }
	        }
        }
        return null;
    }
}
