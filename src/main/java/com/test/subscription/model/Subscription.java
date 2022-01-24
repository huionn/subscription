package com.test.subscription.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Subscription {
	private BigDecimal amount;
	private SubscriptionType subscriptionType;
	private int firstDay;
	private Duration period;

}
