package world

import entities.Entity
import entities.Player
import javafx.scene.input.KeyCode
import je.collections.ArrayConversion
import je.files.ReadFile
import je.visual.Vector2D
import main_package.drawCollisionBoxes
import main_package.printTilesRendered
import states.WorldState
import tiles.BlueRug_BL
import tiles.BlueRug_BR
import tiles.BlueRug_TL
import tiles.BlueRug_TR
import tiles.GrassTile
import tiles.HouseOne_BL
import tiles.HouseOne_BR
import tiles.HouseOne_BaseL
import tiles.HouseOne_BaseR
import tiles.HouseOne_DoorL
import tiles.HouseOne_DoorR
import tiles.HouseOne_DoorTL
import tiles.HouseOne_DoorTR
import tiles.HouseOne_Inner
import tiles.HouseOne_L
import tiles.HouseOne_R
import tiles.HouseOne_TL
import tiles.HouseOne_TR
import tiles.HouseOne_Top
import tiles.HouseOne_Wall
import tiles.RedRug_BL
import tiles.RedRug_BR
import tiles.RedRug_TL
import tiles.RedRug_TR
import tiles.Tile
import tiles.TreeBottom
import tiles.TreeTop
import tiles.Well
import tiles.WoodFloor
import java.util.ArrayList
import HouseOne_InnerSL


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
	var tilesL1: ArrayList<Tile>? = null
	var tilesL2: ArrayList<Tile>? = null
	
	
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
		var reader = ReadFile(World::class.java.getResource("../resources/maps/${mapName}_L1.txt").getFile())
		try {
			val s = reader.read().toString()
			val arr = s.split(",").toTypedArray()
			val map2D = ArrayConversion.OneToTwo(arr, this.mapSize)
			
			// Start making the tiles and the map.
			this.tilesL1 = ArrayList<Tile>()
			this.makeMap(map2D, tilesL1!!)
			
			reader = ReadFile(World::class.java.getResource("../resources/maps/${mapName}_L2.txt").getFile())
			val s2 = reader.read().toString()
			val arr2 = s2.split(",").toTypedArray()
			val map2D2 = ArrayConversion.OneToTwo(arr2, this.mapSize)
			
			this.tilesL2 = ArrayList<Tile>()
			this.makeMap(map2D2, tilesL2!!)
		} catch (er: Exception) {
			er.printStackTrace()
		}
	}

	
	// Handles actually making the tile map.
	private fun makeMap(map: Array<Array<String>>?, tiles: ArrayList<Tile>) {
		for (i in map!!.indices) {
			for (j in 0..map[0].size - 1) {
				when (map[i][j]) {
					"1" -> tiles.add( GrassTile(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"22","68","88","108" -> tiles.add( WoodFloor(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"3" -> tiles.add( Well(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"21" -> tiles.add( TreeTop(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"41" -> tiles.add( TreeBottom(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"47" -> tiles.add( HouseOne_TL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"67" -> tiles.add( HouseOne_L(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"87" -> tiles.add( HouseOne_BL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"107" -> tiles.add( HouseOne_BaseL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"110" -> tiles.add( HouseOne_Wall(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"111", "112" -> tiles.add( HouseOne_DoorL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"113" -> tiles.add( HouseOne_DoorR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"49" -> tiles.add( HouseOne_TR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"69" -> tiles.add( HouseOne_R(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"89" -> tiles.add( HouseOne_BR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"90", "91" -> tiles.add( HouseOne_Inner(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"92" -> tiles.add( HouseOne_DoorTL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"93" -> tiles.add( HouseOne_DoorTR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"109" -> tiles.add( HouseOne_BaseR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"23" -> tiles.add( BlueRug_TL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"24" -> tiles.add( BlueRug_TR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"43" -> tiles.add( BlueRug_BL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"44" -> tiles.add( BlueRug_BR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"48" -> tiles.add( HouseOne_Top(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"25" -> tiles.add( RedRug_TL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"26" -> tiles.add( RedRug_TR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"45" -> tiles.add( RedRug_BL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"46" -> tiles.add( RedRug_BR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"52" -> tiles.add( HouseOne_InnerSL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
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
		
		/* the Initialize methods in Tile and Entity don't actually do anything, so don't waste
		   time calling them. */ 
//		if (tiles != null) {
//			this.tiles!!.forEach({ e -> e.initialize() })
//		}
	}

	fun update() {
		if (player != null) {
			player!!.update()
			camera!!.update(player!!)
		}
		
		
		/* For right now, you don't need to update each tile. */
//		if (tiles != null) {
//			this.tiles!!.forEach({ e -> e.update() })
//		}
	}

	fun draw() {
		worldState!!.graphics.translate(-camera!!.position.X.toDouble(), -camera!!.position.Y.toDouble())
		
		
		if (tilesL1 != null && tilesL2 != null) {
			// Only draw the tiles that are within the camera view
			this.tilesL1!!
				.filter { it -> camera!!.touching(it); }
				.forEach { e -> e.draw(); }
			this.tilesL2!!
				.filter { it -> camera!!.touching(it); }
				.forEach{ e -> e.draw() }
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