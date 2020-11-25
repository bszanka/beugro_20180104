package teszt;

import egyetem.Szamonkeres;
import egyetem.Vizsga;
import egyetem.Zh;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Teszt {

    public static void main(String[] args) {

        if(args[0].length() == 0)
            System.err.println("Nincs megadva parancssori argumentum!");
        try {
            List<Szamonkeres> szk = new ArrayList<>();
            Scanner fajl = new Scanner(new File(args[0]));
            while(fajl.hasNextLine()) {
                Scanner sor = new Scanner(fajl.nextLine());
                sor.useDelimiter(";");
                boolean zh = true;
                if(sor.next().equals("V"))
                    zh = false;
                String nev = sor.next();

                sor.useDelimiter(";");
                int ev = Integer.parseInt(sor.next());
                int ho = Integer.parseInt(sor.next());
                sor.useDelimiter(";");
                int nap = Integer.parseInt(sor.next());

                sor.useDelimiter(":");
                int ora = Integer.parseInt(sor.next());
                sor.useDelimiter(";");
                int perc = Integer.parseInt(sor.next());

                LocalDateTime kezdes = LocalDateTime.of(ev, ho, nap, ora, perc);
                System.out.println(kezdes.toString());
                boolean irasbeli = true;
                if(!zh) {
                    if (sor.next().equals("S"))
                        irasbeli = false;
                }
                int[] pontszamok = new int[0];
                sor.useDelimiter(",");
                while(sor.hasNext()) {
                    int i = 0;
                    pontszamok[i] = sor.nextInt();
                }

                szk.add(zh ? new Zh(nev, kezdes, irasbeli, pontszamok)
                        : new Vizsga(nev, kezdes, irasbeli, pontszamok));
            }
            for (Szamonkeres sz : szk) {
                sz.toString();
            }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.err.println("Nem található ilyen nevű fájl!");
            } catch (IllegalArgumentException e) {
                e.getMessage();
            }
        }
    }

