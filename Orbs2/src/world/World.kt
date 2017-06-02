package world

import entities.Entity
import entities.Player
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import je.collections.ArrayConversion
import je.files.ReadFile
import je.visual.Vector2D
import main_package.Assets
import tiles.*
import main_package.drawCollisionBoxes
import main_package.printTilesRendered
import states.WorldState
import java.util.ArrayList


public open class World(player: Player?, ws: WorldState?, mapName: String?, size: Int) {
	
	/********************
	 *					*
	 *	   VARIABLES	*
	 *					*
	 ********************/
	
	// A reference to the world state.
	var worldState: WorldState? = null
	
	// The rendering camera.
	var camera: Camera? = null
	
	// The name of the file containing the map data.
	var mapName: String? = null
	
	// The size of the map
	var mapSize: Int = 0
	
	// The speed of the world (same thing as in the Entity class)
	var speed: Float = 0.15f
	
	// The matrix of tiles.
	var tiles: ArrayList<Tile>? = null
	
	
	// A reference to the player that is currently in this world.
	var player: Player? = null
	
	// All of the entities in the game world.
	var entities: ArrayList<Entity>? = null

	
	
	init {
		this.worldState = ws
		this.camera = Camera(0f, 0f)
		this.mapName = mapName
		this.mapSize = size
		this.player = player
		this.entities = ArrayList<Entity>()
		this.configureMap()
	}

	/********************
	 *					*
	 *	   SETTERS		*
	 *					*
	 ********************/
	// Creates the matrix of id's for the map.
	private fun configureMap() {
		val reader = ReadFile(World::class.java.getResource("../resources/maps/${mapName}.txt").getFile())
		try {
			val s = reader.read().toString()
			val arr = s.split(",").toTypedArray()
			val map2D = ArrayConversion.OneToTwo(arr, this.mapSize)
			
			// Start making the tiles and the map.
			this.tiles = ArrayList<Tile>()
			this.makeMap(map2D)
		} catch (er: Exception) {
			er.printStackTrace()
		}
	}

	
	// Handles actually making the tile map.
	private fun makeMap(map: Array<Array<String>>?) {
		for (i in map!!.indices) {
			for (j in 0..map[0].size - 1) {
				when (map[i][j]) {
					"1" -> tiles!!.add( GrassTile(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"22","68","108" -> tiles!!.add( WoodTile(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"3" -> tiles!!.add( Well(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"21" -> tiles!!.add( TreeTop(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"41" -> tiles!!.add( TreeBottom(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"47" -> tiles!!.add( HouseOne_TL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"67" -> tiles!!.add( HouseOne_L(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"87" -> tiles!!.add( HouseOne_BL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"107" -> tiles!!.add( HouseOne_BaseL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"110" -> tiles!!.add( HouseOne_Wall(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"112" -> tiles!!.add( HouseOne_DoorL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"113" -> tiles!!.add( HouseOne_DoorR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"49" -> tiles!!.add( HouseOne_TR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"69" -> tiles!!.add( HouseOne_R(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"89" -> tiles!!.add( HouseOne_BR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"90" -> tiles!!.add( HouseOne_Inner(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"92" -> tiles!!.add( HouseOne_DoorTL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"93" -> tiles!!.add( HouseOne_DoorTR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"109" -> tiles!!.add( HouseOne_BaseR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"23" -> tiles!!.add( BlueRug_TL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"24" -> tiles!!.add( BlueRug_TR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"43" -> tiles!!.add( BlueRug_BL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"44" -> tiles!!.add( BlueRug_BR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"48" -> tiles!!.add( HouseOne_Top(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					
					else -> tiles!!.add( GrassTile(Vector2D(j.toFloat(), i.toFloat()), worldState) );
				}
			}
		}
	}


	
	/********************
	 *					*
	 *	   GETTERS		*
	 *					*
	 ********************/

	
	
	
	/********************
	 *					*
	 *	   ABSTRACT		*
	 *					*
	 ********************/
	fun initialize() {
		if (player != null) {
			player!!.initialize()
		}
		if (tiles != null) {
			this.tiles!!.forEach({ e -> e.initialize() })
		}
		camera!!.initialize()
	}

	fun update() {
		if (player != null) {
			player!!.update()
			camera!!.update(player!!)
		}
		if (tiles != null) {
			this.tiles!!.forEach({ e -> e.update() })
		}
	}

	fun draw() {
		worldState!!.graphics.translate(-camera!!.position.X.toDouble(), -camera!!.position.Y.toDouble())
		
		
		if (tiles != null) {
			// Only draw the tiles that are within the "camera" view
			this.tiles!!
				.filter { it -> it.collidingWith( camera!!.collisionBox ) }
				.forEach { e -> e.draw(); }
		}
		if (player != null) {
			player!!.draw()
		}
		
		worldState!!.graphics.translate(camera!!.position.X.toDouble(), camera!!.position.Y.toDouble())
	}
	
	
	
	/********************
	 *					*
	 *	    DEBUG		*
	 *					*
	 ********************/
	
	fun debug(code: KeyCode) {
		if(code == KeyCode.R) {
			printTilesRendered()
		}
		if(code == KeyCode.D) {
			drawCollisionBoxes()
		}
	}

} // End of class.