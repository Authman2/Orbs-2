package main_package

import entities.Entity
import world.World

/* Extensions for debugging. */
	


/** Prints out the number of tiles that are being rendered at a given moment. */
public fun World.printTilesRendered() {
	val t = tiles!!.filter { it -> it.collidingWith(camera!!.collisionBox) }
	println("TILES BEING RENDERED: ${t.size}")
}



/** Toggles whether or not each entity in the game should have its collision box drawn on screen. */
public fun drawCollisionBoxes() {
	Entity.drawCollisionBox = !Entity.drawCollisionBox
}