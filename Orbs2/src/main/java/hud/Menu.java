package hud;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

import je.visual.Vector2D;

import main_package.*;

public class Menu {
	private final float padding = 20;

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// A reference to the current GraphicsContext; used for drawing.
	private GraphicsContext graphics;

	// Whether or not the menu is opened.
	private boolean open;

	// The position of the menu on the screen.
	private Vector2D position;

	// The width and height of the menu.
	private float width, height;

	// The array of menu items.
	private MenuItem[] menuItems;




	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public Menu(GraphicsContext g) {
		this.graphics = g;
		this.open = false;
		this.width = 200;
		this.height = 200;
		this.position = new Vector2D(Orbs2.WIDTH - width - padding, 10 + padding);

		this.menuItems = new MenuItem[3];
		menuItems[0] = new MenuItem("View Tasks", this, g);
		menuItems[1] = new MenuItem("Inventory", this, g);
		menuItems[2] = new MenuItem("Save Game", this, g);

		menuItems[0].setClickAction(e -> {
			System.out.println("Tasks");
			return null;
		});
		menuItems[1].setClickAction(e -> {
			System.out.println("Inventory");
			return null;
		});
		menuItems[2].setClickAction(e -> {
			System.out.println("Save");
			return null;
		});

		// Set relative positions of the menu items.
		for(int i = 0; i < this.menuItems.length; i++) {
			if(i == 0) {
				menuItems[i].setRelativePos(position);
			} else {
				menuItems[i].setRelativePos( menuItems[i-1].getPosition() );
			}
		}
	}



	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	/** Sets the menu to open/not open based on its current state. */
	public void toggle() {
		this.open = !this.open;
	}


	/** Handles mouse click events. */
	public void mouseClickEvents(MouseEvent e) {
		for(MenuItem itm : menuItems) {
			itm.mouseClickEvents(e);
		}
	}


	/** Handles mouse move events. */
	public void mouseMoveEvents(MouseEvent e) {
		for(MenuItem itm : menuItems) {
			itm.mouseMoveEvents(e);
		}
	}


	/** Handles mouse press events. */
	public void mousePressedEvents(MouseEvent e) {
		for(MenuItem itm : menuItems) {
			itm.mousePressedEvents(e);
		}
	}


	/** Handles mouse release events. */
	public void mouseReleasedEvents(MouseEvent e) {
		for(MenuItem itm : menuItems) {
			itm.mouseReleasedEvents(e);
		}
	}



	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	public boolean isOpen() { return open; }

	public Vector2D getPosition() { return position; }

	public float getWidth() { return width; }

	public float getHeight() { return height; }


	

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
		graphics.setFill(new Color(0,1,0,0.5f));

		if(open) {
			// Draw the title label
			graphics.setFill(new Color(0,1,0.4,0.5f));
			graphics.fillRect(position.X, position.Y, width, 10 + padding);
			graphics.strokeText("Menu", 
								position.X + width / 2 - padding + "Menu".length(),
								position.Y + (10 + padding) / 2 + "Menu".length());

			// Draw all the menu items.
			for(MenuItem itm : this.menuItems) {
				itm.draw();
			}
		}
	}


}