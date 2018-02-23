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
 * Created by Administrator on 2018/2/22.
 */

public class Cw extends View {
    private Paint paint=new Paint();
    private int mWidth;
    private int mHeight;
    public Cw(Context context){
        this(context,null);
    }
    public Cw(Context context, AttributeSet attr){
        super(context,attr);
        initPaint();
    }
    private void initPaint(){
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight=h;
        mWidth=w;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth/2,mHeight/2);
       /* @SuppressLint("DrawAllocation")
        Path path=new Path();
        path.addRect(-200,-200,200,200,Path.Direction.CW);
        canvas.drawPath(path,paint);*/
       @SuppressLint("DrawAllocation")
       Path path=new Path();
       @SuppressLint("DrawAllocation")
       Path src=new Path();
       path.addRect(-200,-200,200,200,Path.Direction.CW);
       src.addCircle(0,0,100,Path.Direction.CW);
       path.addPath(src,0,-200);
       canvas.drawPath(path,paint);
    }
}
