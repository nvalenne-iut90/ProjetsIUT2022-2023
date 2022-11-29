package izly;

import izly.exception.*;

public class Purse {
    private double solde = 0;
    private double plafond;
    public int dureeVieMax;
    private CodeSecret codeSecret;

    public Purse(double plafond, int dureeVieMax, CodeSecret secretCode) {
        this.plafond = plafond;
        this.dureeVieMax = dureeVieMax;
        this.codeSecret = secretCode;
    }

    public double getSolde() {
        return solde;
    }

    public void credite(double montant, String codePin) throws OperationRejeteeException {
        if (dureeVieMax <= 0) throw new OperationRejeteeException(new DureeVieDepassee());
        if (montant < 0) throw new OperationRejeteeException(new MontantNegatifnterdit());
        if (montant+solde > plafond) throw new OperationRejeteeException(new DepassementPlafondInterdit());
        if (codeSecret.isBlocked()) throw new OperationRejeteeException(new CodeBloqueExeption());
        if (!codeSecret.verifierCode(codePin)) throw new OperationRejeteeException(new CodeFauxExeption());
        solde += montant;
        dureeVieMax--;
    }

    public void debite(double montant, String codePin) throws OperationRejeteeException {
        if (dureeVieMax <= 0) throw new OperationRejeteeException(new DureeVieDepassee());
        if (montant < 0) throw new OperationRejeteeException(new MontantNegatifnterdit());
        if (montant > solde) throw new OperationRejeteeException(new SoldeNegatifInterdit());
        if (codeSecret.isBlocked()) throw new OperationRejeteeException(new CodeBloqueExeption());
        if (!codeSecret.verifierCode(codePin)) throw new OperationRejeteeException(new CodeFauxExeption());
        solde -= montant;
        dureeVieMax--;
    }
}
