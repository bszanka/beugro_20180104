package egyetem;

import java.time.LocalDateTime;

public class Vizsga extends Szamonkeres {
    public Vizsga(String nev, LocalDateTime kezdes, boolean irasbeli, int[] pontszam) {
        super(nev, kezdes, irasbeli, pontszam);
    }

    @Override
    public String toString() {
        return super.toString().replace("számonkérés","vizsga");
    }
}
