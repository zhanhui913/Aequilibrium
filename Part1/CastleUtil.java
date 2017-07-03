package com.zhan.aequilibrium;

import java.util.ArrayList;

/**
 * Created by Zhan on 2017-07-02.
 */

public final class CastleUtil {

    //assumption is that can build at the end of the array provided its a peak/valley

    /**
     * Calculate the number of castles that can be built.
     * @param land int[] array of integers reprenseting the height of the land.
     * @return The number of castles that can be built.
     */
    public static int getNumCastles(int[] land){
        land = stripLand(land);

        int numCastle = 0;
        if(land.length >= 3){
            //Add 1 for index = 0 since the assumption is that we can always build a castle at the
            //start of the array, provided its non-empty
            numCastle++;

            for(int i = 0; i <= land.length - 3; i++){
                if(isPeak(land[i], land[i+1], land[i+2]) || isValley(land[i], land[i+1], land[i+2])){
                    numCastle++;
                }
            }

            //Compare the last 2 to see if the last index is a peak or valley
            if(land[land.length - 1] != land[land.length - 2]){
                numCastle++;
            }

            return numCastle;
        }else {
            return numCastle + land.length;
        }
    }

    /**
     * Checks if the middlePoint is a peak
     * @param startPoint Integer
     * @param middlePoint Integer
     * @param endPoint Integer
     * @return true if middlePoint is a peak
     */
    public static boolean isPeak(int startPoint, int middlePoint, int endPoint){
        return ((middlePoint >startPoint) && (middlePoint > endPoint));
    }

    /**
     * Checks if the middlePoint is a valley
     * @param startPoint Integer
     * @param middlePoint Integer
     * @param endPoint Integer
     * @return true if middlePoint is a valley
     */
    public static boolean isValley(int startPoint, int middlePoint, int endPoint){
        return ((middlePoint < startPoint) && (middlePoint < endPoint));
    }

    /**
     * Strips the land of any consecutive duplicate integers
     * @param land int[] array of integers
     * @return int[] array of integers after being stripped of consecutive duplicates
     */
    public static int[] stripLand(int[] land){
        if(land.length == 0){
            return land;
        }

        ArrayList<Integer> newLand = new ArrayList<>();

        //Get the first index
        newLand.add(land[0]);

        for(int i = 1; i < land.length; i++){
            if(land[i] != land[i-1]){
                newLand.add(land[i]);
            }
        }

        //Need to convert back to array of int
        int[] returnedInt = new int[newLand.size()];
        for (int i = 0; i < returnedInt.length; i++){
            returnedInt[i] = newLand.get(i);
        }
        return returnedInt;
    }
}


