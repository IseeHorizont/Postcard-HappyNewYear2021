package ru.geekbrains.postcard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Banner {
    private Texture texture;
    private Vector2 position;
    private float size = 350.0f;

    public Banner(){
        this.texture = new Texture("year.png");
        this.position = new Vector2(PostcardGame.WIDTH / 2 - 140, PostcardGame.HEIGHT / 2 - 160);
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y, size, size);
    }

}
