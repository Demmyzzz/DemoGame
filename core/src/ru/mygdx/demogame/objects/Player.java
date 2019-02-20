package ru.mygdx.demogame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * 1. ENUM-класс, характеризующий текущее состояние персонажа(стоит, бежит вправо)
 * 2. Контроль анимации(?)
 */
public class Player extends Rectangle {

    private Texture sheet;
    private TextureRegion[] walkFrames;
    public TextureRegion currentFrame;

    // Анимации
    public Animation<TextureRegion> currentAnimation;
    private Animation<TextureRegion> walk_right;
    private Animation<TextureRegion> walk_left;
    private Animation<TextureRegion> standing;
    private Animation<TextureRegion> spin;

    public float stateTime;

    public Player(int x, int y, int width, int height, String texturePath, int FRAME_COLS, int FRAME_ROWS) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

        sheet = new Texture(Gdx.files.internal(texturePath));
        setTextureFrames(FRAME_COLS, FRAME_ROWS);
        createAndSetAnimations();
        currentAnimation = standing;
        stateTime = 1f;
        Gdx.app.log("Message", "player created");
        Gdx.app.log("Player size:", "Width" + this.width + ", height "  + this.height);
    }

    /**
     * Устанавливает полям анимаций соответствующие анимации
     */
    private void createAndSetAnimations() {
        walk_right = new Animation<TextureRegion>(0.1f, walkFrames[20], walkFrames[21], walkFrames[22], walkFrames[23]);
        walk_left = new Animation<TextureRegion>(0.1f, walkFrames[18], walkFrames[17], walkFrames[16], walkFrames[15]);
        standing = new Animation<TextureRegion>(0.1f, walkFrames[1], walkFrames[2]);
        spin = new Animation<TextureRegion>(0.1f, walkFrames[0],walkFrames[6],walkFrames[12],walkFrames[18]);

    }

    public void setAnimation(ObjectStatus status) {
        switch (status) {
            case STANDING:
                currentAnimation = standing;
                break;
            case RUNNING_LEFT:
                currentAnimation = walk_left;
                break;
            case RUNNING_RIGHT:
                currentAnimation = walk_right;
                break;
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
