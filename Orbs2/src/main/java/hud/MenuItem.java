package hud;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import java.util.function.Function;

import je.visual.Vector2D;

import main_package.*;

public class MenuItem {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// A reference to the current GraphicsContext; used for drawing.
	private GraphicsContext graphics;

	// The menu that this menu item goes on.
	private Menu menu;

	// The title on this menu item.
	private String title;

	// The size of the box.
	private float height = 30;

	// The relative position and actual position.
	private Vector2D relativeTo, position;

	// The color of the menu item.
	private Color color;

	// The completion handler.
	private Function<?,?> comp;



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public MenuItem(String title, Menu m, GraphicsContext g) {
		this.graphics = g;
		this.menu = m;
		this.title = title;
		this.color = new Color(0,0.5f,0.5f,0.5f);
		this.position = new Vector2D();
	}



	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	public void setRelativePos(Vector2D relativeTo) {
		this.relativeTo = relativeTo;
		this.position = new Vector2D(relativeTo.X, relativeTo.Y + height);
	}

	/** The action that should be performed when this menu button is clicked. */
	public void setClickAction( Function<?,?> comp ) {
		this.comp = comp;
	}


	public void mouseMoveEvents(MouseEvent e) {
		if(menu.isOpen()) {
			// Hovering
			if(isHovered(e)) {
				color = new Color(0,0.3f,0.3f,0.5f);
			} else {
				color = new Color(0,0.5f,0.5f,0.5f);
			}
		}
	}


	public void mouseClickEvents(MouseEvent e) {
		// Perform completion.
		if(menu.isOpen()) {
			if(isHovered(e)) {
				if (comp != null) {
					comp.apply(null);
				}
			}
		}
	}


	public void mousePressedEvents(MouseEvent e) {
		if(menu.isOpen()) {
			if(isHovered(e)) {
				color = new Color(0,0.1f,0.1f,0.5f);
			}
		}
	}

	public void mouseReleasedEvents(MouseEvent e) {
		if(menu.isOpen()) {
			if(isHovered(e)) {
				color = new Color(0,0.3f,0.3f,0.5f);
			} else {
				color = new Color(0,0.5f,0.5f,0.5f);
			}
		}
	}



	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	public Vector2D getPosition() { return position; }

	private boolean isHovered(MouseEvent e) {
		if(e.getX() >= position.X && e.getX() < position.X + menu.getWidth()) {
			if(e.getY() >= position.Y && e.getY() < position.Y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	

	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	public void initialize() {

	}

	public void update() {

	}

	public void draw() {
		graphics.setFill(color);
		graphics.fillRect(position.X, position.Y, menu.getWidth(), height);

		// Draw a border.
		graphics.setFill(new Color(0,1,0,1));
		graphics.strokeRect(position.X, position.Y, menu.getWidth(), height);

		// Draw the title.
		graphics.strokeText(this.title, 
							position.X + menu.getWidth() / 2 - menu.getWidth() / 5 + 5,
							position.Y + height / 2);
	}

}