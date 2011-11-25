package com.lge.scc;

import java.text.DateFormat;
import java.util.Calendar;

public class DayInfo 
{
	private Calendar date;
	
	private String day;
	private boolean inMonth;

	private DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
	
	public String getDay()
	{
		return day;
	}

	public void setDay(String day)
	{
		this.day = day;
	}

	public boolean isInMonth()
	{
		return inMonth;
	}

	public void setInMonth(boolean inMonth)
	{
		this.inMonth = inMonth;
	}

	@Override
	public String toString() {
		return df.format(date.getTime());
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Calendar getDate() {
		return date;
	}
}