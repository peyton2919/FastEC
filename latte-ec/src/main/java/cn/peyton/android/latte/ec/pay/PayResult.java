package cn.peyton.android.latte.ec.pay;import android.text.TextUtils;/** * <h3></h3> * <pre> * 作者 <a href="http://www.peyton.cn">peyton</a> * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a> * 创建时间 2017-12-25 10:54 * 版本 1.0.0 * </pre> */public class PayResult {    private String resultStatus;    private String result;    private String memo;    public PayResult(String rawResult) {        if (TextUtils.isEmpty(rawResult))            return;        String[] resultParams = rawResult.split(";");        for (String resultParam : resultParams) {            if (resultParam.startsWith("resultStatus")) {                resultStatus = gatValue(resultParam, "resultStatus");            }            if (resultParam.startsWith("result")) {                result = gatValue(resultParam, "result");            }            if (resultParam.startsWith("memo")) {                memo = gatValue(resultParam, "memo");            }        }    }    @Override    public String toString() {        return "resultStatus={" + resultStatus + "};memo={" + memo                + "};result={" + result + "}";    }    private String gatValue(String content, String key) {        String prefix = key + "={";        return content.substring(content.indexOf(prefix) + prefix.length(),                content.lastIndexOf("}"));    }    /**     * @return the resultStatus     */    public String getResultStatus() {        return resultStatus;    }    /**     * @return the memo     */    public String getMemo() {        return memo;    }    /**     * @return the result     */    public String getResult() {        return result;    }}