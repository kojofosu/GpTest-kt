plugins {
    kotlin("jvm") version "1.9.23"
    `maven-publish`
}

group = "com.edue"
version = "1.4-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}

publishing {
    repositories {
        maven {
            name = "GPTest-kt"
            url = uri("https://maven.pkg.github.com/kojofosu/GpTest-kt")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
//    publications {
//        register<MavenPublication>("gpr") {
//            from(components["kotlin"])
//        }
//    }

    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])

            groupId = "com.edue"
            artifactId = "gptest-kt"
            version = "1.4"

            // If you have sources and Javadoc, include them as well
//            artifact(tasks["javadocJar"])
//            artifact(tasks["sourcesJar"])
        }
    }
}

//tasks {
//    create<Jar>("sourcesJar") {
//        archiveClassifier.set("sources")
//        from(sourceSets.main.get().allSource)
//    }
//
////    create<Jar>("javadocJar") {
////        archiveClassifier.set("javadoc")
////        from(tasks.javadoc)
////    }
//}