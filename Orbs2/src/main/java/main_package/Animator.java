package main_package;

import javafx.scene.image.Image;


/** A class used for creating 2D animations. 
 * 
 * Originally written by TheIndieDeveloper (https://www.youtube.com/user/InidDevo/about)
 */
public class Animator {
	
	//The images of the animation
	Image[] images;
	
	
	//Is the animation running?
	volatile boolean running = false;
	
	
	//The sprite that is currently being shown
	public Image sprite;
	
	
	//The time and speed of the animation
	private long prevTime, speed;
	
	
	//The frame at a particular time
	private int frameatPause, currentFrame;


	
	//////////// Constructor ////////////
	public Animator(Image[] imgs) { 
		images = imgs;
	}
	
	
	
	//////////// Method ////////////
	
	/** Sets the speed of the animation */
	public void setSpeed(long speed){ this.speed = speed; }

	/** Updates the animation. */
	public void update(){
		long currentTimeMS = System.currentTimeMillis();
		if(running){
			if(currentTimeMS - prevTime >= speed){
				currentFrame++;
				try{
					if(currentFrame <= images.length){
						sprite = images[currentFrame];
					}else{
						reset();
					}
				}catch(IndexOutOfBoundsException e){
					reset();
					sprite = images[currentFrame];
				}
				prevTime = currentTimeMS;
			}
		}
	}
	
	/** Play the animation. */
	public void play(){
		running = true;
		prevTime = 0;
		frameatPause = 0;
		currentFrame = 0;
	}
	
	/** Stop the animation. */
	public void stop(){
		running = false;
		prevTime = 0;
		frameatPause = 0;
		currentFrame = 0;
	}
	
	/** Pause the animation. */
	public void pause(){
		frameatPause = currentFrame;
		running = false;
	}
	
	/** Resume the animation after pausing. */
	public void resume(){
		currentFrame = frameatPause;
	}
	
	/** Reset the animation. */
	public void reset(){
		currentFrame = 0;
	}
	
	/** Returns whether or not the animation is done. */
	public boolean isDoneAnimating(){
		if(currentFrame == images.length){
			return true;
		}else{
			return false;
		}
	}
	
	public Image getCurrentSprite() {
		return sprite;
	}
	
	

}