package cn.com.almostlover.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

public class Register implements Plugin<Project> {


    @Override
    void apply(Project project) {
        project.logger.error "================开始plugin=========="
//        def android = project.extensions.findByType(AppExtension)
//        android.registerTransform(new MyPreDexTransform(project))

//        def android1 = project.extensions.findByType(AppExtension)
//        android1.registerTransform(new MyPreDexTransform(project))
        project.android.registerTransform(new MyPreDexTransform(project))
        project.logger.error "================结束plugin=========="
    }
}
