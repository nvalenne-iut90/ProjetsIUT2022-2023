package izly;

public class Purse {

    private double solde = 0;
    private double plafond;
    private int nbOpMaxAuth;
    private CodeSecret codeSecret;

    public Purse(int nbOpMaxAuth, double plafond, CodeSecret codeSecret) {
        this.plafond = plafond;
        this.nbOpMaxAuth = nbOpMaxAuth;
        this.codeSecret = codeSecret;
    }

    public double getSolde() {
        return solde;
    }

    public void credite(double montant) throws DepassementPlafondInterditException, MontantNegatifnterditException, NbOperationMaxAtteindException {
        if (montant < 0) throw new MontantNegatifnterditException();
        if (nbOpMaxAuth <= 0) throw new NbOperationMaxAtteindException();
        if (montant+solde > plafond) throw new DepassementPlafondInterditException();
        solde += montant;
        nbOpMaxAuth--;
    }

    public void debite(double montant, String codePin) throws SoldeNegatifInterditException, MontantNegatifnterditException, NbOperationMaxAtteindException, CodeFauxException {
        if (nbOpMaxAuth <= 0) throw new NbOperationMaxAtteindException();
        if (montant < 0) throw new MontantNegatifnterditException();
        if (montant > solde) throw new SoldeNegatifInterditException();
        if (!codeSecret.verifierCode(codePin)) throw new CodeFauxException();
        solde -= montant;
        nbOpMaxAuth--;
    }
}
