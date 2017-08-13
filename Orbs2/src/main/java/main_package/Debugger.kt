package main_package

import entities.Entity
import world.World
import tasks.TaskSystem

/* Extensions for debugging. */
//inline fun <T>guard(obj: T?, errorBlock: () -> T): T {
//	if(obj != null) { return obj }
//	else {
//		return errorBlock
//	}
//}


/** Prints out the number of tiles that are being rendered at a given moment. */
public fun World.printTilesRendered() {
	val t = tilesL1!!.filter { it -> it.collidingWith(camera!!.collisionBox) }
	val t2 = tilesL2!!.filter { it -> it.collidingWith(camera!!.collisionBox) }
	println("TILES BEING RENDERED: ${t.size + t2.size}")
}



/** Toggles whether or not each entity in the game should have its collision box drawn on screen. */
public fun drawCollisionBoxes() {
	Entity.drawCollisionBox = !Entity.drawCollisionBox
}



/** Toggles drawing the render boxes. */
public fun drawRenderBoxes() {
	Entity.drawRenderBox = !Entity.drawRenderBox
}



/** Prints out all of the current tasks in the task system. */
public fun printTasks() {
	TaskSystem.tasks.forEach { e ->
		println("""
		TASK: ${e.getTaskDescription()}
		----> Started: ${e.isStarted()}
		----> Completed: ${e.isCompleted()}
		""")
	};
}





/** Handles reading a file. */
public fun readFile() {

}