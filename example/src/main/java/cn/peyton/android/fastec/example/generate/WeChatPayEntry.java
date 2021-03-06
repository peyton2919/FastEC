package cn.peyton.android.fastec.example.generate;

import cn.peyton.android.latte.annotations.PayEntryGenerator;
import cn.peyton.android.latte.core.wechat.templates.WXPayEntryTemplate;

/**
 * <h3>自动生成微信WeChatPayEntry 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.festec.example.generate.WeChatEntry
 * 项目名 FestEC
 * 创建时间 2017-12-05 19:10
 * 版本 1.0.0
 * </pre>
 */
@SuppressWarnings("ALL")
@PayEntryGenerator(
        packageName = "cn.peyton.android.fastec.example",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
    
}
