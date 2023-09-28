package PacMan;

import PacMan.MainFunction.PacEngine;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void moveFunctionTest(){
        PacEngine engine = new PacEngine();
        final int startYPosition = engine.getPacman().getY();
        final int startXPosition = engine.getPacman().getX();
        Assert.assertEquals(startXPosition, engine.getPacman().getX());
        engine.getPacman().goLeft();
        engine.getPacman().move();
        Assert.assertNotEquals(startXPosition, engine.getPacman().getX());

        engine.getPacman().setY(startYPosition);
        engine.getPacman().setX(startXPosition);
        engine.getPacman().goRight();
        engine.getPacman().move();
        Assert.assertNotEquals(startXPosition, engine.getPacman().getX());

        engine.getPacman().setY(startYPosition);
        engine.getPacman().setX(startXPosition-50);
        engine.getPacman().goUp();
        engine.getPacman().move();
        Assert.assertNotEquals(startYPosition, engine.getPacman().getY());

        engine.getPacman().setY(startYPosition);
        engine.getPacman().setX(startXPosition-50);
        engine.getPacman().goDown();
        engine.getPacman().move();
        Assert.assertNotEquals(startYPosition, engine.getPacman().getY());
    }

    @Test
    public void frontIsBlockedTest(){
        PacEngine engine = new PacEngine();
        final int startYPosition = engine.getPacman().getY();
        final int startXPosition = engine.getPacman().getX();
        engine.getPacman().goUp();
        engine.getPacman().move();
        Assert.assertTrue(engine.getPacman().frontIsBlocked());

        engine.getPacman().goDown();
        engine.getPacman().move();
        Assert.assertTrue(engine.getPacman().frontIsBlocked());

        engine.getPacman().setX(startXPosition-50);
        engine.getPacman().goRight();
        engine.getPacman().move();
        Assert.assertTrue(engine.getPacman().frontIsBlocked());

        engine.getPacman().setY(startYPosition+25);
        engine.getPacman().setX(startXPosition-50);
        engine.getPacman().goLeft();
        engine.getPacman().move();
        Assert.assertTrue(engine.getPacman().frontIsBlocked());
    }
}
