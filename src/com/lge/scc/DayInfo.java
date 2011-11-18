package com.lge.scc;

//import java.text.DateFormat;
import java.text.DateFormat;
import java.util.Calendar;
//import java.util.GregorianCalendar;

/**
 * �Ϸ��� ��¥������ �����ϴ� Ŭ����
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
	 * ��¥�� ��ȯ�Ѵ�.
	 * 
	 * @return day ��¥
	 */
	public String getDay()
	{
		return day;
	}

	/**
	 * ��¥�� �����Ѵ�.
	 * 
	 * @param day ��¥
	 */
	public void setDay(String day)
	{
		this.day = day;
	}

	/**
	 * �̹����� ��¥���� ������ ��ȯ�Ѵ�.
	 * 
	 * @return inMonth(true/false)
	 */
	public boolean isInMonth()
	{
		return inMonth;
	}

	/**
	 * �̹����� ��¥���� ������ �����Ѵ�.
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