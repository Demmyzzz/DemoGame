package ru.mygdx.demogame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class EventObject extends Rectangle {

    private Texture sheet;
    private TextureRegion[] walkFrames;
    public TextureRegion currentFrame;


    // Анимации
    private Animation<TextureRegion> currentAnimation;

    private Animation<TextureRegion> spin;

    public float stateTime;

    public EventObject(int x, int y, int width, int height, String texturePath, int FRAME_COLS, int FRAME_ROWS) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        sheet = new Texture(texturePath);
        currentAnimation = spin;
        stateTime = 1f;
        setTextureFrames(FRAME_COLS, FRAME_ROWS);
        createAndSetAnimations();


        Gdx.app.log("Message", "player created");
        Gdx.app.log("Player size:", "Width" + this.width + ", height "  + this.height);
    }

    /**
     * Устанавливает полям анимаций соответствующие анимации
     */
    private void createAndSetAnimations() {

        currentFrame = walkFrames[7];
        spin = new Animation<TextureRegion>(0.1f, walkFrames[0],walkFrames[1],walkFrames[2],walkFrames[3]);

    }

    public void setAnimation(ObjectStatus status) {
        switch (status) {
            case SPIN:
                currentAnimation = spin;

        }
    }

    //    Установка фреймов для персонажа
    private void setTextureFrames(int FRAME_COLS, int FRAME_ROWS) {
        TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth() / FRAME_COLS, sheet.getHeight() / FRAME_ROWS); // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
    }
}