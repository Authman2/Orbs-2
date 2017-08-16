package tiles

import je.visual.Vector2D
import main_package.*
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
class ROAD_SIDEWAYS(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.ROAD_SIDEWAYS, ws) { init { setSolid(false) } }
class DOOR(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.DOOR, ws) { init { setSolid(false) } }
class ROCK(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.ROCK, ws) { init { setSolid(false) } }
class GRASS_2(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.GRASS_2, ws) { init { setSolid(false) } }
class FLOWER_1(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.FLOWER_1, ws) { 
    val anim: Animator
    init { 
        setSolid(false)
        setSprites( arrayOf(Assets.FLOWER_1, Assets.FLOWER_1, Assets.FLOWER_2, Assets.FLOWER_2) );
        
        anim = Animator(arrayOf(Assets.FLOWER_1, Assets.FLOWER_1, Assets.FLOWER_2, Assets.FLOWER_2))
        anim.setSpeed(250)
        anim.play()
    }
    
    override fun draw() {
        this.worldState.getGraphics().drawImage(anim.getCurrentSprite(), (position.X*size).toDouble(), (position.Y*size).toDouble())
        anim.update()
    }
}
class FLOWER_2(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.FLOWER_2, ws) {
    val anim: Animator
    init { 
        setSolid(false)
        setSprites( arrayOf(Assets.FLOWER_1, Assets.FLOWER_1, Assets.FLOWER_2, Assets.FLOWER_2) );
        
        anim = Animator(arrayOf(Assets.FLOWER_1, Assets.FLOWER_1, Assets.FLOWER_2, Assets.FLOWER_2))
        anim.setSpeed(250)
        anim.play()
    }
    
    override fun draw() {
        this.worldState.getGraphics().drawImage(anim.getCurrentSprite(), (position.X*size).toDouble(), (position.Y*size).toDouble())
        anim.update()
    }
}
class BREAKABLE_TREE(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.BREAKABLE_TREE, ws) { init { setSolid(true) } }
class WHIRLPOOL(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.WHIRLPOOL, ws) { init { setSolid(false) } }
class TOWEL_1_TOP(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TOWEL_1_TOP, ws) { init { setSolid(false) } }
class TOWEL_1_BOTTOM(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TOWEL_1_BOTTOM, ws) { init { setSolid(false) } }
class TOWEL_2_TOP(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TOWEL_2_TOP, ws) { init { setSolid(false) } }
class TOWEL_2_BOTTOM(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.TOWEL_2_BOTTOM, ws) { init { setSolid(false) } }
class SWAMP_TOP_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SWAMP_TOP_LEFT, ws) { init { setSolid(false) } }
class SWAMP_TOP(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SWAMP_TOP, ws) { init { setSolid(false) } }
class SWAMP_TOP_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SWAMP_TOP_RIGHT, ws) { init { setSolid(false) } }
class SWAMP_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SWAMP_LEFT, ws) { init { setSolid(false) } }
class SWAMP(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SWAMP, ws) { init { setSolid(false) } }
class SWAMP_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SWAMP_RIGHT, ws) { init { setSolid(false) } }
class SWAMP_BOTTOM_LEFT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SWAMP_BOTTOM_LEFT, ws) { init { setSolid(false) } }
class SWAMP_BOTTOM(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SWAMP_BOTTOM, ws) { init { setSolid(false) } }
class SWAMP_BOTTOM_RIGHT(pos: Vector2D?, ws: WorldState?) : Tile(pos, Assets.SWAMP_BOTTOM_RIGHT, ws) { init { setSolid(false) } }