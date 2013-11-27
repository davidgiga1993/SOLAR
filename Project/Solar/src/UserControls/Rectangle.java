package UserControls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Rectangle extends Actor
{
    private ShapeRenderer shapeRenderer;

    public Rectangle()
    {
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        
        shapeRenderer.identity();  
        
        shapeRenderer.begin(ShapeType.Filled);             
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(getColor());
        shapeRenderer.rect(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        shapeRenderer.end();
        
    }
}
