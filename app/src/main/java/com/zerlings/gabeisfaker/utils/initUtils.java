package com.zerlings.gabeisfaker.utils;

import android.content.Context;

import com.zerlings.gabeisfaker.MyApplication;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.db.Case;
import com.zerlings.gabeisfaker.db.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 令子 on 2017/2/14.
 */

public class InitUtils {

    public static List<Case> initCase(){

        List<Case> caseList = new ArrayList<>();

        Context context = MyApplication.getContext();

        Case gloveCase = new Case(context.getResources().getString(R.string.glove_case),R.drawable.glove_case);
        caseList.add(gloveCase);
        Case gamma2Case = new Case(context.getResources().getString(R.string.gamma_2_case),R.drawable.gamma_2_case);
        caseList.add(gamma2Case);
        Case gammaCase = new Case(context.getResources().getString(R.string.gamma_case),R.drawable.gamma_case);
        caseList.add(gammaCase);
        Case csgoWeaponCase = new Case(context.getResources().getString(R.string.csgo_weapon_case),R.drawable.csgo_weapon_case);
        caseList.add(csgoWeaponCase);
        Case csgoWeaponCase2 = new Case(context.getResources().getString(R.string.csgo_weapon_case_2),R.drawable.csgo_weapon_case_2);
        caseList.add(csgoWeaponCase2);
        Case csgoWeaponCase3 = new Case(context.getResources().getString(R.string.csgo_weapon_case_3),R.drawable.csgo_weapon_case_3);
        caseList.add(csgoWeaponCase3);
        Case chromaCase = new Case(context.getResources().getString(R.string.chroma_case),R.drawable.chroma_case);
        caseList.add(chromaCase);
        Case chromaCase2 = new Case(context.getResources().getString(R.string.chroma_case_2),R.drawable.chroma_2_case);
        caseList.add(chromaCase2);
        Case chromaCase3 = new Case(context.getResources().getString(R.string.chroma_case_3),R.drawable.chroma_3_case);
        caseList.add(chromaCase3);
        Case eSports2013Case = new Case(context.getResources().getString(R.string.esports_2013_case),R.drawable.esports_2013_case);
        caseList.add(eSports2013Case);
        /*Case eSports2013WinterCase = new Case(context.getResources().getString(R.string.esports_2013_winter_case),R.drawable.esports_2013_winter_case);
        caseList.add(eSports2013WinterCase);
        Case eSports2014SummerCase = new Case(context.getResources().getString(R.string.esports_2014_summer_case),R.drawable.esports_2014_summer_case);
        caseList.add(eSports2014SummerCase);
        Case falchionCase = new Case(context.getResources().getString(R.string.falchion_case),R.drawable.falchion_case);
        caseList.add(falchionCase);
        Case huntsmanWeaponCase = new Case(context.getResources().getString(R.string.huntsman_weapon_case),R.drawable.huntsman_weapon_case);
        caseList.add(huntsmanWeaponCase);
        Case operationBravoCase = new Case(context.getResources().getString(R.string.operation_bravo_case),R.drawable.operation_bravo_case);
        caseList.add(operationBravoCase);
        Case operationBreakoutWeaponCase = new Case(context.getResources().getString(R.string.operation_breakout_weapon_case),R.drawable.operation_breakout_case);
        caseList.add(operationBreakoutWeaponCase);
        Case operationPhoenixCase = new Case(context.getResources().getString(R.string.operation_phoenix_weapon_case),R.drawable.operation_phoenix_weapon_case);
        caseList.add(operationPhoenixCase);
        Case operationVanguardCase = new Case(context.getResources().getString(R.string.operation_vanguard_weapon_case),R.drawable.operation_vanguard_weapon_case);
        caseList.add(operationVanguardCase);
        Case operationWildfireCase = new Case(context.getResources().getString(R.string.operation_wildfire_case),R.drawable.operation_wildfire_case);
        caseList.add(operationWildfireCase);
        Case revolverCase = new Case(context.getResources().getString(R.string.revolver_case),R.drawable.revolver_case);
        caseList.add(revolverCase);
        Case shadowCase = new Case(context.getResources().getString(R.string.shadow_case),R.drawable.shadow_case);
        caseList.add(shadowCase);
        Case winterOffensiveWeaponCase = new Case(context.getResources().getString(R.string.winter_offensive_weapon_case),R.drawable.winter_offensive_weapon_case);
        caseList.add(winterOffensiveWeaponCase);*/

        return caseList;
    }

    public static List<Weapon> initWeapon(int caseImgId){

        List<Weapon> weaponList = new ArrayList<>();

        Context context = MyApplication.getContext();

        switch (caseImgId){
            case R.drawable.glove_case:
                Weapon weapon1 = new Weapon("M4A4",context.getResources().getString(R.string.buzz_kill),R.drawable.m4a4_buzz_kill,6,0,70,R.drawable.glove_case);
                weaponList.add(weapon1);
                Weapon weapon2 = new Weapon("SSG 08",context.getResources().getString(R.string.dragonfire),R.drawable.ssg_08_dragonfire,6,0,50,R.drawable.glove_case);
                weaponList.add(weapon2);
                Weapon weapon3 = new Weapon("Sawed-Off",context.getResources().getString(R.string.wasteland_princess),R.drawable.sawed_off_wasteland_princess,5,0,70,R.drawable.glove_case);
                weaponList.add(weapon3);
                Weapon weapon4 = new Weapon("FAMAS",context.getResources().getString(R.string.mecha_industries),R.drawable.famas_mecha_industries,5,0,50,R.drawable.glove_case);
                weaponList.add(weapon4);
                Weapon weapon5 = new Weapon("P90",context.getResources().getString(R.string.shallow_grave),R.drawable.p90_shallow_grave,5,0,75,R.drawable.glove_case);
                weaponList.add(weapon5);
                Weapon weapon6 = new Weapon("Nova",context.getResources().getString(R.string.gila),R.drawable.nova_gila,4,0,30,R.drawable.glove_case);
                weaponList.add(weapon6);
                Weapon weapon7 = new Weapon("M4A1-S",context.getResources().getString(R.string.flash_back),R.drawable.m4a1_s_flash_back,4,0,100,R.drawable.glove_case);
                weaponList.add(weapon7);
                Weapon weapon8 = new Weapon("Dual Berettas",context.getResources().getString(R.string.royal_consorts),R.drawable.dual_berettas_royal_consorts,4,0,100,R.drawable.glove_case);
                weaponList.add(weapon8);
                Weapon weapon9 = new Weapon("G3SG1",context.getResources().getString(R.string.stinger),R.drawable.g3sg1_stinger,4,0,70,R.drawable.glove_case);
                weaponList.add(weapon9);
                Weapon weapon10 = new Weapon("USP-S",context.getResources().getString(R.string.cyrex),R.drawable.usp_s_cyrex,4,0,55,R.drawable.glove_case);
                weaponList.add(weapon10);
                Weapon weapon11 = new Weapon("Gali AR",context.getResources().getString(R.string.black_sand),R.drawable.gali_ar_black_sand,3,0,100,R.drawable.glove_case);
                weaponList.add(weapon11);
                Weapon weapon12 = new Weapon("MAG-7",context.getResources().getString(R.string.sonar),R.drawable.mag_7_sonar,3,0,45,R.drawable.glove_case);
                weaponList.add(weapon12);
                Weapon weapon13 = new Weapon("MP9",context.getResources().getString(R.string.sand_scale),R.drawable.mp9_sand_scale,3,0,45,R.drawable.glove_case);
                weaponList.add(weapon13);
                Weapon weapon14 = new Weapon("P2000",context.getResources().getString(R.string.turf),R.drawable.p2000_turf,3,0,100,R.drawable.glove_case);
                weaponList.add(weapon14);
                Weapon weapon15 = new Weapon("MP7",context.getResources().getString(R.string.cirrus),R.drawable.mp7_cirrus,3,0,75,R.drawable.glove_case);
                weaponList.add(weapon15);
                Weapon weapon16 = new Weapon("Glock-18",context.getResources().getString(R.string.ironwork),R.drawable.glock_18_ironwork,3,0,100,R.drawable.glove_case);
                weaponList.add(weapon16);
                Weapon weapon17 = new Weapon("CZ75-Auto",context.getResources().getString(R.string.polymer),R.drawable.cz75_auto_polymer,3,0,50,R.drawable.glove_case);
                weaponList.add(weapon17);
                break;

            case R.drawable.gamma_2_case:
                Weapon weapon18 = new Weapon("AK-47",context.getResources().getString(R.string.neon_revolution),R.drawable.ak_47_neon_revolution,6,0,80,R.drawable.gamma_2_case);
                weaponList.add(weapon18);
                Weapon weapon19 = new Weapon("FAMAS",context.getResources().getString(R.string.roll_cage),R.drawable.famas_roll_cage,6,0,100,R.drawable.gamma_2_case);
                weaponList.add(weapon19);
                Weapon weapon20 = new Weapon("Tec-9",context.getResources().getString(R.string.fuel_injector),R.drawable.tec_9_fuel_injector,5,0,100,R.drawable.gamma_2_case);
                weaponList.add(weapon20);
                Weapon weapon21 = new Weapon("AUG",context.getResources().getString(R.string.syd_mead),R.drawable.aug_syd_mead,5,0,80,R.drawable.gamma_2_case);
                weaponList.add(weapon21);
                Weapon weapon22 = new Weapon("MP9",context.getResources().getString(R.string.airlock),R.drawable.mp9_airlock,5,0,100,R.drawable.gamma_2_case);
                weaponList.add(weapon22);
                Weapon weapon23 = new Weapon("MAG-7",context.getResources().getString(R.string.petroglyph),R.drawable.mag_7_petroglyph,4,0,44,R.drawable.gamma_2_case);
                weaponList.add(weapon23);
                Weapon weapon24 = new Weapon("Desert Eagle",context.getResources().getString(R.string.directive),R.drawable.desert_eagle_diretive,4,6,100,R.drawable.gamma_2_case);
                weaponList.add(weapon24);
                Weapon weapon25 = new Weapon("SCAR-20",context.getResources().getString(R.string.powercore),R.drawable.scar_20_powercore,4,0,100,R.drawable.gamma_2_case);
                weaponList.add(weapon25);
                Weapon weapon26 = new Weapon("SG 553",context.getResources().getString(R.string.triarch),R.drawable.sg_553_triarch,4,0,100,R.drawable.gamma_2_case);
                weaponList.add(weapon26);
                Weapon weapon27 = new Weapon("Glock-18",context.getResources().getString(R.string.weasel),R.drawable.glock_18_weasel,4,0,100,R.drawable.gamma_2_case);
                weaponList.add(weapon27);
                Weapon weapon28 = new Weapon("CZ75-Auto",context.getResources().getString(R.string.imprint),R.drawable.cz75_auto_imprint,3,0,100,R.drawable.gamma_2_case);
                weaponList.add(weapon28);
                Weapon weapon29 = new Weapon("P90",context.getResources().getString(R.string.grim),R.drawable.p90_grim,3,0,80,R.drawable.gamma_2_case);
                weaponList.add(weapon29);
                Weapon weapon30 = new Weapon("G3SG1",context.getResources().getString(R.string.ventilator),R.drawable.g3sg1_ventilator,3,0,45,R.drawable.gamma_2_case);
                weaponList.add(weapon30);
                Weapon weapon31 = new Weapon("UMP-45",context.getResources().getString(R.string.briefing),R.drawable.ump_45_briefing,3,0,100,R.drawable.gamma_2_case);
                weaponList.add(weapon31);
                Weapon weapon32 = new Weapon("XM1014",context.getResources().getString(R.string.slipstream),R.drawable.xm1014_slipstream,3,0,50,R.drawable.gamma_2_case);
                weaponList.add(weapon32);
                Weapon weapon33 = new Weapon("Five-SeveN",context.getResources().getString(R.string.scumbria),R.drawable.five_seven_scumbria,3,0,100,R.drawable.gamma_2_case);
                weaponList.add(weapon33);
                Weapon weapon34 = new Weapon("Negev",context.getResources().getString(R.string.dazzle),R.drawable.negev_dazzle,3,10,65,R.drawable.gamma_2_case);
                weaponList.add(weapon34);
                break;

            case R.drawable.gamma_case:
                Weapon weapon35 = new Weapon("M4A1-S",context.getResources().getString(R.string.mecha_industries),R.drawable.m4a1_s_mecha_industries,6,0,80,R.drawable.gamma_case);
                weaponList.add(weapon35);
                Weapon weapon36 = new Weapon("Glock-18",context.getResources().getString(R.string.wasteland_rebel),R.drawable.glock_18_wasteland_rebel,6,0,54,R.drawable.gamma_case);
                weaponList.add(weapon36);
                Weapon weapon37 = new Weapon("M4A4",context.getResources().getString(R.string.desolate_space),R.drawable.m4a4_desolate_space,5,0,100,R.drawable.gamma_case);
                weaponList.add(weapon37);
                Weapon weapon38 = new Weapon("P2000",context.getResources().getString(R.string.imperial_dragon),R.drawable.p2000_imperial_dragon,5,0,63,R.drawable.gamma_case);
                weaponList.add(weapon38);
                Weapon weapon39 = new Weapon("SCAR-20",context.getResources().getString(R.string.bloodsport),R.drawable.scar_20_bloodsport,5,0,45,R.drawable.gamma_case);
                weaponList.add(weapon39);
                Weapon weapon40 = new Weapon("AUG",context.getResources().getString(R.string.aristocrat),R.drawable.aug_aristocrat,4,0,66,R.drawable.gamma_case);
                weaponList.add(weapon40);
                Weapon weapon41 = new Weapon("Sawed-Off",context.getResources().getString(R.string.limelight),R.drawable.sawed_off_limelight,4,0,100,R.drawable.gamma_case);
                weaponList.add(weapon41);
                Weapon weapon42 = new Weapon("P90",context.getResources().getString(R.string.chopper),R.drawable.p90_chopper,4,0,60,R.drawable.gamma_case);
                weaponList.add(weapon42);
                Weapon weapon43 = new Weapon("AWP",context.getResources().getString(R.string.phobos),R.drawable.awp_phobos,4,0,40,R.drawable.gamma_case);
                weaponList.add(weapon43);
                Weapon weapon44 = new Weapon("R8 Revolver",context.getResources().getString(R.string.reboot),R.drawable.r8_revolver_reboot,4,0,100,R.drawable.gamma_case);
                weaponList.add(weapon44);
                Weapon weapon45 = new Weapon("Tec-9",context.getResources().getString(R.string.ice_cap),R.drawable.tec_9_ice_cap,3,0,50,R.drawable.gamma_case);
                weaponList.add(weapon45);
                Weapon weapon46 = new Weapon("MAC-10",context.getResources().getString(R.string.carnivore),R.drawable.mac_10_carnivore,3,5,100,R.drawable.gamma_case);
                weaponList.add(weapon46);
                Weapon weapon47 = new Weapon("P250",context.getResources().getString(R.string.iron_clad),R.drawable.p250_iron_clad,3,5,80,R.drawable.gamma_case);
                weaponList.add(weapon47);
                Weapon weapon48 = new Weapon("Five-SeveN",context.getResources().getString(R.string.violent_daimyo),R.drawable.five_seven_violent_daimyo,3,0,70,R.drawable.gamma_case);
                weaponList.add(weapon48);
                Weapon weapon49 = new Weapon("PP-Bizon",context.getResources().getString(R.string.harvester),R.drawable.pp_bizon_harvester,3,0,100,R.drawable.gamma_case);
                weaponList.add(weapon49);
                Weapon weapon50 = new Weapon("Nova",context.getResources().getString(R.string.exo),R.drawable.nova_exo,3,0,50,R.drawable.gamma_case);
                weaponList.add(weapon50);
                Weapon weapon51 = new Weapon("SG 553",context.getResources().getString(R.string.aerial),R.drawable.sg_553_aerial,3,0,60,R.drawable.gamma_case);
                weaponList.add(weapon51);
                break;

            case R.drawable.csgo_weapon_case:
                Weapon weapon52 = new Weapon("AWP",context.getResources().getString(R.string.lightning_strike),R.drawable.awp_lightning_strike,6,0,8,R.drawable.csgo_weapon_case);
                weaponList.add(weapon52);
                Weapon weapon53 = new Weapon("Desert Eagle",context.getResources().getString(R.string.hypnotic),R.drawable.desert_eagle_hypnotic,5,0,8,R.drawable.csgo_weapon_case);
                weaponList.add(weapon53);
                Weapon weapon54 = new Weapon("AK-47",context.getResources().getString(R.string.case_hardened),R.drawable.ak_47_case_hardened,5,0,100,R.drawable.csgo_weapon_case);
                weaponList.add(weapon54);
                Weapon weapon55 = new Weapon("M4A1-S",context.getResources().getString(R.string.dark_water),R.drawable.m4a1_s_dark_water,4,10,26,R.drawable.csgo_weapon_case);
                weaponList.add(weapon55);
                Weapon weapon56 = new Weapon("Glock-18",context.getResources().getString(R.string.dragon_tattoo),R.drawable.glock_18_dragon_tattoo,4,0,8,R.drawable.csgo_weapon_case);
                weaponList.add(weapon56);
                Weapon weapon57 = new Weapon("USP-S",context.getResources().getString(R.string.dark_water),R.drawable.usp_s_dark_water,4,10,26,R.drawable.csgo_weapon_case);
                weaponList.add(weapon57);
                Weapon weapon58 = new Weapon("MP7",context.getResources().getString(R.string.skulls),R.drawable.mp7_skulls,3,10,26,R.drawable.csgo_weapon_case);
                weaponList.add(weapon58);
                Weapon weapon59 = new Weapon("SG 553",context.getResources().getString(R.string.ultraviolet),R.drawable.sg_553_ultraviolet,3,6,80,R.drawable.csgo_weapon_case);
                weaponList.add(weapon59);
                Weapon weapon60 = new Weapon("AUG",context.getResources().getString(R.string.wings),R.drawable.aug_wings,3,0,14,R.drawable.csgo_weapon_case);
                weaponList.add(weapon60);
                break;

            case R.drawable.csgo_weapon_case_2:
                Weapon weapon61 = new Weapon("SSG 08",context.getResources().getString(R.string.blood_in_the_water),R.drawable.ssg_08_blood_in_the_water,6,6,20,R.drawable.csgo_weapon_case_2);
                weaponList.add(weapon61);
                Weapon weapon62 = new Weapon("USP-S",context.getResources().getString(R.string.serum),R.drawable.usp_s_serum,5,0,25,R.drawable.csgo_weapon_case_2);
                weaponList.add(weapon62);
                Weapon weapon63 = new Weapon("P90",context.getResources().getString(R.string.cold_blooded),R.drawable.p90_cold_blooded,5,0,8,R.drawable.csgo_weapon_case_2);
                weaponList.add(weapon63);
                Weapon weapon64 = new Weapon("Nova",context.getResources().getString(R.string.graphite),R.drawable.nova_graphite,4,0,12,R.drawable.csgo_weapon_case_2);
                weaponList.add(weapon64);
                Weapon weapon65 = new Weapon("MP9",context.getResources().getString(R.string.hypnotic),R.drawable.mp9_hypnotic,4,0,8,R.drawable.csgo_weapon_case_2);
                weaponList.add(weapon65);
                Weapon weapon66 = new Weapon("Dual Berettas",context.getResources().getString(R.string.hemoglobin),R.drawable.dual_berettas_hemoglobin,4,0,20,R.drawable.csgo_weapon_case_2);
                weaponList.add(weapon66);
                Weapon weapon67 = new Weapon("Five-SeveN",context.getResources().getString(R.string.case_hardened),R.drawable.five_seven_case_hardened,4,0,100,R.drawable.csgo_weapon_case_2);
                weaponList.add(weapon67);
                Weapon weapon68 = new Weapon("FAMAS",context.getResources().getString(R.string.hexane),R.drawable.famas_hexane,3,0,40,R.drawable.csgo_weapon_case_2);
                weaponList.add(weapon68);
                Weapon weapon69 = new Weapon("P250",context.getResources().getString(R.string.hive),R.drawable.p250_hive,3,0,30,R.drawable.csgo_weapon_case_2);
                weaponList.add(weapon69);
                Weapon weapon70 = new Weapon("SCAR-20",context.getResources().getString(R.string.crimson_web),R.drawable.scar_20_crimson_web,3,6,80,R.drawable.csgo_weapon_case_2);
                weaponList.add(weapon70);
                Weapon weapon71 = new Weapon("M4A1-S",context.getResources().getString(R.string.blood_tiger),R.drawable.m4a1_s_blood_tiger,3,0,30,R.drawable.csgo_weapon_case_2);
                weaponList.add(weapon71);
                Weapon weapon72 = new Weapon("Tec-9",context.getResources().getString(R.string.blue_titanium),R.drawable.tec_9_blue_titanum,3,0,4,R.drawable.csgo_weapon_case_2);
                weaponList.add(weapon72);
                break;

            case R.drawable.csgo_weapon_case_3:
                Weapon weapon73 = new Weapon("CZ75-Auto",context.getResources().getString(R.string.victoria),R.drawable.cz75_auto_victoria,6,0,75,R.drawable.csgo_weapon_case_3);
                weaponList.add(weapon73);
                Weapon weapon74 = new Weapon("P250",context.getResources().getString(R.string.undertow),R.drawable.p250_undertow,5,0,20,R.drawable.csgo_weapon_case_3);
                weaponList.add(weapon74);
                Weapon weapon75 = new Weapon("CZ75-Auto",context.getResources().getString(R.string.the_fuschia_is_now),R.drawable.cz75_auto_the_fuschia_is_now,5,0,40,R.drawable.csgo_weapon_case_3);
                weaponList.add(weapon75);
                Weapon weapon76 = new Weapon("Desert Eagle",context.getResources().getString(R.string.heirloom),R.drawable.desert_eagle_heirloom,4,0,80,R.drawable.csgo_weapon_case_3);
                weaponList.add(weapon76);
                Weapon weapon77 = new Weapon("CZ75-Auto",context.getResources().getString(R.string.tread_plate),R.drawable.cz75_auto_tread_plate,4,0,20,R.drawable.csgo_weapon_case_3);
                weaponList.add(weapon77);
                Weapon weapon78 = new Weapon("Tec-9",context.getResources().getString(R.string.titanium_bit),R.drawable.tec_9_titanum_bit,4,0,20,R.drawable.csgo_weapon_case_3);
                weaponList.add(weapon78);
                Weapon weapon79 = new Weapon("Five-SeveN",context.getResources().getString(R.string.copper_galaxy),R.drawable.five_seven_copper_galaxy,4,0,20,R.drawable.csgo_weapon_case_3);
                weaponList.add(weapon79);
                Weapon weapon80 = new Weapon("CZ75-Auto",context.getResources().getString(R.string.crimson_web),R.drawable.cz75_auto_crimson_web,3,6,80,R.drawable.csgo_weapon_case_3);
                weaponList.add(weapon80);
                Weapon weapon81 = new Weapon("USP-S",context.getResources().getString(R.string.stainless),R.drawable.usp_s_stainless,3,0,80,R.drawable.csgo_weapon_case_3);
                weaponList.add(weapon81);
                Weapon weapon82 = new Weapon("P2000",context.getResources().getString(R.string.red_fragCam),R.drawable.p2000_red_fragcam,3,0,70,R.drawable.csgo_weapon_case_3);
                weaponList.add(weapon82);
                Weapon weapon83 = new Weapon("Dual Berettas",context.getResources().getString(R.string.panther),R.drawable.dual_berettas_panther,3,0,58,R.drawable.csgo_weapon_case_3);
                weaponList.add(weapon83);
                Weapon weapon84 = new Weapon("Glock-18",context.getResources().getString(R.string.blue_fissure),R.drawable.glock_18_blue_fissure,3,6,58,R.drawable.csgo_weapon_case_3);
                weaponList.add(weapon84);
                break;

            case R.drawable.chroma_case:
                Weapon weapon85 = new Weapon("AWP",context.getResources().getString(R.string.man_o_war),R.drawable.awp_man_o_war,6,10,20,R.drawable.chroma_case);
                weaponList.add(weapon85);
                Weapon weapon86 = new Weapon("Galil AR",context.getResources().getString(R.string.chatterbox),R.drawable.galil_ar_chatterbox,6,35,85,R.drawable.chroma_case);
                weaponList.add(weapon86);
                Weapon weapon87 = new Weapon("AK-47",context.getResources().getString(R.string.cartel),R.drawable.ak_47_cartel,5,0,75,R.drawable.chroma_case);
                weaponList.add(weapon87);
                Weapon weapon88 = new Weapon("M4A4",context.getResources().getString(R.string.dragon_king),R.drawable.m4a4_dragon_king,5,0,75,R.drawable.chroma_case);
                weaponList.add(weapon88);
                Weapon weapon89 = new Weapon("P250",context.getResources().getString(R.string.muertos),R.drawable.p250_muertos,5,0,60,R.drawable.chroma_case);
                weaponList.add(weapon89);
                Weapon weapon90 = new Weapon("MAC-10",context.getResources().getString(R.string.malachite),R.drawable.mac_10_malachite,4,0,50,R.drawable.chroma_case);
                weaponList.add(weapon90);
                Weapon weapon91 = new Weapon("Desert Eagle",context.getResources().getString(R.string.naga),R.drawable.desert_eagle_naga,4,0,100,R.drawable.chroma_case);
                weaponList.add(weapon91);
                Weapon weapon92 = new Weapon("Sawed-Off",context.getResources().getString(R.string.serenity),R.drawable.sawed_off_serenity,4,0,50,R.drawable.chroma_case);
                weaponList.add(weapon92);
                Weapon weapon93 = new Weapon("Dual Berettas",context.getResources().getString(R.string.urban_shock),R.drawable.dual_berettas_urban_shock,4,0,47,R.drawable.chroma_case);
                weaponList.add(weapon93);
                Weapon weapon94 = new Weapon("XM1014",context.getResources().getString(R.string.quicksilver),R.drawable.xm1014_quicksilver,3,0,50,R.drawable.chroma_case);
                weaponList.add(weapon94);
                Weapon weapon95 = new Weapon("MP9",context.getResources().getString(R.string.deadly_poison),R.drawable.mp9_deadly_poison,3,0,100,R.drawable.chroma_case);
                weaponList.add(weapon95);
                Weapon weapon96 = new Weapon("M249",context.getResources().getString(R.string.system_lock),R.drawable.m249_system_lock,3,0,80,R.drawable.chroma_case);
                weaponList.add(weapon96);
                Weapon weapon97 = new Weapon("Glock-18",context.getResources().getString(R.string.catacombs),R.drawable.glock_18_catacombs,3,0,50,R.drawable.chroma_case);
                weaponList.add(weapon97);
                Weapon weapon98 = new Weapon("SCAR-20",context.getResources().getString(R.string.grotto),R.drawable.scar_20_grotto,3,0,50,R.drawable.chroma_case);
                weaponList.add(weapon98);
                break;

            case R.drawable.chroma_2_case:
                Weapon weapon99 = new Weapon("MAC-10",context.getResources().getString(R.string.neon_rider),R.drawable.mac_10_neon_rider,6,0,45,R.drawable.chroma_2_case);
                weaponList.add(weapon99);
                Weapon weapon100 = new Weapon("M4A1-S",context.getResources().getString(R.string.hyper_beast),R.drawable.m4a1_s_hyper_beast,6,0,100,R.drawable.chroma_2_case);
                weaponList.add(weapon100);
                Weapon weapon101 = new Weapon("FAMAS",context.getResources().getString(R.string.djinn),R.drawable.famas_djinn,5,0,100,R.drawable.chroma_2_case);
                weaponList.add(weapon101);
                Weapon weapon102 = new Weapon("Galil AR",context.getResources().getString(R.string.eco),R.drawable.galil_ar_eco,5,10,85,R.drawable.chroma_2_case);
                weaponList.add(weapon102);
                Weapon weapon103 = new Weapon("Five-SeveN",context.getResources().getString(R.string.monkey_business),R.drawable.five_seven_monkey_business,5,10,90,R.drawable.chroma_2_case);
                weaponList.add(weapon103);
                Weapon weapon104 = new Weapon("AWP",context.getResources().getString(R.string.worm_god),R.drawable.awp_worm_god,4,0,45,R.drawable.chroma_2_case);
                weaponList.add(weapon104);
                Weapon weapon105 = new Weapon("UMP-45",context.getResources().getString(R.string.grand_prix),R.drawable.ump_45_grand_prix,4,25,39,R.drawable.chroma_2_case);
                weaponList.add(weapon105);
                Weapon weapon106 = new Weapon("MAG-7",context.getResources().getString(R.string.heat),R.drawable.mag_7_heat,4,0,100,R.drawable.chroma_2_case);
                weaponList.add(weapon106);
                Weapon weapon107 = new Weapon("CZ75-Auto",context.getResources().getString(R.string.pole_position),R.drawable.cz75_auto_pole_position,4,0,70,R.drawable.chroma_2_case);
                weaponList.add(weapon107);
                Weapon weapon108 = new Weapon("Negev",context.getResources().getString(R.string.man_o_war),R.drawable.negev_man_o_war,3,10,20,R.drawable.chroma_2_case);
                weaponList.add(weapon108);
                Weapon weapon109 = new Weapon("P250",context.getResources().getString(R.string.valence),R.drawable.p250_valence,3,0,80,R.drawable.chroma_2_case);
                weaponList.add(weapon109);
                Weapon weapon110 = new Weapon("Sawed-Off",context.getResources().getString(R.string.origami),R.drawable.sawed_off_origami,3,0,55,R.drawable.chroma_2_case);
                weaponList.add(weapon110);
                Weapon weapon111 = new Weapon("MP7",context.getResources().getString(R.string.armor_core),R.drawable.mp7_armor_core,3,0,50,R.drawable.chroma_2_case);
                weaponList.add(weapon111);
                Weapon weapon112 = new Weapon("Desert Eagle",context.getResources().getString(R.string.bronze_deco),R.drawable.desert_eagle_bronze_deco,3,0,46,R.drawable.chroma_2_case);
                weaponList.add(weapon112);
                Weapon weapon113 = new Weapon("AK-47",context.getResources().getString(R.string.elite_build),R.drawable.ak_47_elite_build,3,0,100,R.drawable.chroma_2_case);
                weaponList.add(weapon113);
                break;

            case R.drawable.chroma_3_case:
                Weapon weapon114 = new Weapon("M4A1-S",context.getResources().getString(R.string.chanticos_fire),R.drawable.m4a1_s_chanticos_fire,6,0,99,R.drawable.chroma_3_case);
                weaponList.add(weapon114);
                Weapon weapon115 = new Weapon("PP-Bizon",context.getResources().getString(R.string.judgement_of_anubis),R.drawable.pp_bizon_judgement_of_anubis,6,0,50,R.drawable.chroma_3_case);
                weaponList.add(weapon115);
                Weapon weapon116 = new Weapon("P250",context.getResources().getString(R.string.asiimov),R.drawable.p250_asiimov,5,10,100,R.drawable.chroma_3_case);
                weaponList.add(weapon116);
                Weapon weapon117 = new Weapon("AUG",context.getResources().getString(R.string.fleet_flock),R.drawable.aug_fleet_flock,5,0,100,R.drawable.chroma_3_case);
                weaponList.add(weapon117);
                Weapon weapon118 = new Weapon("UMP-45",context.getResources().getString(R.string.primal_saber),R.drawable.ump_45_primal_saber,5,0,77,R.drawable.chroma_3_case);
                weaponList.add(weapon118);
                Weapon weapon119 = new Weapon("Galil AR",context.getResources().getString(R.string.firefight),R.drawable.galil_ar_firefight,4,0,100,R.drawable.chroma_3_case);
                weaponList.add(weapon119);
                Weapon weapon120 = new Weapon("XM1014",context.getResources().getString(R.string.black_tie),R.drawable.xm1014_black_tie,4,0,75,R.drawable.chroma_3_case);
                weaponList.add(weapon120);
                Weapon weapon121 = new Weapon("CZ75-Auto",context.getResources().getString(R.string.red_astor),R.drawable.cz75_auto_red_astor,4,0,100,R.drawable.chroma_3_case);
                weaponList.add(weapon121);
                Weapon weapon122 = new Weapon("SSG 08",context.getResources().getString(R.string.ghost_crusader),R.drawable.ssg_08_ghost_crusader,4,0,100,R.drawable.chroma_3_case);
                weaponList.add(weapon122);
                Weapon weapon123 = new Weapon("Tec-9",context.getResources().getString(R.string.re_entry),R.drawable.tec_9_re_entry,4,0,43,R.drawable.chroma_3_case);
                weaponList.add(weapon123);
                Weapon weapon124 = new Weapon("Sawed-Off",context.getResources().getString(R.string.fubar),R.drawable.sawed_off_fubar,3,40,100,R.drawable.chroma_3_case);
                weaponList.add(weapon124);
                Weapon weapon125 = new Weapon("P2000",context.getResources().getString(R.string.oceanic),R.drawable.p2000_oceanic,3,0,60,R.drawable.chroma_3_case);
                weaponList.add(weapon125);
                Weapon weapon126 = new Weapon("SG 553",context.getResources().getString(R.string.atlas),R.drawable.sg_553_atlas,3,0,81,R.drawable.chroma_3_case);
                weaponList.add(weapon126);
                Weapon weapon127 = new Weapon("MP9",context.getResources().getString(R.string.bioleak),R.drawable.mp9_bioleak,3,0,50,R.drawable.chroma_3_case);
                weaponList.add(weapon127);
                Weapon weapon128 = new Weapon("M249",context.getResources().getString(R.string.spectre),R.drawable.m249_spectre,3,0,50,R.drawable.chroma_3_case);
                weaponList.add(weapon128);
                Weapon weapon129 = new Weapon("G3SG1",context.getResources().getString(R.string.orange_crash),R.drawable.g3sg1_orange_crash,3,0,52,R.drawable.chroma_3_case);
                weaponList.add(weapon129);
                Weapon weapon130 = new Weapon("Dual Berettas",context.getResources().getString(R.string.ventilators),R.drawable.dual_berettas_ventilators,3,0,45,R.drawable.chroma_3_case);
                weaponList.add(weapon130);
                break;

            case R.drawable.esports_2013_case:
                Weapon weapon131 = new Weapon("P90",context.getResources().getString(R.string.death_by_kitty),R.drawable.p90_death_by_kitty,6,8,32,R.drawable.esports_2013_case);
                weaponList.add(weapon131);
                Weapon weapon132 = new Weapon("AWP",context.getResources().getString(R.string.boom),R.drawable.awp_boom,5,6,28,R.drawable.esports_2013_case);
                weaponList.add(weapon132);
                Weapon weapon133 = new Weapon("AK-47",context.getResources().getString(R.string.red_laminate),R.drawable.ak_47_red_laminate,5,6,80,R.drawable.esports_2013_case);
                weaponList.add(weapon133);
                Weapon weapon134 = new Weapon("Galil AR",context.getResources().getString(R.string.orange_ddpat),R.drawable.galil_ar_orange_ddpat,4,6,80,R.drawable.esports_2013_case);
                weaponList.add(weapon134);
                Weapon weapon135 = new Weapon("P250",context.getResources().getString(R.string.splash),R.drawable.p250_splash,4,6,18,R.drawable.esports_2013_case);
                weaponList.add(weapon135);
                Weapon weapon136 = new Weapon("Sawed-Off",context.getResources().getString(R.string.orange_ddpat),R.drawable.sawed_off_orange_ddpat,4,6,80,R.drawable.esports_2013_case);
                weaponList.add(weapon136);
                Weapon weapon137 = new Weapon("MAG-7",context.getResources().getString(R.string.memento),R.drawable.mag_7_memento,3,2,18,R.drawable.esports_2013_case);
                weaponList.add(weapon137);
                Weapon weapon138 = new Weapon("M4A4",context.getResources().getString(R.string.faded_zebra),R.drawable.m4a4_faded_zebra,3,6,80,R.drawable.esports_2013_case);
                weaponList.add(weapon138);
                Weapon weapon139 = new Weapon("FAMAS",context.getResources().getString(R.string.doomkitty),R.drawable.famas_doomkitty,3,8,22,R.drawable.esports_2013_case);
                weaponList.add(weapon139);
                break;

            default:break;
        }
        return weaponList;
    }
}
