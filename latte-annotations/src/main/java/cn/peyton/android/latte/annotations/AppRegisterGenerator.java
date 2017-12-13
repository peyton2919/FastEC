package cn.peyton.android.latte.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h3>生成微信AppRegister注解</h3>
 * <h4>用来传入包名,微信所需要代码模板</h4>
 * <pre>
 * Author: <a href="http://www.peyton.cn">peyton</a>
 * Email: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * ClassName: cn.peyton.android.latte.annotations.AppRegisterGenerator
 * ProjectName: FestEC
 * CreateDate: 2017-12-05 17:38
 * Version: 1.0.0
 * </pre>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface AppRegisterGenerator {
    /** 包名 */
    String packageName();
    /** 模板类 */
    Class<?> registerTemplate();
}
