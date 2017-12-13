package cn.peyton.android.fastec.example.generate;

import cn.peyton.android.latte.annotations.AppRegisterGenerator;
import cn.peyton.android.latte.core.wechat.templates.AppRegisterTemplate;

/**
 * <h3>用来生成微信AppRegister 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.festec.example.generate.AppRegister
 * 项目名 FestEC
 * 创建时间 2017-12-05 19:11
 * 版本 1.0.0
 * </pre>
 */
@AppRegisterGenerator(
        packageName = "cn.peyton.android.fastec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
