package a.com.rxokre;

import java.util.Observable;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;

/**
 * Created by Administrator on 2018/2/11.
 */

public class WebUtil {
    private void postIpformation(String ip){
        String url="";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

}
