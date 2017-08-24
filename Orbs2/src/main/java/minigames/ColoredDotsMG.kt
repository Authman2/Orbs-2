package minigames;

import controllers.GameController
import javafx.scene.Scene
import javafx.stage.Stage
import main_package.Orbs2
import controllers.*
import entities.Player

import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import javafx.scene.text.Font

import states.GameState
import je.visual.Vector2D
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseEvent
import java.util.Random



/*
*   The dot that gets displayed in the colored dot minigame.
*/
class Dot(var pos: Vector2D, val graphics: GraphicsContext) {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// The background color of the circle. Changes to green or red.
	var backgroundColor: Color = Color.GRAY




	/********************
	*					*
	*	   METHODS		*
	*					*
	*********************/




	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	fun initialize() {
		


	}

	fun update() {

	}


	fun draw() {
		graphics.setFill(backgroundColor)
		graphics.fillOval(pos.X.toDouble() * 32, pos.Y.toDouble() * 32, 40.0, 40.0)
	}
}



/*
*   The class for the colored dots minigame.
*/
public class ColoredDotsMG(gc: GameController, stage: Stage) : GameState(gc, stage) {

    /********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// The canvas and graphics context used to draw everything on the screen.
	var canvas: Canvas
	var graphics: GraphicsContext

    // The array of colors to choose from.
    val colors: Array<Color> = arrayOf( Color.RED, Color.YELLOW, Color.BLUE,
                                        Color.GREEN, Color.ORANGE, Color.PURPLE )


    // Variables for the minigame.
    var started: Boolean = false
    var finished: Boolean = false
    var changeDotTimer: Int = 100
    var score: Int = 0
    var round: Int = 1
    val rand = Random()

    // The dot that the user clicks on in the minigame.
    val dot: Dot





    /********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

    init {
        canvas = Canvas((Orbs2.WIDTH).toDouble(), (Orbs2.HEIGHT).toDouble())
		graphics = canvas.getGraphicsContext2D()
        
        dot = Dot(randomLocation(), graphics)
        dot.backgroundColor = randomColor()

		// IMPORTANT: Scene Setup
		root.getChildren().add(canvas);
		scene = Scene(root, Orbs2.WIDTH.toDouble(), Orbs2.HEIGHT.toDouble());

        setupKeyActions();
        setupMouseActions();
    }





    /********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

    private fun setupKeyActions() {
        scene.setOnKeyPressed {
			val w: KeyCode = it.getCode();

            // Start the game.
			if(w == KeyCode.SPACE) {
                if(!started) started = true
            }

            if(w == KeyCode.ESCAPE) {
                if(score >= 15) {
                    Player.distractedTownspeople = true
                    NPCManager.oilSpillMan.setSpeech(
                        NPCManager.npcSpeech.get("oilSpillMan_2")!!
                    )   
                }
                gc.goTo(1)
            }
        }
    }

    private fun setupMouseActions() {
        scene.setOnMouseClicked { }
		scene.setOnMouseMoved { }
		scene.setOnMousePressed {
            if(started && !finished) {
                
                // If you click a primary color, add 2 pts. Otherwise, substract 1 pt.
                if(hoveringDot(it)) {
                    if(dot.backgroundColor == Color.RED || dot.backgroundColor == Color.YELLOW || dot.backgroundColor == Color.BLUE) {
                        score += 2
                    } else {
                        score -= 1
                    }

                    reset(true)
                } else {
                    if(dot.backgroundColor == Color.RED || dot.backgroundColor == Color.YELLOW || dot.backgroundColor == Color.BLUE) {
                        score -= 1
                    }

                    reset(false)
                }
            }
		}
		scene.setOnMouseReleased { }
    }


    // Reset the dot and timer.
    private fun reset(updateRound: Boolean) {
        dot.pos = randomLocation()
        dot.backgroundColor = randomColor()
        changeDotTimer = 100

        if(updateRound) {
            if(round + 1 > 10) {
                finished = true
            } else {
                round++
            }
        }
    }





    /********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

    // Returns a random location of the screen in the form of a Vector2D object.
    private fun randomLocation(): Vector2D {
        val x = rand.nextInt(Orbs2.WIDTH / 32)
        val y = rand.nextInt(Orbs2.HEIGHT / 32)

        return Vector2D(x.toFloat(), y.toFloat())
    }


    // Returns a random color from the list of colors above.
    private fun randomColor(): Color {
        return colors[ rand.nextInt(colors.count()) ]
    }


    // Returns whether or not the mouse is hovering above the dot.
    private fun hoveringDot(e: MouseEvent): Boolean {
        if(e.x >= dot.pos.X * 32 && e.x < dot.pos.X * 32 + 40.0) {
            if(e.y >= dot.pos.Y * 32 && e.y < dot.pos.Y * 32 + 40.0) {
                return true
            }
        }
        return false
    }






	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

    override fun initialize() {
        score = 0
        started = false
        finished = false
        round = 1
        changeDotTimer = 100
    }


    override fun update() {
        if(started && !finished) {
            changeDotTimer -= 1;
            
            // Constantly count down on the timer. If it reaches 0, change the location and color or the dot.
            if(changeDotTimer <= 0) {
                reset(true)
            }
        }
    }


    override fun draw() {
        // Clear
		graphics.clearRect(0.0, 0.0, (Orbs2.WIDTH).toDouble(), (Orbs2.HEIGHT).toDouble())

		// Draw background color
		graphics.setFill(Color.rgb(239, 239, 239))
		graphics.fillRect(0.0, 0.0, Orbs2.WIDTH.toDouble(), Orbs2.HEIGHT.toDouble())

        // Draw the timer.
        graphics.setFont(Font("System", 15.0))
		graphics.strokeText("Timer: $changeDotTimer", 10.0, 20.0)

        // Draw the player's score.
        graphics.strokeText("Score: $score", 10.0, 40.0)

        // Draw the round that the player is on.
        graphics.strokeText("Round: $round", 10.0, 60.0)

        // Draw the start label and the dot.
        graphics.setFont(Font("System", 20.0))
        graphics.setStroke(Color.BLACK)
        if(!started) {
            graphics.strokeText("Press 'SPACE' to start the minigame.", Orbs2.WIDTH / 2 - 150.0, 100.0)
        } else if(finished) {
            if(score >= 15)
                graphics.strokeText("You have successfully completed the minigame!\nPress 'Escape' to go back to the game", Orbs2.WIDTH / 2 - 200.0, 100.0)
            else
                graphics.strokeText("You did not complete the mingame successfully.\nPress 'Escape' to go back to the game.", Orbs2.WIDTH / 2 - 200.0, 100.0)
        } else {
            if(!finished) dot.draw();
        }
    }

}