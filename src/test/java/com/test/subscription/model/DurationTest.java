package com.test.subscription.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DurationTest {

	@Test
	void testIsValid() {
		Duration d = new Duration();
		d.setStartDate(LocalDate.of(2022, 1, 1));
		d.setEndDate(LocalDate.of(2022, 3, 31));
		
		assertTrue(d.isValid());
		
		d.setEndDate(LocalDate.of(2022, 4, 1));
		
		assertFalse(d.isValid());
	}

}
