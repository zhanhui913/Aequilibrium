package com.zhan.aequilibrium;

import com.zhan.aequilibrium.Part2.BattleManager;
import com.zhan.aequilibrium.Part2.BattleResult;
import com.zhan.aequilibrium.Part2.Transformer;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 */
public class TransformerUnitTest {

    @Test
    public void transformer_test_odd_number_of_battle() throws Exception{
        Transformer soundWave = new Transformer("SoundWave","D",8,9,2,6,7,5,6,10);
        Transformer blueStreak = new Transformer("BlueStreak","A",6,6,7,9,5,2,9,7);
        Transformer hubcap = new Transformer("Hubcap","A",4,4,4,4,4,4,4,4);

        ArrayList<Transformer> transformerArrayList = new ArrayList<>();
        transformerArrayList.add(soundWave);
        transformerArrayList.add(blueStreak);
        transformerArrayList.add(hubcap);

        BattleManager battleManager = new BattleManager();
        BattleResult battleResult = battleManager.evaluate(transformerArrayList);


        assertEquals(battleResult.numBattles, "1 battle");
        assertEquals(battleResult.winningTeam, "Winning Team (Decepticons): SoundWave, ");
        assertEquals(battleResult.losingTeamSurvivor, "Survivors from the losing team (Autobots): Hubcap, ");
    }

    @Test
    public void transformer_test_even_number_of_battle() throws Exception{
        Transformer soundWave = new Transformer("SoundWave","D",8,9,2,6,7,5,6,10);
        Transformer blueStreak = new Transformer("BlueStreak","A",6,6,7,9,5,2,9,7);
        Transformer hubcap = new Transformer("Hubcap","A",4,4,4,4,4,4,4,4);
        Transformer skull = new Transformer("Skull","D",1,2,3,8,4,7,4,10);

        ArrayList<Transformer> transformerArrayList = new ArrayList<>();
        transformerArrayList.add(soundWave);
        transformerArrayList.add(blueStreak);
        transformerArrayList.add(hubcap);
        transformerArrayList.add(skull);

        BattleManager battleManager = new BattleManager();
        BattleResult battleResult = battleManager.evaluate(transformerArrayList);


        assertEquals(battleResult.numBattles, "2 battles");
        assertEquals(battleResult.winningTeam, "Winning Team (Decepticons): SoundWave, Skull, ");
        assertEquals(battleResult.losingTeamSurvivor, "Survivors from the losing team (Autobots): ");
    }

    @Test
    public void transformer_test_tie_battle() throws Exception{
        Transformer soundWave = new Transformer("SoundWave","D",2,2,2,2,2,2,2,10);
        Transformer blueStreak = new Transformer("BlueStreak","A",2,2,2,2,2,2,2,10);

        ArrayList<Transformer> transformerArrayList = new ArrayList<>();
        transformerArrayList.add(soundWave);
        transformerArrayList.add(blueStreak);

        BattleManager battleManager = new BattleManager();
        BattleResult battleResult = battleManager.evaluate(transformerArrayList);


        assertEquals(battleResult.numBattles, "1 battle");
        assertEquals(battleResult.winningTeam, "Winning Team (Tie)");
        assertEquals(battleResult.losingTeamSurvivor, "Survivors from the losing team (Tie)");
    }

    @Test
    public void transformer_test_tie_score() throws Exception{
        Transformer soundWave = new Transformer("SoundWave","D",8,9,2,6,7,5,6,10);
        Transformer blueStreak = new Transformer("BlueStreak","A",6,6,7,9,5,2,9,7);
        Transformer skull = new Transformer("Skull","D",4,4,4,4,4,4,4,4);
        Transformer bumblebee = new Transformer("Bumblebee","A",1,2,3,8,4,7,4,10);

        ArrayList<Transformer> transformerArrayList = new ArrayList<>();
        transformerArrayList.add(soundWave);
        transformerArrayList.add(blueStreak);
        transformerArrayList.add(bumblebee);
        transformerArrayList.add(skull);

        BattleManager battleManager = new BattleManager();
        BattleResult battleResult = battleManager.evaluate(transformerArrayList);


        assertEquals(battleResult.numBattles, "2 battles");
        assertEquals(battleResult.winningTeam, "Winning Team (Tie)");
        assertEquals(battleResult.losingTeamSurvivor, "Survivors from the losing team (Tie)");
    }

    @Test
    public void transformer_test_optimus_prime() throws Exception{
        Transformer soundWave = new Transformer("SoundWave","D",8,9,2,6,7,5,6,10);
        Transformer prime = new Transformer("Optimus Prime","A",6,6,7,9,5,2,9,7);

        ArrayList<Transformer> transformerArrayList = new ArrayList<>();
        transformerArrayList.add(soundWave);
        transformerArrayList.add(prime);

        BattleManager battleManager = new BattleManager();
        BattleResult battleResult = battleManager.evaluate(transformerArrayList);


        assertEquals(battleResult.numBattles, "1 battle");
        assertEquals(battleResult.winningTeam, "Winning Team (Autobots): Optimus Prime, ");
        assertEquals(battleResult.losingTeamSurvivor, "Survivors from the losing team (Decepticons): ");
    }

    @Test
    public void transformer_test_optimus_prime_and_predaking() throws Exception{
        Transformer predaking = new Transformer("Predaking","D",8,9,2,6,7,5,6,10);
        Transformer prime = new Transformer("Optimus Prime","A",6,6,7,9,5,2,9,7);


        ArrayList<Transformer> transformerArrayList = new ArrayList<>();
        transformerArrayList.add(predaking);
        transformerArrayList.add(prime);

        BattleManager battleManager = new BattleManager();
        BattleResult battleResult = battleManager.evaluate(transformerArrayList);


        assertEquals(battleResult.numBattles, "1 battle");
        assertEquals(battleResult.winningTeam, "Winning Team (Tie)");
        assertEquals(battleResult.losingTeamSurvivor, "Survivors from the losing team (Tie)");
    }
}