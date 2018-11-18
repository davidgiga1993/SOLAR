package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;


/**
 * Created by argannor on 05.03.15.
 */
class ResourceBarTimeTable extends Table implements Telegraph {

    private TextButton fasterButton = new TextButton(">>", Styles.TOOLTIP_SKIN);
    private TextButton slowerButton = new TextButton("<<", Styles.TOOLTIP_SKIN);
    private TextButton speed1Button = new TextButton("1x", Styles.TOOLTIP_SKIN);
    private TextButton speed10Button = new TextButton("10x", Styles.TOOLTIP_SKIN);
    private TextButton speed50Button = new TextButton("50x", Styles.TOOLTIP_SKIN);

    private Label speedLabel = new Label(String.valueOf(GameStartStage.getGameSpeed()), Styles.DEFAULT_LABEL_STYLE);
    private Label dateLabel = new Label("Date: ", Styles.DEFAULT_LABEL_STYLE);
    private ResourceBarTimeLabel timeLabel = new ResourceBarTimeLabel();

    public ResourceBarTimeTable() {
        super();

        align(Align.top);
        defaults().pad(3);
        speedLabel.setAlignment(Align.center);

        slowerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                slowDown();
            }
        });
        fasterButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                speedUp();
            }
        });
        speed1Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSpeed(1);
            }
        });
        speed10Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSpeed(10);
            }
        });
        speed50Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSpeed(50);
            }
        });

        add(speed1Button);
        add(speed10Button);
        add(speed50Button);
        add(slowerButton);
        add(speedLabel).minWidth(30).align(Align.center);
        add(fasterButton);
        add(dateLabel);
        add(timeLabel).width(65).align(Align.right);

        SolarEngine.MESSAGE_DISPATCHER.addListener(this, SolarMessageType.GAME_SPEED_CHANGED);
    }

    private void slowDown() {
        GameStartStage.changeTimeSpeed(-0.01f);
    }

    private void speedUp() {
        GameStartStage.changeTimeSpeed(0.01f);
    }

    private void setSpeed(float speed) {
        GameStartStage.setTimeSpeed(speed);
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        float newValue = (Float) telegram.extraInfo;
        speedLabel.setText(String.valueOf(newValue));
        return true;
    }
}
