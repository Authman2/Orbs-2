package main_package

import javafx.scene.input.KeyCode
import world.World

/* Extensions for debugging. */
	


/** Prints out the number of tiles that are being rendered at a given moment. */
public fun World.printTilesRendered() {
	val t = tiles!!.filter { it -> it.collidingWith(camera!!.collisionBox) }
	println("TILES BEING RENDERED: ${t.size}")
}