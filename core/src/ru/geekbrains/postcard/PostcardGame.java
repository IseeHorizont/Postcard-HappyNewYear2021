package ru.geekbrains.postcard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;

public class PostcardGame extends ApplicationAdapter {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 800;
	private SpriteBatch batch;
	private PolygonSpriteBatch polygonSpriteBatch;
	private Snow[] snowflakes;
	private Texture threadTexture;
	private Moon moon;
	private Cloud[] clouds;
	private PolygonSprite[] ground;
	private Texture houseTexture;
	
	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.polygonSpriteBatch = new PolygonSpriteBatch();
		this.threadTexture = new Texture("thread.png");
		this.snowflakes = new Snow[333];
		for (int i = 0; i < snowflakes.length; i++) {
			snowflakes[i] = new Snow();
		}
		this.clouds = new Cloud[10];
		for (int i = 0; i < clouds.length; i++) {
			clouds[i] = new Cloud();
		}
		this.moon = new Moon();
		this.ground = new PolygonSprite[]{
			generatePolygonSprite(340, 140, 0.1f),
			generatePolygonSprite(240, 80, 0.4f),
			generatePolygonSprite(150, 60, 0.7f),
			generatePolygonSprite(120, 120, 1.0f)
		};
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		// moon
		moon.render(batch, threadTexture);

		// first three layer of earth
		polygonSpriteBatch.begin();
		for (int i = 0; i < ground.length - 1; i++) {
			ground[i].draw(polygonSpriteBatch);
		}
		polygonSpriteBatch.end();

		// snowfall
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
		for (int i = 0; i < snowflakes.length; i++) {
			snowflakes[i].render(batch);
		}
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		// clouds
		for (int i = 0; i < clouds.length; i++) {
			clouds[i].render(batch);
		}
		// last three layer of earth
		polygonSpriteBatch.begin();
		ground[ground.length - 1].draw(polygonSpriteBatch);
		polygonSpriteBatch.end();

		batch.end();
	}

	public void update(float dt){
		for (int i = 0; i < snowflakes.length; i++) {
			snowflakes[i].update(dt);
		}
		moon.update(dt);
		for (int i = 0; i < clouds.length; i++) {
			clouds[i].update(dt);
		}
	}
	@Override
	public void dispose () {
		batch.dispose();
	}

	private void getCenter(float[] arr, int left, int right, int rnd){
		int mid = (left + right) / 2;
		arr[mid] = (arr[left] + arr[right]) / 2 + MathUtils.random(-rnd, rnd);
		if(right - left > 1){
			if(rnd > 2){
				rnd /= 2;
			}
			getCenter(arr, left, mid, rnd);
			getCenter(arr, mid, right, rnd);
		}
	}

	private PolygonSprite generatePolygonSprite(float baseHeight, int randomHeight, float colorFading){
		int blockWidth = 5;
		int blockCount = WIDTH / blockWidth + 5;

		float[] vertices = new float[blockCount * 4];
		short[] indices = new short[blockCount * 6];

		float[] heightMap = new float[blockCount];
		heightMap[0] = baseHeight + MathUtils.random(-50, 50);
		heightMap[heightMap.length - 1] = baseHeight + MathUtils.random(-50, 50);
		getCenter(heightMap, 0, heightMap.length - 1, randomHeight);
		float maxHeight = 0.0f;
		for (int i = 0; i < heightMap.length; i++) {
			if(heightMap[i] > maxHeight){
				maxHeight = heightMap[i];
			}
		}

		for (int i = 0; i < blockCount; i++) {
			vertices[i * 4 + 0] = i * blockWidth;
			vertices[i * 4 + 1] = 0;
			vertices[i * 4 + 2] = i * blockWidth;
			vertices[i * 4 + 3] = heightMap[i];
		}

		for (int i = 0; i < blockCount - 1; i++) {
			indices[i * 6 + 0] = (short) (i * 2 + 0);
			indices[i * 6 + 1] = (short) (i * 2 + 1);
			indices[i * 6 + 2] = (short) (i * 2 + 2);
			indices[i * 6 + 3] = (short) (i * 2 + 1);
			indices[i * 6 + 4] = (short) (i * 2 + 3);
			indices[i * 6 + 5] = (short) (i * 2 + 2);
		}

		Pixmap pix = new Pixmap(2, (int) maxHeight, Pixmap.Format.RGBA8888);
		for (int i = 0; i < maxHeight; i++) {
			float c = (1.0f - i / maxHeight) * colorFading;
			pix.setColor(c, c, c, 1.0f);
			pix.fillRectangle(0, i * 4, 2, 4);
		}
		TextureRegion textureRegion = new TextureRegion(new Texture(pix));

		return new PolygonSprite(new PolygonRegion(textureRegion, vertices, indices));
	}

}
