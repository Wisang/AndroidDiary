package com.lge.scc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * BaseAdapter를 상속받아 구현한 CalendarAdapter
 * 
 * @author croute
 * @since 2011.03.08
 */
public class CalendarAdapter extends BaseAdapter
{
	private ArrayList<DayInfo> mDayList;
	private Context mContext;
	private int mResource;
	private LayoutInflater mLiInflater;
	private LinearLayout cell;
	private int gridviewHeight;
	
	LinearLayout layout;

	/**
	 * Adpater 생성자
	 * 
	 * @param context
	 *            컨텍스트
	 * @param textResource
	 *            레이아웃 리소스
	 * @param dayList
	 *            날짜정보가 들어있는 리스트
	 */
	public CalendarAdapter(Context context, int textResource, ArrayList<DayInfo> dayList)
	{
		this.mContext = context;
		this.mDayList = dayList;
		this.mResource = textResource;
		this.mLiInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mDayList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return mDayList.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	
	public LinearLayout getCell()
	{
		return cell;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		DayInfo day = mDayList.get(position);

		DayViewHolde dayViewHolder;
		
		Map<Calendar, DailyRecord> mapData = ((FitnessApplication)mContext.getApplicationContext()).getRecords();


		if(convertView == null || convertView.getHeight() == 0)
		{
			int width;
			int height;

			gridviewHeight = parent.getHeight();
			convertView = mLiInflater.inflate(mResource, null);

			if(position % 7 == 6)
				width = getCellWidthDP()+getRestCellWidthDP();
			else
				width = getCellWidthDP();
			
			if(position / 7 == 6)
				height = getCellHeightDP()+getRestCellHeightDP();
			else
				height = getCellHeightDP();

			convertView.setLayoutParams(new GridView.LayoutParams(width, height));	
			
			dayViewHolder = new DayViewHolde();

			dayViewHolder.llBackground = (LinearLayout)convertView.findViewById(R.id.day_cell_ll_background);
			dayViewHolder.tvDay = (TextView) convertView.findViewById(R.id.day_cell_tv_day);
			
			convertView.setTag(dayViewHolder);
		}
		else
		{
			dayViewHolder = (DayViewHolde) convertView.getTag();
		}

		if(day != null)
		{
			dayViewHolder.tvDay.setText(day.getDay());

			if(day.isInMonth())
			{		
				if(position % 7 == 0)
				{
					dayViewHolder.tvDay.setTextColor(Color.RED);
					
				}
				else if(position % 7 == 6)
				{
					dayViewHolder.tvDay.setTextColor(Color.BLUE);
				}
				else
				{
					dayViewHolder.tvDay.setTextColor(Color.BLACK);
				}
				
				if(mapData.containsKey(day.getDate()))
				{
					Log.d("enter cell", "contains the key");
					dayViewHolder.tvDay.setBackgroundColor(Color.GREEN);
				}
				
			}
			else
			{
				dayViewHolder.tvDay.setTextColor(Color.GRAY);
			}
		}
		
		return convertView;
	}

	public class DayViewHolde
	{
		public LinearLayout llBackground;
		public TextView tvDay;
		
	}

	private int getCellWidthDP()
	{
		int width = mContext.getResources().getDisplayMetrics().widthPixels;
		int cellWidth = width/7;
		
		return cellWidth;
	}
	
	private int getRestCellWidthDP()
	{
		int width = mContext.getResources().getDisplayMetrics().widthPixels;
		int cellWidth = width%7;
		
		return cellWidth;
	}
	
	private int getCellHeightDP()
	{
		int cellHeight = gridviewHeight / 6;

		return cellHeight;
	}
	
	private int getRestCellHeightDP()
	{
		int cellHeight = gridviewHeight % 6;

		return cellHeight;
	}
}
