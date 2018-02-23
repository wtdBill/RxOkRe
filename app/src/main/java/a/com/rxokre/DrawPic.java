package a.com.rxokre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/2/12.
 */

public class DrawPic extends View{
    Picture mPicture=new Picture();
    public DrawPic(Context context){
        this(context,null);
    }
    public DrawPic(Context context,AttributeSet attr){
        super(context,attr);
        recoding();
    }
    private void recoding(){
        Paint mPaint =new Paint();
        Canvas canvas=mPicture.beginRecording(500,500);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.translate(250,250);
        canvas.drawCircle(0,0,100,mPaint);

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        /*mPicture.draw(canvas);*/
        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),200));
        PictureDrawable pictureDrawable=new PictureDrawable(mPicture);
        pictureDrawable.setBounds(0,0,mPicture.getHeight(),250);
    }
}
