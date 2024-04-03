package com.mygdx.imageeditor;

import com.badlogic.gdx.math.Vector2;

public class CollisionManager {
    public static CollisionManager Instance;
    public CollisionManager() {
        Instance = this;
    }
    public Rec2D getCollision(Vector2 coordinates) {
        //Check to see if we collided with the rectangle instance
        if(coordinates.x >= Instance.Position.x && coordinates.x <= Instance.Position.x + Instance.position.Scale.x) {
            if(coordinates.y >= Instance.Position.y && coordinates.y < Instance.Position.y + Instance.position.Scale.y) {
                return Instance;
            }
        }
        Button iteratingButton;
        for(int i = 0; i < InputManager.Instance.Buttons.size; i++) {
        	iteratingButton = InputManager.Instance.Buttons.get(i);
        }
    }
}
