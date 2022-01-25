package com.test.subscription.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.test.subscription.model.Duration;
import com.test.subscription.model.SubscriptionReq;
import com.test.subscription.model.SubscriptionType;

class SimpleSubscriptionServiceTest {

	@Test
	void test() {
		SimpleSubscriptionService s = new SimpleSubscriptionService(new Scheduler());
		SubscriptionReq req = new SubscriptionReq();
		req.setAmount(new BigDecimal("10.00"));
		Duration d = new Duration();
		d.setStartDate(LocalDate.of(2022, 1, 1));
		d.setEndDate(LocalDate.of(2022, 4, 1)); // more than 3 months
		req.setDuration(d);
		req.setSubscriptionType(SubscriptionType.DAILY);

		assertThrows(IllegalArgumentException.class, () -> s.register(req));

	}

}
