package world

import controllers.GameController
import entities.Entity
import entities.Player
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import je.collections.ArrayConversion
import je.files.ReadFile
import je.visual.Vector2D
import main_package.Assets
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
			val arr = s.split("-").toTypedArray()
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
					"0" -> this.createTile(0, j.toFloat(), i.toFloat(), Assets.GRASS_1)
					"20" -> this.createTile(20, j.toFloat(), i.toFloat(), Assets.TREE_TOP)
					"40" -> this.createTile(40, j.toFloat(), i.toFloat(), Assets.TREE_BOTTOM)
					else -> this.createTile(0, j.toFloat(), i.toFloat(), Assets.GRASS_1)
				}
			}
		}
	}

	// Creates a tile.
	private fun createTile(type: Int, i: Float, j: Float, image: Image?) {
		val tile = Tile(Vector2D(i, j), image, this.worldState)
		tile.setType(type)
		tiles!!.add(tile)
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
	}

} // End of class.