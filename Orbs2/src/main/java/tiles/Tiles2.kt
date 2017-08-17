package tiles

import je.visual.Vector2D
import main_package.Assets
import states.WorldState
import tiles.Tile


class HouseOne_InnerSL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_INNER_SL, ws) {
	
	init {
		setSolid(true)
	}
	
}


class HouseOne_InnerSR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_INNER_SR, ws) {
	
	init {
		setSolid(true)
	}
	
}


class HouseOne_InnerTop(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_INNER_TOP, ws) {
	
	init {
		setSolid(true)
	}
	
}


class HouseOne_InnerCR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_INNER_CR, ws) {
	
	init {
		setSolid(true)
	}
	
}


class HouseOne_InnerCL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_INNER_CL, ws) {
	
	init {
		setSolid(true)
	}
	
}


class HOUSE_1_VERTICAL_WALL_L(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_VERTICAL_WALL_L, ws) {
    init { setSolid(true) }
}

class HOUSE_1_VERTICAL_WALL_R(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_VERTICAL_WALL_R, ws) {
    init { setSolid(true) }
}

class HOUSE_INNER_CONVEX_CBL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_INNER_CONVEX_CBL, ws) {
    init { setSolid(true) }
}

class HOUSE_INNER_CONVEX_CBR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_INNER_CONVEX_CBR, ws) {
    init { setSolid(true) }
}
class HOUSE_INNERWALL_FACING_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_INNERWALL_FACING_LEFT, ws) {
    init { setSolid(true) }
}
class HOUSE_INNERWALL_FACING_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_INNERWALL_FACING_RIGHT, ws) {
    init { setSolid(true) }
}


class HouseOne_InnerVerticalL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_INNER_VERTICAL_L, ws) {
	
	init {
		setSolid(true)
	}
	
}


class HouseOne_InnerVerticalR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_INNER_VERTICAL_R, ws) {
	
	init {
		setSolid(true)
	}
	
}


class HouseOne_InnerSTL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_INNER_STL, ws) {
	
	init {
		setSolid(true)
	}
	
}


class HouseOne_InnerSTR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_INNER_STR, ws) {
	
	init {
		setSolid(true)
	}
	
}


class HouseOne_ConcaveCBL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_CONCAVE_CBL, ws) {
	
	init {
		setSolid(true)
	}
	
}


class HouseOne_ConcaveCBR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_CONCAVE_CBR, ws) {
	
	init {
		setSolid(true)
	}
	
}


class HouseOne_ConcaveSideL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_CONCAVE_SL, ws) {
	
	init {
		setSolid(true)
	}
	
}


class HouseOne_ConcaveSideR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.HOUSE_1_CONCAVE_SR, ws) {
	
	init {
		setSolid(true)
	}
	
}



class SAND_TOP_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SAND_TOP_LEFT, ws) {
	
	init {
		setSolid(false)
	}
	
}
class SAND_TOP(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SAND_TOP, ws) {
	
	init {
		setSolid(false)
	}
	
}
class SAND_TOP_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SAND_TOP_RIGHT, ws) {
	
	init {
		setSolid(false)
	}
	
}
class SAND_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SAND_RIGHT, ws) {
	
	init {
		setSolid(false)
	}
	
}
class SAND_BOTTOM_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SAND_BOTTOM_RIGHT, ws) {
	
	init {
		setSolid(false)
	}
	
}
class SAND_BOTTOM(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SAND_BOTTOM, ws) {
	
	init {
		setSolid(false)
	}
	
}
class SAND_BOTTOM_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SAND_BOTTOM_LEFT, ws) {
	
	init {
		setSolid(false)
	}
	
}
class SAND_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SAND_LEFT, ws) {
	
	init {
		setSolid(false)
	}
	
}
class SAND(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SAND, ws) { init { setSolid(false) } }



class WATER_TOP_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.WATER_TOP_LEFT, ws) {
	
	init {
		setSolid(true)
	}
	
}
class WATER_TOP(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.WATER_TOP, ws) {
	
	init {
		setSolid(true)
	}
	
}
class WATER_TOP_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.WATER_TOP_RIGHT, ws) {
	
	init {
		setSolid(true)
	}
	
}
class WATER_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.WATER_RIGHT, ws) {
	
	init {
		setSolid(true)
	}
	
}
class WATER_BOTTOM_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.WATER_BOTTOM_RIGHT, ws) {
	
	init {
		setSolid(true)
	}
	
}
class WATER_BOTTOM(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.WATER_BOTTOM, ws) {
	
	init {
		setSolid(true)
	}
	
}
class WATER_BOTTOM_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.WATER_BOTTOM_LEFT, ws) {
	
	init {
		setSolid(true)
	}
	
}
class WATER_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.WATER_LEFT, ws) {
	
	init {
		setSolid(true)
	}
	
}
class WATER(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.WATER, ws) { init { setSolid(true) } }