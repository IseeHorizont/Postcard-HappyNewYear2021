package ru.geekbrains.postcard.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.geekbrains.postcard.PostcardGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PostcardGame.WIDTH;
		config.height = PostcardGame.HEIGHT;
		new LwjglApplication(new PostcardGame(), config);
	}
}
