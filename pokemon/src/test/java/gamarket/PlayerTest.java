package gamarket;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class PlayerTest {
    Player player;

    @Test
    public void startUpTest () {
        File file = new File("uglyrat.txt");
        assertEquals(false, file.exists());
            try {
                if (file.exists() == false){
                    if (file.createNewFile()) {
                        System.out.println("File created: " + file.getName());
                    } else {

                        System.out.println("File already exists.\n Path: " + file.getAbsolutePath());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        assertEquals(true, file.exists());


    }
}
