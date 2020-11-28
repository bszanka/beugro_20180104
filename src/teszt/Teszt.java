package teszt;

import egyetem.*;
import unideb.FelevesTesztek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

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

    public static int szobeliDarabszam(Tanulnivalok[] tomb){
        int db = 0;
        for (Tanulnivalok t : tomb) {
            if(t.toString().contains("szóbeli")){
                db++;
                System.out.println("Szóbeli found!");}
        }
        return db;
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
            System.out.println("7. Feladat: ");
            FelevesTesztek ft = new FelevesTesztek(args[1].length() > 0 ? args[1] : "2017/2018/1.félév", szk);
            System.out.println(ft.toString());
//            //Megnézni miért dob NullPointerException-t, ha null a paraméter. Boolean is lehet null!?
//            List<Szamonkeres> teszt = new ArrayList<>(ft.tesztek(null, true));
//            for (Szamonkeres sz : teszt) {
//                System.out.println(sz.toString());
//            }

            System.out.println("8. Feladat: ");
            System.out.println("Kérem adja meg, hogy mely tárgy számonkérését keresi: ");
            Scanner stdin = new Scanner(System.in);
            stdin.useDelimiter("\n");
            String input1 = stdin.next();
            System.out.println("Kérem adja meg, hogy mely fájlba kívánja menteni: ");
            String input2 = stdin.next();
            System.out.println(input1 + "\n" + input2);
            keres(szk,input1, input2);

            System.out.println("9. Feladat: ");
            Set<Zh> atlagFolottiek = new HashSet<>(ft.zhAtlagFolott());
            for (Zh z : atlagFolottiek) {
                System.out.println(z.toString());
            }

            System.out.println("10. Feladat: ");
            ft.nagybetusit("2018");
            System.out.println(ft.toString());
            System.out.println("11. Feladat: ");
            Tanulnivalok[] tomb = new Tanulnivalok[1];
            tomb[0] = ft;
            System.out.println(szobeliDarabszam(tomb));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Nem található ilyen nevű fájl!");
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        catch (NincsTesztException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
}

