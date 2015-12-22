package com.devdream.model;

import com.devdream.helper.MathHelper;

/**
 * The commercial is the employee of the shop that
 * sells the shop offers.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Commercial extends User {
	
	//
	// Attributes
	/**
	 * Points earned by a commercial. The more,
	 * the better salary.
	 */
	private int earnedPoints;
	private float salary;

	//
	// Constructors
	public Commercial(final String ID, String name, String surname, float salary) {
		super(ID, name, surname);
		setSalary(salary);
		setEarnedPoints(0);
	}
	
	//
	// Methods
	/**
	 * Increases the points of the commercial (Point per sale line).
	 * @param points The points (The amount of sale lines)
	 */
	public void increasePoints(int points) {
		earnedPoints += points;
	}
	
	public void increaseSalary() {
		setSalary(getSalary() + earnedPoints);
	}
	
	//
	// Getters && Setters
	public float getSalary() {
		return salary;
	}
	
	public void setSalary(float salary) {
		this.salary = (!MathHelper.isNegativeNumber((int) salary) &&
						salary > Shop.MINIMUN_SALARY)
				? salary
				: Shop.MINIMUN_SALARY;
	}
	
	public int getEarnedPoints() {
		return earnedPoints;
	}
	
	public void setEarnedPoints(int earnedPoints) {
		this.earnedPoints = (earnedPoints > 0)
				? earnedPoints
				: 0;
	}
	
}
