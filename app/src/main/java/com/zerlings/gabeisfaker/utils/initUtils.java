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
        Case eSports2013WinterCase = new Case(context.getResources().getString(R.string.esports_2013_winter_case),R.drawable.esports_2013_winter_case);
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
        caseList.add(winterOffensiveWeaponCase);

        return caseList;
    }

    public static List<Weapon> initWeapon(int imageId){

        List<Weapon> weaponList = new ArrayList<>();

        Context context = MyApplication.getContext();

        switch (imageId){
            case R.drawable.glove_case:
                Weapon weapon1 = new Weapon("M4A4",context.getResources().getString(R.string.buzz_kill),R.drawable.m4a4_buzz_kill,6,0,70);
                weaponList.add(weapon1);
                Weapon weapon2 = new Weapon("SSG 08",context.getResources().getString(R.string.dragonfire),R.drawable.ssg_08_dragonfire,6,0,50);
                weaponList.add(weapon2);
                Weapon weapon3 = new Weapon("Sawed-Off",context.getResources().getString(R.string.wasteland_princess),R.drawable.sawed_off_wasteland_princess,5,0,70);
                weaponList.add(weapon3);
                Weapon weapon4 = new Weapon("FAMAS",context.getResources().getString(R.string.mecha_industries),R.drawable.famas_mecha_industries,5,0,50);
                weaponList.add(weapon4);
                Weapon weapon5 = new Weapon("P90",context.getResources().getString(R.string.shallow_grave),R.drawable.p90_shallow_grave,5,0,75);
                weaponList.add(weapon5);
                Weapon weapon6 = new Weapon("Nova",context.getResources().getString(R.string.gila),R.drawable.nova_gila,4,0,30);
                weaponList.add(weapon6);
                Weapon weapon7 = new Weapon("M4A1-S",context.getResources().getString(R.string.flash_back),R.drawable.m4a1_s_flash_back,4,0,100);
                weaponList.add(weapon7);
                Weapon weapon8 = new Weapon("Dual Berettas",context.getResources().getString(R.string.royal_consorts),R.drawable.dual_berettas_royal_consorts,4,0,100);
                weaponList.add(weapon8);
                Weapon weapon9 = new Weapon("G3SG1",context.getResources().getString(R.string.stinger),R.drawable.g3sg1_stinger,4,0,70);
                weaponList.add(weapon9);
                Weapon weapon10 = new Weapon("USP-S",context.getResources().getString(R.string.cyrex),R.drawable.usp_s_cyrex,4,0,55);
                weaponList.add(weapon10);
                Weapon weapon11 = new Weapon("Gali AR",context.getResources().getString(R.string.black_sand),R.drawable.gali_ar_black_sand,3,0,100);
                weaponList.add(weapon11);
                Weapon weapon12 = new Weapon("MAG-7",context.getResources().getString(R.string.sonar),R.drawable.mag_7_sonar,3,0,45);
                weaponList.add(weapon12);
                Weapon weapon13 = new Weapon("MP9",context.getResources().getString(R.string.sand_scale),R.drawable.mp9_sand_scale,3,0,45);
                weaponList.add(weapon13);
                Weapon weapon14 = new Weapon("P2000",context.getResources().getString(R.string.turf),R.drawable.p2000_turf,3,0,100);
                weaponList.add(weapon14);
                Weapon weapon15 = new Weapon("MP7",context.getResources().getString(R.string.cirrus),R.drawable.mp7_cirrus,3,0,75);
                weaponList.add(weapon15);
                Weapon weapon16 = new Weapon("Glock-18",context.getResources().getString(R.string.ironwork),R.drawable.glock_18_ironwork,3,0,100);
                weaponList.add(weapon16);
                Weapon weapon17 = new Weapon("CZ75-Auto",context.getResources().getString(R.string.polymer),R.drawable.cz75_auto_polymer,3,0,50);
                weaponList.add(weapon17);
                break;
            default:break;
        }
        return weaponList;
    }
}
