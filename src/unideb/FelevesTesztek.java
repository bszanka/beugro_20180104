package unideb;

import egyetem.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FelevesTesztek implements Tanulnivalok {
    private String felevAzonosito;
    private List <Szamonkeres> felevesTesztek = new ArrayList<>();

    public FelevesTesztek(String felevAzonosito, List<Szamonkeres> felevesTesztek) {
        this.felevAzonosito = felevAzonosito;
        this.felevesTesztek = felevesTesztek;
    }

    @Override
    public void hozzaad(Szamonkeres teszt) {
        this.felevesTesztek.add(teszt);
    }

    @Override
    public double atlagPontszam() throws NincsTesztException {
        int sum = 0;
        int db = 0;
        for (Szamonkeres s : felevesTesztek) {
            int[] pontszamokTomb = s.getPontszam();
            for (int j : pontszamokTomb) {
                sum += j;
                db++;
            }
        }
        return (double)sum/(double)db;
    }

    // visszaadja a számonkérések listáját a természetes rendezettségük sorrendjében;
    // ha az első paraméter igaz, akkor csak a vizsgákat, ha hamis, akkor csak a
    // zh-kat, ha null, akkor mindegyiket; ha a második paraméter igaz, akkor csak az
    // írásbeliket, ha hamis, akkor csak a szóbeliket, ha null, akkor mindegyiket

    @Override
    public List<Szamonkeres> tesztek(Boolean vizsgak, Boolean irasbelik) {
        List<Szamonkeres> res = new ArrayList<>();
        if(!vizsgak && !irasbelik)
            System.out.println("Üres a lista!");
//        if(vizsgak == null && irasbelik == null) {
//            res.addAll(this.felevesTesztek);
//            return res;
//        }
        if(!vizsgak && (irasbelik/* || irasbelik == null*/)){
            for (Szamonkeres s : this.felevesTesztek) {
                if(s instanceof Zh)
                    res.add(s);
            }
            return res;
        }
       if(vizsgak && irasbelik) {
           for (Szamonkeres s : this.felevesTesztek) {
               if (s instanceof Vizsga && s.isIrasbeli())
                   res.add(s);
           }
       return res;
       }
        if((vizsgak/* || vizsgak == null*/) && !irasbelik) {
            for (Szamonkeres s : this.felevesTesztek) {
                if (s instanceof Vizsga && !s.isIrasbeli())
                    res.add(s);
            }
        return res;
        }
//        if(vizsgak == null && irasbelik) {
//            for (Szamonkeres s : this.felevesTesztek) {
//                if (s.isIrasbeli())
//                    res.add(s);
//            }
//            return res;
//        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder res = null;
        res.append(felevAzonosito + "\n");
        for (Szamonkeres s : felevesTesztek) {
            res.append(s.toString() + "\n");
        }
        return res.toString();
    }
}
