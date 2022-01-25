package com.test.subscription.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DurationTest {

	@Test
	void testValidate() {
		Duration d = new Duration();
		d.setStartDate(LocalDate.of(2022, 1, 1));
		d.setEndDate(LocalDate.of(2022, 4, 30));
		
		assertThrows(IllegalArgumentException.class, () -> d.validate());
		
		d.setEndDate(LocalDate.of(2021, 12, 31));
		assertThrows(IllegalArgumentException.class, () -> d.validate());
		
		d.setEndDate(LocalDate.of(2022, 3, 1));		
		d.validate();
	}

}
