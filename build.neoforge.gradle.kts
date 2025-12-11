plugins {
	id("mod-platform")
	id("net.neoforged.moddev")
}

platform {
	loader = "neoforge"
	dependencies {
		required("minecraft") {
			forgeVersionRange = "[${prop("deps.minecraft")}]"
		}
		required("neoforge") {
			forgeVersionRange = "[1,)"
		}
	}
}

neoForge {
	version = property("deps.neoforge") as String
	validateAccessTransformers = true

	if (hasProperty("deps.parchment")) parchment {
		val (mc, ver) = (property("deps.parchment") as String).split(':')
		mappingsVersion = ver
		minecraftVersion = mc
	}

	runs {
		register("client") {
			client()
			gameDirectory = file("run/")
			ideName = "NeoForge Client (${stonecutter.active?.version})"
			programArgument("--username=Dev")
		}
		register("server") {
			server()
			gameDirectory = file("run/")
			ideName = "NeoForge Server (${stonecutter.active?.version})"
		}
	}

	mods {
		register(property("mod.id") as String) {
			sourceSet(sourceSets["main"])
		}
	}
	sourceSets["main"].resources.srcDir("${rootDir}/versions/datagen/${stonecutter.current.version.split("-")[0]}/src/main/generated")
}

repositories{
	maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
	maven("https://dl.cloudsmith.io/public/tslat/sbl/maven/")
	maven("https://maven.minecraftforge.net/")
	maven("https://api.modrinth.com/maven")
	maven("https://maven.isxander.dev/releases")
	maven("https://maven.terraformersmc.com/")
}

dependencies {
	implementation("software.bernie.geckolib:geckolib-neoforge-${prop("deps.minecraft")}:${prop("deps.geckolib")}")
	implementation("net.tslat.smartbrainlib:SmartBrainLib-neoforge-${prop("deps.minecraft")}:${prop("deps.smartbrainlib")}")
	implementation("maven.modrinth:eveningstarlib:${prop("deps.eveningstarlib")}")
	compileOnly("dev.isxander:yet-another-config-lib:${prop("deps.yacl")}")
	implementation("com.terraformersmc:biolith-neoforge:${prop("deps.biolith")}")
}

tasks.named("createMinecraftArtifacts") {
	dependsOn(tasks.named("stonecutterGenerate"))
}
