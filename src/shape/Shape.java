package shape;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shape implements Cloneable {
	
	protected Rectangle boundRect;
	protected Color color;
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public Rectangle getBoundRect() {
		return boundRect;
	}
	
	public void Draw(Graphics2D g){
		g.setColor(color);
	}
}
