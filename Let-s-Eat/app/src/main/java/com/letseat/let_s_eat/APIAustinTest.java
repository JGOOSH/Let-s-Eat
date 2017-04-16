import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.junit.Assert.*;

import java.io.IOException;

/**
 * Created by Hojae Jung on 3/3/2017.
 */

public class APIAustinTest {

    GooglePlacesAPIHandler gpah;

    public void createHandler() {
        if(gpah == null) {
            try {
                gpah = new GooglePlacesAPIHandler(30.2882, -97.7401);
            } catch(IOException e) {
                fail("API Not Created");
            }
        }
    }

    @Test
    public void searchWhataburger() {
        createHandler();
        assertTrue(gpah.getSearchResult("Whataburger").getFirst().getName().equals("Whataburger"));
    }

    @Test
    public void searchWhataburgerPhotos() {
        createHandler();
        Place whataburger = gpah.getSearchResult("Whataburger").getFirst();
        assertTrue(gpah.getImages(whataburger, 10000, 10000) != null);
    }
}
