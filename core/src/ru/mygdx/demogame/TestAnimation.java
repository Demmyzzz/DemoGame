package ru.mygdx.demogame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.graphics.GL20.GL_DEPTH_BUFFER_BIT;

public class TestAnimation extends Game {

    private static final int FRAME_COLS = 6; // #1
    private static final int FRAME_ROWS = 4; // #2

    Animation walkAnimation;                 // #3
    Texture walkSheet;                       // #4
    TextureRegion[] walkFrames;              // #5
    SpriteBatch spriteBatch;                 // #6
    public TextureRegion currentFrame;       // #7

    float stateTime;                         // #8

    @Override
    public void create() {
        walkSheet = new Texture(Gdx.files.internal("Sprite_laila_run.png")); // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS); // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.1f, walkFrames[20], walkFrames[21], walkFrames[22], walkFrames[23]); // #11
        spriteBatch = new SpriteBatch(); // #12
        stateTime = 0f; // #13
        Gdx.app.log("Message", "test");
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render () {
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // #14
        stateTime += Gdx.graphics.getDeltaTime(); // #15
        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true); // #16
        spriteBatch.begin();
        // Тут нужно отрисовать задний фон
//        spriteBatch.draw("Pixel_BrickWall.png", 800, 480);
//        spriteBatch.draw();
        spriteBatch.draw(currentFrame, 200, 200); // #17
        spriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
