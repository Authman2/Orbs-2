package entities;

public enum Direction {

	UP, DOWN, LEFT, RIGHT;


	
	
 	/** Returns a random direction. */
 	public static Direction random() {
 		Direction[] dirs = new Direction[4];
 		dirs[0] = Direction.UP;
 		dirs[1] = Direction.DOWN;
 		dirs[2] = Direction.LEFT;
 		dirs[3] = Direction.RIGHT;
 		return dirs[ (int)(Math.random() * 4 ) ];
 	}
 	
 	
 	/** Returns the opposite direction of the current one. */
 	public static Direction opposite(Direction d) {
 		if(d == Direction.UP) {
 			return Direction.DOWN;
 		}
 		else if(d == Direction.DOWN) {
 			return Direction.UP;
 		}
 		else if(d == Direction.LEFT) {
 			return Direction.RIGHT;
 		}
 		else {
 			return Direction.LEFT;
 		}
 	}
}