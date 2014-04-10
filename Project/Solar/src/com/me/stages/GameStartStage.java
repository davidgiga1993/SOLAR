package com.me.stages;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.me.UserControls.Rectangle;
import com.me.solar.SolarEngine;

public class GameStartStage extends BaseStage
{

    public GameStartStage(SolarEngine SE)
    {
        super(SE);

        Rectangle rect = new Rectangle();
        rect.setPosition(0, 0);
        rect.setSize(100, 100);        

        rect.addListener(new InputListener()
        {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                System.out.println("enter");
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                System.out.println("exit");
            }

            // Hier können weitere Events stehen
        });

        // Animationen
        RotateToAction ac = new RotateToAction();
        ac.setRotation(900);
        ac.setDuration(20);
        ac.setInterpolation(Interpolation.exp5);

        rect.addAction(ac);

        // Zum Zeichen / Logik loop hinzufügen
        addActor(rect);
    }

}
