package cn.peyton.android.latte.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * <h3>AppRegister 观察者类{用于自动生成微信相关包与类}</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.compiler.AppRegisterVisitor
 * 项目名 FestEC
 * 创建时间 2017-12-05 19:00
 * 版本 1.0.0
 * </pre>
 */
public class AppRegisterVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {
    /** Filer对象 */
    private final Filer FILER;
    /** 包名 */
    private String mPackageName = null;

    /**
     * 构造函数
     * @param filer Filer对象
     */
    AppRegisterVisitor(Filer filer) {
        this.FILER = filer;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackageName = s;
        return p;
    }

    @Override
    public Void visitType(TypeMirror t, Void p) {
        generateJavaCode(t);
        return p;
    }

    /**
     * 自动生成java code
     * @param typeMirror
     */
    private void generateJavaCode(TypeMirror typeMirror) {
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("AppRegister")
                        .addModifiers(Modifier.PUBLIC)
                        .addModifiers(Modifier.FINAL)
                        .superclass(TypeName.get(typeMirror))
                        .build();

        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信广播接收器")
                .build();
        try {
            javaFile.writeTo(FILER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
