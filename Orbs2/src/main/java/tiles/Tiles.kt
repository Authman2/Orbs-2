package tiles

import je.visual.Vector2D
import main_package.Assets
import states.WorldState

/**
 EMPTY TILE
*/
class EmptyTile(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.EMPTY, ws) {
	
	init {
		setSolid(false)
	}
	
}


/**
 COBBLESTONE TILE
*/
class CobbleStoneTile(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.COBBLESTONE, ws) {
	
	init {
		setSolid(false)
	}
	
}


/**
 GRASS TILE
*/
class GrassTile(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.GRASS_1, ws) {
	
	init {
		setSolid(false)
	}
}


/**
 WOOD TILE
*/
class WoodFloor(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.WOOD_FLOOR, ws) {
	
	init {
		setSolid(false)
	}
}


/**
 Well
*/
class Well(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.WELL, ws) {
	
	init {
		setSolid(true)
	}
}


/**
 TREE TOP TILE
*/
class TreeTop(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TREE_TOP, ws) {
	
	init {
		setSolid(true)
	}
}


/**
 TREE BOTTOM TILE
*/
class TreeBottom(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TREE_BOTTOM, ws) {
	
	init {
		setSolid(true)
	}
}


/**
 BLUE RUG TOP LEFT TILE
*/
class BlueRug_TL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.BLUE_RUG_TL, ws) {
	
	init {
		setSolid(false)
	}
}


/**
 BLUE RUG BOTTOM LEFT TILE
*/
class BlueRug_BL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.BLUE_RUG_BL, ws) {
	
	init {
		setSolid(false)
	}
}



/**
 BLUE RUG TOP RIGHT TILE
*/
class BlueRug_TR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.BLUE_RUG_TR, ws) {
	
	init {
		setSolid(false)
	}
}



/**
 BLUE RUG BOTTOM RIGHT TILE
*/
class BlueRug_BR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.BLUE_RUG_BR, ws) {
	
	init {
		setSolid(false)
	}
}


/**
 RED RUG TOP LEFT TILE
*/
class RedRug_TL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.RED_RUG_TL, ws) {
	
	init {
		setSolid(false)
	}
}


/**
 RED RUG BOTTOM LEFT TILE
*/
class RedRug_BL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.RED_RUG_BL, ws) {
	
	init {
		setSolid(false)
	}
}



/**
 RED RUG TOP RIGHT TILE
*/
class RedRug_TR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.RED_RUG_TR, ws) {
	
	init {
		setSolid(false)
	}
}



/**
 RED RUG BOTTOM RIGHT TILE
*/
class RedRug_BR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.RED_RUG_BR, ws) {
	
	init {
		setSolid(false)
	}
}



/**
 HOUSE 1 TOP LEFT TILE
*/
class HouseOne_TL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_TL, ws) {
	
	init {
		setSolid(true)
	}
}


/**
 HOUSE 1 LEFT TILE
*/
class HouseOne_L(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_L, ws) {
	
	init {
		setSolid(true)
		defineCollisionArea(position.X*size, position.Y*size, size - 18, size)
	}
	
}


/**
 HOUSE 1 BOTTOM LEFT TILE
*/
class HouseOne_BL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_BL, ws) {
	
	init {
		setSolid(true)
		defineCollisionArea(position.X*size, position.Y*size + 8, size - 18, size - 8)
	}
	
}


/**
 HOUSE 1 BASE LEFT TILE
*/
class HouseOne_BaseL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_BASE_L, ws) {
	
	init {
		setSolid(true)
	}
}


/**
 HOUSE 1 TOP RIGHT TILE
*/
class HouseOne_TR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_TR, ws) {
	
	init {
		setSolid(true)
	}
}



/**
 HOUSE 1 RIGHT TILE
*/
class HouseOne_R(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_R, ws) {
	
	init {
		setSolid(true)
		defineCollisionArea(position.X*size + 18, position.Y*size, size - 18, size)
	}
	
}


/**
 HOUSE 1 BOTTOM RIGHT TILE
*/
class HouseOne_BR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_BR, ws) {
	
	init {
		setSolid(true)
		defineCollisionArea(position.X*size + 18, position.Y*size + 8, size - 18, size - 8)
	}
	
}


/**
 HOUSE 1 BASE RIGHT TILE
*/
class HouseOne_BaseR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_BASE_R, ws) {
	
	init {
		setSolid(true)
	}
}



/**
 HOUSE 1 TOP TILE
*/
class HouseOne_Top(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_T, ws) {
	
	init {
		setSolid(true)
	}
}



/**
 HOUSE 1 INNER TILE
*/
class HouseOne_Inner(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_INNER, ws) {
	
	init {
		setSolid(true)
		defineCollisionArea(position.X*size, position.Y*size + 24, size, size - 24)
	}
	
}


/**
 HOUSE 1 WINDOW TILE
*/
class HouseOne_Window(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_WINDOW, ws) {
	
	init {
		setSolid(true)
	}
	
}


/**
 HOUSE 1 WALL TILE
*/
class HouseOne_Wall(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_WALL, ws) {
	
	init {
		setSolid(true)
	}
	
}



/**
 HOUSE 1 DOOR LEFT TILE
*/
class HouseOne_DoorL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_DOOR_L, ws) {
	
	init {
		setSolid(true)
	}
}


/**
 HOUSE 1 DOOR RIGHT TILE
*/
class HouseOne_DoorR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_DOOR_R, ws) {
	
	init {
		setSolid(true)
	}
	
}


/**
 HOUSE 1 DOOR TOP LEFT TILE
*/
class HouseOne_DoorTL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_DOOR_TL, ws) {
	
	init {
		setSolid(true)
		defineCollisionArea(position.X*size, position.Y*size + 24, size, size - 24)
	}
	
}


/**
 HOUSE 1 DOOR TOP RIGHT TILE
*/
class HouseOne_DoorTR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_DOOR_TR, ws) {
	
	init {
		setSolid(true)
		defineCollisionArea(position.X*size, position.Y*size + 24, size, size - 24)
	}
	
}