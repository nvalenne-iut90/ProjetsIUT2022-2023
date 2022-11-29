package izly;

import izly.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PurseUnitTest {

    protected String pinCode = "0666";

    public CodeSecret codeSecret;

    @BeforeEach
    public void setup() throws CodeBloqueException {
        codeSecret = Mockito.mock(CodeSecret.class);
        Mockito.when(codeSecret.verifierCode(pinCode)).thenReturn(true);
    }

    @Test
    public void testCredit() throws DepassementPlafondInterdit, MontantNegatifnterdit, DureeVieDepassee, CodeFauxException, CodeBloqueException {
        Purse purse = new Purse(100, 10, codeSecret);
        double solde = purse.getSolde();
        purse.credite(50, pinCode);
        Assertions.assertEquals(solde+50, purse.getSolde());
    }

    @Test
    public void testDebit() throws Exception {
        Purse purse = new Purse(100, 10, codeSecret);
        purse.credite(50, pinCode);
        double solde = purse.getSolde();
        purse.debite(50, pinCode);
        Assertions.assertEquals(solde-50, purse.getSolde());
    }

    @Test
    public void testSoldeJamaisNegatif(){
        Purse purse = new Purse(100, 10, codeSecret);
        Assertions.assertThrows(SoldeNegatifInterdit.class, ()->purse.debite(purse.getSolde()+1, pinCode));
    }

    /*
JUnit4
    @Test (expected = SoldeNegatifInterdit.class)
    public void testSoldeJamaisNegatifJUnit4(){
        Purse purse = new Purse();
        purse.debite(purse.getSolde()+1);
    }
*/

    @Test
    public void testSoldeToujoursInferieurAuPlafond(){
        double plafond = 100;
        Purse purse = new Purse(plafond, 10, codeSecret);
        Assertions.assertThrows(DepassementPlafondInterdit.class, ()->purse.credite(plafond+1, pinCode));
    }

    @Test
    public void testMontantNegatifInterdit(){
        Purse purse = new Purse(100, 10, codeSecret);
        Assertions.assertThrows(MontantNegatifnterdit.class, ()->purse.credite(-50, pinCode));
        Assertions.assertThrows(MontantNegatifnterdit.class, ()->purse.debite(-50, pinCode));

    }

    @Test
    public void testDureeViePorteMonaie() throws DepassementPlafondInterdit, MontantNegatifnterdit, DureeVieDepassee, SoldeNegatifInterdit, CodeFauxException, CodeBloqueException {
        Purse purse = new Purse(100, 2, codeSecret);
        purse.credite(50, pinCode);
        Assertions.assertThrows(SoldeNegatifInterdit.class, ()->purse.debite(60, pinCode));
        purse.debite(40, pinCode);
        Assertions.assertThrows(DureeVieDepassee.class, ()-> purse.credite(50, pinCode));
        Assertions.assertThrows(DureeVieDepassee.class, ()-> purse.debite(50, pinCode));
    }

    @Test
    public void testDebitSurCodeFaux() throws DureeVieDepassee, DepassementPlafondInterdit, MontantNegatifnterdit, CodeFauxException, CodeBloqueException {
        String badPinCode = "1234";
        Mockito.when(codeSecret.verifierCode(badPinCode)).thenReturn(false);
        Purse purse = new Purse(100, 50, codeSecret);
        purse.credite(50, pinCode);
        Assertions.assertThrows(CodeFauxException.class, ()->purse.debite(20, badPinCode));
    }

}
