package izly;

import izly.exception.CodeBloqueException;

import java.util.Random;

public class CodeSecret {

    private boolean alreadyRevealed;

    private String code;
    private int nbEssai;

    private CodeSecret(String code) {
        this.code = code;
        alreadyRevealed = false;
        nbEssai=3;
    }

    public static CodeSecret creerCode(Random random){
        String code = "";
        for (int i = 0; i < 4; i++) {
            code += random.nextInt(10);
        }
        return new CodeSecret(code);
    }

    public boolean verifierCode(String codePin) throws CodeBloqueException {
        if (isBlocked()) throw new CodeBloqueException();
        if (!code.equals(codePin)){
            nbEssai--;
            return false;
        }
        nbEssai = 3;
        return true;
    }

    public boolean isBlocked(){
        return nbEssai <= 0;
    }

    public String revelerCode() {
        if (alreadyRevealed)
            return "xxxx";
        else {
            alreadyRevealed = true;
            return code;
        }
    }
}
