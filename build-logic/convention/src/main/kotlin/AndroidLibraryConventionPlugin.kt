import com.android.build.api.dsl.LibraryExtension
import com.boooplay.configureKotlinAndroid
import com.boooplay.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("maven-publish")
                apply("org.jetbrains.dokka")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(commonExtension = this)

                publishing {
                    singleVariant("release") {
                        withSourcesJar()
                    }
                }
            }

            dependencies {
                add("implementation", libs.findLibrary("kotlinx-serialization").get())
            }
        }
    }

}