package project;

import java.awt.Color;
import java.util.List;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;
/**
 * Class that allows to create plant in the game
 */
public class Plant extends Creature {
	private static final double MAX_HEALTH = 1.0;

	/**
	 * Create a new Plant instance
	 * @param x integer represent plant x coordinate on the panel
	 * @param y integer represent plant y coordinate on the panel
	 */
	public Plant (int x,int y) {
		super(Plant.MAX_HEALTH/2.0,x,y);
	}
	/**
	 * 	The method draw the new Plant on the panel
	 * @param panel GridPanel represent this game panel
	 */

	@Override
	public void draw(GridPanel panel) {
		// draw a plant, color can be changed
		panel.drawSquare(getX(),getY(), new Color(0,(int)(250-this.health*150),0));


	}

	/**
	 * The method determine which proper action should be chosen by a plant
	 * @param info LocalInformtion that keeps the plant local information.
	 * @return action
	 */
	@Override
	public Action chooseAction(LocalInformation info) {
		// stores direction 
		List<Direction> freeDirections = info.getFreeDirections();
		// a random direction from freeDirections
		Direction directionToReproduce = LocalInformation.getRandomDirection(freeDirections);
		// choose the proper action for plant
		if(health >= Plant.MAX_HEALTH*3.0 / 4.0 && directionToReproduce != null) {
			Action action = new Action(Action.Type.REPRODUCE,directionToReproduce);
			return action;
		}
		else {
			Action action = new Action(Action.Type.STAY);
			return action;
		}
	}

	/**
	 * The method provides reproduce action condition according to plant
	 * @param direction direction to which plant reproduce
	 */
	public Creature reproduce(Direction direction) {
		// reproduce plant
		if(direction == Direction.UP) {
			// a new-born plant
			Plant cck = new Plant(x,y-1);
			// temporary paramater keeps first health before reproduce
			double temp = this.health;
			this.health = temp*70.0 / 100.0;
			cck.health = temp*10.0 / 100.0;
			return cck;		
		} else if(direction == Direction.DOWN) {
			Plant cck = new Plant(x,y+1);
			double temp = this.health;
			this.health = temp*70.0 / 100.0;
			cck.health = temp*10.0 / 100.0;
			return cck;		
		} else if(direction == Direction.RIGHT) {
			Plant cck = new Plant(x+1,y);
			double temp = this.health;
			this.health = temp*70.0 / 100.0;
			cck.health = temp*10.0 / 100.0;
			return cck;		
		} else  {
			Plant cck = new Plant(x-1,y);
			double temp = this.health;
			this.health = temp*70.0 / 100.0;
			cck.health = temp*10.0 / 100.0;
			return cck;		
		} 

	}

	/**
	 * The method provides stay action condition according to plant
	 */
	@Override
	public void stay() {	
		health += 0.05;
		// check whether plant's health exceed the max health
		if(health >= Plant.MAX_HEALTH) {
			health=Plant.MAX_HEALTH;
		}

	}



}
