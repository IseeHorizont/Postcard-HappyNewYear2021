package ru.geekbrains.postcard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Moon {
    private Texture texture;
    private Vector2 position;
    private float time;

    public Moon(){
        this.texture = new Texture("luna.png");
        this.position = new Vector2(PostcardGame.WIDTH - 300, PostcardGame.HEIGHT - 240);
    }

    public void render(SpriteBatch batch, Texture threadTexture){
        batch.draw(threadTexture, position.x + 80, position.y + 170);
        float k = 0.06f * MathUtils.sin(time);
        batch.draw(texture, position.x, position.y, 100, 0, 200, 200, 1, 1 + k, 0, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    public void update(float dt){
        time += dt;
    }

}
