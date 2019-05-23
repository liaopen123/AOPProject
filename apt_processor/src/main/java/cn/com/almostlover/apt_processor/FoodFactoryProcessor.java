package cn.com.almostlover.apt_processor;

import cn.com.almostlover.apt_annotation.Food;
import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.List;
import java.util.Set;


@AutoService(Processor.class)
public class FoodFactoryProcessor extends AbstractProcessor {


    private Messager messager;
    private Filer filer;
    private Elements elementUtils;
    private Types typeUtils;
    private String qualifiedSuperClassName;
    private String simpleTypeName;

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
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Food.class);
        MethodSpec.Builder method = MethodSpec.methodBuilder("getFoodInstance")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class,"foodName");



        for (Element element : elements) {

            if (element.getKind() == ElementKind.CLASS) {


                TypeElement typeElement = (TypeElement) element;
                Food annotation = typeElement.getAnnotation(Food.class);//找到上方的注解
                String foodName = annotation.value();



                try {
                    Class<?> type = annotation.type();

                    qualifiedSuperClassName = type.getCanonicalName();
                    simpleTypeName = type.getSimpleName();

                } catch (MirroredTypeException mte) {
                    DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
                    TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
                    qualifiedSuperClassName = classTypeElement.getQualifiedName().toString(); //cn.com.almostlover.aopproject.factory.Meal
                    simpleTypeName = classTypeElement.getSimpleName().toString(); //Meal

                }
//                这里有一点小麻烦，因为这里的类型是一个java.lang.Class。这就意味着，他是一个真正的Class对象。因为注解处理是在编译Java源代码之前。我们需要考虑如下两种情况：
//                这个类已经被编译：这种情况是：如果第三方.jar包含已编译的被@Factory注解.class文件。在这种情况下，我们可以想try中那块代码中所示直接获取Class。
//                这个还没有被编译：这种情况是我们尝试编译被@Fractory注解的源代码。这种情况下，直接获取Class会抛出MirroredTypeException异常。幸运的是，MirroredTypeException包含一个TypeMirror，它表示我们未编译类。因为我们已经知道它必定是一个类类型（我们已经在前面检查过），我们可以直接强制转换为DeclaredType，然后读取TypeElement来获取合法的名字。
                method.addStatement("if (foodName.equals($S)){" +
                        "return new "+typeElement.getQualifiedName().toString()+"();}",foodName);

                try {

                } catch (Exception e) {
                }
            } else {

                messager.printMessage(Diagnostic.Kind.ERROR, "Food Must defined with a class...");
            }


        }
        //通过 字符串的name 生成对应的class文件
        ClassName className = ClassName.bestGuess(qualifiedSuperClassName);
//        ClassName returnType = ClassName.get("cn.com.almostlover.aopproject.factory","Meal");
        method.addStatement("return null");
        method.returns(className);



        TypeSpec classType = TypeSpec.classBuilder(simpleTypeName+"Factory")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(method.build())
                .addJavadoc("@自动生成")
                .build();

        JavaFile javaFile = JavaFile.builder("cn.com.almostlover.aopproject.factory", classType)
                .build();
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(Food.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
