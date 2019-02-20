package ru.mygdx.demogame.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

/**
 * В нашем GameScreen, мы делегируем обновление и отрисовку нашим классам GameWorld и GameRenderer, соответственно
 */

public class GameRenderer {

    private GameWorld myWorld;
    public OrthographicCamera camera;

    public TiledMap map;
//  Объект типа ShapeRenderer, который будет рисовать формы и линии для нас. Эта функциональность предоставлена libGDX
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    public TmxMapLoader mapLoader;
    public OrthogonalTiledMapRenderer mapRenderer;


    /**
     * Конструктор рендерера игрового мира GameWorld
     * @param world
     */
    public GameRenderer(GameWorld world) {
        myWorld = world;
        Stage stage = new Stage();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 2000, 1200);

        StretchViewport viewport = new StretchViewport(1, 6, camera);



        stage.setViewport(viewport);

        mapLoader = new TmxMapLoader();
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    public void render() {
        mapRenderer.setView(camera);
        mapRenderer.render();

//        Прорисовка объектов
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        mapRenderer();
//        rectangleRender();
        playerRenderer();
        eventObjectRender();
    }

    private void mapRenderer() {
        mapRenderer.render();
    }

    private void rectangleRender() {

        // Говорим shapeRenderer начинать отрисовывать формы
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Выбираем RGB Color 87, 109, 120, не прозрачный
        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

        // Отрисовываем квадрат из myWorld (Используем ShapeType.Filled)
        shapeRenderer.rect(myWorld.getRect().x, myWorld.getRect().y,
                myWorld.getRect().width, myWorld.getRect().height);

        // говорим shapeRenderer прекратить отрисовку
        // Мы ДОЛЖНЫ каждый раз это делать
        shapeRenderer.end();

        // Говорим shapeRenderer нарисовать рамку следующей формы
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Выбираем цвет RGB Color 255, 109, 120, не прозрачный
        shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

        // Отрисовываем квадрат из myWorld (Using ShapeType.Line)
        shapeRenderer.rect(myWorld.getRect().x, myWorld.getRect().y,
                myWorld.getRect().width, myWorld.getRect().height);
        shapeRenderer.end();
    }

    private void playerRenderer() {
        myWorld.player.stateTime += Gdx.graphics.getDeltaTime();
        myWorld.player.currentFrame = myWorld.player.currentAnimation.getKeyFrame(myWorld.player.stateTime, true);

        spriteBatch.begin();
        spriteBatch.draw(myWorld.player.currentFrame, myWorld.player.x, myWorld.player.y);
//        spriteBatch.draw(myWorld.player.currentFrame, 100 + (int)(Math.random() * 1200), 100 + (int)(Math.random() * 1200));
        spriteBatch.end();
    }

    private void eventObjectRender(){
        myWorld.evenet.stateTime += Gdx.graphics.getDeltaTime();
        spriteBatch.begin();
        spriteBatch.draw(myWorld.evenet.currentFrame, myWorld.evenet.x, myWorld.evenet.y, myWorld.evenet.width, myWorld.evenet.height);
        spriteBatch.end();

    }

    public void disposeAll() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
        mapRenderer.dispose();
        map.dispose();
    }
}
