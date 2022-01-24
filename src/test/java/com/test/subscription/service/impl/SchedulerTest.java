package com.test.subscription.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.test.subscription.model.Duration;
import com.test.subscription.model.SubscriptionType;



class SchedulerTest {

	@Test
	void testGenerateDatesDaily() {
		Scheduler s = new Scheduler();
		
		Duration d = new Duration(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 1));
		List<LocalDate> result = s.generateDates(d, 0, SubscriptionType.DAILY);
		
		assertEquals(32, result.size());
		assertEquals(LocalDate.of(2022, 1, 1), result.get(0));
		assertEquals(LocalDate.of(2022, 2, 1), result.get(result.size() - 1));
	}
	
	@Test
	void testGenerateDatesWeeklySimple() {
		Scheduler s = new Scheduler();
		
		Duration d = new Duration(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 1));
		List<LocalDate> result = s.generateDates(d, 6, SubscriptionType.WEEKLY);
		
		assertEquals(5, result.size());
		assertEquals(LocalDate.of(2022, 1, 1), result.get(0));
		assertEquals(LocalDate.of(2022, 1, 29), result.get(result.size() - 1));
	}
	
	@Test
	void testGenerateDatesWeeklySameWeek() {
		Scheduler s = new Scheduler();
		
		Duration d = new Duration(LocalDate.of(2021, 12, 31), LocalDate.of(2022, 2, 1));
		List<LocalDate> result = s.generateDates(d, 6, SubscriptionType.WEEKLY);
		
		assertEquals(5, result.size());
		assertEquals(LocalDate.of(2022, 1, 1), result.get(0));
		assertEquals(LocalDate.of(2022, 1, 29), result.get(result.size() - 1));
	}
	
	@Test
	void testGenerateDatesWeeklyNextWeek() {
		Scheduler s = new Scheduler();
		
		Duration d = new Duration(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 1));
		List<LocalDate> result = s.generateDates(d, 7, SubscriptionType.WEEKLY);
		
		assertEquals(5, result.size());
		assertEquals(LocalDate.of(2022, 1, 2), result.get(0));
		assertEquals(LocalDate.of(2022, 1, 30), result.get(result.size() - 1));
	}
	
	@Test
	void testGenerateDatesMonthlySimple() {
		Scheduler s = new Scheduler();
		
		Duration d = new Duration(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 3, 1));
		List<LocalDate> result = s.generateDates(d, 1, SubscriptionType.MONTHLY);
		
		assertEquals(3, result.size());
		assertEquals(LocalDate.of(2022, 1, 1), result.get(0));
		assertEquals(LocalDate.of(2022, 3, 1), result.get(result.size() - 1));
	}
	
	@Test
	void testGenerateDatesMonthlyAdjusted() {
		Scheduler s = new Scheduler();
		
		Duration d = new Duration(LocalDate.of(2022, 2, 28), LocalDate.of(2022, 5, 27));
		List<LocalDate> result = s.generateDates(d, 31, SubscriptionType.MONTHLY);
		
		assertEquals(3, result.size());
		assertEquals(LocalDate.of(2022, 2, 28), result.get(0));
		assertEquals(LocalDate.of(2022, 3, 31), result.get(1));
		assertEquals(LocalDate.of(2022, 4, 30), result.get(2));
	}
	
	@Test
	void testGenerateDatesMonthlyAdjusted2() {
		Scheduler s = new Scheduler();
		
		Duration d = new Duration(LocalDate.of(2022, 1, 30), LocalDate.of(2022, 3, 30));
		List<LocalDate> result = s.generateDates(d, 30, SubscriptionType.MONTHLY);
		
		assertEquals(3, result.size());
		assertEquals(LocalDate.of(2022, 1, 30), result.get(0));
		assertEquals(LocalDate.of(2022, 2, 28), result.get(1));
		assertEquals(LocalDate.of(2022, 3, 30), result.get(2));
	}

}
