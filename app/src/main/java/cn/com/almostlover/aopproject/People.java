package cn.com.almostlover.aopproject;


import cn.com.almostlover.apt_annotation.TestAnnotation;

@TestAnnotation("className是People")
public class People {
    @TestAnnotation("FieldName是age")
    private  int  age;
    @TestAnnotation("FieldName是name")
    private  String  name;

    @TestAnnotation("Constructor是People")
    public People(int age, String name) {
        this.age = age;
        this.name = name;
    }
    @TestAnnotation("Method是People")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
