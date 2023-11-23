import arbolcl1.ArbolBinario;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

    public MainTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSample() {
        assertEquals(2, 2);
    }
     public void pruebaDeteccionSubarbol() {
        ArbolBinario arbolPrincipal = new ArbolBinario();
        arbolPrincipal.reconstruirArbol("d,b,e,a,f,c,g", "a,b,d,e,c,f,g");

     }
}
}
