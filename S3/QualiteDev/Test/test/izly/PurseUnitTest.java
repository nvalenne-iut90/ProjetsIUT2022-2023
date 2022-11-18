package izly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PurseUnitTest {

    private String goodPinCode = "9876";
    private String badPinCode = "1234";

    private CodeSecret secretCode;

    @BeforeEach
    public void setUp(){
        secretCode = Mockito.mock(CodeSecret.class);
        Mockito.when(secretCode.verifierCode(goodPinCode)).thenReturn(true);
        Mockito.when(secretCode.verifierCode(badPinCode)).thenReturn(false);
    }

    @Test
    public void testCredit() throws Exception {
        Purse purse = new Purse(1000, 100, secretCode);
        double solde = purse.getSolde();
        purse.credite(50);
        Assertions.assertEquals(solde+50, purse.getSolde());
    }

    @Test
    public void testDebit() throws Exception {
        Purse purse = new Purse(1000, 100, secretCode);
        purse.credite(50);
        double solde = purse.getSolde();
        purse.debite(50, goodPinCode);
        Assertions.assertEquals(solde-50, purse.getSolde());
    }

    @Test
    public void testSoldeJamaisNegatif() throws Exception{
        Purse purse = new Purse(1000, 100, secretCode);
        Assertions.assertThrows(SoldeNegatifInterditException.class, ()->purse.debite(purse.getSolde()+1, goodPinCode));
    }


    //JUnit4
//    @Test (expected = SoldeNegatifInterdit.class)
//    public void testSoldeJamaisNegatifJUnit4(){
//        Purse purse = new Purse();
//        purse.debite(purse.getSolde()+1);
//    }

    @Test
    public void testSoldeToujoursInferieurAuPlafond() throws Exception {
        double plafond = 100;
        Purse purse = new Purse(1000, plafond, secretCode);
        Assertions.assertThrows(DepassementPlafondInterditException.class, ()->purse.credite(plafond+1));
    }

    @Test
    public void testMontantNegatifInterdit() throws Exception{
        Purse purse = new Purse(1000, 100, secretCode);
        Assertions.assertThrows(MontantNegatifnterditException.class, ()->purse.credite(-50));
        Assertions.assertThrows(MontantNegatifnterditException.class, ()->purse.debite(-50, goodPinCode));
    }

    @Test
    public void testDureeDeVieLimite() throws Exception {
        Purse purse = new Purse(4, 100, secretCode);
        purse.credite(70);
        purse.debite(40, goodPinCode);
        Assertions.assertThrows(SoldeNegatifInterditException.class, ()->purse.debite(40, goodPinCode));
        purse.debite(10, goodPinCode);
        purse.credite(30);
        Assertions.assertThrows(NbOperationMaxAtteindException.class, ()->purse.credite(10));
        Assertions.assertThrows(NbOperationMaxAtteindException.class, ()->purse.debite(10, goodPinCode));
    }

    @Test
    public void testDebitRejeteSurCodeFaux() throws Exception {
        Mockito.when(secretCode.isBlocked()).thenReturn(true);
        Purse purse = new Purse(1000, 100, secretCode);
        Assertions.assertThrows(CodeFauxException.class, ()->purse.debite(20, goodPinCode));

    }

    @Test
    public void testDebitRejeteSurCodeBloque() throws Exception {
        Assertions.fail();
    }

}
