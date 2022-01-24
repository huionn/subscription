package com.test.subscription.model;

import java.time.Period;

import lombok.Getter;

@Getter
public enum SubscriptionType {
	DAILY(Period.ofDays(1)), WEEKLY(Period.ofWeeks(1)), MONTHLY(Period.ofMonths(1));

	private SubscriptionType(Period period) {
		this.period = period;
	}

	private final Period period;

}
