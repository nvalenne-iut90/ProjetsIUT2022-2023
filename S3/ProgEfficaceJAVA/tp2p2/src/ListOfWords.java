import java.util.*;
import java.io.*;
public class ListOfWords {
    FileReader fr;
    List<String> mots;
    public ListOfWords(){
        this.mots = new ArrayList<>();
        try {
            fr = new FileReader("liste_mot_francais.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            do {
                line = br.readLine();
                mots.add(br.readLine());
            } while (line != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<String> getMots() {
        return mots;
    }

    public List<String> randomSelect(int nbElements){
        List<String> liste = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < nbElements; i++){
            liste.add(mots.get(random.nextInt(mots.size())));
        }        
        return liste;
    }

    public List<String> find(List<String> l){
        List<String> listeCopy = new ArrayList<>();

        for (String mot : l){
            listeCopy.add(mot);
            if (mots.indexOf(mot) == -1){
                listeCopy.add("no");
            } else {
                listeCopy.add("yes");
            }
        }

        return listeCopy;
    }
}
