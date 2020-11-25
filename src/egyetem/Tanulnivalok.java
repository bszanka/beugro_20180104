package egyetem;

public interface Tanulnivalok {
    // új számonkérést ad hozzá a tanulnivalókhoz
    public void hozzaad(Szamonkeres teszt);
    // visszaadja a számonkérések átlagos pontszámát; kivételt dob, ha egyetlen
    // számonkérés sincs a nyilvántartásban
    public double atlagPontszam() throws NincsTesztException;
    // visszaadja a számonkérések listáját a természetes rendezettségük sorrendjében;
    // ha az első paraméter igaz, akkor csak a vizsgákat, ha hamis, akkor csak a
    // zh-kat, ha null, akkor mindegyiket; ha a második paraméter igaz, akkor csak az
    // írásbeliket, ha hamis, akkor csak a szóbeliket, ha null, akkor mindegyiket
    public java.util.List<Szamonkeres> tesztek(Boolean vizsgak, Boolean irasbelik);
}
