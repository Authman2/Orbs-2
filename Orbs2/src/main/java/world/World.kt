package world

import entities.Entity
import entities.Player
import javafx.scene.input.KeyCode
import je.collections.ArrayConversion
import je.files.ReadFile
import je.visual.Vector2D
import main_package.Orbs2
import main_package.drawCollisionBoxes
import main_package.drawRenderBoxes
import main_package.printTilesRendered
import main_package.printTasks
import main_package.printPlayerPosition
import states.WorldState
import tiles.*
import java.util.ArrayList
import java.io.*
import tasks.TaskSystem
import controllers.*


public open class World(var player: Player, val worldState: WorldState, mapName: String?, size: Int) {
	
	/********************
	*					*
	*	   VARIABLES	*
	*					*
	********************/
	
	// The rendering camera.
	var camera: Camera? = null
	
	// The name of the file containing the map data.
	var mapName: String? = null
	
	// The size of the map
	var mapSize: Int = 0
	
	// The speed of the world (same thing as in the Entity class)
	var speed: Float = 0.15f
	
	// The matrix of tiles.
	// L1 refers to the floor tiles.
	// L2 refers to the see through items on top of the floor.
	var tilesL1: ArrayList<Tile>? = null
	var tilesL2: ArrayList<Tile>? = null
	
	// All of the npcs in the game world.
	val npcManager: NPCManager

	// All of the actionable objects in the game.
	val actionableManager: ActionableManager

	
	
	init {
		this.camera = Camera(0f, 0f)
		this.mapName = mapName
		this.mapSize = size
		this.player = player
		this.npcManager = NPCManager(worldState)
		this.actionableManager = ActionableManager(worldState)
		this.configureMap()
	}

	/********************
	*					*
	*	   SETTERS		*
	*					*
	********************/
	
	// Creates the matrix of id's for the map.
	private fun configureMap() {
		// Make the first layer of the map
		val lines: ArrayList<String> = Networking.read("maps/${mapName}_L1.txt")
		var text = ""
		for(line in lines) { text += line }
		val arr = text.replace(" ","").split(",").toTypedArray()
        val map2D = ArrayConversion.OneToTwo(arr, this.mapSize)
        this.tilesL1 = ArrayList<Tile>()
        this.makeMap(map2D, tilesL1!!)


        // Make the second layer of the map
        val lines2: ArrayList<String> = Networking.read("maps/${mapName}_L2.txt")
        text = ""
        for(line in lines2) { text += line }
        val arr2 = text.replace(" ","").split(",").toTypedArray()
        val map2D2 = ArrayConversion.OneToTwo(arr2, this.mapSize)
        this.tilesL2 = ArrayList<Tile>()
        this.makeMap(map2D2, tilesL2!!)
	}


	
	// Handles actually making the tile map.
	private fun makeMap(map: Array<Array<String>>?, tiles: ArrayList<Tile>) {
		for (i in map!!.indices) {
			for (j in 0..map[0].size - 1) {
				when (map[i][j]) {
					"1" -> tiles.add( GrassTile(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "2" -> tiles.add( CobbleStoneTile(Vector2D(j.toFloat(), i.toFloat()), worldState) );
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
                    "32" -> tiles.add( HOUSE_INNERWALL_FACING_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"45" -> tiles.add( RedRug_BL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"46" -> tiles.add( RedRug_BR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"52" -> tiles.add( HOUSE_INNERWALL_FACING_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"72" -> tiles.add( HouseOne_InnerTop(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"73" -> tiles.add( HOUSE_INNER_CONVEX_CBR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "74" -> tiles.add( HOUSE_INNER_CONVEX_CBL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"53" -> tiles.add( HOUSE_1_VERTICAL_WALL_L(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "54" -> tiles.add( HOUSE_1_VERTICAL_WALL_R(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"33" -> tiles.add( HouseOne_InnerSTL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "34" -> tiles.add( HouseOne_InnerSTR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"51" -> tiles.add( HouseOne_ConcaveCBL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
					"71" -> tiles.add( HouseOne_ConcaveSideL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "61" -> tiles.add( WATER_TOP_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "62" -> tiles.add( WATER_TOP(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "63" -> tiles.add( WATER_TOP_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "81" -> tiles.add( WATER_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "82" -> tiles.add( WATER(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "83" -> tiles.add( WATER_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "101" -> tiles.add( WATER_BOTTOM_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "102" -> tiles.add( WATER_BOTTOM(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "103" -> tiles.add( WATER_BOTTOM_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "64" -> tiles.add( SAND_TOP_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "65" -> tiles.add( SAND_TOP(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "66" -> tiles.add( SAND_TOP_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "84" -> tiles.add( SAND_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "85" -> tiles.add( SAND(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "86" -> tiles.add( SAND_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "104" -> tiles.add( SAND_BOTTOM_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "105" -> tiles.add( SAND_BOTTOM(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "106" -> tiles.add( SAND_BOTTOM_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "121" -> tiles.add( TV_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "122" -> tiles.add( TV_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "123" -> tiles.add( TV(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "141" -> tiles.add( TABLE_TOP_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "142" -> tiles.add( TABLE_TOP_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "161" -> tiles.add( TABLE_BOTTOM_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "162" -> tiles.add( TABLE_BOTTOM_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "181" -> tiles.add( ROAD_MIDDLE(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "182" -> tiles.add( ROAD(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "124" -> tiles.add( LAMP_TOP(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "144" -> tiles.add( LAMP_BOTTOM(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "4" -> tiles.add( BREAKABLE_TREE(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "5" -> tiles.add( GRASS_2(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "6" -> tiles.add( FLOWER_1(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "7" -> tiles.add( FLOWER_2(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "8" -> tiles.add( ROCK(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "27" -> tiles.add( WHIRLPOOL(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "201" -> tiles.add( ROAD_SIDEWAYS(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "143" -> tiles.add( DOOR(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "125" -> tiles.add( TOWEL_1_TOP(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "126" -> tiles.add( TOWEL_2_TOP(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "145" -> tiles.add( TOWEL_1_BOTTOM(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "146" -> tiles.add( TOWEL_2_BOTTOM(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "127" -> tiles.add( SWAMP_TOP_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "128" -> tiles.add( SWAMP_TOP(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "129" -> tiles.add( SWAMP_TOP_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "147" -> tiles.add( SWAMP_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "148" -> tiles.add( SWAMP(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "149" -> tiles.add( SWAMP_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "167" -> tiles.add( SWAMP_BOTTOM_LEFT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "168" -> tiles.add( SWAMP_BOTTOM(Vector2D(j.toFloat(), i.toFloat()), worldState) );
                    "169" -> tiles.add( SWAMP_BOTTOM_RIGHT(Vector2D(j.toFloat(), i.toFloat()), worldState) );
				}
			}
		}
	}







	
	/********************
	*					*
	*	   GETTERS		*
	*					*
	********************/

	fun getNPCManager() = this.npcManager
	

	
	
	
	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	********************/
	fun initialize() {
		player.initialize()
		
		/* the Initialize methods in Tile and Entity don't actually do anything, so don't waste
		   time calling them. */ 
//		if (tiles != null) {
//			this.tiles!!.forEach({ e -> e.initialize() })
//		}
	}

	fun update() {
		player.update()
		camera!!.update(player)
		npcManager.update(camera!!)
		actionableManager.update(camera!!)
		
		/* For right now, you don't need to update each tile. */
//		if (tiles != null) {
//			this.tiles!!.forEach({ e -> e.update() })
//		}
		
	}

	fun draw() {
		worldState.graphics.translate(-camera!!.position.X.toDouble(), -camera!!.position.Y.toDouble())
		
		if (tilesL1 != null && tilesL2 != null) {
			// Only draw the tiles that are within the camera view
			this.tilesL1!!.filter { it -> camera!!.touching(it); }.forEach { e -> e.draw(); }
			this.tilesL2!!.filter { it -> camera!!.touching(it); }.forEach{ e -> e.draw() }
		}

		// Draw the npcs and player
		actionableManager.draw(camera!!)
		npcManager.draw(camera!!)
		player.draw()
		
		worldState.graphics.translate(camera!!.position.X.toDouble(), camera!!.position.Y.toDouble())
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
		if(code == KeyCode.B) {
			drawRenderBoxes()
		}
		if(code == KeyCode.T) {
			printTasks()
		}
        if(code == KeyCode.P) {
            printPlayerPosition();
        }
		if(code == KeyCode.L) {
			player.setSpeed(2.5f)
		}
	}

} // End of class.