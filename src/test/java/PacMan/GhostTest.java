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
        for(int y = 0; y < PacField.map.length; y++){
            for(int x = 0; x < PacField.map[y].length; x++ ){
                if(PacField.map[y][x] == 2 && !(x == 1 && y == 1)){
                    aStar.setNodes(Player.WIDTH, Player.HEIGHT, x*Player.WIDTH, y*Player.HEIGHT);
                    aStar.search();
                    Assert.assertTrue(aStar.isGoalReached());
                }
            }
        }
    }
    @Test
    public void GhostTestInRadius(){
        PacEngine engine = new PacEngine();
        engine.getPacman().setY(8*Player.HEIGHT);
        engine.getPacman().setX(9*Player.HEIGHT);
        engine.getOrangeGhost().update();
        engine.getBlueGhost().update();
        Assert.assertEquals(engine.getOrangeGhost().getaStar().getEnd().getX(), engine.getPacman().getX());
        Assert.assertEquals(engine.getBlueGhost().getaStar().getEnd().getX(), engine.getPacman().getX());
        Assert.assertEquals(engine.getOrangeGhost().getaStar().getEnd().getY(), engine.getPacman().getY());
        Assert.assertEquals(engine.getBlueGhost().getaStar().getEnd().getY(), engine.getPacman().getY());
    }

    @Test
    public void GhostTestOutRadius(){
        PacEngine engine = new PacEngine();
        engine.getOrangeGhost().update();
        engine.getBlueGhost().update();
        Assert.assertNotEquals(engine.getOrangeGhost().getaStar().getEnd().getX(), engine.getPacman().getX());
        Assert.assertNotEquals(engine.getBlueGhost().getaStar().getEnd().getX(), engine.getPacman().getX());
        Assert.assertNotEquals(engine.getOrangeGhost().getaStar().getEnd().getY(), engine.getPacman().getY());
        Assert.assertNotEquals(engine.getBlueGhost().getaStar().getEnd().getY(), engine.getPacman().getY());
    }
}
