package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SelectionRectangle extends Actor
{

	private ShapeRenderer shapeRenderer;
	private boolean visible;
	private GridPoint2 startPosition;
	private GridPoint2 mousePosition;

	public SelectionRectangle() {
		this.shapeRenderer = new ShapeRenderer();
		this.visible = false;
		this.setName("SelectionRectangle");
		this.setSize(0, 0);
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
        if ( visible )
        {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());        
        shapeRenderer.identity();
            shapeRenderer.begin(ShapeType.Line);  
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
        shapeRenderer.end(); 
        }
	}
	
	public void updatePositionAndSize(float mouseX, float mouseY)
	{
		//Berechnet Position, Width und Height neu abhängig von ursprünglicher Startposition und derzeitiger Mausposition
		if( startPosition.x > mouseX)
		{
			setX(mouseX);
			setWidth((float)startPosition.x - mouseX);
		}
		else
		{
			setX((float)startPosition.x);
			setWidth(mouseX - (float)startPosition.x);
		}
		
		if( startPosition.y > mouseY)
		{
			setY(mouseY);
			setHeight((float)startPosition.y - mouseY);
		}
		else
		{
			setY((float)startPosition.y);
			setHeight(mouseY - (float)startPosition.y);
		}
		
		mousePosition = new GridPoint2((int)mouseX, (int)mouseY);

	}
	
	public void resetSelRec(float x, float y)
	{
    	setPosition(x, y);
    	setStartPosition(new GridPoint2((int)x,(int)y));
		visible = true;
		setWidth(0);
		setHeight(0);
	}

	public void hide()
	{
		visible = false;
		
	}
	
	public void setStartPosition(GridPoint2 position)
	{
		startPosition = position;
	}
	
	public GridPoint2 getStartPosition()
	{
		return startPosition;
	}
	
	public GridPoint2 getEndPosition()
	{
		return mousePosition;
	}
	
}
