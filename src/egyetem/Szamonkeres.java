package egyetem;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Szamonkeres implements Comparable<Szamonkeres> {
    private String megnevezes;
    private LocalDateTime kezdes;
    private boolean irasbeli;
    private int[] pontszam;

    public Szamonkeres(String megnevezes, LocalDateTime kezdes, boolean irasbeli, int[] pontszam) {
        this.megnevezes = megnevezes;
        this.kezdes = kezdes;
        this.irasbeli = irasbeli;
        this.pontszam = pontszam;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
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
        sb.append(kezdes.getYear());
        sb.append(kezdes.getMonth());
        sb.append(kezdes.getDayOfYear());
        sb.append(" " + kezdes.getHour());
        sb.append(kezdes.getMinute());
        return sb.toString();
    }

    public int maxPontszam(int[] pontszam){
        int max = 0;
        for (int i = 0; i < pontszam.length; i++) {
            if(pontszam[i] >= max)
                max = pontszam[i];
        }
        return max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Szamonkeres)) return false;
        Szamonkeres that = (Szamonkeres) o;
        return Objects.equals(megnevezes, that.megnevezes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(megnevezes);
    }

    @Override
    public String toString() {
        return this.megnevezes + (this.irasbeli ? " írásbeli számonkérés ("
                : " szóbeli számonkérés (") + kezdesToString(kezdes) + ")";
    }

    @Override
    public int compareTo(Szamonkeres o) {
        int kulonbseg =
                kezdesToString(this.kezdes).compareTo(kezdesToString(o.kezdes));
        if(kulonbseg != 0)
            return this.megnevezes.compareTo(o.megnevezes);
        return -kulonbseg;
    }
}
