package ru.mygdx.demogame.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import ru.mygdx.demogame.objects.EventObject;
import ru.mygdx.demogame.objects.ObjectStatus;
import ru.mygdx.demogame.objects.Player;
import ru.mygdx.demogame.screens.GameScreen;

/**
 * В нашем GameScreen, мы делегируем обновление и отрисовку нашим классам GameWorld и GameRenderer, соответственно
 *
 * Главной задачей GameWorld является обновление игровой сцены. По сути, таким образом игра "движется"
 * Класс GameWorld хранит все объекты экрана(GameScreen) и предоставляет их для взаимодействия.
 */

public class GameWorld {

    private Rectangle rect;

    public Player player;
    public EventObject evenet;

    public GameWorld(GameScreen screen) {

        rect = new Rectangle(0, 0, 500, 500);

        player = new Player(40, 200, 40, 80, "Sprite_laila_run.png", 6,4);

        evenet = new EventObject(600, 180, 70, 80, "walkcyclevarious.png",12,8);
    }

    public void update(float delta) {
//        screen.getRenderer().camera.position.x++;
        handleInput(delta);


        player.x++;

        if (player.x < 1000) {
            player.setAnimation(ObjectStatus.SPIN);
        } else {
            player.setAnimation(ObjectStatus.RUNNING_RIGHT);
        }

        if (player.x == 1200 - player.width){
            player.x = 300;
        }

        if (evenet.x == 400){
            evenet.setAnimation(ObjectStatus.SPIN);
        }

    }

    private void handleInput(float delta) {
        Gdx.input.isTouched();
    }

    public Rectangle getRect() {
        return rect;
    }

    public void disposeAll() {}
}
