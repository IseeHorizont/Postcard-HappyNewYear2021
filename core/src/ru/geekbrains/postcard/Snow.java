package ru.geekbrains.postcard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Snow {
    private static Texture texture;
    private Vector2 position;
    private Vector2 velocity;
    private float size;

    Snow(){
        if(texture == null){
            texture = new Texture("snowflake.png");
        }
        this.position = new Vector2(MathUtils.random(PostcardGame.WIDTH), MathUtils.random(PostcardGame.HEIGHT));
        this.velocity = new Vector2(MathUtils.random(-5.0f, 1.0f), MathUtils.random(-60.0f, -15.0f));
        this.size = MathUtils.random(5.0f, 12.0f);
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y, size, size);
    }

    public void update(float dt){
        position.mulAdd(velocity, dt);
        if(position.y < -20){
            position.set(MathUtils.random(PostcardGame.WIDTH), PostcardGame.HEIGHT + MathUtils.random(100));
        }
    }
}
