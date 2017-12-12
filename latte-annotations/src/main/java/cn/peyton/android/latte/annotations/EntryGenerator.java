package cn.peyton.android.latte.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *  用来传入包名,微信所需要代码模板
 *
 *
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.annotations.EntryGenerator
 * 项目名 FestEC
 * 创建时间 2017-12-05 17:34
 * 版本 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {

    String packageName();

    Class<?> entryTemplete();
}
