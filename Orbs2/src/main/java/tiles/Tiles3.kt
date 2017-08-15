package tiles

import je.visual.Vector2D
import main_package.Assets
import states.WorldState
import tiles.Tile

class TV_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TV_LEFT, ws) { init { setSolid(true) } }
class TV_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TV_RIGHT, ws) { init { setSolid(true) } }
class TV(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TV, ws) { init { setSolid(true) } }
class LAMP_TOP(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.LAMP_TOP, ws) { init { setSolid(true) } }
class LAMP_BOTTOM(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.LAMP_BOTTOM, ws) { init { setSolid(true) } }
class TABLE_TOP_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TABLE_TOP_LEFT, ws) { init { setSolid(true) } }
class TABLE_TOP_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TABLE_TOP_RIGHT, ws) { init { setSolid(true) } }
class TABLE_BOTTOM_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TABLE_BOTTOM_LEFT, ws) { init { setSolid(true) } }
class TABLE_BOTTOM_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TABLE_BOTTOM_RIGHT, ws) { init { setSolid(true) } }
class ROAD_MIDDLE(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.ROAD_MIDDLE, ws) { init { setSolid(false) } }
class ROAD(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.ROAD, ws) { init { setSolid(false) } }