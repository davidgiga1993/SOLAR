package dhbw.karlsruhe.it.html;

import dhbw.karlsruhe.it.yourgame.core.YourGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class YourGameHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new YourGame();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
