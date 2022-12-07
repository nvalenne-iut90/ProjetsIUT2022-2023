import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {
        int nbElements=10000;

        ListOfWords lWords=new ListOfWords();
        List<String> l= lWords.randomSelect(nbElements);
        long start=System.currentTimeMillis();
        List<String> lfound = lWords.find(l);
        long end=System.currentTimeMillis();
        long timeElapsed = end - start;
        System.out.println("time with List "+timeElapsed);


        HashOfWords hWords=new HashOfWords();
        System.out.println(hWords.getMots());
        
        start=System.currentTimeMillis();
        lfound = hWords.findValuesList(l);
        end=System.currentTimeMillis();
        timeElapsed = end - start;
        System.out.println("time with HashMap values List "+timeElapsed);


        start=System.currentTimeMillis();
        lfound = hWords.findValuesToSet(l);
        end=System.currentTimeMillis();
        timeElapsed = end - start;
        System.out.println("time with HashMap values converted to Set "+timeElapsed);


        start=System.currentTimeMillis();
        lfound = hWords.findKeys(l);
        end=System.currentTimeMillis();
        timeElapsed = end - start;
        /*for(String s : lfound){
            System.out.println(s);
        }*/
        System.out.println("time with HashMap keys "+timeElapsed);


    }

}