
public class Uebung1_7 {
    // Klassendefinition:
    static class Auto {
        // Beschreibung des Autotyps.
        String typ;
        // Autofarbe
        String farbe;
        // Baujahr
        int bauJahr;
        // Kilometerstand
        int kmStand;
        // Inspektion alle x Kilometer.
        int inspektionsIntervall;

        public Auto() {
            this.typ = "Unbekannt";
            this.farbe = "Unbekannt";
            this.bauJahr = 0;
            this.kmStand = 0;
            this.inspektionsIntervall = 15_000;
        }

        public Auto(String farbe) {
            this();
            this.farbe = farbe;
        }

        public Auto(String farbe, int bauJahr) {
            this(farbe);
            this.bauJahr = bauJahr;
        }

        public Auto(
            String typ, 
            String farbe, 
            int bauJahr, 
            int kmStand, 
            int inspektionsIntervall
        ) {
            this(farbe, bauJahr);
            this.typ = typ;
            this.kmStand = kmStand;
            this.inspektionsIntervall = inspektionsIntervall;
        }
    }

        static class Bsp extends Auto {
            void ändereFarbe(String farbe) {
                this.farbe = "rot";
            }
        }

        public static void main(String[] args) {

            Auto meinAuto = new Auto();
            meinAuto.farbe = "blau";

            Bsp bsp = new Bsp();

            bsp.ändereFarbe(meinAuto.farbe);
            System.out.println(meinAuto.farbe);
        }
}
