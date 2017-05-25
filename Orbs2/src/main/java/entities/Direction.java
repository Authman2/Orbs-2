package entities;

public enum Direction {

	UP, DOWN, LEFT, RIGHT;



 	/** Returns a random direction. */
 	static Direction random() {
 		Direction[] dirs = new Direction[4];
 		dirs[0] = Direction.UP;
 		dirs[1] = Direction.DOWN;
 		dirs[2] = Direction.LEFT;
 		dirs[3] = Direction.RIGHT;
 		return dirs[ (int)(Math.random() * 4 ) ];
 	}
}