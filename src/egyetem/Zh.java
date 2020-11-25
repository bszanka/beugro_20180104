package egyetem;

import java.time.LocalDateTime;

public class Zh extends Szamonkeres {

    public Zh(String nev, LocalDateTime kezdes, boolean irasbeli, int[] pontszam) {
        super(nev, kezdes, irasbeli, pontszam);
        super.setIrasbeli(true);
    }

    @Override
    public String toString() {
        return super.toString().replace("számonkérés", "zh");
    }
}
