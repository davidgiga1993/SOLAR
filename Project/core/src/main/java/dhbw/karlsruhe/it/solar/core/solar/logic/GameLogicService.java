package dhbw.karlsruhe.it.solar.core.solar.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import dhbw.karlsruhe.it.solar.core.resources.Credits;

public class GameLogicService
{
    // Globale Spiellogik wird hier eingefügt!

    public Credits credits;

    public void StartGame()
    {
        setStartRessourcesCredits(1000, 0.1);
        credits.raise();
    }

    private void setStartRessourcesCredits(double Value, double RaiseRate)
    {
        credits = new Credits();
        credits.setValue(Value);
        credits.setRaiseRate(RaiseRate);
    }

}
