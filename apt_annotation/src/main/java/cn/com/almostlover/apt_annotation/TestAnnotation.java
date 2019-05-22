package cn.com.almostlover.apt_annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface TestAnnotation {
    String value();
}
