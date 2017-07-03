package com.zhan.aequilibrium;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 */
public class CastleUnitTest {

    @Test
    public void calculate_num_of_castle() throws Exception{
        assertEquals(CastleUtil.getNumCastles(new int[]{}), 0);
        assertEquals(CastleUtil.getNumCastles(new int[]{1}), 1);
        assertEquals(CastleUtil.getNumCastles(new int[]{2,2}), 1);
        assertEquals(CastleUtil.getNumCastles(new int[]{5,5,5}), 1);
        assertEquals(CastleUtil.getNumCastles(new int[]{0,0,0,5}), 2);
        assertEquals(CastleUtil.getNumCastles(new int[]{2,6,6,6,3}), 3);
        assertEquals(CastleUtil.getNumCastles(new int[]{2,6,3}), 3);
        assertEquals(CastleUtil.getNumCastles(new int[]{3,0,3}), 3);
        assertEquals(CastleUtil.getNumCastles(new int[]{3,0,0,1}), 3);
        assertEquals(CastleUtil.getNumCastles(new int[]{2,6,3}), 3);
        assertEquals(CastleUtil.getNumCastles(new int[]{5,4,3}), 2);
        assertEquals(CastleUtil.getNumCastles(new int[]{5,4,3,2,6}), 3);
        assertEquals(CastleUtil.getNumCastles(new int[]{1,2,3,4,2}), 3);
        assertEquals(CastleUtil.getNumCastles(new int[]{5,3,4,6,2,9,6}), 6);
    }

}