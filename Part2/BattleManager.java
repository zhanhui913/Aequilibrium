package com.zhan.aequilibrium.Part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Zhan on 2017-07-02.
 */

public class BattleManager {
    private ArrayList<Transformer> autobotList = new ArrayList<>();
    private ArrayList<Transformer> decepticonList = new ArrayList<>();
    private int numBattles = 0;
    private int numBattlesAutobotWin = 0;
    private int numBattlesDecepticonWin = 0;

    final String OPTIMUS  = "Optimus Prime";
    final String PREDAKING = "Predaking";
    final String AUTOBOTS = "(Autobots): ";
    final String DECEPTICONS = "(Decepticons): ";

    public BattleManager(){}

    //Assume sort rank by highest first
    //Assume we only print the winners of the winning team

    /**
     * Evaluate the entire battlelist between decepticons and autobots
     * @param transformerList The list of transformers (autobots and decepticons)
     * @return The result
     */
    public BattleResult evaluate(ArrayList<Transformer> transformerList){
        splitTransformers(transformerList);

        //Sort both autobots and decepticons
        sortTransformerByRank(autobotList);
        sortTransformerByRank(decepticonList);

        //set the number of battles to be the smaller of the 2 list
        numBattles = (autobotList.size() > decepticonList.size()) ? decepticonList.size(): autobotList.size();

        for(int i = 0 ; i < numBattles ; i++){
            //Special rules
            if((autobotList.get(i).getName().equalsIgnoreCase(OPTIMUS) || autobotList.get(i).getName().equalsIgnoreCase(PREDAKING)) &&
                    (decepticonList.get(i).getName().equalsIgnoreCase(OPTIMUS) || decepticonList.get(i).getName().equalsIgnoreCase(PREDAKING))){
                //End Game
                destroyAll();
                break;
            }else if((autobotList.get(i).getName().equalsIgnoreCase(OPTIMUS) || autobotList.get(i).getName().equalsIgnoreCase(PREDAKING)) &&
                    (!decepticonList.get(i).getName().equalsIgnoreCase(OPTIMUS)) && (!decepticonList.get(i).getName().equalsIgnoreCase(PREDAKING))){
                //Autobots win
                numBattlesAutobotWin++;
                this.autobotList.get(i).setBattleState(Transformer.BATTLE_STATE.WIN);
                this.decepticonList.get(i).setBattleState(Transformer.BATTLE_STATE.LOST);
            }else if((decepticonList.get(i).getName().equalsIgnoreCase(OPTIMUS) || decepticonList.get(i).getName().equalsIgnoreCase(PREDAKING)) &&
                    (!autobotList.get(i).getName().equalsIgnoreCase(OPTIMUS)) && (!autobotList.get(i).getName().equalsIgnoreCase(PREDAKING))){
                //Decepticons win
                numBattlesDecepticonWin++;
                this.autobotList.get(i).setBattleState(Transformer.BATTLE_STATE.LOST);
                this.decepticonList.get(i).setBattleState(Transformer.BATTLE_STATE.WIN);
            }else{
                battle(autobotList.get(i), decepticonList.get(i));
            }
        }

        BattleResult result = new BattleResult();
        result.numBattles = numBattles + ((numBattles > 1) ? " battles" : " battle") ;
        result.winningTeam = determineWinningTeam();
        result.losingTeamSurvivor = determineLosingTeam();

        return result;
    }

    /**
     * Put both autobot and decepticon into battle
     * @param autobot Autobot
     * @param decepticon Decepticon
     */
    private void battle(Transformer autobot, Transformer decepticon){
        //Rule 1 (courage & strength)
        int diffCourage = autobot.getCourage() - decepticon.getCourage();
        int diffStrength = autobot.getStrength() - decepticon.getStrength();

        //There is a difference of 4 or more in courage and difference of 3 or more in strength
        if(Math.abs(diffCourage) >= 4 && Math.abs(diffStrength) >= 3){
            //Now determine which transformer win
            if(diffCourage < 0 && diffStrength < 0){
                //decepticon wins
                autobot.setBattleState(Transformer.BATTLE_STATE.LOST);
                decepticon.setBattleState(Transformer.BATTLE_STATE.WIN);
                numBattlesDecepticonWin++;
            }else if(diffCourage > 0 && diffStrength > 0){
                //autobot wins
                autobot.setBattleState(Transformer.BATTLE_STATE.WIN);
                decepticon.setBattleState(Transformer.BATTLE_STATE.LOST);
                numBattlesAutobotWin++;
            }
            return;
        }

        //Rule 2 (skill)
        int diffSkill = autobot.getSkill() - decepticon.getSkill();

        //There is a difference of 3 or more in skills
        if(Math.abs(diffSkill) >= 3){
            if(diffSkill < 0){
                //decepticon wins
                autobot.setBattleState(Transformer.BATTLE_STATE.LOST);
                decepticon.setBattleState(Transformer.BATTLE_STATE.WIN);
                numBattlesDecepticonWin++;
            }else if(diffSkill > 0){
                //autobot wins
                autobot.setBattleState(Transformer.BATTLE_STATE.WIN);
                decepticon.setBattleState(Transformer.BATTLE_STATE.LOST);
                numBattlesAutobotWin++;
            }
            return;
        }

        //Rule 3 (overall rating)
        if(autobot.getOverallRating() > decepticon.getOverallRating()){
            autobot.setBattleState(Transformer.BATTLE_STATE.WIN);
            decepticon.setBattleState(Transformer.BATTLE_STATE.LOST);
            numBattlesAutobotWin++;
            return;
        }else if(autobot.getOverallRating() < decepticon.getOverallRating()){
            autobot.setBattleState(Transformer.BATTLE_STATE.LOST);
            decepticon.setBattleState(Transformer.BATTLE_STATE.WIN);
            numBattlesDecepticonWin++;
            return;
        }

        //Rule 4 (tie)
        autobot.setBattleState(Transformer.BATTLE_STATE.LOST);
        decepticon.setBattleState(Transformer.BATTLE_STATE.LOST);
    }

    /**
     * Separate the list of transformers into an sublist of autobots and decepticons
     * @param transformerList The mixed list of transformers
     */
    private void splitTransformers(ArrayList<Transformer> transformerList){
        for(Transformer transformer : transformerList){
            if(transformer.getType().equalsIgnoreCase("A")){
                autobotList.add(transformer);
            }else if(transformer.getType().equalsIgnoreCase("D")){
                decepticonList.add(transformer);
            }
        }
    }

    /**
     * Sorts the arraylist of transformers by highest rank first
     * @param transformerList Transformers Arraylist
     */
    private void sortTransformerByRank(ArrayList<Transformer> transformerList){
        Collections.sort(transformerList, new Comparator<Transformer>() {
            @Override
            public int compare(Transformer o1, Transformer o2) {
                return o2.getRank() - o1.getRank();
            }
        });
    }

    /**
     * Calculates the winning team
     * @return string that determines which team won and the list of transformers who won the battle.
     */
    private String determineWinningTeam(){
        String value = "Winning Team ";

        if(numBattlesAutobotWin > numBattlesDecepticonWin){
            //autobots win
            value += AUTOBOTS;

            for(int i = 0; i < this.autobotList.size(); i++){
                if(this.autobotList.get(i).getBattleState() == Transformer.BATTLE_STATE.WIN){
                    value += this.autobotList.get(i).getName() + ", ";
                }
            }
        }else if(numBattlesAutobotWin < numBattlesDecepticonWin){
            //decepticons win
            value += DECEPTICONS;

            for(int i = 0; i < this.decepticonList.size(); i++){
                if(this.decepticonList.get(i).getBattleState() == Transformer.BATTLE_STATE.WIN){
                    value += this.decepticonList.get(i).getName() + ", ";
                }
            }
        }else{
            //tie
            value += "(Tie)";
        }

        return value;
    }

    /**
     * Calculates the losing team
     * @return string that determines which team lost and the list of transformers who survive.
     */
    private String determineLosingTeam(){
        String value = "Survivors from the losing team ";

        if(numBattlesAutobotWin > numBattlesDecepticonWin){
            //decepticons lost
            value += DECEPTICONS;

            for(int i = 0; i < this.decepticonList.size(); i++){
                if(this.decepticonList.get(i).getBattleState() == Transformer.BATTLE_STATE.NEUTRAL){
                    value += this.decepticonList.get(i).getName() + ", ";
                }
            }
        }else if(numBattlesAutobotWin < numBattlesDecepticonWin){
            //autobots lost
            value += AUTOBOTS;

            for(int i = 0; i < this.autobotList.size(); i++){
                if(this.autobotList.get(i).getBattleState() == Transformer.BATTLE_STATE.NEUTRAL){
                    value += this.autobotList.get(i).getName() + ", ";
                }
            }
        }else{
            //tie
            value += "(Tie)";
        }

        return value;
    }

    /**
     * Destroy all competitors and reset counter for autobots and decepticons battle won.
     */
    private void destroyAll(){
        for(int i = 0; i < autobotList.size(); i++){
            this.autobotList.get(i).setBattleState(Transformer.BATTLE_STATE.LOST);
        }

        for(int i = 0; i < decepticonList.size(); i++){
            this.decepticonList.get(i).setBattleState(Transformer.BATTLE_STATE.LOST);
        }

        numBattlesAutobotWin = 0;
        numBattlesDecepticonWin = 0;
    }
}
