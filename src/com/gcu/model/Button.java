/**
 * Author:          Kaleb Eberhart
 * Date:            10/14/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
 * Module Name:     Button.java
 * Module Version:  1.0
 * Summary:         This is the model for the Minesweeper button. This is actually a very simple
 * 					class and everything is pretty self-explantory. Also wasn't changed much from c#'s
 * 					implementation of the button model.
 */

package com.gcu.model;

public class Button {
	private boolean live; //Button is or is not a live bomb
	private int liveNeighbors; //How many bombs the button has surrounding it
	private boolean visited; //Has the button been visited?
	private int x; //X coordinate of the button
	private int y; //Y coordinate of the button
	private boolean flagged; //Flagged by right click. Not yet implemented
	
	/**
	 * Getters and setters below
	 */
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean isLive) {
		this.live = isLive;
	}

	public int getLiveNeighbors() {
		return liveNeighbors;
	}

	public void setLiveNeighbors(int liveNeighbors) {
		this.liveNeighbors = liveNeighbors;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean isVisited) {
		this.visited = isVisited;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	//Non default constructor that creates a global button.
	//There won't be a default constructor for this model.
	public Button(Integer x, Integer y) {
		this.live = false;
		this.visited = false;
		this.liveNeighbors = 0;
		this.x = x;
		this.y = y;
		this.flagged = false;
	}
}
