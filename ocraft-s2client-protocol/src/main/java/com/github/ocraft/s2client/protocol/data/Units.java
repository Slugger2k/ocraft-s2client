package com.github.ocraft.s2client.protocol.data;

/*-
 * #%L
 * ocraft-s2client-protocol
 * %%
 * Copyright (C) 2017 - 2018 Ocraft Project
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.ocraft.s2client.protocol.data.Abilities.*;
import static java.util.Arrays.asList;

public enum Units implements UnitType {
    INVALID(0),

    // Terran
    TERRAN_ARMORY(29, CANCEL, HALT, CANCEL_LAST, RESEARCH_TERRAN_SHIP_WEAPONS, RESEARCH_TERRAN_VEHICLE_AND_SHIP_PLATING,
            RESEARCH_TERRAN_VEHICLE_WEAPONS),
    TERRAN_AUTO_TURRET(31, SMART, STOP, ATTACK),
    TERRAN_BANSHEE(55, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK, BEHAVIOR_CLOAK_ON, BEHAVIOR_CLOAK_OFF),
    TERRAN_BARRACKS(21, SMART, TRAIN_MARINE, TRAIN_REAPER, TRAIN_GHOST, TRAIN_MARAUDER, CANCEL, HALT, CANCEL_LAST,
            RALLY_UNITS, LIFT, BUILD_TECHLAB, BUILD_REACTOR),
    TERRAN_BARRACKS_FLYING(46, SMART, MOVE, PATROL, HOLD_POSITION, STOP, LAND, BUILD_TECHLAB, BUILD_REACTOR),
    TERRAN_BARRACKS_REACTOR(38, CANCEL),
    TERRAN_BARRACKS_TECHLAB(37, RESEARCH_STIMPACK, RESEARCH_COMBAT_SHIELD, RESEARCH_CONCUSSIVE_SHELLS, CANCEL,
            CANCEL_LAST),
    TERRAN_BATTLECRUISER(57, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_YAMATO_GUN, EFFECT_TACTICAL_JUMP, STOP, ATTACK),
    TERRAN_BUNKER(24, SMART, EFFECT_SALVAGE, CANCEL, HALT, UNLOAD_ALL, STOP, LOAD, RALLY_UNITS, ATTACK, EFFECT_STIM),
    TERRAN_COMMAND_CENTER(18, SMART, TRAIN_SCV, MORPH_PLANETARY_FORTRESS, MORPH_ORBITAL_COMMAND, CANCEL, HALT, LOAD_ALL,
            UNLOAD_ALL, CANCEL_LAST, LIFT, RALLY_WORKERS),
    TERRAN_COMMAND_CENTER_FLYING(36, SMART, MOVE, PATROL, HOLD_POSITION, LOAD_ALL, UNLOAD_ALL, STOP, LAND),
    TERRAN_CYCLONE(692, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_LOCK_ON, CANCEL, STOP, ATTACK),
    TERRAN_ENGINEERING_BAY(22, RESEARCH_HISEC_AUTOTRACKING, RESEARCH_TERRAN_STRUCTURE_ARMOR_UPGRADE,
            RESEARCH_NEOSTEEL_FRAME, CANCEL, HALT, CANCEL_LAST, RESEARCH_TERRAN_INFANTRY_ARMOR,
            RESEARCH_TERRAN_INFANTRY_WEAPONS),
    TERRAN_FACTORY(27, SMART, TRAIN_SIEGE_TANK, TRAIN_THOR, TRAIN_HELLION, TRAIN_HELLBAT, TRAIN_CYCLONE,
            TRAIN_WIDOWMINE, CANCEL, HALT, CANCEL_LAST, RALLY_UNITS, LIFT, BUILD_TECHLAB, BUILD_REACTOR),
    TERRAN_FACTORY_FLYING(43, SMART, MOVE, PATROL, HOLD_POSITION, STOP, LAND, BUILD_TECHLAB, BUILD_REACTOR),
    TERRAN_FACTORY_REACTOR(40, CANCEL),
    TERRAN_FACTORY_TECHLAB(39, RESEARCH_INFERNAL_PREIGNITER, RESEARCH_DRILLING_CLAWS, RESEARCH_MAGFIELD_LAUNCHERS,
            CANCEL, CANCEL_LAST),
    TERRAN_FUSION_CORE(30, RESEARCH_BATTLECRUISER_WEAPON_REFIT, CANCEL, HALT, CANCEL_LAST),
    TERRAN_GHOST(50, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_NUKE_CALL_DOWN, EFFECT_EMP, EFFECT_GHOST_SNIPE, CANCEL,
            STOP, ATTACK, BEHAVIOR_CLOAK_ON, BEHAVIOR_CLOAK_OFF, BEHAVIOR_HOLD_FIRE_ON, BEHAVIOR_HOLD_FIRE_OFF),
    TERRAN_GHOST_ACADEMY(26, BUILD_NUKE, RESEARCH_PERSONAL_CLOAKING, CANCEL, HALT, CANCEL_LAST),
    TERRAN_HELLION(53, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_HELLBAT, STOP, ATTACK),
    TERRAN_HELLION_TANK(484, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_HELLION, STOP, ATTACK),
    TERRAN_LIBERATOR(689, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_LIBERATOR_AG_MODE, STOP, ATTACK),
    TERRAN_LIBERATOR_AG(734, SMART, MORPH_LIBERATOR_AA_MODE, STOP, ATTACK),
    TERRAN_MARAUDER(51, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK, EFFECT_STIM),
    TERRAN_MARINE(48, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK, EFFECT_STIM),
    TERRAN_MEDIVAC(54, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_HEAL, EFFECT_MEDIVAC_IGNITE_AFTERBURNERS, STOP, LOAD,
            UNLOAD_ALL_AT, ATTACK),
    TERRAN_MISSILE_TURRET(23, SMART, CANCEL, HALT, STOP, ATTACK),
    TERRAN_MULE(268, SMART, MOVE, PATROL, HOLD_POSITION, STOP, HARVEST_GATHER, HARVEST_RETURN, ATTACK, EFFECT_REPAIR),
    TERRAN_ORBITAL_COMMAND(132, SMART, EFFECT_CALL_DOWN_MULE, EFFECT_SUPPLY_DROP, EFFECT_SCAN, TRAIN_SCV, CANCEL_LAST,
            LIFT, RALLY_WORKERS),
    TERRAN_ORBITAL_COMMAND_FLYING(134, SMART, MOVE, PATROL, HOLD_POSITION, STOP, LAND),
    TERRAN_PLANETARY_FORTRESS(130, SMART, TRAIN_SCV, LOAD_ALL, STOP, CANCEL_LAST, ATTACK, RALLY_WORKERS),
    TERRAN_RAVEN(56, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_POINT_DEFENSE_DRONE, EFFECT_HUNTER_SEEKER_MISSILE,
            EFFECT_AUTO_TURRET, STOP, ATTACK),
    TERRAN_REAPER(49, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_KD8CHARGE, STOP, ATTACK),
    TERRAN_REFINERY(20, CANCEL, HALT),
    TERRAN_SCV(45, SMART, MOVE, PATROL, HOLD_POSITION, BUILD_COMMAND_CENTER, BUILD_SUPPLY_DEPOT, BUILD_REFINERY,
            BUILD_BARRACKS, BUILD_ENGINEERING_BAY, BUILD_MISSILE_TURRET, BUILD_BUNKER, BUILD_SENSOR_TOWER,
            BUILD_GHOST_ACADEMY, BUILD_FACTORY, BUILD_STARPORT, BUILD_ARMORY, BUILD_FUSION_CORE, HALT, STOP,
            HARVEST_GATHER, HARVEST_RETURN, ATTACK, EFFECT_SPRAY, EFFECT_REPAIR),
    TERRAN_SENSOR_TOWER(25, CANCEL, HALT),
    TERRAN_SIEGE_TANK(33, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_SIEGE_MODE, STOP, ATTACK),
    TERRAN_SIEGE_TANK_SIEGED(32, SMART, MORPH_UNSIEGE, STOP, ATTACK),
    TERRAN_STARPORT(28, SMART, TRAIN_MEDIVAC, TRAIN_BANSHEE, TRAIN_RAVEN, TRAIN_BATTLECRUISER, TRAIN_VIKING_FIGHTER,
            TRAIN_LIBERATOR, CANCEL, HALT, CANCEL_LAST, RALLY_UNITS, LIFT, BUILD_TECHLAB, BUILD_REACTOR),
    TERRAN_STARPORT_FLYING(44, SMART, MOVE, PATROL, HOLD_POSITION, STOP, LAND, BUILD_TECHLAB, BUILD_REACTOR),
    TERRAN_STARPORT_REACTOR(42, CANCEL),
    TERRAN_STARPORT_TECHLAB(41, RESEARCH_BANSHEE_CLOAKING_FIELD, RESEARCH_RAVEN_CORVID_REACTOR,
            RESEARCH_BANSHEE_HYPER_FLIGHT_ROTORS, RESEARCH_RAVEN_RECALIBRATED_EXPLOSIVES,
            RESEARCH_HIGH_CAPACITY_FUEL_TANKS, RESEARCH_ADVANCED_BALLISTICS, CANCEL, CANCEL_LAST),
    TERRAN_SUPPLY_DEPOT(19, MORPH_SUPPLY_DEPOT_LOWER, CANCEL, HALT),
    TERRAN_SUPPLY_DEPOT_LOWERED(47, MORPH_SUPPLY_DEPOT_RAISE),
    TERRAN_THOR(52, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_THOR_HIGH_IMPACT_MODE, STOP, ATTACK),
    TERRAN_THOR_AP(691, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_THOR_EXPLOSIVE_MODE, CANCEL, STOP, ATTACK),
    TERRAN_VIKING_ASSAULT(34, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_VIKING_FIGHTER_MODE, STOP, ATTACK),
    TERRAN_VIKING_FIGHTER(35, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_VIKING_ASSAULT_MODE, STOP, ATTACK),
    TERRAN_WIDOWMINE(498, SMART, MOVE, PATROL, HOLD_POSITION, BURROW_DOWN, STOP, ATTACK),
    TERRAN_WIDOWMINE_BURROWED(500, SMART, EFFECT_WIDOWMINE_ATTACK, BURROW_UP),

    // Terran non-interactive
    TERRAN_KD8CHARGE(830),
    TERRAN_NUKE(58),
    TERRAN_POINT_DEFENSE_DRONE(11),
    TERRAN_REACTOR(6),
    TERRAN_TECHLAB(5),

    // Zerg
    ZERG_BANELING(9, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_EXPLODE, BEHAVIOR_BUILDING_ATTACK_ON,
            BEHAVIOR_BUILDING_ATTACK_OFF, BURROW_DOWN, STOP, ATTACK),
    ZERG_BANELING_BURROWED(115, EFFECT_EXPLODE, BURROW_UP),
    ZERG_BANELING_COCOON(8, SMART, CANCEL_LAST, RALLY_UNITS),
    ZERG_BANELING_NEST(96, RESEARCH_CENTRIFUGAL_HOOKS, CANCEL, CANCEL_LAST),
    ZERG_BROODLING(289, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    ZERG_BROODLORD(114, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    ZERG_BROODLORD_COCOON(113, SMART, MOVE, PATROL, HOLD_POSITION, CANCEL),
    ZERG_CHANGELING(12, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    ZERG_CHANGELING_MARINE(15, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    ZERG_CHANGELING_MARINE_SHIELD(14, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    ZERG_CHANGELING_ZEALOT(13, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    ZERG_CHANGELING_ZERGLING(17, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    ZERG_CHANGELING_ZERGLING_WINGS(16, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    ZERG_CORRUPTOR(112, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_BROODLORD, EFFECT_CAUSTIC_SPRAY, STOP, ATTACK),
    ZERG_CREEP_TUMOR(87, CANCEL),
    ZERG_CREEP_TUMOR_BURROWED(137, SMART, CANCEL, BUILD_CREEP_TUMOR),
    ZERG_CREEP_TUMOR_QUEEN(138, CANCEL),
    ZERG_DRONE(104, SMART, MOVE, PATROL, HOLD_POSITION, BUILD_HATCHERY, BUILD_EXTRACTOR, BUILD_SPAWNING_POOL,
            BUILD_EVOLUTION_CHAMBER, BUILD_HYDRALISK_DEN, BUILD_SPIRE, BUILD_ULTRALISK_CAVERN, BUILD_INFESTATION_PIT,
            BUILD_NYDUS_NETWORK, BUILD_BANELING_NEST, BUILD_ROACH_WARREN, BUILD_SPINE_CRAWLER, BUILD_SPORE_CRAWLER,
            BURROW_DOWN, STOP, HARVEST_GATHER, HARVEST_RETURN, ATTACK, EFFECT_SPRAY),
    ZERG_DRONE_BURROWED(116, BURROW_UP),
    ZERG_EGG(103, SMART, CANCEL_LAST, RALLY_UNITS),
    ZERG_EVOLUTION_CHAMBER(90, CANCEL, CANCEL_LAST, RESEARCH_ZERG_GROUND_ARMOR, RESEARCH_ZERG_MELEE_WEAPONS,
            RESEARCH_ZERG_MISSILE_WEAPONS),
    ZERG_EXTRACTOR(88, CANCEL),
    ZERG_GREATER_SPIRE(102, CANCEL_LAST, RESEARCH_ZERG_FLYER_ARMOR, RESEARCH_ZERG_FLYER_ATTACK),
    ZERG_HATCHERY(86, SMART, MORPH_LAIR, RESEARCH_PNEUMATIZED_CARAPACE, RESEARCH_BURROW, TRAIN_QUEEN, CANCEL,
            CANCEL_LAST, RALLY_UNITS, RALLY_WORKERS),
    ZERG_HIVE(101, SMART, RESEARCH_PNEUMATIZED_CARAPACE, RESEARCH_BURROW, TRAIN_QUEEN, CANCEL_LAST, RALLY_UNITS,
            RALLY_WORKERS),
    ZERG_HYDRALISK(107, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_LURKER, BURROW_DOWN, STOP, ATTACK),
    ZERG_HYDRALISK_BURROWED(117, BURROW_UP),
    ZERG_HYDRALISK_DEN(91, RESEARCH_GROOVED_SPINES, RESEARCH_MUSCULAR_AUGMENTS, MORPH_LURKER_DEN, CANCEL, CANCEL_LAST),
    ZERG_INFESTATION_PIT(94, RESEARCH_PATHOGEN_GLANDS, RESEARCH_NEURAL_PARASITE, CANCEL, CANCEL_LAST),
    ZERG_INFESTED_TERRANS_EGG(150, SMART, MOVE, PATROL, HOLD_POSITION),
    ZERG_INFESTOR(111, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_FUNGAL_GROWTH, EFFECT_INFESTED_TERRANS,
            EFFECT_NEURAL_PARASITE, CANCEL, BURROW_DOWN, STOP, ATTACK),
    ZERG_INFESTOR_BURROWED(127, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_FUNGAL_GROWTH, EFFECT_INFESTED_TERRANS,
            EFFECT_NEURAL_PARASITE, CANCEL, BURROW_UP, STOP, ATTACK),
    ZERG_INFESTOR_TERRAN(7, SMART, MOVE, PATROL, HOLD_POSITION, BURROW_DOWN, STOP, ATTACK),
    ZERG_LAIR(100, SMART, MORPH_HIVE, RESEARCH_PNEUMATIZED_CARAPACE, RESEARCH_BURROW, TRAIN_QUEEN, CANCEL, CANCEL_LAST,
            RALLY_UNITS, RALLY_WORKERS),
    ZERG_LARVA(151, TRAIN_DRONE, TRAIN_ZERGLING, TRAIN_OVERLORD, TRAIN_HYDRALISK, TRAIN_MUTALISK, TRAIN_ULTRALISK,
            TRAIN_ROACH, TRAIN_INFESTOR, TRAIN_CORRUPTOR, TRAIN_VIPER, TRAIN_SWARMHOST),
    ZERG_LOCUS_TMP(489, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    ZERG_LOCUS_TMP_FLYING(693, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_LOCUST_SWOOP, STOP, ATTACK),
    ZERG_LURKER_DEN_MP(504, RESEARCH_GROOVED_SPINES, RESEARCH_MUSCULAR_AUGMENTS, CANCEL_LAST),
    ZERG_LURKER_MP(502, SMART, MOVE, PATROL, HOLD_POSITION, BURROW_DOWN, STOP, ATTACK),
    ZERG_LURKER_MP_BURROWED(503, SMART, BURROW_UP, STOP, ATTACK, BEHAVIOR_HOLD_FIRE_ON, BEHAVIOR_HOLD_FIRE_OFF),
    ZERG_LURKER_MP_EGG(501, SMART, CANCEL, RALLY_UNITS),
    ZERG_MUTALISK(108, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    ZERG_NYDUS_CANAL(142, SMART, UNLOAD_ALL, STOP, LOAD, RALLY_UNITS),
    ZERG_NYDUS_NETWORK(95, SMART, BUILD_NYDUS_WORM, CANCEL, UNLOAD_ALL, STOP, LOAD, RALLY_UNITS),
    ZERG_OVERLORD(106, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_OVERSEER, BEHAVIOR_GENERATE_CREEP_ON,
            BEHAVIOR_GENERATE_CREEP_OFF, MORPH_OVERLORD_TRANSPORT, CANCEL, STOP, ATTACK),
    ZERG_OVERLORD_COCOON(128, SMART, MOVE, PATROL, HOLD_POSITION, CANCEL),
    ZERG_OVERLORD_TRANSPORT(893, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_OVERSEER, BEHAVIOR_GENERATE_CREEP_ON,
            BEHAVIOR_GENERATE_CREEP_OFF, STOP, LOAD, UNLOAD_ALL_AT, ATTACK),
    ZERG_OVERSEER(129, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_SPAWN_CHANGELING, EFFECT_CONTAMINATE, STOP, ATTACK),
    ZERG_QUEEN(126, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_INJECT_LARVA, EFFECT_TRANSFUSION, BURROW_DOWN, STOP,
            ATTACK, BUILD_CREEP_TUMOR),
    ZERG_QUEEN_BURROWED(125, BURROW_UP),
    ZERG_RAVAGER(688, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_CORROSIVE_BILE, BURROW_DOWN, STOP, ATTACK),
    ZERG_RAVAGER_COCOON(687, SMART, CANCEL, RALLY_UNITS),
    ZERG_ROACH(110, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_RAVAGER, BURROW_DOWN, STOP, ATTACK),
    ZERG_ROACH_BURROWED(118, SMART, MOVE, PATROL, HOLD_POSITION, BURROW_UP, STOP, ATTACK),
    ZERG_ROACH_WARREN(97, RESEARCH_GLIAL_REGENERATION, RESEARCH_TUNNELING_CLAWS, CANCEL, CANCEL_LAST),
    ZERG_SPAWNING_POOL(89, RESEARCH_ZERGLING_ADRENAL_GLANDS, RESEARCH_ZERGLING_METABOLIC_BOOST, CANCEL, CANCEL_LAST),
    ZERG_SPINE_CRAWLER(98, SMART, CANCEL, STOP, ATTACK, MORPH_UPROOT),
    ZERG_SPINE_CRAWLER_UPROOTED(139, SMART, MOVE, PATROL, HOLD_POSITION, CANCEL, STOP, ATTACK, MORPH_ROOT),
    ZERG_SPIRE(92, MORPH_GREATER_SPIRE, CANCEL, CANCEL_LAST, RESEARCH_ZERG_FLYER_ARMOR, RESEARCH_ZERG_FLYER_ATTACK),
    ZERG_SPORE_CRAWLER(99, SMART, CANCEL, STOP, ATTACK, MORPH_UPROOT),
    ZERG_SPORE_CRAWLER_UPROOTED(140, SMART, MOVE, PATROL, HOLD_POSITION, CANCEL, STOP, ATTACK, MORPH_ROOT),
    ZERG_SWARM_HOST_BURROWED_MP(493, SMART, EFFECT_SPAWN_LOCUSTS, BURROW_UP),
    ZERG_SWARM_HOST_MP(494, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_SPAWN_LOCUSTS, BURROW_DOWN, STOP, ATTACK),
    ZERG_TRANSPORT_OVERLORD_COCOON(892, SMART, MOVE, PATROL, HOLD_POSITION, CANCEL),
    ZERG_ULTRALISK(109, SMART, MOVE, PATROL, HOLD_POSITION, BURROW_DOWN, STOP, ATTACK),
    ZERG_ULTRALISK_CAVERN(93, RESEARCH_CHITINOUS_PLATING, CANCEL, CANCEL_LAST),
    ZERG_VIPER(499, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_BLINDING_CLOUD, EFFECT_ABDUCT, EFFECT_VIPER_CONSUME,
            EFFECT_PARASITIC_BOMB, STOP, ATTACK),
    ZERG_ZERGLING(105, SMART, MOVE, PATROL, HOLD_POSITION, TRAIN_BANELING, BURROW_DOWN, STOP, ATTACK),
    ZERG_ZERGLING_BURROWED(119, BURROW_UP),

    // Zerg non-interactive
    ZERG_PARASITIC_BOMB_DUMMY(824),

    // Protoss
    PROTOSS_ADEPT(311, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_ADEPT_PHASE_SHIFT, CANCEL, STOP, RALLY_UNITS, ATTACK),
    PROTOSS_ADEPT_PHASE_SHIFT(801, SMART, MOVE, PATROL, HOLD_POSITION, CANCEL, STOP, ATTACK),
    PROTOSS_ARCHON(141, SMART, MOVE, PATROL, HOLD_POSITION, STOP, RALLY_UNITS, ATTACK),
    PROTOSS_ASSIMILATOR(61, CANCEL),
    PROTOSS_CARRIER(79, SMART, MOVE, PATROL, HOLD_POSITION, BUILD_INTERCEPTORS, STOP, CANCEL_LAST, ATTACK),
    PROTOSS_COLOSSUS(4, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    PROTOSS_CYBERNETICS_CORE(72, RESEARCH_WARP_GATE, CANCEL, CANCEL_LAST, RESEARCH_PROTOSS_AIR_ARMOR,
            RESEARCH_PROTOSS_AIR_WEAPONS),
    PROTOSS_DARK_SHRINE(69, RESEARCH_SHADOW_STRIKE, CANCEL, CANCEL_LAST),
    PROTOSS_DARK_TEMPLAR(76, SMART, MOVE, PATROL, HOLD_POSITION, STOP, RALLY_UNITS, ATTACK, EFFECT_BLINK),
    PROTOSS_DISRUPTOR(694, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_PURIFICATION_NOVA, STOP, ATTACK),
    PROTOSS_DISRUPTOR_PHASED(733, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    PROTOSS_FLEET_BEACON(64, RESEARCH_INTERCEPTOR_GRAVITON_CATAPULT, RESEARCH_PHOENIX_ANION_PULSE_CRYSTALS, CANCEL,
            CANCEL_LAST),
    PROTOSS_FORGE(63, CANCEL, CANCEL_LAST, RESEARCH_PROTOSS_GROUND_ARMOR, RESEARCH_PROTOSS_GROUND_WEAPONS,
            RESEARCH_PROTOSS_SHIELDS),
    PROTOSS_GATEWAY(62, SMART, TRAIN_ZEALOT, TRAIN_STALKER, TRAIN_HIGH_TEMPLAR, TRAIN_DARK_TEMPLAR, TRAIN_SENTRY,
            TRAIN_ADEPT, MORPH_WARP_GATE, CANCEL, CANCEL_LAST, RALLY_UNITS),
    PROTOSS_HIGH_TEMPLAR(75, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_FEEDBACK, EFFECT_PSI_STORM, STOP, RALLY_UNITS,
            ATTACK),
    PROTOSS_IMMORTAL(83, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_IMMORTAL_BARRIER, STOP, ATTACK),
    PROTOSS_INTERCEPTOR(85, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    PROTOSS_MOTHERSHIP(10, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_PHOTON_OVERCHARGE, EFFECT_TIME_WARP, STOP, ATTACK,
            EFFECT_MASS_RECALL),
    PROTOSS_MOTHER_SHIP_CORE(488, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_MOTHERSHIP, EFFECT_PHOTON_OVERCHARGE,
            EFFECT_TIME_WARP, CANCEL, STOP, ATTACK, EFFECT_MASS_RECALL),
    PROTOSS_NEXUS(59, SMART, EFFECT_CHRONO_BOOST, TRAIN_PROBE, TRAIN_MOTHERSHIP_CORE, CANCEL, CANCEL_LAST,
            RALLY_WORKERS),
    PROTOSS_OBSERVER(82, SMART, MOVE, PATROL, HOLD_POSITION, STOP, ATTACK),
    PROTOSS_ORACLE(495, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_ORACLE_REVELATION, BEHAVIOR_PULSAR_BEAM_ON,
            BEHAVIOR_PULSAR_BEAM_OFF, BUILD_STASIS_TRAP, CANCEL, STOP, ATTACK),
    PROTOSS_ORACLE_STASIS_TRAP(732, CANCEL),
    PROTOSS_PHOENIX(78, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_GRAVITON_BEAM, CANCEL, STOP, ATTACK),
    PROTOSS_PHOTON_CANNON(66, SMART, CANCEL, STOP, ATTACK),
    PROTOSS_PROBE(84, SMART, MOVE, PATROL, HOLD_POSITION, BUILD_NEXUS, BUILD_PYLON, BUILD_ASSIMILATOR, BUILD_GATEWAY,
            BUILD_FORGE, BUILD_FLEET_BEACON, BUILD_TWILIGHT_COUNCIL, BUILD_PHOTON_CANNON, BUILD_STARGATE,
            BUILD_TEMPLAR_ARCHIVE, BUILD_DARK_SHRINE, BUILD_ROBOTICS_BAY, BUILD_ROBOTICS_FACILITY,
            BUILD_CYBERNETICS_CORE, STOP, HARVEST_GATHER, HARVEST_RETURN, ATTACK, EFFECT_SPRAY),
    PROTOSS_PYLON(60, CANCEL),
    PROTOSS_PYLON_OVERCHARGED(894, SMART, STOP, ATTACK),
    PROTOSS_ROBOTICS_BAY(70, RESEARCH_GRAVITIC_BOOSTER, RESEARCH_GRAVITIC_DRIVE, RESEARCH_EXTENDED_THERMAL_LANCE,
            CANCEL, CANCEL_LAST),
    PROTOSS_ROBOTICS_FACILITY(71, SMART, TRAIN_WARP_PRISM, TRAIN_OBSERVER, TRAIN_COLOSSUS, TRAIN_IMMORTAL,
            TRAIN_DISRUPTOR, CANCEL, CANCEL_LAST, RALLY_UNITS),
    PROTOSS_SENTRY(77, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_GUARDIAN_SHIELD, HALLUCINATION_ARCHON,
            HALLUCINATION_COLOSSUS, HALLUCINATION_HIGH_TEMPLAR, HALLUCINATION_IMMORTAL, HALLUCINATION_PHOENIX,
            HALLUCINATION_PROBE, HALLUCINATION_STALKER, HALLUCINATION_VOIDRAY, HALLUCINATION_WARP_PRISM,
            HALLUCINATION_ZEALOT, EFFECT_FORCE_FIELD, HALLUCINATION_ORACLE, HALLUCINATION_DISRUPTOR,
            HALLUCINATION_ADEPT, STOP, RALLY_UNITS, ATTACK),
    PROTOSS_STALKER(74, SMART, MOVE, PATROL, HOLD_POSITION, STOP, RALLY_UNITS, ATTACK, EFFECT_BLINK),
    PROTOSS_STARGATE(67, SMART, TRAIN_PHOENIX, TRAIN_CARRIER, TRAIN_VOIDRAY, TRAIN_ORACLE, TRAIN_TEMPEST, CANCEL,
            CANCEL_LAST, RALLY_UNITS),
    PROTOSS_TEMPEST(496, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_TEMPEST_DISRUPTION_BLAST, CANCEL, STOP, ATTACK),
    PROTOSS_TEMPLAR_ARCHIVE(68, RESEARCH_PSI_STORM, CANCEL, CANCEL_LAST),
    PROTOSS_TWILIGHT_COUNCIL(65, RESEARCH_CHARGE, RESEARCH_BLINK, RESEARCH_ADEPT_RESONATING_GLAIVES, CANCEL,
            CANCEL_LAST),
    PROTOSS_VOIDRAY(80, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_VOIDRAY_PRISMATIC_ALIGNMENT, STOP, ATTACK),
    PROTOSS_WARP_GATE(133, SMART, TRAIN_WARP_ZEALOT, TRAIN_WARP_STALKER, TRAIN_WARP_HIGH_TEMPLAR,
            TRAIN_WARP_DARK_TEMPLAR, TRAIN_WARP_SENTRY, TRAIN_WARP_ADEPT, MORPH_GATEWAY),
    PROTOSS_WARP_PRISM(81, SMART, MOVE, PATROL, HOLD_POSITION, MORPH_WARP_PRISM_PHASING_MODE, STOP, LOAD, UNLOAD_ALL_AT,
            ATTACK),
    PROTOSS_WARP_PRISM_PHASING(136, SMART, MORPH_WARP_PRISM_TRANSPORT_MODE, STOP, LOAD, UNLOAD_ALL_AT),
    PROTOSS_ZEALOT(73, SMART, MOVE, PATROL, HOLD_POSITION, EFFECT_CHARGE, STOP, RALLY_UNITS, ATTACK),

    // Protoss non-interactive

    // Neutral
    NEUTRAL_COLLAPSIBLE_ROCK_TOWER_DEBRIS(490),
    NEUTRAL_COLLAPSIBLE_ROCK_TOWER_DIAGONAL(588),
    NEUTRAL_COLLAPSIBLE_ROCK_TOWER_PUSH_UNIT(561),
    NEUTRAL_COLLAPSIBLE_TERRAN_TOWER_DEBRIS(485),
    NEUTRAL_COLLAPSIBLE_TERRAN_TOWER_DIAGONAL(589),
    NEUTRAL_COLLAPSIBLE_TERRAN_TOWER_PUSH_UNIT(562),
    NEUTRAL_COLLAPSIBLE_TERRAN_TOWER_PUSH_UNIT_RAMP_LEFT(559),
    NEUTRAL_COLLAPSIBLE_TERRAN_TOWER_PUSH_UNIT_RAMP_RIGHT(560),
    NEUTRAL_COLLAPSIBLE_TERRAN_TOWER_RAMP_LEFT(590),
    NEUTRAL_COLLAPSIBLE_TERRAN_TOWER_RAMP_RIGHT(591),
    NEUTRAL_DEBRIS_RAMP_LEFT(486),
    NEUTRAL_DEBRIS_RAMP_RIGHT(487),
    NEUTRAL_DESTRUCTIBLE_DEBRIS6X6(365),
    NEUTRAL_DESTRUCTIBLE_DEBRIS_RAMP_DIAGONAL_HUGE_BL_UR(377),
    NEUTRAL_DESTRUCTIBLE_DEBRIS_RAMP_DIAGONAL_HUGE_UL_BR(376),
    NEUTRAL_DESTRUCTIBLE_ROCK6X6(371),
    NEUTRAL_DESTRUCTIBLE_ROCK_EX1_DIAGONAL_HUGE_BL_UR(641),
    NEUTRAL_FORCE_FIELD(135),
    NEUTRAL_KARAK_FEMALE(324),
    NEUTRAL_LAB_MINERAL_FIELD(665),
    NEUTRAL_LAB_MINERAL_FIELD750(666),
    NEUTRAL_MINERAL_FIELD(341),
    NEUTRAL_MINERAL_FIELD750(483),
    NEUTRAL_PROTOSS_VESPANE_GEYSER(608),
    NEUTRAL_RICH_MINERAL_FIELD(146),
    NEUTRAL_RICH_MINERAL_FIELD750(147),
    NEUTRAL_SCANTIPEDE(335),
    NEUTRAL_SPACE_PLATFORM_GEYSER(343),
    NEUTRAL_UNBUILDABLE_BRICKS_DESTRUCTIBLE(473),
    NEUTRAL_UNBUILDABLE_PLATES_DESTRUCTIBLE(474),
    NEUTRAL_UTILITY_BOT(330),
    NEUTRAL_VESPANE_GEYSER(342),
    NEUTRAL_XELNAGA_TOWER(149),
    NEUTRAL_BATTLE_STATION_MINERAL_FIELD(886),
    NEUTRAL_BATTLE_STATION_MINERAL_FIELD750(887),
    NEUTRAL_PURIFIER_MINERAL_FIELD(884),
    NEUTRAL_PURIFIER_MINERAL_FIELD750(885),
    NEUTRAL_PURIFIER_RICH_MINERAL_FIELD(796),
    NEUTRAL_PURIFIER_RICH_MINERAL_FIELD750(797),
    NEUTRAL_PURIFIER_VESPENE_GEYSER(880),
    NEUTRAL_RICH_VESPENE_GEYSER(344),
    NEUTRAL_SHAKURAS_VESPENE_GEYSER(881);

    public static final class Other implements UnitType {

        private static final long serialVersionUID = -3455759091613077278L;

        private static final Map<Integer, Other> INSTANCES = new ConcurrentHashMap<>();

        private final int unitTypeId;

        private Other(int unitTypeId) {
            this.unitTypeId = unitTypeId;
        }

        public static Other of(int unitTypeId) {
            INSTANCES.computeIfAbsent(unitTypeId, Other::new);
            return INSTANCES.get(unitTypeId);
        }

        @Override
        public Set<Abilities> getAbilities() {
            return EnumSet.noneOf(Abilities.class);
        }

        @Override
        public Integer toSc2Api() {
            return getUnitTypeId();
        }

        @Override
        public int getUnitTypeId() {
            return unitTypeId;
        }

        @Override
        public String toString() {
            return "UNIT_TYPE_" + unitTypeId;
        }
    }

    private static Map<Integer, UnitType> unitTypeIdMap = new HashMap<>();

    static {
        EnumSet.allOf(Units.class).forEach(unitType -> unitTypeIdMap.put(unitType.getUnitTypeId(), unitType));
    }

    private int unitTypeId;
    private final EnumSet<Abilities> abilities;

    Units(int unitTypeId, Abilities... abilities) {
        this.unitTypeId = unitTypeId;
        this.abilities = abilities.length > 0 ? EnumSet.copyOf(asList(abilities)) : EnumSet.noneOf(Abilities.class);
    }

    public static UnitType from(int sc2ApiUnitTypeId) {
        return Optional.ofNullable(unitTypeIdMap.get(sc2ApiUnitTypeId)).orElse(Other.of(sc2ApiUnitTypeId));
    }

    @Override
    public Integer toSc2Api() {
        return getUnitTypeId();
    }

    @Override
    public int getUnitTypeId() {
        return unitTypeId;
    }

    @Override
    public Set<Abilities> getAbilities() {
        return abilities;
    }

}