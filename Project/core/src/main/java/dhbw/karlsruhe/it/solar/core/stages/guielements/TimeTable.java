package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;



/**
 * Created by argannor on 05.03.15.
 */
public class TimeTable extends Table implements Telegraph {

    TextButton fasterButton = new TextButton(">>", Styles.TOOLTIPSKIN);
    TextButton slowerButton = new TextButton("<<", Styles.TOOLTIPSKIN);
    TextButton speed1Button = new TextButton("1x", Styles.TOOLTIPSKIN);
    TextButton speed10Button = new TextButton("10x", Styles.TOOLTIPSKIN);
    TextButton speed50Button = new TextButton("50x", Styles.TOOLTIPSKIN);

    Label speedLabel = new Label(String.valueOf(GameStartStage.getGameSpeed()), Styles.DEFAULTLABEL_STYLE);
    Label dateLabel = new Label("Date: ", Styles.DEFAULTLABEL_STYLE);
    TimeLabel timeLabel = new TimeLabel();

    public TimeTable() {
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
        GameStartStage.changeTimeSpeed(-0.1f);
    }

    private void speedUp() {
        GameStartStage.changeTimeSpeed(0.1f);
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
