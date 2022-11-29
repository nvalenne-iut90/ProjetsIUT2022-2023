package izly;

import izly.exception.CodeBloqueException;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

public class CodeSecretUnitTest {

    private CodeSecret codeSecret;

    private final String badCode = "1111";
    private MyRandom random;
    private final String goodPinCode = "9876";

    @BeforeEach
    public void setup(){
        random = Mockito.mock(MyRandom.class);
        Mockito.when(random.nextInt(10)).thenReturn(9,8,7,6);
        codeSecret = CodeSecret.creerCode(random);
    }

    @Test
    public void testRevelationCode(){
        Assertions.assertEquals("9876", codeSecret.revelerCode());
        Assertions.assertEquals("xxxx", codeSecret.revelerCode());
        Assertions.assertEquals("xxxx", codeSecret.revelerCode());
        Mockito.verify(random, Mockito.times(4)).nextInt(10);
    }

    @Test
    public void testVerifierCodeSurCodeJusteEtFaux() throws CodeBloqueException {
        Assertions.assertTrue(codeSecret.verifierCode("9876"));
        Assertions.assertFalse(codeSecret.verifierCode("1234"));
    }

    @Test
    public void testCodeBloqueApresTroisEssaisFauxSuccessifs() throws CodeBloqueException {
        codeSecret.verifierCode(badCode);
        codeSecret.verifierCode(badCode);
        Assertions.assertFalse(codeSecret.isBlocked());
        codeSecret.verifierCode(badCode);
        Assertions.assertTrue(codeSecret.isBlocked());
    }

    @Test
    public void testNbEssaiReinitialiseApresCodeJuste() throws CodeBloqueException {
        codeSecret.verifierCode(badCode);
        codeSecret.verifierCode(badCode);
        codeSecret.verifierCode(goodPinCode);
        codeSecret.verifierCode(badCode);
        codeSecret.verifierCode(badCode);
        Assertions.assertFalse(codeSecret.isBlocked());
        codeSecret.verifierCode(badCode);
        Assertions.assertTrue(codeSecret.isBlocked());

    }

}
