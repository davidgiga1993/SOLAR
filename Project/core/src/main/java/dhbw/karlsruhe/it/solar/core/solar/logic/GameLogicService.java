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
    public String backgroundImage = "defaultBackground.png";

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

    public Image AddBackgroundImage()
    {
        Texture texture = new Texture(Gdx.files.internal("data/" + backgroundImage));
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        int height = texture.getHeight();
        int width = texture.getWidth();
        TextureRegion region = new TextureRegion(texture, 0, 0, width, height);

        Image background = new Image(region);
        background.setScaling(Scaling.fill);
        background.setPosition(0,0);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return background;
    }

    public void setBackgroundImage(String backgroundPath)
    {
        backgroundImage = backgroundPath;
    }
}
