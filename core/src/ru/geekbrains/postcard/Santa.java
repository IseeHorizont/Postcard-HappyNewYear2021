package ru.geekbrains.postcard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Santa {
    private Texture texture;
    private Vector2 position;
    private float size;
    private float time;

    public Santa() {
        this.texture = new Texture("santa.png");
        this.position = new Vector2(10, -10);
        this.size = 300.0f;
    }

    public void render(SpriteBatch batch){
        //batch.draw(texture, position.x, position.y, size, size);
        float k = 2.0f * MathUtils.sin(time);
        batch.draw(texture, position.x + k, position.y, 0, 0, 300, 300, 1, 1, 0+k, 30, 100, texture.getWidth(), texture.getHeight(), false, false);
    }

    public void update(float dt){
        time += dt;
    }

}
