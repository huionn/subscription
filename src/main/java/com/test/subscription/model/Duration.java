package com.test.subscription.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Duration {

	@JsonFormat(pattern = "d/M/yyyy")
	private LocalDate startDate;
	@JsonFormat(pattern = "d/M/yyyy")
	private LocalDate endDate;
	
	public void validate() {
		if (endDate.isBefore(startDate)) {
			throw new IllegalArgumentException("endDate must not be earlier than startDate");
		}
		if (endDate.isAfter(startDate.plusMonths(3).minusDays(1))) {
			throw new IllegalArgumentException("duration must not be longer than 3 months");
		}

	}
}
