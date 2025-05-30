2.0.0+Alpha.8
=

_Added:_
* Added Falling Moss Particles to the Pave Caves



2.0.0+Alpha.7
=

_Changed:_
* Changed ID of Pale Caves from "pale_cave" to "pale_caves"

_Fixed:_
  * Several Item Textures

2.0.0+Alpha.6
=

_Changed:_
*  All advancement text is now translatable
  * You can submit translations on GitHub

_Fixed:_
* Pale Caves spawn again


2.0.0+Alpha.5
=
* Updated to 1.21.5

2.0.0+Alpha.4
=

_Added:_
* Pale Gardens can now be massive... have fun >:)

_Changed:_
* Decreased Pale Spike Generation
* Increased possible size of Pale Spikes
* New Palestone Texture

_Fixed:_
* Missing Item Group name after modid change



2.0.0+Alpha.3
=

_Added:_
* Palestone
  * Name subject to change
  * Temp texture
* Pale Spikes to Pale Gardens
  * Palestone Monoliths

_Changed:_
* Changed mob spawn weights in Pale Garden
* Bare Pale Oak changes
  * Made a custom foliage placer instead of placing air using the Dark Oak placer
    * this fixes an issue where the "leaves" of the bare trees were deleting actual leaves
  * Reduced weight and extra chance

2.0.0+Alpha.2
=

_Added:_
* Open Eyeblossoms now glow
* Tall Pale Oaks (Stripped and Regular)


_Changed:_
* Fog is now customizable through the config
* Tweaked "Horror Mode" fog transparency (1 -> 0.99)
  * While this seems like a nothing change, this massively changed the way Minecraft was rendering. Chunks mostly or totally in fog shouldn't completely unrender anymore.
    * This also mostly fixes the issue of seeing the skybox everywhere
* Changed some tree gen numbers


2.0.0+Alpha.1
=

_Added:_
* At the Heart Advancement 
* 5 new tree variants
  * Some Description idk
* Added Fog to the Pale Garden
  * "Horror Mode" is available in the mod settings and significantly reduces visibility
  * The Fog will be customizable through the config in future versions

_Changed:_
* Tweaked Pale Cave ceiling generation (again)
* Vampire Bats now spawn in the Pale Garden
* Pale Apples are now always consumable

1.3.1
=

_Fixed:_
* Fixed Vampire Bats will now actually spawn 

1.3.0
=

_Added:_
* Pale Apple
  * Craftable with 8 Drained Amethyst Fragments and an Apple
    * Gives Regeneration for 15 seconds
    * Absorption for 1 minute
    * Nausea for 5 seconds

_Changed:_
* Pale Apple has been added to the criteria for "That Can't Be Healthy"
* Updated Pale Cave ceiling generation

_Fixed:_
* Added Drained Amethyst Fragment to creative menu

1.2.0
=

_Added:_
* Pale Hanging Moss now generates on the ceiling of Pale Caves
  * I'm not all that happy with the current generation of them, expect it to update soon 
* Drained Amethyst
  * _"Wait... was the purple... blood?"_
    * You can find these in Geodes that spawn in Pale Caves, in the spot usually occupied by Calcite!
  * Drained Amethyst Fragment
    * Craftable from a Drained Amethyst
    * Craftable into a Drained Amethyst

_Changed:_
* (Massively) Increased generation of Pale Vines
  * I had the wrong feature placed in the biome, whoops, this is (closer) to how I intended it to be from the start.
    * I would still expect this to undergo an update again
* Pale Vines can now be extended with Pale Berries
* Reduced the chance of Pale Berries growing on a given block (From 25% to 10%)

_Fixed:_
* The end of Pale Vines will now display the correct texture if they have berries


1.1.1
=

_Changed:_

* Changed Vampire Bat circling behaviour
* Changed Vampire Bat Movement speed from 1.5 to 2
* Changed Vampire Bat follow range from 25 to 35
* Updated icon



1.1.0
=

_Added:_

* Vampire Bat
  * _"Awww how cu- OW!"_
  * This tiny hunter spawns in the Pale Caves, be careful to keep your distance otherwise it might bite you!

* Pale Berries
  * _"I wouldn't eat that..."_
  * Turns out the Pale Vines can still grow berries, they don't look that good though.
    * Gives 2 Hunger & 0.1 Saturation
    * Applies Blindness for 30 seconds on consumption

_Changed:_

* TerraBlender is now included
* Increased Pale Axolotl Spawn Weight
* Pale Vine Luminance is now tied to each block's berry state

_Fixed:_

* Pale Axolotl Spawn Egg Texture
* Pale Axolotl's no longer get stuck when trying to exit the water


1.0.0
=
# The Pale Cave

### What happens when the last of the nutrients fade from a Lush Cave?

## Added

- The Pale Cave Biome
- Pale Axolotl
  ![A Pale Axolotl Sitting In A Pale Cave](https://cdn.modrinth.com/data/cached_images/b8e3b0d3a23e969fc8fc1979d412d0baee66bb6a.jpeg)
- Pale Vines
- Big Dying Dripleaf
- Small Dying Dripleaf
- Dying Azalea
- Advancements
  - Pale World
  - Clinging To Life
  - You're My Friend Now


1.0.0+RC.3
=

_Added:_

* Clinging To Life Advancement 

_Fixed:_

* Pale Vines now behave as expected

1.0.0+RC.2
=

_Added:_

* Pale Axolotl Bucket
* You're My Friend Now Advancement 

_Fixed:_

* Fixed double Pale World Advancement

1.0.0+RC.1
=

_Added:_

* Advancements
  * Pale World
    * Load into a world with Pale World installed

* Pale Axolotl Spawn Egg

_Changed:_

* Updated Pale Axolotl texture



1.0.0+Beta.4
=

_Added:_

* Pale Axolotl
  * Temp Texture(!!!)

_Changed:_

* Updated Fabric Api to 0.115.0

_Fixed:_

* Big Dying Dripleaves should now have the correct particles in all cases
* Fixed an error related to Big Dying Dripleaf loottable


1.0.0+Beta.3
=

_Changed:_

* Reduced spawn rate of Big Dying Dripleaf
* Increased spawn rate of Small Dying Dripleaf

_Fixed:_

* Fixed Big Dying Dripleaf particles in most cases


1.0.0+Beta.2
=

_Changed:_

* Axe's now break dying dripleaves faster
* Made big dying dripleaves slightly less red

_Fixed:_

* Fixed Dying Dripleaf loot table


1.0.0+Beta.1
=

_Added:_

* Small Dying Dripleaf
* Big Dying Dripleaf

_Changed:_

* Replace Dripleaves with Dying variants in Pale Caves
* Update Fabric API to 0.114.3+1.21.4


1.0.0+Alpha.8
=
_Added:_
* Dying Azalea

_Changed:_

* Re-added several underground features to Pale Caves
* Replace Azalea's in Pale cave with Dying Azalea


1.0.0+Alpha.7
=

_Changed:_

* Pale Caves will now use the correct Carver(s)**

1.0.0+Alpha.6
=
_Added:_

* Pale Vines can now be climbed

_Changed:_
    
* Up the region weight (The caves were really only spawning as tiny corners after the last weight reduction, The hope is to find a balance between having it as a large explorable biome and not overriding all Lush Caves)
* Increase the Pale Vine luminance again

1.0.0+Alpha.5
=
Added:
    
    Add a small amount of fish spawn to Pale Caves

Changed:
    
    Updated Fabric API to 0.114.2
    Update Fabric Loader to 0.16.10
    Changed Pale Vine luminance (It should now actually have a bit of light to it, trying to find a moddle ground between super bright and no light)
Fixed:

    Pale Vine Ceiling Gen



1.0.0+Alpha.4
=
Added:

    Small Dying Dripleaf (WIP)
    Big Dying Dripleaf (WIP)

Changed:

    Reduced Pale Vine luminance
    Reduce weight of Pale Vines ceiling spawn (My hope is this makes the caves a bit less monolithic while I create better generation)
    Reduce Weight of Biome Spawn

1.0.0+Alpha.3
=
Added:
    
    Pale Vine body
    Pale Vines now spawn on the ceiling of Pale Caves

Changed:

    Reduced Dead Bush spawn rate
    Reduced Azalea spawn rate

Fixed:

    Fixed broken Pale Vine texture

1.0.0+Alpha.2
=
Added:
        
    Petrified Wood (WIP)

Changed:
    
    Update TerraBlender to 4.3.0.2
    Replace vanilla lush caves vegetation with a custom mix in Pale Caves
    Removed Lush Cave ceiling generation from Pale Caves pending customization

    


----------------
1.0.0+Alpha.1
=
Added:

    Pale Caves
        Lush Cave variant
            Features Pale moss, and water color changes
            Many more changes to come
    Pale Vine
        Block is broken and doesn't generate naturally for the time being.

