plugins {
	id("mod-platform")
	id("net.fabricmc.fabric-loom")
	id("com.github.spotbugs") version "6.5.8"
}

platform {
	loader = "fabric"
	dependencies {
		required("minecraft") {
			versionRange = prop("deps.minecraft")
		}
		required("fabric-api") {
			slug("fabric-api")
			versionRange = ">=${prop("deps.fabric-api")}"
		}
		required("fabricloader") {
			versionRange = ">=${libs.fabric.loader.get().version}"
		}
		required("biolith"){
			slug("biolith")
			versionRange = ">=${prop("deps.biolith")}"
		}
		required("geckolib"){
			slug("geckolib")
			versionRange = ">=${prop("deps.geckolib")}"
		}
		required("smartbrainlib"){
			slug("smartbrainlib")
			versionRange = ">=${prop("deps.smartbrainlib")}"
		}
		required("eveningstarlib"){
			slug("eveningstarlib")
			versionRange = ">=${prop("deps.eveningstarlib")}"
		}
		required("yacl"){
			slug("yacl")
			versionRange = ">=${prop("deps.yacl")}"
		}
		optional("modmenu") {
			slug("modmenu")
			versionRange = ">=${prop("deps.modmenu")}"
		}
	}
}

spotbugs {
	toolVersion = "4.10.2"
	ignoreFailures=true
}

loom {
	accessWidenerPath = rootProject.file("src/main/resources/${prop("mod.id")}.accesswidener")
	runs.named("client") {
		client()
		ideConfigGenerated(true)
		runDir = "run/"
		environment = "client"
		programArgs("--username=Dev")
		configName = "Fabric Client"
	}
	runs.named("server") {
		server()
		ideConfigGenerated(true)
		runDir = "run/"
		environment = "server"
		configName = "Fabric Server"
	}
}

fabricApi {
	configureDataGeneration {
		outputDirectory = file("${rootDir}/versions/datagen/${stonecutter.current.version.split("-")[0]}/src/main/generated")
		client = true
	}
}

repositories{
	maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
	maven("https://dl.cloudsmith.io/public/tslat/sbl/maven/")
	maven("https://maven.minecraftforge.net/")
	maven("https://api.modrinth.com/maven")
	maven("https://maven.isxander.dev/releases")
	maven("https://maven.terraformersmc.com/")
	mavenCentral()
	gradlePluginPortal()
}

dependencies {
	minecraft("com.mojang:minecraft:${prop("deps.minecraft")}")
	implementation(libs.fabric.loader)
	implementation("net.fabricmc.fabric-api:fabric-api:${prop("deps.fabric-api")}")
	implementation("com.geckolib:geckolib-fabric-${prop("deps.minecraft")}:${prop("deps.geckolib")}")
	implementation("maven.modrinth:smartbrainlib:${prop("deps.smartbrainlib")}")
	implementation("maven.modrinth:eveningstarlib:${prop("deps.eveningstarlib")}")
	implementation("maven.modrinth:yacl:${prop("deps.yacl")}")
	implementation("com.terraformersmc:biolith-fabric:${prop("deps.biolith")}")
	implementation("com.terraformersmc:modmenu:${prop("deps.modmenu")}")
	compileOnly("maven.modrinth:iris:${prop("deps.iris")}")
	spotbugsPlugins("com.h3xstream.findsecbugs:findsecbugs-plugin:1.14.0")
}
