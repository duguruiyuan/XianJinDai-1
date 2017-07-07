/*package com.daiqile.xianjindai.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.daiqile.xianjindai.R;

*//**
 * @author Mr.Bean
 * @date Created on 2016/12/22.
 *//*
public class CircleSeekBar extends View {

    private static final double RADIAN = 180 / Math.PI;

    private static final String INATANCE_STATE = "state";
    private static final String INSTANCE_MAX_PROCESS1 = "max_process1";
    private static final String INSTANCE_MAX_PROCESS2 = "max_process2";
    private static final String INSTANCE_CUR_PROCESS1 = "cur_process1";
    private static final String INSTANCE_CUR_PROCESS2 = "cur_process2";
    private static final String INSTANCE_REACHED_COLOR1 = "reached_color1";
    private static final String INSTANCE_REACHED_COLOR2 = "reached_color2";
    private static final String INSTANCE_REACHED_WIDTH = "reached_width";
    private static final String INSTANCE_REACHED_CORNER_ROUND = "reached_corner_round";
    private static final String INSTANCE_UNREACHED_COLOR = "unreached_color";
    private static final String INSTANCE_UNREACHED_WIDTH = "unreached_width";
    private static final String INSTANCE_POINTER_COLOR1 = "pointer_color1";
    private static final String INSTANCE_POINTER_COLOR2 = "pointer_color2";
    private static final String INSTANCE_POINTER_RADIUS = "pointer_radius";
    private static final String INSTANCE_POINTER_SHADOW = "pointer_shadow";
    private static final String INSTANCE_POINTER_SHADOW_RADIUS = "pointer_shadow_radius";
    private static final String INSTANCE_WHEEL_SHADOW = "wheel_shadow";
    private static final String INSTANCE_WHEEL_SHADOW_RADIUS = "wheel_shadow_radius";
    private static final String INSTANCE_WHEEL_HAS_CACHE = "wheel_has_cache";
    private static final String INSTANCE_WHEEL_CAN_TOUCH = "wheel_can_touch";

    private static final String INSTANCE_MCURANGLE1 = "m_curAngle1";

    private static final String INSTANCE_MCURANGLE2 = "m_curAngle2";

    private Paint mWheelPaint;

    private Paint mReachedPaint1;

    private Paint mReachedPaint2;

//    private Paint mReachedEdgePaint;

    private Paint mInPointerPaint1;
    private Paint mInPointerPaint2;

    private Paint mOutPointerPaint;

    private int mMaxProcess1;
    private int mCurProcess1;
    private int mMaxProcess2;
    private int mCurProcess2;
    private int mReachedColor1, mReachedColor2,mUnreachedColor;
    private float mReachedWidth, mUnreachedWidth;
    private boolean isHasReachedCornerRound;
    private int mInPointerColor1;
    private int mInPointerColor2;
    private int mOutPointerColor;
    private float mPointerRadius;

    private double mCurAngle1;

    private double mCurAngle2;

    private float mWheelCurX1, mWheelCurY1;

    private float mWheelCurX2, mWheelCurY2;

    private boolean isHasWheelShadow, isHasPointerShadow;
    private float mWheelShadowRadius, mPointerShadowRadius;

    private boolean isHasCache;
    private Canvas mCacheCanvas;
    private Bitmap mCacheBitmap;

    private boolean isCanTouch;

    private float mDefShadowOffset;

    private OnSeekBarChangeListener mChangListener;

    public CircleSeekBar(Context context) {
        this(context, null);
    }

    public CircleSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(attrs, defStyleAttr);
        initPadding();
        initPaints();
    }

    private void initPaints() {
        mDefShadowOffset = getDimen(R.dimen.def_shadow_offset);
        *//**
         * 圆环画笔
         *//*
        mWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWheelPaint.setColor(mUnreachedColor);
        mWheelPaint.setStyle(Paint.Style.STROKE);
        mWheelPaint.setStrokeWidth(mUnreachedWidth);
        if (isHasWheelShadow) {
            mWheelPaint.setShadowLayer(mWheelShadowRadius, mDefShadowOffset, mDefShadowOffset, Color.DKGRAY);
        }

        *//**
         * 选中区域画笔
         *//*
        mReachedPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mReachedPaint1.setColor(mReachedColor1);
        mReachedPaint1.setStyle(Paint.Style.STROKE);
        mReachedPaint1.setStrokeWidth(mReachedWidth);
        if (isHasReachedCornerRound) {
            mReachedPaint1.setStrokeCap(Paint.Cap.ROUND);
        }
        *//**
         * 选中区域画笔
         *//*
        mReachedPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mReachedPaint2.setColor(mReachedColor2);
        mReachedPaint2.setStyle(Paint.Style.STROKE);
        mReachedPaint2.setStrokeWidth(mReachedWidth);
        if (isHasReachedCornerRound) {
            mReachedPaint2.setStrokeCap(Paint.Cap.ROUND);
        }
        *//**
         * 锚点画笔
         *//*
        mInPointerPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInPointerPaint1.setColor(mInPointerColor1);
        mInPointerPaint1.setStyle(Paint.Style.FILL);

        mInPointerPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInPointerPaint2.setColor(mInPointerColor2);
        mInPointerPaint2.setStyle(Paint.Style.FILL);

        mOutPointerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutPointerPaint.setColor(mOutPointerColor);
        mOutPointerPaint.setStyle(Paint.Style.FILL);
        if (isHasPointerShadow) {
            mOutPointerPaint.setShadowLayer(mPointerShadowRadius, mDefShadowOffset, mDefShadowOffset, Color.DKGRAY);
        }


        *//**
         * 选中区域两头的圆角画笔
         *//*
//        mReachedEdgePaint = new Paint(mReachedPaint);
//        mReachedEdgePaint.setStyle(Paint.Style.FILL);
    }

    private void initAttrs(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CircleSeekBar, defStyle, 0);
        mMaxProcess1 = a.getInt(R.styleable.CircleSeekBar_wheel_max_process, 100);
        mCurProcess1 = a.getInt(R.styleable.CircleSeekBar_wheel_cur_process, 0);
        mMaxProcess2 = a.getInt(R.styleable.CircleSeekBar_wheel_max_process, 100);
        mCurProcess2 = a.getInt(R.styleable.CircleSeekBar_wheel_cur_process, 0);
        if (mCurProcess1 > mMaxProcess1) mCurProcess1 = mMaxProcess1;
        mReachedColor1 = a.getColor(R.styleable.CircleSeekBar_wheel_reached_color1, getColor(R.color.def_reached_color1));
        mReachedColor2 = a.getColor(R.styleable.CircleSeekBar_wheel_reached_color2, getColor(R.color.def_reached_color2));
        mUnreachedColor = a.getColor(R.styleable.CircleSeekBar_wheel_unreached_color,
                getColor(R.color.def_wheel_color));
        mUnreachedWidth = a.getDimension(R.styleable.CircleSeekBar_wheel_unreached_width,
                getDimen(R.dimen.def_wheel_width));
        isHasReachedCornerRound = a.getBoolean(R.styleable.CircleSeekBar_wheel_reached_has_corner_round, true);
        mReachedWidth = a.getDimension(R.styleable.CircleSeekBar_wheel_reached_width, mUnreachedWidth);
        mInPointerColor1 = a.getColor(R.styleable.CircleSeekBar_wheel_pointer_color, getColor(R.color.def_in_pointer_color1));
        mInPointerColor2 = a.getColor(R.styleable.CircleSeekBar_wheel_pointer_color, getColor(R.color.def_in_pointer_color2));
        mOutPointerColor = a.getColor(R.styleable.CircleSeekBar_wheel_pointer_color, getColor(R.color.def_out_pointer_color));
        mPointerRadius = a.getDimension(R.styleable.CircleSeekBar_wheel_pointer_radius, mReachedWidth / 2);
        isHasWheelShadow = a.getBoolean(R.styleable.CircleSeekBar_wheel_has_wheel_shadow, false);
        if (isHasWheelShadow) {
            mWheelShadowRadius = a.getDimension(R.styleable.CircleSeekBar_wheel_shadow_radius,
                    getDimen(R.dimen.def_shadow_radius));
        }
        isHasPointerShadow = a.getBoolean(R.styleable.CircleSeekBar_wheel_has_pointer_shadow, false);
        if (isHasPointerShadow) {
            mPointerShadowRadius = a.getDimension(R.styleable.CircleSeekBar_wheel_pointer_shadow_radius,
                    getDimen(R.dimen.def_shadow_radius));
        }
        isHasCache = a.getBoolean(R.styleable.CircleSeekBar_wheel_has_cache, isHasWheelShadow);
        isCanTouch = a.getBoolean(R.styleable.CircleSeekBar_wheel_can_touch, true);

        if (isHasPointerShadow | isHasWheelShadow) {
            setSoftwareLayer();
        }
        a.recycle();
    }

    private void initPadding() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int paddingStart = 0, paddingEnd = 0;
        if (Build.VERSION.SDK_INT >= 17) {
            paddingStart = getPaddingStart();
            paddingEnd = getPaddingEnd();
        }
        int maxPadding = Math.max(paddingLeft, Math.max(paddingTop,
                Math.max(paddingRight, Math.max(paddingBottom, Math.max(paddingStart, paddingEnd)))));
        setPadding(maxPadding, maxPadding, maxPadding, maxPadding);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private int getColor(int colorId) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return getContext().getColor(colorId);
        } else {
            return getResources().getColor(colorId);
        }
    }

    private float getDimen(int dimenId) {
        return getResources().getDimension(dimenId);
    }

    private void setSoftwareLayer() {
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        int height = getDefaultSize(getSuggestedMinimumHeight(), widthMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), heightMeasureSpec);

//        int widthMode =  MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int fixWidth,fixHeight;
//        if(widthMode== MeasureSpec.EXACTLY){
//            fixWidth = width;
//        }else{
//            fixWidth =(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,getResources().getDisplayMetrics());
//        }
//        if(heightMode==MeasureSpec.EXACTLY){
//            fixHeight = height;
//        }else{
//            fixHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,getResources().getDisplayMetrics());
//        }
        int min = Math.min(width, height);

        setMeasuredDimension(min, min);

        mCurAngle1 = (double) mCurProcess1 / mMaxProcess1 * (180 + 30);

        mCurAngle2 = (double) mCurProcess2 / mMaxProcess2 * (180 - 60);

        double fixCurAngle1, fixCurAngle2;

        if (mCurAngle1 <= 105) {
            fixCurAngle1 = mCurAngle1 + 75 + 180;
        } else {
            fixCurAngle1 = mCurAngle1 - 105;
        }

        double cos1 = -Math.cos(Math.toRadians(fixCurAngle1));
        float radius = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - mUnreachedWidth) / 2;

        mWheelCurX1 = calcXLocationInWheel(fixCurAngle1 > 180 ? 0 : min, (float) cos1, radius);
        mWheelCurY1 = calcYLocationInWheel((float) cos1, radius);

        if (mCurAngle2 < 60) {
            fixCurAngle2 = mCurAngle2 + (90 + 30);
        } else {
            fixCurAngle2 = mCurAngle2 + (180 - 60);
        }

        double cos2 = -Math.cos(Math.toRadians(fixCurAngle2));

        mWheelCurX2 = calcXLocationInWheel(fixCurAngle2 > 180 ? 0 : min, (float) cos2, radius);
        mWheelCurY2 = calcYLocationInWheel((float) cos2, radius);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        float left = getPaddingLeft() + mUnreachedWidth / 2;
        float top = getPaddingTop() + mUnreachedWidth / 2;
        float right = canvas.getWidth() - getPaddingRight() - mUnreachedWidth / 2;
        float bottom = canvas.getHeight() - getPaddingBottom() - mUnreachedWidth / 2;
//        float centerX = (left + right) / 2;
//        float centerY = (top + bottom) / 2;

//        float wheelRadius = (canvas.getWidth() - getPaddingLeft() - getPaddingRight()) / 2 - mUnreachedWidth / 2;

//        if (isHasCache) {
//            if (mCacheCanvas == null) {
//                buildCache(centerX, centerY, wheelRadius);
//            }
//            canvas.drawBitmap(mCacheBitmap, 0, 0, null);
//        } else {
//            canvas.drawCircle(centerX, centerY, wheelRadius, mWheelPaint);
//        }
        canvas.drawArc(new RectF(left, top, right, bottom), -195, (float) (180+30), false, mWheelPaint);

        canvas.drawArc(new RectF(left, top, right, bottom), 30, (float) (180 - 60), false, mWheelPaint);

        //画选中区域
        canvas.drawArc(new RectF(left, top, right, bottom), -195, (float) mCurAngle1, false, mReachedPaint1);

        canvas.drawArc(new RectF(left, top, right, bottom), 30, (float) mCurAngle2, false, mReachedPaint2);

        //画锚点

        canvas.drawCircle(mWheelCurX1, mWheelCurY1, mPointerRadius + 5, mOutPointerPaint);
        canvas.drawCircle(mWheelCurX2, mWheelCurY2, mPointerRadius + 5, mOutPointerPaint);

        canvas.drawCircle(mWheelCurX1, mWheelCurY1, mPointerRadius, mInPointerPaint1);
        canvas.drawCircle(mWheelCurX2, mWheelCurY2, mPointerRadius, mInPointerPaint2);


    }

    private void buildCache(float centerX, float centerY, float wheelRadius) {
        mCacheBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCacheCanvas = new Canvas(mCacheBitmap);

        //画环
        mCacheCanvas.drawCircle(centerX, centerY, wheelRadius, mWheelPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (isCanTouch && (event.getAction() == MotionEvent.ACTION_MOVE || isTouch(x, y))) {
            // 通过当前触摸点搞到cos角度值
            float cos = computeCos(x, y);

            double angle = Math.acos(cos) * RADIAN;

            //angle2
            if (angle <= 60) {

                // 通过反三角函数获得角度值

                if (x < getWidth() / 2) {
                    mCurAngle2 = Math.acos(cos) * RADIAN + (90 - 30);
                } else {
                    mCurAngle2 = 90 - Math.acos(cos) * RADIAN - 30;
                }

//            if (x < getWidth() / 2) { // 滑动超过180度
//                mCurAngle = Math.PI * RADIAN +(90+15)+ Math.acos(cos) * RADIAN;
//            } else { // 没有超过180度
//                mCurAngle = Math.PI * RADIAN+(90+15) - Math.acos(cos) * RADIAN;
//            }
                mCurProcess2 = getSelectedValue2();
                float radius = (getWidth() - getPaddingLeft() - getPaddingRight() - mUnreachedWidth) / 2;

                mWheelCurX2 = calcXLocationInWheel(x, cos, radius);
                mWheelCurY2 = calcYLocationInWheel(cos, radius);

            }

            if (angle >= 75) {

                // 通过反三角函数获得角度值

                if (x < getWidth() / 2) {
                    mCurAngle1 = Math.acos(cos) * RADIAN - 75;
                } else {
                    mCurAngle1 = Math.PI * RADIAN - Math.acos(cos) * RADIAN + (180 - 75);
                }

//            if (x < getWidth() / 2) { // 滑动超过180度
//                mCurAngle = Math.PI * RADIAN +(90+15)+ Math.acos(cos) * RADIAN;
//            } else { // 没有超过180度
//                mCurAngle = Math.PI * RADIAN+(90+15) - Math.acos(cos) * RADIAN;
//            }
                mCurProcess1 = getSelectedValue1();
                mCurProcess2 = getSelectedValue2();

                float radius = (getWidth() - getPaddingLeft() - getPaddingRight() - mUnreachedWidth) / 2;
                mWheelCurX1 = calcXLocationInWheel(x, cos, radius);
                mWheelCurY1 = calcYLocationInWheel(cos, radius);


            }
            if (mChangListener != null) {
                mChangListener.onChanged(this, mCurProcess1, mCurProcess2);
            }
            invalidate();
            return true;
        } else {
            return super.onTouchEvent(event);
        }
    }

    private boolean isTouch(float x, float y) {
        double radius = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight() + getCircleWidth()) / 2;
        double centerX = getMeasuredWidth() / 2;
        double centerY = getMeasuredHeight() / 2;
        return Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2) < radius * radius;
    }

    private float getCircleWidth() {
        return Math.max(mUnreachedWidth, Math.max(mReachedWidth, mPointerRadius));
    }

    private float calcXLocationInWheel(float x, float cos, float radius) {
        if (x > (getMeasuredWidth() / 2)) {
            return (float) (getMeasuredWidth() / 2 + Math.sqrt(1 - cos * cos) * radius);
        } else {
            return (float) (getMeasuredWidth() / 2 - Math.sqrt(1 - cos * cos) * radius);
        }
    }

    private float calcYLocationInWheel(float cos, float radius) {
        return getMeasuredWidth() / 2 + radius * cos;
    }

    *//**
     * 拿到倾斜的cos值
     *//*
    private float computeCos(float x, float y) {
        float width = x - getWidth() / 2;
        float height = y - getHeight() / 2;
        float slope = (float) Math.sqrt(width * width + height * height);
        return height / slope;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INATANCE_STATE, super.onSaveInstanceState());
        bundle.putInt(INSTANCE_MAX_PROCESS1, mMaxProcess1);
        bundle.putInt(INSTANCE_MAX_PROCESS2, mMaxProcess2);
        bundle.putInt(INSTANCE_CUR_PROCESS1, mCurProcess1);
        bundle.putInt(INSTANCE_CUR_PROCESS2, mCurProcess2);
        bundle.putInt(INSTANCE_REACHED_COLOR1, mReachedColor1);
        bundle.putInt(INSTANCE_REACHED_COLOR2, mReachedColor2);
        bundle.putFloat(INSTANCE_REACHED_WIDTH, mReachedWidth);
        bundle.putBoolean(INSTANCE_REACHED_CORNER_ROUND, isHasReachedCornerRound);
        bundle.putInt(INSTANCE_UNREACHED_COLOR, mUnreachedColor);
        bundle.putFloat(INSTANCE_UNREACHED_WIDTH, mUnreachedWidth);
        bundle.putInt(INSTANCE_POINTER_COLOR1, mInPointerColor1);
        bundle.putInt(INSTANCE_POINTER_COLOR2, mInPointerColor2);
        bundle.putFloat(INSTANCE_POINTER_RADIUS, mPointerRadius);
        bundle.putBoolean(INSTANCE_POINTER_SHADOW, isHasPointerShadow);
        bundle.putFloat(INSTANCE_POINTER_SHADOW_RADIUS, mPointerShadowRadius);
        bundle.putBoolean(INSTANCE_WHEEL_SHADOW, isHasWheelShadow);
        bundle.putFloat(INSTANCE_WHEEL_SHADOW_RADIUS, mPointerShadowRadius);
        bundle.putBoolean(INSTANCE_WHEEL_HAS_CACHE, isHasCache);
        bundle.putBoolean(INSTANCE_WHEEL_CAN_TOUCH, isCanTouch);

        bundle.putDouble(INSTANCE_MCURANGLE1, mCurAngle1);
        bundle.putDouble(INSTANCE_MCURANGLE1,mCurAngle2);

        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(bundle.getParcelable(INATANCE_STATE));
            mMaxProcess1 = bundle.getInt(INSTANCE_MAX_PROCESS1);
            mMaxProcess2 = bundle.getInt(INSTANCE_MAX_PROCESS2);
            mCurProcess1 = bundle.getInt(INSTANCE_CUR_PROCESS1);
            mCurProcess2 = bundle.getInt(INSTANCE_CUR_PROCESS2);
            mReachedColor1 = bundle.getInt(INSTANCE_REACHED_COLOR1);
            mReachedColor2 = bundle.getInt(INSTANCE_REACHED_COLOR2);
            mReachedWidth = bundle.getFloat(INSTANCE_REACHED_WIDTH);
            isHasReachedCornerRound = bundle.getBoolean(INSTANCE_REACHED_CORNER_ROUND);
            mUnreachedColor = bundle.getInt(INSTANCE_UNREACHED_COLOR);
            mUnreachedWidth = bundle.getFloat(INSTANCE_UNREACHED_WIDTH);
            mInPointerColor1 = bundle.getInt(INSTANCE_POINTER_COLOR1);
            mInPointerColor2 = bundle.getInt(INSTANCE_POINTER_COLOR2);
            mPointerRadius = bundle.getFloat(INSTANCE_POINTER_RADIUS);
            isHasPointerShadow = bundle.getBoolean(INSTANCE_POINTER_SHADOW);
            mPointerShadowRadius = bundle.getFloat(INSTANCE_POINTER_SHADOW_RADIUS);
            isHasWheelShadow = bundle.getBoolean(INSTANCE_WHEEL_SHADOW);
            mPointerShadowRadius = bundle.getFloat(INSTANCE_WHEEL_SHADOW_RADIUS);
            isHasCache = bundle.getBoolean(INSTANCE_WHEEL_HAS_CACHE);
            isCanTouch = bundle.getBoolean(INSTANCE_WHEEL_CAN_TOUCH);
            mCurAngle1 = bundle.getDouble(INSTANCE_MCURANGLE1);
            mCurAngle2 = bundle.getDouble(INSTANCE_MCURANGLE2);
            initPaints();
        } else {
            super.onRestoreInstanceState(state);
        }

        if (mChangListener != null) {
            mChangListener.onChanged(this, mCurProcess1, mCurProcess2);
        }
    }

    private int getSelectedValue1() {
        return Math.round(mMaxProcess1 * ((float) mCurAngle1 / (180 + 30)));
    }

    private int getSelectedValue2() {
        return Math.round(mMaxProcess2 * ((float) mCurAngle2 / (180 - 60)));
    }

    public int getCurProcess1() {
        return mCurProcess1;
    }

    public int getCurProcess2() {
        return mCurProcess2;
    }

    public void setCurProcess(int curProcess1,int curProcess2) {
        this.mCurProcess1 = curProcess1 > mMaxProcess1 ? mMaxProcess1 : curProcess1;
        this.mCurProcess2 = curProcess2 > mMaxProcess2 ? mMaxProcess2 : curProcess2;
        if (mChangListener != null) {
            mChangListener.onChanged(this, mCurProcess1, mCurProcess2);
        }
        invalidate();
    }

    public void setReachedColor1(int reachedColor) {
        this.mReachedColor1 = reachedColor;
        mReachedPaint1.setColor(reachedColor);
//        mReachedEdgePaint.setColor(reachedColor);
        invalidate();
    }

    public void setReachedColor2(int reachedColor) {
        this.mReachedColor2 = reachedColor;
        mReachedPaint2.setColor(reachedColor);
//        mReachedEdgePaint.setColor(reachedColor);
        invalidate();
    }

    public int getUnreachedColor() {
        return mUnreachedColor;
    }

    public void setUnreachedColor(int unreachedColor) {
        this.mUnreachedColor = unreachedColor;
        mWheelPaint.setColor(unreachedColor);
        invalidate();
    }

    public float getReachedWidth() {
        return mReachedWidth;
    }

    public void setReachedWidth1(float reachedWidth) {
        this.mReachedWidth = reachedWidth;
        mReachedPaint1.setStrokeWidth(reachedWidth);
//        mReachedEdgePaint.setStrokeWidth(reachedWidth);
        invalidate();
    }
    public void setReachedWidth2(float reachedWidth) {
        this.mReachedWidth = reachedWidth;
        mReachedPaint1.setStrokeWidth(reachedWidth);
//        mReachedEdgePaint.setStrokeWidth(reachedWidth);
        invalidate();
    }

    public boolean isHasReachedCornerRound() {
        return isHasReachedCornerRound;
    }

    public void setHasReachedCornerRound1(boolean hasReachedCornerRound) {
        isHasReachedCornerRound = hasReachedCornerRound;
        mReachedPaint1.setStrokeCap(hasReachedCornerRound ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        invalidate();
    }

    public void setHasReachedCornerRound2(boolean hasReachedCornerRound) {
        isHasReachedCornerRound = hasReachedCornerRound;
        mReachedPaint2.setStrokeCap(hasReachedCornerRound ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        invalidate();
    }

    public float getUnreachedWidth() {
        return mUnreachedWidth;
    }

    public void setUnreachedWidth(float unreachedWidth) {
        this.mUnreachedWidth = unreachedWidth;
        mWheelPaint.setStrokeWidth(unreachedWidth);
        invalidate();
    }

//    public int getPointerColor() {
//        return mInPointerColor;
//    }

//    public void setPointerColor(int pointerColor) {
//        this.mInPointerColor = pointerColor;
//        mInPointerPaint.setColor(pointerColor);
//    }
//
//    public float getPointerRadius() {
//        return mPointerRadius;
//    }
//
//    public void setPointerRadius(float pointerRadius) {
//        this.mPointerRadius = pointerRadius;
//        mInPointerPaint.setStrokeWidth(pointerRadius);
//        invalidate();
//    }

    public boolean isHasWheelShadow() {
        return isHasWheelShadow;
    }

    public void setWheelShadow(float wheelShadow) {
        this.mWheelShadowRadius = wheelShadow;
        if (wheelShadow == 0) {
            isHasWheelShadow = false;
            mWheelPaint.clearShadowLayer();
            mCacheCanvas = null;
            mCacheBitmap.recycle();
            mCacheBitmap = null;
        } else {
            mWheelPaint.setShadowLayer(mWheelShadowRadius, mDefShadowOffset, mDefShadowOffset, Color.DKGRAY);
            setSoftwareLayer();
        }
        invalidate();
    }

    public float getWheelShadowRadius() {
        return mWheelShadowRadius;
    }

    public boolean isHasPointerShadow() {
        return isHasPointerShadow;
    }

    public float getPointerShadowRadius() {
        return mPointerShadowRadius;
    }

//    public void setPointerShadowRadius(float pointerShadowRadius) {
//        this.mPointerShadowRadius = pointerShadowRadius;
//        if (mPointerShadowRadius == 0) {
//            isHasPointerShadow = false;
//            mInPointerPaint.clearShadowLayer();
//        } else {
//            mInPointerPaint.setShadowLayer(pointerShadowRadius, mDefShadowOffset, mDefShadowOffset, Color.DKGRAY);
//            setSoftwareLayer();
//        }
//        invalidate();
//    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener listener) {
        mChangListener = listener;
    }

    public interface OnSeekBarChangeListener {
        void onChanged(CircleSeekBar seekbar, int curValue1, int curValue2);
    }
}*/
