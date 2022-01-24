package com.test.subscription.service.impl;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.test.subscription.model.Duration;
import com.test.subscription.model.SubscriptionType;

@Service
public class Scheduler {
	/**
	 * 
	 * @param duration
	 * @param firstDay either day of week or day of month based on subscription type; not applicable for DAILY subscription type
	 * @param type
	 * @return
	 */
	public List<LocalDate> generateDates(Duration duration, int firstDay, SubscriptionType type) {
		// calculate the first date based on first day
		LocalDate firstDate;
		LocalDate startDate = duration.getStartDate();

		switch (type) {
		case WEEKLY:
			if (startDate.getDayOfWeek().getValue() != firstDay) {
				if (firstDay > startDate.getDayOfWeek().getValue()) {
					firstDate = startDate.plusDays(firstDay - startDate.getDayOfWeek().getValue());
				} else {
					firstDate = startDate.plusDays(firstDay + 7 - startDate.getDayOfWeek().getValue());
				}
			} else {
				firstDate = startDate;
			}
			break;
		case MONTHLY:
			if (startDate.getDayOfMonth() != firstDay) {
				if (firstDay > startDate.getDayOfMonth()) {
					firstDate = resolvePreviousValid(startDate.getYear(), startDate.getMonthValue(), firstDay);
				} else {
					LocalDate nextMonth = startDate.plusMonths(1);
					firstDate = resolvePreviousValid(nextMonth.getYear(), nextMonth.getMonthValue(), firstDay);
				}
			} else {
				firstDate = startDate;
			}
			break;
		case DAILY:
		default:
			firstDate = startDate;
			break;
		}

		// add 1 day in end date to make original end date inclusive
		LocalDate exclusiveEndDate = duration.getEndDate().plusDays(1);
		if (type != SubscriptionType.MONTHLY) {
			// for DAILY and WEEKLY subscription
			return firstDate.datesUntil(exclusiveEndDate, type.getPeriod()).collect(Collectors.toList());
		} else {
			List<LocalDate> dates = new ArrayList<>();
			// handle end of month e.g 29, 30, 31
			for (LocalDate d = firstDate; d.isBefore(exclusiveEndDate); ) {
				dates.add(d);
				
				LocalDate nextMonth = d.plusMonths(1);
				d = resolvePreviousValid(nextMonth.getYear(), nextMonth.getMonthValue(), firstDay);
			}
			return dates;
		}

	}

	/**
	 * copied from LocalDate.resolvePreviousValid() to handle end of month scenario
	 * 
	 * @param year
	 * @param month the month-of-year to represent, validated from 1 to 12
	 * @param day
	 * @return
	 */
	private static LocalDate resolvePreviousValid(int year, int month, int day) {
		switch (month) {
		case 2:
			day = Math.min(day, IsoChronology.INSTANCE.isLeapYear(year) ? 29 : 28);
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day = Math.min(day, 30);
			break;
		}
		return LocalDate.of(year, month, day);
	}

}
