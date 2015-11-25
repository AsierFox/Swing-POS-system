package com.devdream.model;

import com.devdream.helper.MathHelper;

/**
 * TODO Description
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Commercial extends User {

	/**
	 * Points earned by a commercial. The more,
	 * the better salary.
	 */
	private int pointsEarned;
	private float salary;
	
	public Commercial(final String ID, String name, SubscriberCard subscriberCard, float salary) {
		super(ID, name, subscriberCard);
		setSalary(salary);
	}

	public void setSalary(float salary) {
		this.salary = (!MathHelper.isNegativeNumber((int) salary) &&
						salary > Shop.MINIMUN_SALARY) ?
				salary :
				Shop.MINIMUN_SALARY;
	}

	//
	// Getters && Setters
	public float getSalary() {
		return salary;
	}
	
}