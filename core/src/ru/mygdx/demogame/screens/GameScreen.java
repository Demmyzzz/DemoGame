package ru.mygdx.demogame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import ru.mygdx.demogame.gameworld.GameRenderer;
import ru.mygdx.demogame.gameworld.GameWorld;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen(Game game) {
        world = new GameWorld(this); // initialize world
        renderer = new GameRenderer(world); // initialize renderer
    }

    public GameWorld getWorld() {
        return world;
    }

    public GameRenderer getRenderer() {
        return renderer;
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }


    @Override
    public void show() {
        renderer.map = renderer.mapLoader.load("maps\\testMap3.tmx");
        renderer.mapRenderer = new OrthogonalTiledMapRenderer(renderer.map);
    }

    @Override
    public void resize(int width, int height) {
        renderer.camera.viewportWidth = width;
        renderer.camera.viewportHeight = height;
        renderer.camera.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        world.disposeAll();
        renderer.disposeAll();
    }
}
