package cn.com.almostlover.aopproject.factory;


import cn.com.almostlover.apt_annotation.Food;

@Food(value = "金针菇",type = Meal.class)
public class JinZhengGu implements Meal {
    @Override
    public int getPrice() {
        return 111;
    }

    @Override
    public String getName() {
        return "金针菇2333";
    }
}
