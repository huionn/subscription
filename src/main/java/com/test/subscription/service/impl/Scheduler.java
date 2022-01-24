package com.test.subscription.service.impl;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.Collections;
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
					//fall in this week
					firstDate = startDate.plusDays(firstDay - startDate.getDayOfWeek().getValue());
				} else {
					//fall in next week
					firstDate = startDate.plusDays(firstDay + 7 - startDate.getDayOfWeek().getValue());
				}
			} else {
				firstDate = startDate;
			}
			break;
		case MONTHLY:
			if (firstDay < startDate.getDayOfMonth()) {
				// adjust if the first day fall into next month
				LocalDate nextMonth = startDate.plusMonths(1);
				firstDate = resolvePreviousValid(nextMonth.getYear(), nextMonth.getMonthValue(), firstDay);
			} else {
				firstDate = startDate;
			}
			break;
		case DAILY:
		default:
			firstDate = startDate;
			break;
		}

		if (firstDate.isAfter(duration.getEndDate())) {
			// no match date (day of week/month does not fit into the duration range)
			return Collections.emptyList();
		}

		// add 1 day in end date to make original end date inclusive
		LocalDate exclusiveEndDate = duration.getEndDate().plusDays(1);
		if (type != SubscriptionType.MONTHLY) {
			// for DAILY and WEEKLY subscription
			return firstDate.datesUntil(exclusiveEndDate, type.getPeriod()).collect(Collectors.toList());
		} else {
			// for MONTHLY subscription
			return firstDate.datesUntil(exclusiveEndDate, type.getPeriod())
					.map(d -> resolvePreviousValid(d.getYear(), d.getMonthValue(), firstDay))
					.filter(exclusiveEndDate::isAfter).collect(Collectors.toList());
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
