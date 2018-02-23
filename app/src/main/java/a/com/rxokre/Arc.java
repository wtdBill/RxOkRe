package a.com.rxokre;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Path;

/**
 * Created by Administrator on 2018/2/22.
 */

public class Arc extends View{
    private Paint mPaint;
    private int mHeight;
    private int mWidth;
    public Arc(Context context){
        this(context,null);
    }
    public Arc(Context context, AttributeSet attri){
        super(context,attri);
        initPaint();
    }
    private void initPaint(){
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight=MeasureSpec.getSize(heightMeasureSpec);
        mWidth=MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth/2,mHeight/2);
        canvas.scale(1,-1);
        Path path=new Path();
        path.lineTo(100,100);
        RectF rectF=new RectF(0,0,300,300);
        path.addArc(rectF,0,280);
        canvas.drawPath(path,mPaint);
    }
}
