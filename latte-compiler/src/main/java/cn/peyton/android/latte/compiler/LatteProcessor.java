package cn.peyton.android.latte.compiler;

import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import cn.peyton.android.latte.annotations.AppRegisterGenerator;
import cn.peyton.android.latte.annotations.EntryGenerator;
import cn.peyton.android.latte.annotations.PayEntryGenerator;

/**
 * <pre>
 *
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.compiler.LatteProcessor
 * 项目名 FestEC
 * 创建时间 2017-12-05 17:40
 * 版本 1.0.0
 */
@SuppressWarnings("unused")
@AutoService(Processor.class)
public class LatteProcessor extends AbstractProcessor {


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterCode(roundEnvironment);
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportAnnotations();
        for (Class<? extends Annotation> annotation : supportAnnotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportAnnotations() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }

    /**
     * 扫描
     * @param env
     * @param annotation
     * @param visitor
     */
    private void scan(RoundEnvironment env, Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {
        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
            for (AnnotationMirror annotationMirror : annotationMirrors) {
                final Map<? extends ExecutableElement,? extends AnnotationValue> elementValue =
                        annotationMirror.getElementValues();
                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValue.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }

    private void generateEntryCode(RoundEnvironment env) {
        final EntryVisitor entryVisitor = new EntryVisitor(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVisitor);
    }

    private void generatePayEntryCode(RoundEnvironment env) {
        final PayEntryVisitor payEntryVisitor = new PayEntryVisitor(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, payEntryVisitor);
    }

    private void generateAppRegisterCode(RoundEnvironment env) {
        final AppRegisterVisitor appRegisterVisitor =  new AppRegisterVisitor(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, appRegisterVisitor);
    }
}
