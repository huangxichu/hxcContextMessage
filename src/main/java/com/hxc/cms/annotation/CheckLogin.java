package com.hxc.cms.annotation;


import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckLogin {

    /**
     * 方法描述
     * @return
     */
    String description()  default "";

    /**
     * 方法类型 0 表示不进行处理，1 表示进行处理
     * @return
     */
    int type() default 0;

    /**
     * 类的元数据,用于指定需要转换为的目标格式
     * @return
     */
    Class classType();


}
