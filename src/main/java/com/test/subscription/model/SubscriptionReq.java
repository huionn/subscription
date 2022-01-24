package com.test.subscription.model;

import java.math.BigDecimal;
import java.time.DayOfWeek;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubscriptionReq {

	private BigDecimal amount;
	private SubscriptionType subscriptionType;
	private DayOfWeek dayOfWeek;
	private int dayOfMonth;
	private Duration duration;

}
