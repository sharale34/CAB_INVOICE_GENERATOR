package com.bridgelabz.cabinvoicegenerator;

import java.util.logging.Logger;

public class InvoiceGenerator {

	private static final double MINIMUM_COST_PER_KM = 10;
	private static final int COST_PER_TIME = 1;
	private static final double MINIMUM_FARE = 5.0;
	private final RideRepository USER_RIDES;

	public InvoiceGenerator() {
		this.USER_RIDES = null;
	}

	public InvoiceGenerator(int userId, Ride[] rides) {
		USER_RIDES = new RideRepository(userId, rides);
	}

	public static void main(String[] args) {
		Logger log = Logger.getLogger(InvoiceGenerator.class.getName());
		log.info("Welcome to the Cab Invoice Generator Program.");
	}

	public double calculateFare(double distance, int time) {
		double totalFare = distance * MINIMUM_COST_PER_KM + time * COST_PER_TIME;
		return Math.max(totalFare, MINIMUM_FARE);
	}

	public InvoiceSummary calculateFare(int userId) {
		Ride[] rides = USER_RIDES.getRides(userId);
		double totalFare = 0.0;
		for (Ride ride : rides) {
			totalFare += this.calculateFare(ride.distance, ride.time);
		}
		return new InvoiceSummary(rides.length, totalFare);
	}
}
