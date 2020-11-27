package teszt;

import egyetem.NincsTesztException;
import egyetem.Szamonkeres;
import egyetem.Vizsga;
import egyetem.Zh;
import unideb.FelevesTesztek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Teszt {
    public static boolean keres(List<Szamonkeres> szk, String nev, String fajlnev) {
        boolean van = false;
        try {
            van = false;
            int sum = 0;
            StringBuilder sb = new StringBuilder();
            FileWriter fw = new FileWriter(new File(fajlnev));
            for (Szamonkeres sz : szk) {
                // a feladat equals-t kért
                if (sz.getNev().contains(nev)) {
                    van = true;
                    sb.append(sz.getNev() + " ("
                            + sz.kezdesToString(sz.getKezdes())
                            + ") Max pontszám: " + sz.maxPontszam(sz.getPontszam())+ " pont \n");
                    sum += sz.maxPontszam(sz.getPontszam());
                }
            }
            sb.append(sum);
//            System.out.println(sb.toString());
            fw.append(sb.toString());
            // MINDIG ZÁRJUK BE, MERT FLUSHOLJA A TARTALMÁT HA NEM!
            fw.close();
            System.out.println((van ? "A keresés sikeres volt." : "Nincs ilyen nevű számonkérés!"));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Nem sikerült megnyitni a fájlt!");
        }
        return van;
    }

    public static void main(String[] args) {
        if (args[0].length() == 0)
            System.err.println("Nincs megadva parancssori argumentum!");
        try {
            List<Szamonkeres> szk = new ArrayList<>();
            Scanner fajl = new Scanner(new File(args[0]));
            while (fajl.hasNextLine()) {
                Scanner sor = new Scanner(fajl.nextLine());
                sor.useDelimiter(";");
                boolean zh = true;
                if (sor.next().equals("V"))
                    zh = false;
                String nev = sor.next();

                sor.useDelimiter("\\W");
                int ev = Integer.parseInt(sor.next());
                int ho = Integer.parseInt(sor.next());
                int nap = Integer.parseInt(sor.next());
                int ora = Integer.parseInt(sor.next());
                int perc = Integer.parseInt(sor.next());
                LocalDateTime kezdes = LocalDateTime.of(ev, ho, nap, ora, perc);


                boolean irasbeli = true;
                if (!zh && sor.next().equals("S"))
                    irasbeli = false;
                int db = 1;
                sor.useDelimiter("\n");
                String maradek = sor.next();
                for (int i = 0; i < maradek.length(); i++) {
                    if (maradek.charAt(i) == ',')
                        db++;
                }
                Scanner maradekScan = new Scanner(maradek);
                maradekScan.useDelimiter("\\W");
                int[] pontszamok = new int[db];
                int i = 0;
                while (maradekScan.hasNext()) {
                    pontszamok[i] = Integer.parseInt(maradekScan.next());
                    i++;
                }


                szk.add(zh ? new Zh(nev, kezdes, irasbeli, pontszamok)
                        : new Vizsga(nev, kezdes, irasbeli, pontszamok));
            }
            for (Szamonkeres sz : szk) {
                System.out.println(sz.toString());
            }

            FelevesTesztek ft = new FelevesTesztek(args[1].length() > 0 ? args[1] : "2017/2018/1.félév", szk);
//            Tesztek:
//            System.out.println("Átlag: " + ft.atlagPontszam() + " pont."); //5.6
//
//            List<Szamonkeres> teszt = new ArrayList<>(ft.tesztek(true, false));
//            for (Szamonkeres sz : teszt) {
//                System.out.println(sz.toString());
//            }
            keres(szk,"rendszer", "kimenet.txt");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Nem található ilyen nevű fájl!");
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
//        catch (NincsTesztException e) {
//            e.printStackTrace();
//        }
    }
}

