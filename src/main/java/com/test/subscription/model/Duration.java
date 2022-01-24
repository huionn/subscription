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
	
	public boolean isValid() {
		if (endDate.isBefore(startDate)) {
			return false;
		}
		if (endDate.isAfter(startDate.plusMonths(3).minusDays(1))) {
			return false;
		}
		return true;
	}
}
