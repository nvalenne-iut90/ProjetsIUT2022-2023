import java.io.*;
import java.util.*;

public class HashOfWords {
    Map<Integer, String> mots;
    HashOfWords(){
        this.mots = new HashMap<>();
        try {
            FileReader fr = new FileReader("liste_mot_francais.txt");
            BufferedReader br = new BufferedReader(fr);
            int increment_value = 1;
            String line;
            do {
                line = br.readLine();
                mots.put(increment_value,line);
                increment_value++;
            } while (line != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<Integer, String> getMots() {
        return mots;
    }

    public List<String> findValuesList(List<String> l) {
        List<String> newListe = new ArrayList<>();
        for (String mot : l){
            if (mots.containsValue(mot)) 
                newListe.add(mot);
        }

        return newListe;
    }

    public List<String> findValuesToSet(List<String> l) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>(mots.values());
    
        for (String mot : l){
            if (set.contains(mot)){
                res.add(mot);
            }
        }
        return res;
    }

    public List<String> findKeys(List<String> l) {
        List<String> newListe = new ArrayList<>();
        for (String mot : l){
            if (mots.containsKey(mot.hashCode())) 
                newListe.add(mot);
        }

        return newListe;
    }
    
}
