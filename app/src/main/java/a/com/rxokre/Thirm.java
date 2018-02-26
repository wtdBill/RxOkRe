package a.com.rxokre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/2/26.
 */

public class Thirm extends android.support.v7.widget.AppCompatTextView {
    private int minute=30;
    private int second=0;
    private Timer timer;
    private TimerTask timerTask;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            if (minute==0){
                if (second==0){
                    Thirm.this.setText("结束");
                    if (timer!=null){
                        timer.cancel();
                        timer=null;
                    }
                    if (timerTask!=null){
                        timerTask=null;
                    }
                    else {
                        second--;
                        if (second>=10){
                            Thirm.this.setText("0"+minute+":"+second);
                        }
                        else {
                            Thirm.this.setText("0"+minute+":0"+second);
                        }
                    }
                }else {
                    if (second==0){
                        second=59;
                        minute--;
                        if (minute>=10){
                            Thirm.this.setText(minute+":"+second);
                        }else {
                            Thirm.this.setText("0"+minute+":"+second);
                        }
                    }else {
                        second--;
                        if (second>=10){
                            if (minute>=10){
                                Thirm.this.setText(minute+":"+second);
                            }else {
                                Thirm.this.setText("0"+minute+":"+second);
                            }
                        }else {
                            if (minute>=10){
                                Thirm.this.setText(minute+":0"+second);
                            }else {
                                Thirm.this.setText("0"+minute+":0"+second);
                            }
                        }
                    }
                }
            }
        }
    };
    public Thirm(Context context){
        this(context,null);
    }
    @SuppressLint("SetTextI18n")
    public Thirm(Context context, AttributeSet attr){
        super(context,attr);
//        Thirm.this.setText(minute+":"+second);
        timerTask=new TimerTask() {
            @Override
            public void run() {
                Message msg=new Message();
                msg.what=0;
                handler.sendMessage(msg);
            }
        };
        timer=new Timer();
        timer.schedule(timerTask,0,1000);
    }
    public void cancel(){
        if (timer!=null){
            timer.cancel();
            timer=null;
        }
        if (timerTask!=null){
            timerTask=null;
        }
        minute=-1;
        second=-1;
    }
}
