package com.terrago.app.data.local

import com.terrago.app.domain.species.SpeciesRepository
import com.terrago.app.domain.species.model.Species
import javax.inject.Inject

class AppInitializer @Inject constructor(
    private val repository: SpeciesRepository
) {

    suspend fun initialize() {
        if (repository.countSpecies() == 0) {
            prepopulateSpecies()
        }
    }

    private suspend fun prepopulateSpecies() {
        val defaultSpecies = listOf(
            Species(
                nameLatin = "Pogona vitticeps",
                nameCommon = "Bearded Dragon",
                description = "Diurnal, terrestrial lizard native to Australia. Hardy, intelligent, and one of the most commonly kept beginner reptiles.",
                temperatureMin = 24.0,
                temperatureMax = 32.0,
                humidityMin = 30.0,
                humidityMax = 40.0,
                lightCycleH = 12
            ),
            Species(
                nameLatin = "Eublepharis macularius",
                nameCommon = "Leopard Gecko",
                description = "Nocturnal, terrestrial gecko from arid regions. Hardy and widely kept; suitable for beginners.",
                temperatureMin = 22.0,
                temperatureMax = 32.0,
                humidityMin = 30.0,
                humidityMax = 40.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Correlophus ciliatus",
                nameCommon = "Crested Gecko",
                description = "Nocturnal, arboreal gecko from New Caledonia. Does not require high heat; popular beginner species.",
                temperatureMin = 20.0,
                temperatureMax = 26.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Anolis carolinensis",
                nameCommon = "Green Anole",
                description = "Diurnal, arboreal lizard native to the southeastern United States. Active and visually oriented.",
                temperatureMin = 22.0,
                temperatureMax = null,
                humidityMin = 60.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),
            Species(
                nameLatin = "Phelsuma spp.",
                nameCommon = "Day Gecko",
                description = "Diurnal, arboreal geckos from Madagascar. Brightly colored and require high-intensity lighting.",
                temperatureMin = 24.0,
                temperatureMax = 28.0,
                humidityMin = 60.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Gekko gecko",
                nameCommon = "Tokay Gecko",
                description = "Nocturnal, arboreal gecko native to Southeast Asia. Large, territorial, and vocal.",
                temperatureMin = 24.0,
                temperatureMax = 30.0,
                humidityMin = 60.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Rhacodactylus auriculatus",
                nameCommon = "Gargoyle Gecko",
                description = "Nocturnal, arboreal gecko from New Caledonia. Sensitive to heat; avoid temperatures above 27°C.",
                temperatureMin = 20.0,
                temperatureMax = 25.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Pachydactylus rangei",
                nameCommon = "Web-foot Gecko",
                description = "Nocturnal, terrestrial gecko. Requires dry substrate with high humidity spikes at night.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 30.0,
                humidityMax = 50.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Uromastyx spp.",
                nameCommon = "Uromastyx",
                description = "Diurnal, terrestrial herbivore. Needs very low humidity and extreme heat gradients.",
                temperatureMin = 26.0,
                temperatureMax = 35.0,
                humidityMin = 10.0,
                humidityMax = 30.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Tiliqua spp.",
                nameCommon = "Blue-Tongued Skink",
                description = "Diurnal, terrestrial. Humidity needs vary (40% for Australian, 70% for Indonesian species).",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 40.0,
                humidityMax = 75.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Chlamydosaurus kingii",
                nameCommon = "Frilled Lizard",
                description = "Diurnal, arboreal to semi-terrestrial. Requires large enclosures and high heat.",
                temperatureMin = 24.0,
                temperatureMax = 32.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Crotaphytus collaris",
                nameCommon = "Collared Lizard",
                description = "Diurnal, terrestrial. Extremely active; requires high UV and desert-like heat.",
                temperatureMin = 24.0,
                temperatureMax = 32.0,
                humidityMin = 20.0,
                humidityMax = 40.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Physignathus cocincinus",
                nameCommon = "Water Dragon",
                description = "Diurnal, semi-arboreal. Needs a large swimming area and constant high humidity.",
                temperatureMin = 24.0,
                temperatureMax = 29.0,
                humidityMin = 70.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Chamaeleo calyptratus",
                nameCommon = "Veiled Chameleon",
                description = "Diurnal, arboreal. Requires screened enclosures for high airflow to prevent infections.",
                temperatureMin = 22.0,
                temperatureMax = 30.0,
                humidityMin = 40.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Furcifer pardalis",
                nameCommon = "Panther Chameleon",
                description = "Diurnal, arboreal. Highly dependent on hydration through misting or drippers.",
                temperatureMin = 22.0,
                temperatureMax = 26.0,
                humidityMin = 60.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Lepidophyma flavimaculatum",
                nameCommon = "Yellow-Spotted Night Lizard",
                description = "Nocturnal, terrestrial/saxicolous. Prefers cool, damp, and dark microclimates.",
                temperatureMin = 20.0,
                temperatureMax = 26.0,
                humidityMin = 60.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Scincus scincus",
                nameCommon = "Sandfish Skink",
                description = "Diurnal, fossorial desert specialist. Requires deep, fine sand to burrow.",
                temperatureMin = 24.0,
                temperatureMax = 32.0,
                humidityMin = 10.0,
                humidityMax = 30.0,
                lightCycleH = 12
            ),
            Species(
                nameLatin = "Lepidodactylus lugubris",
                nameCommon = "Mourning Gecko",
                description = "Nocturnal, arboreal gecko. Parthenogenetic; tolerant of moderate temperatures and humidity.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 60.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Uroplatus fimbriatus",
                nameCommon = "Giant Leaf-tailed Gecko",
                description = "Nocturnal, arboreal gecko from Madagascar. Requires high humidity and excellent ventilation.",
                temperatureMin = 20.0,
                temperatureMax = 26.0,
                humidityMin = 70.0,
                humidityMax = 90.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Coleonyx variegatus",
                nameCommon = "Western Banded Gecko",
                description = "Nocturnal, terrestrial gecko. Prefers rocky microhabitats and moderate humidity.",
                temperatureMin = 20.0,
                temperatureMax = 28.0,
                humidityMin = 40.0,
                humidityMax = 60.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Pantherophis guttatus",
                nameCommon = "Corn Snake",
                description = "Nocturnal/crepuscular, terrestrial snake. Hardy and popular beginner species.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 40.0,
                humidityMax = 50.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Python regius",
                nameCommon = "Ball Python",
                description = "Nocturnal, terrestrial snake. Requires higher humidity to prevent respiratory issues.",
                temperatureMin = 24.0,
                temperatureMax = 30.0,
                humidityMin = 60.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Gongylophis colubrinus",
                nameCommon = "Kenyan Sand Boa",
                description = "Nocturnal, fossorial snake. Needs sandy substrate and relatively dry air.",
                temperatureMin = 24.0,
                temperatureMax = 30.0,
                humidityMin = 30.0,
                humidityMax = 50.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Eryx johnii",
                nameCommon = "Indian Sand Boa",
                description = "Nocturnal, fossorial snake. Requires dry substrate with occasional moderate humidity.",
                temperatureMin = 24.0,
                temperatureMax = 30.0,
                humidityMin = 30.0,
                humidityMax = 50.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Lampropeltis getula",
                nameCommon = "Eastern Kingsnake",
                description = "Diurnal/nocturnal, terrestrial snake. Hardy and adaptable to moderate humidity.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 40.0,
                humidityMax = 60.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Lampropeltis triangulum",
                nameCommon = "Milk Snake",
                description = "Nocturnal, terrestrial snake. Needs moderate humidity and stable temperatures.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 40.0,
                humidityMax = 60.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Thamnophis sirtalis",
                nameCommon = "Common Garter Snake",
                description = "Diurnal/nocturnal, semi-aquatic snake. Tolerates variable humidity and temperatures.",
                temperatureMin = 20.0,
                temperatureMax = 28.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Morelia spilota",
                nameCommon = "Carpet Python",
                description = "Nocturnal, semi-arboreal snake. Prefers moderate humidity and climbing opportunities.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Lichanura trivirgata",
                nameCommon = "Rosy Boa",
                description = "Nocturnal, terrestrial snake. Desert-adapted; low humidity is essential to prevent rot.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 20.0,
                humidityMax = 40.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Boa constrictor",
                nameCommon = "Boa Constrictor",
                description = "Nocturnal, semi-arboreal snake. Prefers moderate humidity and stable warmth.",
                temperatureMin = 24.0,
                temperatureMax = 30.0,
                humidityMin = 60.0,
                humidityMax = 75.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Dendrobates tinctorius",
                nameCommon = "Dyeing Poison Dart Frog",
                description = "Diurnal, terrestrial. Cannot tolerate high heat.",
                temperatureMin = 21.0,
                temperatureMax = 26.0,
                humidityMin = 70.0,
                humidityMax = 95.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Hyla cinerea",
                nameCommon = "Green Tree Frog",
                description = "Nocturnal/crepuscular, arboreal. Prefers moderate humidity and ambient warmth.",
                temperatureMin = 20.0,
                temperatureMax = 28.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Ceratophrys cranwelli",
                nameCommon = "Cranwell's Pacman Frog",
                description = "Nocturnal, terrestrial frog. Requires high humidity and soft, damp substrate.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 70.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Kaloula pulchra",
                nameCommon = "Banded Bullfrog",
                description = "Nocturnal, terrestrial/fossorial frog. Prefers moist substrate to burrow into.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 60.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Lithobates pipiens",
                nameCommon = "Northern Leopard Frog",
                description = "Diurnal/nocturnal, semi-aquatic frog. Prefers cooler, oxygenated environments.",
                temperatureMin = 15.0,
                temperatureMax = 24.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Bombina orientalis",
                nameCommon = "Fire-Bellied Toad",
                description = "Diurnal, semi-aquatic toad. Needs moderate temperatures; temperatures over 26°C are stressful.",
                temperatureMin = 16.0,
                temperatureMax = 24.0,
                humidityMin = 60.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Anaxyrus americanus",
                nameCommon = "American Toad",
                description = "Nocturnal, terrestrial. Hardy; requires loose soil and a water dish.",
                temperatureMin = 15.0,
                temperatureMax = 25.0,
                humidityMin = 40.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Ambystoma mexicanum",
                nameCommon = "Axolotl",
                description = "Fully aquatic salamander. Water must remain cool; temperatures above 22°C are dangerous.",
                temperatureMin = 15.0,
                temperatureMax = 19.0,
                humidityMin = null,
                humidityMax = null,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Notophthalmus viridescens",
                nameCommon = "Eastern Newt",
                description = "Semi-aquatic. Requires clean water and a land area for the 'eft' stage.",
                temperatureMin = 16.0,
                temperatureMax = 22.0,
                humidityMin = 70.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Ambystoma mavortium",
                nameCommon = "Tiger Salamander",
                description = "Terrestrial/fossorial. Needs deep, moist substrate to burrow and moderate temperatures.",
                temperatureMin = 16.0,
                temperatureMax = 22.0,
                humidityMin = 70.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Brachypelma hamorii",
                nameCommon = "Mexican Redknee Tarantula",
                description = "Terrestrial, docile tarantula. Desert/scrubland species; keep substrate mostly dry.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 50.0,
                humidityMax = 60.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Grammostola pulchripes",
                nameCommon = "Chaco Golden Knee Tarantula",
                description = "Terrestrial, calm tarantula. Known for its large size and golden leg markings.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 50.0,
                humidityMax = 60.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Avicularia avicularia",
                nameCommon = "Pink Toe Tarantula",
                description = "Arboreal tarantula. Requires high humidity combined with high cross-ventilation.",
                temperatureMin = 24.0,
                temperatureMax = 28.0,
                humidityMin = 70.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Nhandu chromatus",
                nameCommon = "Red and White Striped Tarantula",
                description = "Terrestrial tarantula. Fast-growing and often defensive; prefers slightly damp substrate.",
                temperatureMin = 24.0,
                temperatureMax = 28.0,
                humidityMin = 60.0,
                humidityMax = 75.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Acanthoscurria geniculata",
                nameCommon = "Brazilian Whiteknee Tarantula",
                description = "Terrestrial tarantula. Very large and opportunistic eater; keep humidity moderate.",
                temperatureMin = 24.0,
                temperatureMax = 28.0,
                humidityMin = 60.0,
                humidityMax = 75.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Pandinus imperator",
                nameCommon = "Emperor Scorpion",
                description = "Nocturnal, terrestrial scorpion. Requires deep substrate and high humidity to prevent molting issues.",
                temperatureMin = 24.0,
                temperatureMax = 30.0,
                humidityMin = 75.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),
            Species(
                nameLatin = "Hadrurus arizonensis",
                nameCommon = "Desert Hairy Scorpion",
                description = "Largest North American scorpion; known for aggressive hunting and burrowing deep into sandy substrates.",
                temperatureMin = 24.0,
                temperatureMax = 35.0,
                humidityMin = 10.0,
                humidityMax = 30.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Mastigoproctus giganteus",
                nameCommon = "Vinegaroon (Whip Scorpion)",
                description = "Lacks venom but can spray acetic acid from its tail; uses heavy pedipalps for crushing prey.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 70.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Damon medius",
                nameCommon = "Tailless Whip Scorpion",
                description = "Flat-bodied arachnid with elongated front legs used as sensory feelers; famous for its appearance in popular media.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 75.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Centruroides sculpturatus",
                nameCommon = "Arizona Bark Scorpion",
                description = "Highly agile climber often found on trees or walls; possesses a potent neurotoxic sting.",
                temperatureMin = 24.0,
                temperatureMax = 32.0,
                humidityMin = 20.0,
                humidityMax = 40.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Hierodula membranacea",
                nameCommon = "Giant Asian Mantis",
                description = "Aggressive predator capable of taking down large insects and small vertebrates; popular for its bold personality.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 60.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Phyllocrania paradoxa",
                nameCommon = "Ghost Mantis",
                description = "Expertly mimicks dried leaves with leafy protrusions on the head and limbs; communal if food is plentiful.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Mantis religiosa",
                nameCommon = "European Mantis",
                description = "Highly adaptable species with a distinctive black spot on the inner front legs used for threat displays.",
                temperatureMin = 18.0,
                temperatureMax = 28.0,
                humidityMin = 40.0,
                humidityMax = 60.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Extatosoma tiaratum",
                nameCommon = "Giant Prickly Stick Insect",
                description = "Covered in thorn-like spikes and curls its tail like a scorpion to ward off predators; feeds on eucalyptus and bramble.",
                temperatureMin = 20.0,
                temperatureMax = 26.0,
                humidityMin = 60.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Phyllium pulchrifolium",
                nameCommon = "Walking Leaf",
                description = "Incredible leaf mimicry including 'brown spots' and ragged edges; moves with a swaying gait to simulate wind-blown foliage.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 70.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Hymenopus coronatus",
                nameCommon = "Orchid Mantis",
                description = "Females look like flowers to lure pollinators; males are much smaller and reach maturity significantly faster.",
                temperatureMin = 24.0,
                temperatureMax = 28.0,
                humidityMin = 70.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Hierodula patellifera",
                nameCommon = "Giant Rainforest Mantis",
                description = "Sturdy, green-colored predator that thrives in lush foliage; requires cross-ventilation to prevent stagnant air.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 60.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Platymeris rhadamanthus",
                nameCommon = "Red-Spot Assassin Bug",
                description = "Injects liquefying enzymes into prey via a sharp proboscis; capable of spitting venom defensively.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Asbolus verrucosus",
                nameCommon = "Blue Death Feigning Beetle",
                description = "Coated in a blue waxy secretion to prevent dehydration; known for 'playing dead' when startled.",
                temperatureMin = 22.0,
                temperatureMax = 30.0,
                humidityMin = 10.0,
                humidityMax = 25.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Archispirostreptus gigas",
                nameCommon = "Giant African Millipede",
                description = "Possesses hundreds of legs and secretes a mild irritating liquid if threatened; vital for breaking down leaf litter.",
                temperatureMin = 22.0,
                temperatureMax = 26.0,
                humidityMin = 70.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Scolopendra dehaani",
                nameCommon = "Vietnam Giant Centipede",
                description = "Extremely fast and aggressive predator with a painful venomous bite; requires a highly secure escape-proof enclosure.",
                temperatureMin = 24.0,
                temperatureMax = 28.0,
                humidityMin = 70.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Lissachatina fulica",
                nameCommon = "Giant African Land Snail",
                description = "Voracious eater of vegetation; requires cuttlefish bone or similar sources for shell growth.",
                temperatureMin = 20.0,
                temperatureMax = 26.0,
                humidityMin = 75.0,
                humidityMax = 90.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Coenobita clypeatus",
                nameCommon = "Caribbean Hermit Crab",
                description = "Social scavenger that must periodically trade shells; requires deep substrate for molting underground.",
                temperatureMin = 24.0,
                temperatureMax = 30.0,
                humidityMin = 75.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Porcellio scaber",
                nameCommon = "Common Rough Isopod",
                description = "Hardy scavenger that consumes mold, decaying wood, and animal waste; excellent for larger bioactive setups.",
                temperatureMin = 18.0,
                temperatureMax = 26.0,
                humidityMin = 60.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Folsomia candida",
                nameCommon = "White Springtails",
                description = "Essential micro-scavenger that prevents fungal outbreaks by consuming mold and spores in damp soil.",
                temperatureMin = 18.0,
                temperatureMax = 28.0,
                humidityMin = 80.0,
                humidityMax = 95.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Trichorhina tomentosa",
                nameCommon = "Dwarf White Isopod",
                description = "Tiny, soft-bodied clean-up crew that stays mostly underground; ideal for tropical vivariums.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 75.0,
                humidityMax = 90.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Anadenobolus monilicornis",
                nameCommon = "Bumblebee Millipede",
                description = "Visually striking detritivore that thrives in moist leaf litter and rotting wood layers.",
                temperatureMin = 22.0,
                temperatureMax = 27.0,
                humidityMin = 75.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Eisenia fetida",
                nameCommon = "Red Wiggler Worm",
                description = "Prodigious composter that processes organic matter into nutrient-rich castings for terrarium plants.",
                temperatureMin = 15.0,
                temperatureMax = 25.0,
                humidityMin = 70.0,
                humidityMax = 90.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Deroceras laeve",
                nameCommon = "Marsh Slug",
                description = "Moisture-dependent gastropod that thrives in heavily planted, swampy environments.",
                temperatureMin = 15.0,
                temperatureMax = 22.0,
                humidityMin = 85.0,
                humidityMax = 95.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Subulina octona",
                nameCommon = "Miniature Awl Snail",
                description = "Rapidly breeding scavenger that aids in recycling nutrients in tropical forest floor setups.",
                temperatureMin = 20.0,
                temperatureMax = 26.0,
                humidityMin = 70.0,
                humidityMax = 90.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Porcellio laevis",
                nameCommon = "Dairy Cow Isopod",
                description = "Large, fast-moving scavenger with a high protein appetite; frequently used as a clean-up crew for large reptiles.",
                temperatureMin = 20.0,
                temperatureMax = 28.0,
                humidityMin = 70.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Armadillidium maculatum",
                nameCommon = "Zebra Isopod",
                description = "Distinctive black and white striped species that prefers to congregate under pieces of cork bark.",
                temperatureMin = 20.0,
                temperatureMax = 26.0,
                humidityMin = 70.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Grammostola rosea",
                nameCommon = "Chilean Rose Hair Tarantula",
                description = "Known for its low maintenance and calm demeanor; goes through long periods of fasting and inactivity.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 40.0,
                humidityMax = 60.0,
                lightCycleH = 12
            ),
            Species(
                nameLatin = "Tliltocatl albopilosus",
                nameCommon = "Curly Hair Tarantula",
                description = "Distinctive for its unique 'wool-like' bristles; exceptionally calm and frequently stays out in the open.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 60.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Chromatopelma cyaneopubescens",
                nameCommon = "Green Bottle Blue Tarantula",
                description = "Heavy webber that creates intricate silk tunnels; famous for its vibrant orange abdomen and blue legs.",
                temperatureMin = 24.0,
                temperatureMax = 30.0,
                humidityMin = 40.0,
                humidityMax = 60.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Aphonopelma chalcodes",
                nameCommon = "Arizona Blonde Tarantula",
                description = "Slow-moving desert dweller with a high tolerance for heat; females are known to live for over 30 years.",
                temperatureMin = 24.0,
                temperatureMax = 32.0,
                humidityMin = 20.0,
                humidityMax = 40.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Cyriopagopus lividus",
                nameCommon = "Cobalt Blue Tarantula",
                description = "Shy but highly defensive fossorial species; its brilliant blue coloration is only visible under specific lighting.",
                temperatureMin = 24.0,
                temperatureMax = 28.0,
                humidityMin = 70.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Theraphosa blondi",
                nameCommon = "Goliath Birdeater",
                description = "The world's largest tarantula by mass; produces audible 'hissing' sounds through stridulation when threatened.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 70.0,
                humidityMax = 90.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Lasiodora parahybana",
                nameCommon = "Salmon Pink Birdeater",
                description = "Grows at an incredible rate and possesses a voracious feeding response; frequently kicks urticating hairs if disturbed.",
                temperatureMin = 24.0,
                temperatureMax = 28.0,
                humidityMin = 65.0,
                humidityMax = 75.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Pelinobius muticus",
                nameCommon = "King Baboon Tarantula",
                description = "Extremely slow-growing African species with thickened rear legs used for digging deep, permanent burrows.",
                temperatureMin = 24.0,
                temperatureMax = 28.0,
                humidityMin = 60.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Heterometrus silenus",
                nameCommon = "Asian Forest Scorpion",
                description = "Large, glossy black scorpion often confused with the Emperor Scorpion but features more slender pincers.",
                temperatureMin = 24.0,
                temperatureMax = 30.0,
                humidityMin = 75.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Heterometrus spinifer",
                nameCommon = "Giant Forest Scorpion",
                description = "Tropical predator that uses its massive pedipalps rather than its sting to dispatch prey; thrives in leaf litter.",
                temperatureMin = 24.0,
                temperatureMax = 28.0,
                humidityMin = 75.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Leiurus quinquestriatus",
                nameCommon = "Deathstalker Scorpion",
                description = "One of the most dangerous scorpions in the world; fast-moving and possesses a complex, potent neurotoxic venom.",
                temperatureMin = 26.0,
                temperatureMax = 35.0,
                humidityMin = 10.0,
                humidityMax = 25.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Paravaejovis spinigerus",
                nameCommon = "Stripe-Tailed Scorpion",
                description = "Small, hardy scorpion found under rocks; easily identified by the dark stripes on the dorsal side of its tail.",
                temperatureMin = 24.0,
                temperatureMax = 32.0,
                humidityMin = 20.0,
                humidityMax = 35.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Pandinus imperator",
                nameCommon = "Black Emperor Scorpion",
                description = "A classic pet species known for its heavy armor and fluorescent glow under ultraviolet light.",
                temperatureMin = 24.0,
                temperatureMax = 28.0,
                humidityMin = 75.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Phidippus audax",
                nameCommon = "Bold Jumping Spider",
                description = "Highly intelligent spider with excellent vision; known for performing complex 'dances' and interactive hunting.",
                temperatureMin = 20.0,
                temperatureMax = 28.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Hogna carolinensis",
                nameCommon = "Carolina Wolf Spider",
                description = "Active hunter that does not spin webs to catch prey, instead relying on speed and camouflage on the forest floor.",
                temperatureMin = 18.0,
                temperatureMax = 28.0,
                humidityMin = 40.0,
                humidityMax = 60.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Heteropoda venatoria",
                nameCommon = "Brown Huntsman Spider",
                description = "Renowned for its incredible speed and flattened body, allowing it to squeeze into tight crevices and wall corners.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 60.0,
                humidityMax = 75.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Dolomedes triton",
                nameCommon = "Six-Spotted Fishing Spider",
                description = "Capable of running across the surface of water and diving beneath to catch small fish or aquatic insects.",
                temperatureMin = 18.0,
                temperatureMax = 26.0,
                humidityMin = 70.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Araneus diadematus",
                nameCommon = "Cross Orb-Weaver",
                description = "Spins large, circular geometric webs daily; helps control garden pests and prefers outdoor-like ventilation.",
                temperatureMin = 15.0,
                temperatureMax = 25.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Gorgyrella spp.",
                nameCommon = "African Trapdoor Spider",
                description = "Lifts a perfectly camouflaged hinged lid to ambush prey; spends almost its entire life in a silk-lined burrow.",
                temperatureMin = 20.0,
                temperatureMax = 28.0,
                humidityMin = 60.0,
                humidityMax = 75.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Macrothele gigas",
                nameCommon = "Giant Funnel-Web Spider",
                description = "Asian funnel-web species that creates massive sheet webs; very fast and defensive but lacks the lethality of Australian cousins.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 70.0,
                humidityMax = 80.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Latrodectus geometricus",
                nameCommon = "Brown Widow Spider",
                description = "Recognizable by the orange hourglass mark and spiked egg sacs; less aggressive than the Black Widow.",
                temperatureMin = 22.0,
                temperatureMax = 30.0,
                humidityMin = 40.0,
                humidityMax = 60.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Steatoda grossa",
                nameCommon = "False Black Widow",
                description = "Frequently found in dark, damp areas of buildings; its bite is medically insignificant but resembles a bee sting.",
                temperatureMin = 15.0,
                temperatureMax = 26.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Scytodes thoracica",
                nameCommon = "Spitting Spider",
                description = "Slow-moving predator that fires a mixture of silk and venom to immobilize prey from a distance.",
                temperatureMin = 20.0,
                temperatureMax = 28.0,
                humidityMin = 50.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Pholcus phalangioides",
                nameCommon = "Long-bodied Cellar Spider",
                description = "Vibrates rapidly in its web when disturbed to become a blur; known for preying on much larger spiders.",
                temperatureMin = 15.0,
                temperatureMax = 28.0,
                humidityMin = 40.0,
                humidityMax = 70.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Phalangium opilio",
                nameCommon = "European Harvestman",
                description = "Often mistaken for a spider but lacks silk glands and venom; feeds on a variety of organic matter and small insects.",
                temperatureMin = 15.0,
                temperatureMax = 26.0,
                humidityMin = 50.0,
                humidityMax = 75.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Chelifer cancroides",
                nameCommon = "House Pseudoscorpion",
                description = "Teeny-tiny 'book scorpion' that hunts carpet beetle larvae and mites; hitches rides on flying insects to travel.",
                temperatureMin = 18.0,
                temperatureMax = 26.0,
                humidityMin = 70.0,
                humidityMax = 85.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Galeodes spp.",
                nameCommon = "Camel Spider",
                description = "Possesses massive chelicerae for its size; extremely fast desert runner that is neither a spider nor a scorpion.",
                temperatureMin = 24.0,
                temperatureMax = 35.0,
                humidityMin = 10.0,
                humidityMax = 25.0,
                lightCycleH = 12
            ),

            Species(
                nameLatin = "Charinus spp.",
                nameCommon = "Dwarf Tailless Whip Scorpion",
                description = "Cryptic and extremely flat; perfect for small bioactive setups where it hunts micro-prey like springtails.",
                temperatureMin = 22.0,
                temperatureMax = 28.0,
                humidityMin = 75.0,
                humidityMax = 90.0,
                lightCycleH = 12
            )

        )

        repository.insertAllSpecies(defaultSpecies)
    }
}
