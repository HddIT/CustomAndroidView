package com.example.pzdf.jinjie.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.pzdf.jinjie.entity.MeetingEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能描述:
 * Created by hdd on 2018/11/14.
 */

public class DateBarView extends View {

    private Context mc;
    private SimpleDateFormat sdf;
    private Paint Rect_paint;
    private Paint cutLine_paint;
    private Paint timeText_paint;
    private float timeText_width;
    private float node_width;
    private Date start_DateTime;
    private Date end_DateTime;
    private ArrayList<String> time_whole_Lists = new ArrayList<>();
    private ArrayList<String> time_half_Lists = new ArrayList<>();
    private ArrayList<MeetingEntity> meetingEntities = new ArrayList<>();


    public void setMeetingDates(ArrayList<MeetingEntity> meetingEntities){
        this.meetingEntities = meetingEntities;
    }

    public DateBarView(Context context) {
        super(context);
        init(context);
    }

    public DateBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DateBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mc = context;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Rect_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Rect_paint.setAntiAlias(true);
        Rect_paint.setStyle(Paint.Style.FILL_AND_STROKE);
        timeText_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        timeText_paint.setAntiAlias(true);
        timeText_paint.setStyle(Paint.Style.FILL_AND_STROKE);
        timeText_paint.setTextSize(30);
        timeText_paint.setColor(Color.BLUE);
        cutLine_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        cutLine_paint.setAntiAlias(true);
        cutLine_paint.setStrokeWidth(1);
        cutLine_paint.setStyle(Paint.Style.FILL_AND_STROKE);
        cutLine_paint.setColor(Color.WHITE);
    }

    public void setTimeBucket(Date start,Date end){
        this.start_DateTime = start;
        this.end_DateTime = end;
        computeTimeSize();
        computeTimeHalfSize();
    }

    private void computeTimeSize() {
        Calendar calendar_start = Calendar.getInstance();
        calendar_start.setTime(start_DateTime);
        Calendar calendar_end = Calendar.getInstance();
        calendar_end.setTime(end_DateTime);
        time_whole_Lists.add(sdf.format(calendar_start.getTime()));
        while (end_DateTime.after(calendar_start.getTime())){
            calendar_start.add(Calendar.HOUR_OF_DAY,1);
            if (end_DateTime.after(calendar_start.getTime())){
                time_whole_Lists.add(sdf.format(calendar_start.getTime()));
            }
        }
    }

    private void computeTimeHalfSize() {
        Calendar calendar_start = Calendar.getInstance();
        calendar_start.setTime(start_DateTime);
        Calendar calendar_end = Calendar.getInstance();
        calendar_end.setTime(end_DateTime);
        time_half_Lists.add(sdf.format(calendar_start.getTime()));
        while (end_DateTime.after(calendar_start.getTime())){
            calendar_start.add(Calendar.MINUTE,30);
            if (end_DateTime.after(calendar_start.getTime())){
                time_half_Lists.add(sdf.format(calendar_start.getTime()));
            }
        }
    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (time_half_Lists.size() == 0 || time_whole_Lists.size() == 0){
            return;
        }
        node_width = getWidth() / time_half_Lists.size();
        timeText_width = getWidth() / time_whole_Lists.size();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#EEE8CD"));
        onDrawTimeBar(canvas);
        onDrawTimeText(canvas);
    }

    private void onDrawTimeBar(Canvas canvas) {
        for (int i = 0;i<time_half_Lists.size();i++)
        {
            Date date = null;
            Date currentTime = null;
            try {
                currentTime = new Date(System.currentTimeMillis());
                date = sdf.parse(time_half_Lists.get(i));
                if (date.getTime()<currentTime.getTime()){
                    Rect_paint.setColor(Color.GRAY);
                } else {
                    if (isApproved(date)){
                        Rect_paint.setColor(Color.YELLOW);
                    } else if (isOccupy(date)){
                        Rect_paint.setColor(Color.RED);
                    } else {
                        Rect_paint.setColor(Color.GREEN);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            RectF rect = new RectF((0 + node_width) * i,0,(node_width * i) + node_width,dp2px(mc,10));
            canvas.drawRect(rect,Rect_paint);
            Path path = new Path();
            path.moveTo((0 + node_width) * i, dp2px(mc,10));
            path.lineTo((0 + node_width) * i,0);
            canvas.drawPath(path,cutLine_paint);
        }
    }

    private void onDrawTimeText(Canvas canvas) {
        float textWidth = timeText_paint.measureText("11:11");
        float w = 0.0f;
        if (timeText_width > textWidth){
            w = (timeText_width - textWidth) / 2;
        }
        for (int i = 0;i<time_whole_Lists.size();i++){
            String timeText = time_whole_Lists.get(i).substring(10,16);
            canvas.drawText(timeText,(0 + node_width * 2) * i + w,dp2px(mc,20),timeText_paint);
        }
    }

    private boolean isApproved(Date date) throws ParseException {
        Date nodeStartTime = date;
        Date nodeEndTime = null;
        Date mStart = null;
        Date mEnd = null;
        nodeEndTime = plusTime(nodeStartTime,30,Calendar.MINUTE,true);
        for (MeetingEntity m : meetingEntities){
            mStart = sdf.parse(m.getStart_time());
            mStart = plusTime(mStart,5,Calendar.MINUTE,true);
            mEnd = sdf.parse(m.getEnd_time());
            mEnd = plusTime(mEnd,5,Calendar.MINUTE,false);
            if ((nodeStartTime.before(mStart)
                    && nodeEndTime.after(mStart))
                    || (nodeStartTime.before(mEnd)
                    && nodeEndTime.after(mEnd))
                    ||(mStart.before(nodeStartTime)
                    && mEnd.after(nodeEndTime))){
                if (!m.isApproved()){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOccupy(Date date) throws ParseException {
        Date nodeStartTime = date;
        Date nodeEndTime = null;
        Date mStart = null;
        Date mEnd = null;
        nodeEndTime = plusTime(nodeStartTime,30,Calendar.MINUTE,true);
        for (MeetingEntity m : meetingEntities){
            mStart = sdf.parse(m.getStart_time());
            mStart = plusTime(mStart,5,Calendar.MINUTE,true);
            mEnd = sdf.parse(m.getEnd_time());
            mEnd = plusTime(mEnd,5,Calendar.MINUTE,false);
            if ((nodeStartTime.before(mStart)
                    && nodeEndTime.after(mStart))
                    || (nodeStartTime.before(mEnd)
                    && nodeEndTime.after(mEnd))
                    ||(mStart.before(nodeStartTime)
                    && mEnd.after(nodeEndTime))){
                return true;
            }
        }
        return false;
    }

    public Date plusTime(Date date,int time,int timeType,boolean isPlus){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (isPlus){
            calendar.add(timeType,time);
        } else {
            calendar.add(timeType,-time);
        }
        return calendar.getTime();
    }

    public static int dp2px(Context ctx, float dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, ctx.getResources().getDisplayMetrics());
    }


}
