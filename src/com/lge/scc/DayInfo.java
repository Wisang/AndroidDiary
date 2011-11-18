package com.lge.scc;

//import java.text.DateFormat;
import java.text.DateFormat;
import java.util.Calendar;
//import java.util.GregorianCalendar;

/**
 * 하루의 날짜정보를 저장하는 클래스
 * 
 * @author croute
 * @since 2011.03.08
 */
public class DayInfo 
{
	private Calendar date;
	
	private String day;
	private boolean inMonth;

	private DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
	
	/**
	 * 날짜를 반환한다.
	 * 
	 * @return day 날짜
	 */
	public String getDay()
	{
		return day;
	}

	/**
	 * 날짜를 저장한다.
	 * 
	 * @param day 날짜
	 */
	public void setDay(String day)
	{
		this.day = day;
	}

	/**
	 * 이번달의 날짜인지 정보를 반환한다.
	 * 
	 * @return inMonth(true/false)
	 */
	public boolean isInMonth()
	{
		return inMonth;
	}

	/**
	 * 이번달의 날짜인지 정보를 저장한다.
	 * 
	 * @param inMonth(true/false)
	 */
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