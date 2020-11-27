package egyetem;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Szamonkeres implements Comparable<Szamonkeres> {
    private String nev;
    private LocalDateTime kezdes;
    private boolean irasbeli;
    private int[] pontszam;

    public Szamonkeres(String nev, LocalDateTime kezdes, boolean irasbeli, int[] pontszam) {
        this.nev = nev;
        this.kezdes = kezdes;
        this.irasbeli = irasbeli;
        this.pontszam = pontszam;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public LocalDateTime getKezdes() {
        return kezdes;
    }

    public void setKezdes(LocalDateTime kezdes) {
        this.kezdes = kezdes;
    }

    public boolean isIrasbeli() {
        return irasbeli;
    }

    public void setIrasbeli(boolean irasbeli) {
        this.irasbeli = irasbeli;
    }

    public int[] getPontszam() {
        return pontszam;
    }

    public void setPontszam(int[] pontszam) {
        this.pontszam = pontszam;
    }

    public String kezdesToString(LocalDateTime kezdes){
        StringBuilder sb = new StringBuilder();
        sb.append(kezdes.getYear() + ". ");
        sb.append((kezdes.getMonthValue() < 10 ? "0": "") + kezdes.getMonthValue() + ". ");
        sb.append((kezdes.getDayOfMonth() < 10 ? "0": "") + kezdes.getDayOfMonth() + ". ");
        sb.append((kezdes.getHour() < 10 ? "0": "") + kezdes.getHour() + ":");
        sb.append((kezdes.getMinute() < 10 ? "0": "") + kezdes.getMinute());
        return sb.toString();
    }

    public int maxPontszam(int[] pontszam){
        int max = 0;
        for (int i : pontszam) {
            max += i;
        }
        return max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Szamonkeres)) return false;
        Szamonkeres that = (Szamonkeres) o;
        return Objects.equals(nev, that.nev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nev);
    }

    @Override
    public String toString() {
        return this.nev + (this.irasbeli ? " írásbeli számonkérés ("
                : " szóbeli számonkérés (") + kezdesToString(kezdes) + ")";
    }

    @Override
    public int compareTo(Szamonkeres o) {
        int kulonbseg =
                kezdesToString(kezdes).compareTo(kezdesToString(o.kezdes));
        if(kulonbseg == 0)
            return nev.compareTo(o.nev);
        return -kulonbseg;
    }
}
