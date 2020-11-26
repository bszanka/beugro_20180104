package unideb;

import egyetem.NincsTesztException;
import egyetem.Szamonkeres;
import egyetem.Tanulnivalok;

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
            for (int i = 0; i < pontszamokTomb.length; i++) {
                if(pontszamokTomb[i] != 0) {
                    sum += pontszamokTomb[i];
                    db++;
                }
            }
        }
        double atlag = (double)sum/(double)db;
        return atlag;
    }

    @Override
    public List<Szamonkeres> tesztek(Boolean vizsgak, Boolean irasbelik) {
        return null;
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
