package cn.peyton.android.latte.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h3>生成微信WXPayEntryActivity注解</h3>
 * <h4>用来传入包名,微信所需要代码模板</h4>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.annotations.PayEntryGenerator
 * 项目名 FestEC
 * 创建时间 2017-12-05 17:37
 * 版本 1.0.0
 * </pre>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface PayEntryGenerator {
    /** 包名 */
    String packageName();
    /** 模板类 */
    Class<?> payEntryTemplete();
}
