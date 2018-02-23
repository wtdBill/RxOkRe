package a.com.rxokre;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2018/2/23.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Swordsman {
    String name() default "222";
    int age() default 11;
}
