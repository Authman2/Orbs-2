package minigames;

import controllers.GameController
import javafx.scene.Scene
import javafx.stage.Stage
import main_package.Orbs2
import controllers.*

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
class Dot(val pos: Vector2D, val graphics: GraphicsContext) {

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
    var changeDotTimer: Int = 1000
    var score: Int = 0
    val rand = Random()

    // The dot that the user clicks on in the minigame.
    val dot: Dot
    var dotPos: Vector2D





    /********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

    init {
        canvas = Canvas((Orbs2.WIDTH).toDouble(), (Orbs2.HEIGHT).toDouble())
		graphics = canvas.getGraphicsContext2D()
        
        dotPos = randomLocation()
        dot = Dot(dotPos, graphics)
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
        }
    }
	


    private fun setupMouseActions() {
        scene.setOnMouseClicked {
			
		}
		scene.setOnMouseMoved {
			
		}
		scene.setOnMousePressed {
			
		}
		scene.setOnMouseReleased {
			
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





	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

    override fun initialize() {

    }


    override fun update() {
        if(started) {
            changeDotTimer -= 1;
            
            // Constantly count down on the timer. If it reaches 0, change the location and color or the dot.
            if(changeDotTimer <= 0) {
                dotPos = randomLocation()
                dot.backgroundColor = randomColor()
                changeDotTimer = 1000
            }
        }
    }


    override fun draw() {
        // Clear
		graphics.clearRect(0.0, 0.0, (Orbs2.WIDTH).toDouble(), (Orbs2.HEIGHT).toDouble())

		// Draw background color
		graphics.setFill(Color.CYAN)
		graphics.fillRect(0.0, 0.0, Orbs2.WIDTH.toDouble(), Orbs2.HEIGHT.toDouble())


        // Draw the start label.
        graphics.setFont(Font("System", 20.0))
        graphics.setStroke(Color.BLACK)

        if(!started)
            graphics.strokeText("Press 'SPACE' to start the minigame.", Orbs2.WIDTH / 2 - 150.0, 100.0)
        else
            dot.draw();
    }

}