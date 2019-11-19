package com.hty.iotprivate.vali_aspc.aspect;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthCheck {
    String value() default "";
}
