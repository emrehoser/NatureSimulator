package project;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;
/**
 * Class that allows to create Herbivore in the game
 */
public class Herbivore extends Creature {
	private static final double MAX_HEALTH = 20.0;
	/**
	 * Create a new herbivore instance
	 * @param x integer represent herbivore x coordinate on the panel
	 * @param y integer represent herbivore y coordinate on the panel
	 */
	public Herbivore(int x,int y) {
		super(Herbivore.MAX_HEALTH/2.0, x, y);

	}
	/**
	 * getter for herbivore's max health
	 * @return max health
	 */
	public static double getMaxHealth() {
		return Herbivore.MAX_HEALTH;
	}
	/**
	 * 	The method draw the new herbivore on the panel
	 * @param panel GridPanel represent this game panel
	 */
	@Override
	public void draw(GridPanel panel) {
		// draw a herbivore, color can be changed
		panel.drawSquare(getX(), getY(), new Color((int)(50+this.health*10.0),0,0));

	}
	/**
	 * The method determine which proper action should be chosen by a herbivore
	 * @param info LocalInformtion that keeps the herbivore local information.
	 * @return action
	 */
	@Override
	public Action chooseAction(LocalInformation info) {
		// stores null pixels direction
		List<Direction> freeDirections = info.getFreeDirections();
		// random directions from freeDirections
		Direction directionToReproduce = LocalInformation.getRandomDirection(freeDirections);
		Direction directionToMove = LocalInformation.getRandomDirection(freeDirections);
		
		// stores plant around the this herbivore location
		List<Direction> plantDirections = new ArrayList<>();
		//add direction where the plant is
		if(info.getCreatureDown() instanceof Plant) plantDirections.add(Direction.DOWN);
		if(info.getCreatureUp() instanceof Plant) plantDirections.add(Direction.UP);
		if(info.getCreatureLeft() instanceof Plant) plantDirections.add(Direction.LEFT);
		if(info.getCreatureRight() instanceof Plant) plantDirections.add(Direction.RIGHT);
		// a random direction from plantDirections
		Direction directionToAttack = LocalInformation.getRandomDirection(plantDirections);

		// choose the proper action for herbivore
		if(health == Herbivore.MAX_HEALTH && directionToReproduce != null) {
			Action action = new Action(Action.Type.REPRODUCE,directionToReproduce);
			return action;
		} else if(directionToAttack != null) {
			Action action = new Action(Action.Type.ATTACK,directionToAttack);
			return action;
		} else if(directionToMove != null && health > 1.0) {
			Action action = new Action(Action.Type.MOVE,directionToMove);
			return action;
		} else {
			Action action = new Action(Action.Type.STAY);
			return action;
		}



	}
	/**
	 * The method provides stay action condition according to herbivore
	 */

	@Override
	public void stay() {
		health-=0.1;

	}
	/**
	 * The method provides move action condition according to herbivore
	 * @param direction direction to which herbivore move
	 */
	@Override
	public void move(Direction direction) {
		// move the herbivore
		if(direction == Direction.UP) {
			y--;
			health -= 1.0;
		}
		else if(direction == Direction.DOWN) { 
			y++;
			health -= 1.0;
		}
		else if(direction == Direction.RIGHT) {
			x++;
			health -= 1.0;

		}else {
			x--;
			health -= 1.0;
		}
	}
	/**
	 * The method provides reproduce action condition according to herbivore
	 * @param direction direction to which herbivore reproduce
	 */
	public Creature reproduce(Direction direction) {
		// reproduce the herbivore
		if(direction == Direction.UP) {
			// the new-born herbivore
			Herbivore it = new Herbivore(x,y--);
			// temporary parameter keeps parent first health
			double temp = this.health;
			this.health = temp*40.0 / 100.0;
			it.health = temp*20.0 / 100.0;
			return it;		
		} else if(direction == Direction.DOWN) {
			Herbivore it = new Herbivore(x,y++);
			double temp = this.health;
			this.health = temp*40.0 / 100.0;
			it.health = temp*20.0 / 100.0;
			return it;		
		} else if(direction == Direction.RIGHT) {
			Herbivore it = new Herbivore(x++,y);
			double temp = this.health;
			this.health = temp*40.0 / 100.0;
			it.health = temp*20.0 / 100.0;
			return it;		
		} else  {
			Herbivore it = new Herbivore(x--,y);
			double temp = this.health;
			this.health = temp*40.0 / 100.0;
			it.health = temp*20.0 / 100.0;
			return it;		
		} 

	}
	/**
	 * The method provides attack action condition according to herbivore
	 * @param attackedCreature the creature will be attacked.
	 */
	public void attack(Creature attackedCreature) {
		health += attackedCreature.health;
		// check whether herbivore exceed the max health or not
		if(health > Herbivore.MAX_HEALTH)
			health = Herbivore.MAX_HEALTH;
		attackedCreature.health = 0.0;
		this.x = attackedCreature.x;
		this.y = attackedCreature.y;

	}

}
