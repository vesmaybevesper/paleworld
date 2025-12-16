package dev.vesper.paleworld.common.world.gen;

public class PaleWorldWorldGen {
	public static void genWorld(){
		//? fabric{
		RockGen.generate();
		TreeGen.generateTrees();
		VegGen.generateVeg();
		//?}
		//? neoforge{

		//?}
	}

}
