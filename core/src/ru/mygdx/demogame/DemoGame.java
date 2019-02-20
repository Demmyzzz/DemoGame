package ru.mygdx.demogame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import ru.mygdx.demogame.screens.GameScreen;

/**
 * Представляет класс игры
 */
public class DemoGame extends Game {

    public static int width;
    public static int height;

    public DemoGame(int width, int height) {
        DemoGame.width = width;
        DemoGame.height = height;
    }

	@Override
	public void create () {
	    Gdx.app.log("DemoGame", "created");
//	    Здесь происходит смена игровых экранов? Меню, игра и т.д.
        setScreen(new GameScreen(this));
	}
}
