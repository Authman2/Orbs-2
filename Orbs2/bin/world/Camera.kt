package world

import entities.Entity
import javafx.geometry.Rectangle2D
import je.visual.Vector2D
import main_package.Orbs2


class Camera(x: Float, y: Float) {
	
	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/
	
	public var position: Vector2D = Vector2D(x,y)
	
	public var collisionBox: Rectangle2D = Rectangle2D(position.X.toDouble(), position.Y.toDouble(),
														Orbs2.WIDTH.toDouble(), Orbs2.HEIGHT.toDouble())
	
	
	
	/********************
	*					*
	*      GETTERS		*
	*					*
	*********************/

	/** Returns whether or not the camera is touching a certain entity. This means that the entity
	 is within the camera view. */
	public fun touching(ent: Entity): Boolean {
		if( collisionBox.intersects( ent.renderBox ) ) {
			return true;
		}
		return false;
	}
	
	
	
	/********************
	*					*
	*	  ABSTRACT		*
	*					*
	*********************/
	
	fun initialize() {
		
	}
	
	fun update(ent: Entity) {
		position.X = ent.position.X*ent.size - Orbs2.WIDTH/2
		position.Y = ent.position.Y*ent.size - Orbs2.HEIGHT/2
		
		this.collisionBox = Rectangle2D(position.X.toDouble(),
										position.Y.toDouble(),
										Orbs2.WIDTH.toDouble(),
										Orbs2.HEIGHT.toDouble())
	}
	
	fun draw() {
		
	}
}