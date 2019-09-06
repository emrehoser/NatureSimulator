package project;

import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

/*	Abstract class that allows to create creatures on the game.
 * 
 */

public abstract class Creature implements Drawable{
	protected double health;
	protected int x;
	protected int y;
	/*
	 * Create a new creature instance
	 * @param health double represent creature health
	 * @param x integer represent creature x coordinate on the panel
	 * @param y integer represent creature y coordinate on the panel
	 */
	public Creature(double health,int x,int y) {
		this.health = health;
		this.setX(x);
		this.setY(y);
	}
	/**
	 * 	The method draw the new creatures on the panel
	 * @param panel GridPanel represent this game panel
	 */
	public abstract void draw(GridPanel panel);
	/**
	 * The method determine which proper action should be chosen by a creature
	 * @param info LocalInformtion that keeps the creature local information.
	 * @return action
	 */
	public abstract Action chooseAction(LocalInformation info);
	/**
	 * The abstract method provides stay action condition according to creature
	 */
	public abstract void stay();
	/**
	 * The method provides move action condition according to creature
	 * @param direction direction to which creature move
	 */
	public void move(Direction direction) {
		
	}
	/**
	 * The abstract method provides reproduce action condition according to creature
	 * @param direction direction to which creature reproduce
	 * @return direction
	 */
	public abstract Creature reproduce(Direction direction);
	/**
	 * The method provides attack action condition according to creature
	 * @param attackedCreature the creature will be attacked.
	 */
	public void attack(Creature attackedCreature) {
		
	}
	/**
	 * getter for creature's health
	 * @return health
	 */

	public double getHealth() {
		return health;
	}
	/**
	 * setter for creature's health
	 * @param health double keeps creatures's health
	 */
	public void setHealth(double health) {
		this.health = health;
	}
	/**
	 * getter for creature's y coordinate
	 * @return y
	 */

	public int getY() {
		return y;
	}
	/**
	 * setter for creature's y coordinate
	 * @param y integer keeps creatures's y coordinate
	 */

	public void setY(int y) {
		this.y = y;
	}
	/**
	 * getter for creature's x coordinate
	 * @return x
	 */

	public int getX() {
		return x;
	}
	/**
	 * setter for creature's x coordinate
	 * @param x integer keeps creatures's x coordinate
	 */

	public void setX(int x) {
		this.x = x;
	}
	

}
