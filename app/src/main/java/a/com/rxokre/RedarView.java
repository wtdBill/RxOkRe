package a.com.rxokre;

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

public class RedarView extends View {
    private int count=6;
    private float radius;
    private float angle = (float) (Math.PI*2/count);
    private int centerX;
    private int centerY;
    private String[]titles={"一","二","三","四","五","六"};
    private double[] data={60,50,60,80,20,70};
    private float maxValue=100;
    private Paint mainPaint;
    private Paint valuePaint;
    private Paint textPaint;

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }
    public void setMainPaintColor(int color){
        mainPaint.setColor(color);
    }
    public void setValuePaintColor(int color){
        valuePaint.setColor(color);
    }
    public void setTextPaintColor(int color){
        textPaint.setColor(color);
    }
    public RedarView(Context context){
        this(context,null);
    }
    public RedarView(Context context, AttributeSet attr){
        super(context,attr);
    }
    private void initPaint(){
        mainPaint=new Paint();
        mainPaint.setColor(Color.BLUE);
        mainPaint.setStyle(Paint.Style.STROKE);
        textPaint=new Paint();
        valuePaint=new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius=Math.min(h,w)/2*0.9f;
        centerX=w/2;
        centerY=h/2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initPaint();
        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    private void drawPolygon(Canvas canvas){

        Path path=new Path();
        float r=radius/(count-1);
        for (int i=1;i<count;i++){
            float curR=r*i;
            path.reset();
            for (int j=0;j<count;j++){
                if (j==0){
                    path.moveTo(centerX+curR,centerY);
                }
                else {
                    float X=(float)(centerX+curR*Math.cos(angle*j));
                    float Y=(float)(centerY+curR*Math.sin(angle*j));
                    path.lineTo(X,Y);
                }
            }
            path.close();
            canvas.drawPath(path,mainPaint);
        }
    }

    private void drawLines(Canvas canvas){
        Path path=new Path();
        for (int i=0;i<count;i++){
            path.reset();
            path.moveTo(centerX,centerY);
            float x=(float)(centerX+radius*Math.cos(angle*i));
            float y=(float)(centerY+radius*Math.sin(angle*i));
            path.lineTo(x,y);
            canvas.drawPath(path,mainPaint);
        }
    }
    private void drawText(Canvas canvas){
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for(int i=0;i<count;i++){
            float x = (float) (centerX+(radius+fontHeight/2)*Math.cos(angle*i));
            float y = (float) (centerY+(radius+fontHeight/2)*Math.sin(angle*i));
            if(angle*i>=0&&angle*i<=Math.PI/2){//第4象限
                canvas.drawText(titles[i], x,y,textPaint);
            }else if(angle*i>=3*Math.PI/2&&angle*i<=Math.PI*2){//第3象限
                canvas.drawText(titles[i], x,y,textPaint);
            }else if(angle*i>Math.PI/2&&angle*i<=Math.PI){//第2象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x-dis,y,textPaint);
            }else if(angle*i>=Math.PI&&angle*i<3*Math.PI/2){//第1象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x-dis,y,textPaint);
            }
        }
    }
    private void drawRegion(Canvas canvas){
        Path path = new Path();
        valuePaint.setAlpha(255);
        for(int i=0;i<count;i++){
            double percent = data[i]/maxValue;
            float x = (float) (centerX+radius*Math.cos(angle*i)*percent);
            float y = (float) (centerY+radius*Math.sin(angle*i)*percent);
            if(i==0){
                path.moveTo(x, centerY);
            }else{
                path.lineTo(x,y);
            }
            //绘制小圆点
            canvas.drawCircle(x,y,10,valuePaint);
        }
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(127);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }
}
