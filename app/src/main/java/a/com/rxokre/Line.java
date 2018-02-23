package a.com.rxokre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/2/12.
 */

public class Line extends View {
    private Paint mPaint=new Paint();
    private int mWidth;
    private int mHeight;
    public Line(Context context){
        this(context,null);
    }
    public Line(Context context, AttributeSet attrs){
        super(context,attrs);
        initPaint();
    }

    private void initPaint(){
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w,h,oldw,oldh);
        mHeight=h;
        mWidth=w;
    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth/2,mHeight/2);
        Path path=new Path();
        path.lineTo(200,200);
        path.moveTo(200,100);
        path.lineTo(200,0);
        path.close();
        canvas.drawPath(path,mPaint);


    }
}
