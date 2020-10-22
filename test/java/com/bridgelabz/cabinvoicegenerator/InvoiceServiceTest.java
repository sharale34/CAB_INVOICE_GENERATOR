package com.bridgelabz.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {

	InvoiceGenerator invoiceGenerator = null;

	@Before
	public void setUp() throws Exception {
		invoiceGenerator = new InvoiceGenerator();
	}

	@Test
	public void givenDistanceAndTimeShouldReturnTotalFare() {
		double distance = 2.0;
		int time = 5;
		double fare = invoiceGenerator.calculateFare(distance, time);
		Assert.assertEquals(25, fare, 0.0);
	}

	@Test
	public void givenLessDistanceAndTimeShouldReturnMinimumFare() {
		double distance = 0.1;
		int time = 2;
		double fare = invoiceGenerator.calculateFare(distance, time);
		Assert.assertEquals(5, fare, 0.0);
	}

	@Test
	public void givenMultipleRidesShouldReturnInvoiceSummary() {
		int userId = 456;
		Ride[] rides = { new Ride(2.0, 5), new Ride(0.1, 1) };
		InvoiceGenerator cabInvoiceGenerator = new InvoiceGenerator(userId, rides);
		InvoiceSummary actualInvoiceSummary = cabInvoiceGenerator.calculateFare(userId);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
		Assert.assertEquals(expectedInvoiceSummary, actualInvoiceSummary);
	}
}
