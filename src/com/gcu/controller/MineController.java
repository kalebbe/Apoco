/**
 * Author:          Kaleb Eberhart
 * Date:            10/14/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
 * Module Name:     MineController.java
 * Module Version:  1.1
 * Summary:         This is an extra bit I decided to do with this milestone. It cost me about 8 hours of my free
 * 					time, so I regret it a bit, but that's besides the point. I've ported most of my minesweeper
 * 					to Java for this website. The visuals are still pretty crappy in chrome, but I will get that
 * 					figured out soon. Also right clicks aren't working yet and I intend to get that fixed with the
 * 					next lilestone. I would also like to get the game board ajaxed, but that isn't a priority.
 * 					There are many other things I would like to get ajaxed as well.
 * 
 * 					-----UPDATES MILESTONE 4-----
 * 					-Right clicking is now detected through javascript and changes the cell to a flag picture and
 * 					 disables it.
 */

package com.gcu.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.model.Button;
import com.gcu.utilities.MinesweeperLogic;

@Controller
@RequestMapping("/mines")
public class MineController {
	
	private MinesweeperLogic ml;
	
	/**
	 * Dependency injection for Minesweeper Logic
	 */
	@Autowired
	public void setUserService(MinesweeperLogic ml) {
		this.ml = ml;
	} 
	
	private int size; //Used multiple times in controller
	
	/**
	 * This method generates the game board and sets the size for the board.
	 * It then returns a multidimensional array of minesweeper buttons to the view.
	 */
	@RequestMapping(path="/start", method=RequestMethod.POST)
	public ModelAndView startGame(@RequestParam("diff") int difficulty, HttpSession session){
		MinesweeperLogic.setLose(false); //Sets lose to false, so they don't lose immediately upon start
		MinesweeperLogic.setWin(false); //See above
		
		size = (difficulty * 5) + 5; //Creates the board size
		
		session.setAttribute("size", size);
		ml.generateBoard(size); //Generates the minesweeper board
		
		return new ModelAndView("minesweeper", "button", MinesweeperLogic.getBtnHolder());
	}
	
	/**
	 * This method handles user left clicks in the game. The input is taken and split
	 * then run with the game logic to determine the results.
	 */
	@RequestMapping(path="/left", method=RequestMethod.POST)
	public ModelAndView leftClick(@RequestParam("btn") String btn, String direction) {
		String[] coor = btn.split(" "); //Splits btn into an array of coordinates
		int x = Integer.parseInt((coor[0])); //Sets x as first coordinate
		int y = Integer.parseInt((coor[1])); //Sets y as second coordinate
		
		MinesweeperLogic.processCell(x, y, size); //This processes the game logic for the cell they clicked
		MinesweeperLogic.checkWin(size); //Checks to see if the user has won the game
		
		return new ModelAndView("minesweeper", "button", MinesweeperLogic.getBtnHolder());
	}
	
	/**
	 * This method gets x and y from the http post sent through javascript. The minesweeper
	 * button is set to flagged (or unflagged) and the gameboard is returned again.
	 */
	@RequestMapping(path="/right", method=RequestMethod.POST)
	public ModelAndView rightClick(@RequestParam("xcoor") int x, @RequestParam("ycoor") int y) {
		Button[][] btnHolder = MinesweeperLogic.getBtnHolder(); //Grabs the buttonholder from minelogic
		if(btnHolder[x][y].isFlagged()) { 
			btnHolder[x][y].setFlagged(false);
		}
		else {
			btnHolder[x][y].setFlagged(true);
		}
		return new ModelAndView("minesweeper", "button", MinesweeperLogic.getBtnHolder());
	}
}
