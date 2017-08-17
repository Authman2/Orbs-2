package minigames

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

//-KrhvqCIGS_2i3CbmWT7

/*
*	The circles that get displayed at the bottom of the page to signal which trials
* have been completed.
*/
class TrialCircle(val pos: Vector2D, val graphics: GraphicsContext) {

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

	// Sets the result. True means correct, false means incorrect.
	fun setResult(res: Boolean) { backgroundColor = if(res) Color.GREEN else Color.RED }




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
		graphics.fillOval(pos.X.toDouble() * 32, pos.Y.toDouble() * 32, 50.0, 50.0)
	}
}


/*
*	The swimming minigame class.
*/
public class SwimmingMG(gc: GameController, stage: Stage) : GameState(gc, stage) {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// The canvas and graphics context used to draw everything on the screen.
	var canvas: Canvas
	var graphics: GraphicsContext

	// The text that tells the user what keys to click
	lateinit var keyDirectionsText: ArrayList<String>

	// Where to draw the text
	val position: Vector2D = Vector2D( ((Orbs2.WIDTH / 2) - 150).toFloat(), ((Orbs2.HEIGHT / 2) - 120).toFloat() )

	// The circles that display each trial.
	var trialIndicators: Array<TrialCircle>

	// Variables for this minigame
	var timer: Int = 500
	var MAX: Int = 500
	var started: Boolean = false
	var finished: Boolean = false

	var currentIndex = 0
	var maxIndex = 0
	var trial = 0



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public companion object SwimmingMG {
		@JvmField
		public var swimmingTaskCompleted: Boolean = false
	}

	init {
		canvas = Canvas((Orbs2.WIDTH).toDouble(), (Orbs2.HEIGHT).toDouble())
		graphics = canvas.getGraphicsContext2D()

		// Setup the positions of the trial indicators
		trialIndicators = arrayOf(
			TrialCircle(Vector2D(0.2f,12f), graphics),
			TrialCircle(Vector2D(2.2f,12f), graphics),
			TrialCircle(Vector2D(4.2f,12f), graphics),
			TrialCircle(Vector2D(6.2f,12f), graphics),
			TrialCircle(Vector2D(8.2f,12f), graphics),
			TrialCircle(Vector2D(10.2f,12f), graphics),
			TrialCircle(Vector2D(12.2f,12f), graphics),
			TrialCircle(Vector2D(14.2f,12f), graphics),
			TrialCircle(Vector2D(16.2f,12f), graphics),
			TrialCircle(Vector2D(18.2f,12f), graphics)
		)

		// IMPORTANT: Scene Setup
		root.getChildren().add(canvas);
		scene = Scene(root, Orbs2.WIDTH.toDouble(), Orbs2.HEIGHT.toDouble());
		setupKeys();
	}




	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	fun setupKeys() {
		scene.setOnKeyPressed({
			val w: KeyCode = it.getCode();

			if(w == KeyCode.SPACE) {
				if(started == false) {
					resetIndexes()
					started = true
					position.X = ((Orbs2.WIDTH / 2) - 20).toFloat()
					position.Y = ((Orbs2.HEIGHT / 2) - 130).toFloat()
				}
			}
			if(w == KeyCode.ESCAPE) {
				if(finished || started == false) {
					var i = 0
					for(tc in trialIndicators) {
						if(tc.backgroundColor == Color.GREEN)
							i++
					}
					if(i == trialIndicators.count()) {
						swimmingTaskCompleted = true
						
						NPCManager.swimmingLessonsWoman.setSpeech(
							NPCManager.npcSpeech.get("swimmingLessonsWoman_4")!!
						);
					}
					gc.goTo(1);
				}
			}
			if(w == KeyCode.UP || w == KeyCode.W) {
				if(started) {
					if(!keyDirectionsText.get(0).split("\n")[currentIndex].equals("Up")) {
						trialIndicators[trial].backgroundColor = Color.RED
						trial++
						resetIndexes()
					} else {
						currentIndex++
						if(currentIndex >= maxIndex) {
							trialIndicators[trial].backgroundColor = Color.GREEN
							trial++
							resetIndexes()
						}
					}
				}
			}
			if(w == KeyCode.DOWN || w == KeyCode.S) {
				if(started) {
					if(!keyDirectionsText.get(0).split("\n")[currentIndex].equals("Down")) {
						trialIndicators[trial].backgroundColor = Color.RED
						trial++
						resetIndexes()
					} else {
						currentIndex++
						if(currentIndex >= maxIndex) {
							trialIndicators[trial].backgroundColor = Color.GREEN
							trial++
							resetIndexes()
						}
					}
				}
			}
			if(w == KeyCode.LEFT || w == KeyCode.A) {
				if(started) {
					if(!keyDirectionsText.get(0).split("\n")[currentIndex].equals("Left")) {
						trialIndicators[trial].backgroundColor = Color.RED
						trial++
						resetIndexes()
					} else {
						currentIndex++
						if(currentIndex >= maxIndex) {
							trialIndicators[trial].backgroundColor = Color.GREEN
							trial++
							resetIndexes()
						}
					}
				}
			}
			if(w == KeyCode.RIGHT || w == KeyCode.D) {
				if(started) {
					if(!keyDirectionsText.get(0).split("\n")[currentIndex].equals("Right")) {
						trialIndicators[trial].backgroundColor = Color.RED
						trial++
						resetIndexes()
					} else {
						currentIndex++
						if(currentIndex >= maxIndex) {
							trialIndicators[trial].backgroundColor = Color.GREEN
							trial++
							resetIndexes()
						}
					}
				}
			}
		});
	}


	private fun resetIndexes() {
		keyDirectionsText.removeAt(0)
		MAX -= 35
		timer = MAX
		currentIndex = 0
		maxIndex = keyDirectionsText.get(0).split("\n").count()
	}
	


	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/




	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	override fun initialize() {
		position.X = ((Orbs2.WIDTH / 2) - 150).toFloat()
		position.Y = ((Orbs2.HEIGHT / 2) - 120).toFloat()
		timer = 500
		MAX = 500
		started = false
		finished = false
		currentIndex = 0
		maxIndex = 0
		for(tc in trialIndicators) {
			tc.backgroundColor = Color.GRAY
		}
		keyDirectionsText = arrayListOf(
			"Press SPACE to start the minigame",
			"Left\nLeft\nRight\nLeft",
			"Right\nUp\nDown\nUp",
			"Up\nDown\nLeft\nRight",
			"Right\nRight\nRight\nUp",
			"Up\nDown\nUp\nRight\nUp",
			"Up\nUp\nRight\nLeft\nDown\nDown",
			"Up\nRight\nLeft\nLeft\nUp\nRight\nRight",
			"Left\nLeft\nRight\nRight\nDown\nUp\nRight\nDown\nUp",
			"Up\nUp\nUp\nRight\nDown\nRight\nLeft\nUp\nUp\nUp",
			"Up\nRight\nLeft\nDown\nRight\nRight\nUp\nUp\nDown",
			"Congratulations, you successfully completed \nthe minigame! Press the 'Escape' key to go back \nto the game."
		)
	}

	override fun update() {
		if(started) {
			timer--
		}

		if(timer <= 0) {
			resetIndexes()
			trialIndicators[trial].backgroundColor = Color.RED
			trial++
		}
		//println("TRIAL: $trial")
		//println("CI: $currentIndex, MI: $maxIndex")

		if(trial > 9) {
			position.X = ((Orbs2.WIDTH / 2) - 260).toFloat()
			position.Y = ((Orbs2.HEIGHT / 2) - 120).toFloat()
			started = false
			finished = true

			for(tc in trialIndicators) {
				if(tc.backgroundColor == Color.RED) {
					SwimmingMG.swimmingTaskCompleted = false
					keyDirectionsText[0] = "Sorry, you did not complete the minigame successfully.\nPress 'Escape' to return to the game."
					break;
				}
			}
		}
	}

	override fun draw() {
		// Clear
		graphics.clearRect(0.0, 0.0, (Orbs2.WIDTH).toDouble(), (Orbs2.HEIGHT).toDouble())

		// Draw background color
		graphics.setFill(Color.CYAN)
		graphics.fillRect(0.0, 0.0, Orbs2.WIDTH.toDouble(), Orbs2.HEIGHT.toDouble())

		// Set the font of the text label.
		graphics.setFont(Font("System", 20.0))

		// Draw the text label that displays the directions for the player.
		graphics.strokeText(keyDirectionsText[0], position.X.toDouble(), position.Y.toDouble())

		// Draw the time label.
		graphics.setFont(Font("System", 15.0))
		graphics.strokeText("Time Left: $timer", 10.0, 20.0)


		// Draw each trial indicator.
		for(tc in trialIndicators) {
			tc.draw();
		}
	}


}