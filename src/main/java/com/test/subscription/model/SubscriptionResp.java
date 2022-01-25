package com.test.subscription.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubscriptionResp {
	private BigDecimal amount;
	private SubscriptionType subscriptionType;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private List<LocalDate> invoiceDates;
	
}
