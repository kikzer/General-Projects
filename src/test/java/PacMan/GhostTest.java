package PacMan;

import PacMan.Ghosts.AStar;
import PacMan.MainFunction.PacEngine;
import PacMan.MainFunction.PacField;
import PacMan.PlayerFunction.Player;
import org.junit.Assert;
import org.junit.Test;

public class GhostTest {

    @Test
    public void aStarTest() {
        AStar aStar = new AStar(new PacEngine());
        for (int y = 0; y < PacField.map.length; y++) {
            for (int x = 0; x < PacField.map[y].length; x++) {
                if (PacField.map[y][x] == 2 && !(x == 1 && y == 1)) {
                    aStar.setNodes(Player.WIDTH, Player.HEIGHT, x * Player.WIDTH, y * Player.HEIGHT);
                    aStar.search();
                    Assert.assertTrue(aStar.isGoalReached());
                }
            }
        }
    }

    @Test
    public void ghostTestInRadius() {
        PacEngine engine = new PacEngine();
        engine.getPacman().setY(8 * Player.HEIGHT);
        engine.getPacman().setX(9 * Player.HEIGHT);
        engine.getOrangeGhost().update();
        engine.getBlueGhost().update();
        Assert.assertEquals(engine.getOrangeGhost().getaStar().getEnd().getX(), engine.getPacman().getX());
        Assert.assertEquals(engine.getBlueGhost().getaStar().getEnd().getX(), engine.getPacman().getX());
        Assert.assertEquals(engine.getOrangeGhost().getaStar().getEnd().getY(), engine.getPacman().getY());
        Assert.assertEquals(engine.getBlueGhost().getaStar().getEnd().getY(), engine.getPacman().getY());
    }

    @Test
    public void ghostTestOutRadius() {
        PacEngine engine = new PacEngine();
        engine.getOrangeGhost().update();
        engine.getBlueGhost().update();
        Assert.assertNotEquals(engine.getOrangeGhost().getaStar().getEnd().getX(), engine.getPacman().getX());
        Assert.assertNotEquals(engine.getBlueGhost().getaStar().getEnd().getX(), engine.getPacman().getX());
        Assert.assertNotEquals(engine.getOrangeGhost().getaStar().getEnd().getY(), engine.getPacman().getY());
        Assert.assertNotEquals(engine.getBlueGhost().getaStar().getEnd().getY(), engine.getPacman().getY());
    }

    @Test
    public void inRangeTest() {
        PacEngine engine = new PacEngine();

        Assert.assertTrue(engine.getOrangeGhost().inRange(10000));
        Assert.assertTrue(engine.getBlueGhost().inRange(10000));
        Assert.assertFalse(engine.getOrangeGhost().inRange(2));
        Assert.assertFalse(engine.getBlueGhost().inRange(2));
        engine.getPacman().setY(15 * 25);
        engine.getPacman().setX(14 * 25);
        engine.getBlueGhost().update();
        engine.getOrangeGhost().update();
        Assert.assertFalse(engine.getOrangeGhost().inRange(engine.getOrangeGhost().getRadius()));
        Assert.assertTrue(engine.getBlueGhost().inRange(engine.getBlueGhost().getRadius()));
        engine.getPacman().setY(17 * 25);
        engine.getPacman().setX(11 * 25);
        engine.getBlueGhost().update();
        engine.getOrangeGhost().update();
        Assert.assertFalse(engine.getOrangeGhost().inRange(engine.getOrangeGhost().getRadius()));
        Assert.assertTrue(engine.getBlueGhost().inRange(engine.getBlueGhost().getRadius()));
        engine.getPacman().setY(12 * 25);
        engine.getPacman().setX(13 * 25);
        engine.getBlueGhost().update();
        engine.getOrangeGhost().update();
        Assert.assertTrue(engine.getOrangeGhost().inRange(engine.getOrangeGhost().getRadius()));
        Assert.assertTrue(engine.getBlueGhost().inRange(engine.getBlueGhost().getRadius()));
    }
}
