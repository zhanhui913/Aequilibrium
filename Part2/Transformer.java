package com.zhan.aequilibrium.Part2;

/**
 * Created by Zhan on 2017-07-02.
 */

public class Transformer {

    enum BATTLE_STATE{
        WIN,
        LOST,
        NEUTRAL
    }

    protected String name;
    protected String type;
    protected int strength;
    protected int intelligence;
    protected int speed;
    protected int endurance;
    protected int rank;
    protected int courage;
    protected int firepower;
    protected int skill;
    protected BATTLE_STATE battleState;

    public Transformer(){}

    public Transformer(String name, String type, int strength, int intelligence, int speed, int endurance,
                   int rank, int courage, int firepower, int skill){

        this.name = name;
        this.type = type;
        this.strength = strength;
        this.intelligence = intelligence;
        this.speed = speed;
        this.endurance = endurance;
        this.rank = rank;
        this.courage = courage;
        this.firepower = firepower;
        this.skill = skill;
        this.battleState = BATTLE_STATE.NEUTRAL;
    }

    public int getOverallRating(){
        return (this.strength + this.intelligence + this.speed + this.endurance + this.firepower);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getCourage() {
        return courage;
    }

    public void setCourage(int courage) {
        this.courage = courage;
    }

    public int getFirepower() {
        return firepower;
    }

    public void setFirepower(int firepower) {
        this.firepower = firepower;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public BATTLE_STATE getBattleState() {
        return battleState;
    }

    public void setBattleState(BATTLE_STATE battleState) {
        this.battleState = battleState;
    }
}
