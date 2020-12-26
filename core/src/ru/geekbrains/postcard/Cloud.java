package ru.geekbrains.postcard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Cloud {
    private static Texture texture;
    private Vector2 position;
    private float speed;
    private float size;
    private float time;

    public Cloud() {
        if(texture == null){
            texture = new Texture("cloud.png");
        }
        this.position = new Vector2(MathUtils.random(PostcardGame.WIDTH * 1.2f), PostcardGame.HEIGHT - 40 - MathUtils.random(240.0f));
        this.speed = MathUtils.random(10.0f, 25.0f);
        this.size = MathUtils.random(0.2f, 0.5f);
    }

    public void render(SpriteBatch batch){
        batch.setColor(0.7f, 0.7f, 0.7f, 1.0f);
        batch.draw(texture, position.x, position.y, texture.getWidth() / 2, 0, texture.getWidth(), texture.getHeight(), size, size, 0, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void update(float dt){
        time += dt;
        position.x -= speed * dt;
        if(position.x < - texture.getWidth()){
            position.x = PostcardGame.WIDTH + MathUtils.random(PostcardGame.WIDTH * 0.4f);
            speed = MathUtils.random(10.0f, 25.0f);
            size = MathUtils.random(0.2f, 0.5f);
        }
    }
}
