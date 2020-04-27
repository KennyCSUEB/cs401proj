package gamarket;

import org.junit.Test;

import gamarket.Tile.Type;

import static org.junit.Assert.assertEquals;

import java.io.File;

import static org.junit.Assert.assertArrayEquals;
/**
 * Unit test for simple App.
 */
public class GridTest 
{
    public static int[] startSpot = {10, 10};

    @Test
    public void playerStartPostiion_is_10_10 () {
        Grid testGrid = new Grid();
        testGrid.loadData("new", true);
        assertArrayEquals(startSpot, testGrid.getPlayerPosition());
    }
    @Test
    public void s_Makes_Player_Go_Down () {
        Grid testGrid = new Grid();
        testGrid.loadData("new", true);
        testGrid.updateGrid("s");
        int[] oneDown = {10, 11};
        assertArrayEquals( oneDown, testGrid.getPlayerPosition());
    }
    
    @Test
    public void w_Makes_Player_Go_Up () {
        Grid testGrid = new Grid();
        testGrid.loadData("new", true);
        testGrid.updateGrid("w");
        int[] oneUp = {10 , 9};
        assertArrayEquals(oneUp, testGrid.getPlayerPosition() );
    }

    @Test
    public void d_Makes_Player_Go_right () { //fix
        Grid testGrid = new Grid();
        testGrid.loadData("new", true);
        testGrid.updateGrid("d");
        int[] oneRight = {11, 10};
        assertArrayEquals(oneRight, testGrid.getPlayerPosition());
    }

    @Test
    public void a_Makes_Player_Go_left () {
        Grid testGrid = new Grid();
        testGrid.loadData("new", true);
        testGrid.updateGrid("a");
        int[] oneLeft = {9, 10};
        assertArrayEquals(oneLeft, testGrid.getPlayerPosition());
    }

    @Test
    public void player_Cant_Move_off_Grid () {
        Grid testGrid = new Grid();
        testGrid.loadData("new", true);
        testGrid.setPlayerPosition(9,0);
        testGrid.updateGrid("a");
        int position[] = {9,0};
        assertArrayEquals(position, testGrid.getPlayerPosition());

    }

    @Test
    public void player_Cant_Move_into_nonPerme () {
        Grid testGrid = new Grid();
        testGrid.loadData("new", true);
        testGrid.changeTile(10, 11, Tile.Type.TREE);
        testGrid.updateGrid("s");
        assertArrayEquals(startSpot, testGrid.getPlayerPosition());
    }

    @Test
    public void save_Load_test () {
        Grid testGrid = new Grid(20, 20, 10, 10);
        testGrid.loadData("new", true);
        testGrid.changeTile(3, 2, Type.WATER);

        testGrid.save("save_Loadtest", true);
        Grid freshGrid = new Grid();
        freshGrid.loadData("save_Loadtest",true);
        
        System.out.println(freshGrid.toString());
        assertEquals(Type.WATER, freshGrid.getTile(3, 2).getType() );
        
        int[] expectedLoc = { 10, 10};
        assertArrayEquals(expectedLoc, freshGrid.getPlayerPosition());

        File file = new File("./databaseFiles/gridFiles/save_Loadtest_gridData.txt");
        file.delete();
    }
}
