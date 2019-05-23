package cn.com.almostlover.apt_processor;

import cn.com.almostlover.apt_annotation.TestAnnotation;
import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.Set;


@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {


    private Messager messager;
    private Filer filer;
    private Elements elementUtils;
    private Types typeUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        messager = processingEnv.getMessager();//日志输出工具
        filer = processingEnv.getFiler();//生成java类的工具
        elementUtils = processingEnv.getElementUtils();//获取元素的工具类
        typeUtils = processingEnv.getTypeUtils();//这个我不记得了
        messager.printMessage(Diagnostic.Kind.NOTE, "processing1111...");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.NOTE, "processing2222...");
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(TestAnnotation.class);
        for(Element element:elements){

            if(element.getKind()== ElementKind.CLASS){

                element.getModifiers();//获取修饰符
                element.getSimpleName();



            }else if(element.getKind()==ElementKind.INTERFACE){

            }else if (element.getKind()==ElementKind.CONSTRUCTOR){

            }else if (element.getKind()==ElementKind.ENUM){

            }else if (element.getKind()==ElementKind.FIELD){

            }else if (element.getKind()==ElementKind.LOCAL_VARIABLE){

            }else if (element.getKind()==ElementKind.METHOD){
                ExecutableElement element1 = (ExecutableElement) element;
                TypeMirror returnType = element1.getReturnType(); //获取type mirror
                TypeKind kind = returnType.getKind();
                //返回的数据类型
            }else if (element.getKind()==ElementKind.PARAMETER){

            }


        }


        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return  ImmutableSet.of(TestAnnotation.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
