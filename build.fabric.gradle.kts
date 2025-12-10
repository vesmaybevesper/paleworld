plugins {
	id("mod-platform")
	id("fabric-loom")
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
		optional("modmenu") {}
	}
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
	configureDataGeneration() {
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
}

dependencies {
	minecraft("com.mojang:minecraft:${prop("deps.minecraft")}")
	mappings(
		loom.layered {
			officialMojangMappings()
			if (hasProperty("deps.parchment")) parchment("org.parchmentmc.data:parchment-${prop("deps.parchment")}@zip")
		})
	modImplementation(libs.fabric.loader)
	modImplementation("net.fabricmc.fabric-api:fabric-api:${prop("deps.fabric-api")}")
	modImplementation("software.bernie.geckolib:geckolib-fabric-${prop("deps.minecraft")}:${prop("deps.geckolib")}")
	modImplementation("net.tslat.smartbrainlib:SmartBrainLib-fabric-${prop("deps.minecraft")}:${prop("deps.smartbrainlib")}")
	modImplementation("com.github.glitchfiend:TerraBlender-fabric:${prop("deps.minecraft")}-${prop("deps.terrablender")}")
	modImplementation("maven.modrinth:eveningstarlib:${prop("deps.eveningstarlib")}")
	modImplementation("dev.isxander:yet-another-config-lib:${prop("deps.yacl")}")
}
