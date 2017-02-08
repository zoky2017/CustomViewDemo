package com.feoul.customviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by 5555 on 2017/2/7 0007.
 */

public class PieView extends View {

    DecimalFormat decimalFormat=new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足3位,会以0补足.

    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
    private ArrayList<CircleData> mData;
    // 宽高
    private int mWidth, mHeight;
    // 画笔
    private Paint mPaint = new Paint();
    private float mCenterX;
    private float mCenterY;


    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }



    private void initPaint(){
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        //点
//        canvas.drawPoint(200,200,mPaint);
//        canvas.drawPoints(new float[]{500,500,500,600,500,700},mPaint);
//        //线
//        canvas.drawLine(300,300,600,600,mPaint);
//        canvas.drawLines(new float[]{100,200,200,200,100,300,200,400},mPaint);
//        //矩形
//        canvas.drawRect(100,100,150,150,mPaint);
//        //圆角矩形
//        RectF rect = new RectF(100,500,200,600);
//        canvas.drawRoundRect(rect,30,30,mPaint);
//        //椭圆
//        RectF rectOval = new RectF(100,650,400,750);
//        canvas.drawOval(rectOval,mPaint);
//        //绘制圆形
//        canvas.drawCircle(100,900,50,mPaint);

//        canvas.translate(mWidth / 2, mHeight / 2);
//
//        RectF rect = new RectF(0,-400,400,0);   // 矩形区域
//
//        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
//        canvas.drawRect(rect,mPaint);
//
//        canvas.scale(0.5f,-0.5f,100,0);          // 画布缩放  <-- 缩放中心向右偏移了200个单位
//
//        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
//        canvas.drawRect(rect,mPaint);

        if (null == mData)
            return;
        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth/2, mHeight/2);
        float r = (float) (Math.min(mHeight,mWidth)/2*0.8);
        RectF rect = new RectF(-r,-r,r,r);

        for (int i = 0; i< mData.size(); i++){
            CircleData data = mData.get(i);
            mPaint.setColor(data.getColor());
            canvas.drawArc(rect,currentStartAngle,data.getAngle(),true,mPaint);
            currentStartAngle += data.getAngle();
            // 平移画布
            mCenterX = (float) ((float)0.5 * r * Math.cos(Math.toRadians(currentStartAngle - 0.5 * data.getAngle())));
            mCenterY = (float) ((float)0.5 * r * Math.sin(Math.toRadians(currentStartAngle - 0.5 * data.getAngle())));
            canvas.translate(mCenterX,mCenterY); // 文字中心坐标
            // 设置字体颜色
            mPaint.setColor(Color.BLACK);
            mPaint.setTextSize(30f);
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(decimalFormat.format(data.getPercentage()*100)+" %",0,0,mPaint);
            canvas.translate(-mCenterX,-mCenterY);//绘制文字完成回到原点
        }

    }

    // 设置起始角度
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();   // 刷新
    }

    // 设置数据
    public void setData(ArrayList<CircleData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();   // 刷新
    }

    // 初始化数据
    private void initData(ArrayList<CircleData> mData) {
        if (null == mData || mData.size() == 0)   // 数据有问题 直接返回
            return;

        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            CircleData data = mData.get(i);

            sumValue += data.getValue();       //计算数值和

            int j = i % mColors.length;       //设置颜色
            data.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            CircleData data = mData.get(i);
            float percentage =data.getValue() / sumValue;
            float angle = percentage * 360;                 // 对应的角度

            data.setPercentage(percentage);                  // 记录百分比
            data.setAngle(angle);                            // 记录角度大小
            sumAngle += angle;

            Log.i("angle", "" + data.getAngle());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
