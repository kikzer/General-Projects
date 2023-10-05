package PacMan;
import PacMan.MainFunction.PacEngine;
import org.junit.Assert;
import org.junit.Test;

public class PacEngineTest {

    @Test
    public void FoodTest(){
        PacEngine engine = new PacEngine();
        engine.getFoodList().clear();
        engine.getRedGhost().update();
        engine.getOrangeGhost().update();
        engine.getBlueGhost().update();
        Assert.assertTrue(engine.isWin());
    }

    @Test
    public void lifeTest(){
        PacEngine engine = new PacEngine();
        engine.getPacman().setLife(0);
        engine.getRedGhost().update();
        engine.getOrangeGhost().update();
        engine.getBlueGhost().update();
        Assert.assertTrue(engine.isLost());
    }
}
