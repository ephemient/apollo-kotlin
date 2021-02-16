if (System.getProperty("idea.sync.active") == null) {
  apply(plugin = "com.android.library")
}
apply(plugin = "org.jetbrains.kotlin.multiplatform")
apply(plugin = "com.squareup.sqldelight")

configure<com.squareup.sqldelight.gradle.SqlDelightExtension> {
  database("ApolloDatabase") {
    packageName = "com.apollographql.apollo3.cache.normalized.sql"
    schemaOutputDirectory = file("src/main/sqldelight/schemas")
  }
}

configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
  data class iOSTarget(val name: String, val preset: String, val id: String)

  val iosTargets = listOf(
      iOSTarget("ios", "iosArm64", "ios-arm64"),
      iOSTarget("iosSim", "iosX64", "ios-x64")
  )

  for ((targetName, presetName, id) in iosTargets) {
    targetFromPreset(presets.getByName(presetName), targetName) {
      mavenPublication {
        artifactId = "${project.name}-$id"
      }
    }
  }

  if (System.getProperty("idea.sync.active") == null) {
    android {
      publishAllLibraryVariants()
    }
  }

  jvm()

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(project(":apollo-api"))
        api(project(":apollo-normalized-cache-api"))
        implementation(groovy.util.Eval.x(project, "x.dep.kotlin.coroutines"))
      }
    }

    val jvmMain by getting {
      dependsOn(commonMain)
      dependencies {
        implementation(groovy.util.Eval.x(project, "x.dep.sqldelight.jvm"))
      }
    }

    if (System.getProperty("idea.sync.active") == null) {
      val androidMain by getting {
        dependsOn(commonMain)
        dependencies {
          api(groovy.util.Eval.x(project, "x.dep.androidx.sqlite"))
          implementation(groovy.util.Eval.x(project, "x.dep.sqldelight.android"))
          implementation(groovy.util.Eval.x(project, "x.dep.androidx.sqliteFramework"))
        }
      }
    }

    val iosMain by getting {
      dependsOn(commonMain)
      dependencies {
        implementation(groovy.util.Eval.x(project, "x.dep.sqldelight.native"))
      }
    }

    val iosSimMain by getting {
      dependsOn(iosMain)
    }

    val commonTest by getting {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }

    val jvmTest by getting {
      dependsOn(commonTest)
      dependencies {
        implementation(kotlin("test-junit"))

        implementation(groovy.util.Eval.x(project, "x.dep.junit"))
        implementation(groovy.util.Eval.x(project, "x.dep.truth"))
      }
    }

    if (System.getProperty("idea.sync.active") == null) {
      val androidTest by getting {
        dependsOn(jvmTest)
      }
    }

    val iosTest by getting {
      dependsOn(commonTest)
    }

    val iosSimTest by getting {
      dependsOn(iosTest)
    }
  }
}

if (System.getProperty("idea.sync.active") == null) {
  configure<com.android.build.gradle.LibraryExtension> {
    compileSdkVersion(groovy.util.Eval.x(project, "x.androidConfig.compileSdkVersion").toString().toInt())

    defaultConfig {
      minSdkVersion(groovy.util.Eval.x(project, "x.androidConfig.minSdkVersion").toString())
      targetSdkVersion(groovy.util.Eval.x(project, "x.androidConfig.targetSdkVersion").toString())
    }
  }
}

