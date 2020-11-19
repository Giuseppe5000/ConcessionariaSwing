public class Automobile
{
        private String marca;
        private String modello;
        private int cilindrata;
        private int potenza;
        private int euro;
        private int posti;
        private int porte;
        private String targa;

        public Automobile(String marca, String modello, int cilindrata, int potenza, int euro, int posti, int porte,String targa)
        {
                this.marca = marca;
                this.modello = modello;
                this.cilindrata = cilindrata;
                this.potenza = potenza;
                this.euro = euro;
                this.posti = posti;
                this.porte = porte;
                this.targa = targa;
        }

        public String getTarga()
        {
                return targa;
        }

        public void setTarga(String targa)
        {
                this.targa = targa;
        }

        public Automobile()
        {
        }

        public String getMarca()
        {
                return marca;
        }

        public void setMarca(String marca)
        {
                this.marca = marca;
        }

        public String getModello() {
                return modello;
        }

        public void setModello(String modello)
        {
                this.modello = modello;
        }

        public int getCilindrata()
        {
                return cilindrata;
        }

        public void setCilindrata(int cilindrata)
        {
                if (cilindrata >= 500 && cilindrata <= 30000) {
                        this.cilindrata = cilindrata;
                }
                else {
                        this.cilindrata = 500;
                }
        }

        public int getPotenza()
        {
                return potenza;
        }

        public void setPotenza(int potenza)
        {
                if (potenza >= 1 && potenza <= 200) {
                        this.potenza = potenza;
                }
                else {
                        this.potenza = 1;
                }

        }

        public int getEuro()
        {
                return euro;
        }

        public void setEuro(int euro)
        {

                if(euro>=0 && euro <=6) {
                        this.euro = euro;
                }
                else {
                        this.euro = 0;
                }
        }

        public int getPosti()
        {
                return posti;
        }

        public void setPosti(int posti)
        {
                if (posti >= 1 && posti <= 7) {
                        this.posti = posti;
                }
                else {
                        this.posti = 1;
                }
        }

        public int getPorte()
        {
                return porte;
        }

        public void setPorte(int porte)
        {
                if (porte >=2 && porte <=5) {
                        this.porte = porte;
                }
                else {
                        this.porte = 2;
                }
        }

        public double potenzaCavalli()
        {
                int potenzakw = this.potenza;

                //1cv :  0.73549875c = cv : potenzakw

                final double kw = 0.73549875;

                return potenzakw / kw;
        }

        public double importoTassa()
        {
                final double peuro = this.potenza -100;
                final double potenza = this.potenza;
                double tot = 0;

                switch (this.euro) {
                        case 0:
                                if(potenza <= 100 ) {
                                        tot += potenza*3.30;
                                }
                                else if(potenza >100) {
                                        tot += 100*3.30;
                                        tot += peuro*4.95;
                                }

                                break;

                        case 1:
                                if(potenza <= 100 ) {
                                        tot += potenza*3.19;
                                }
                                else if(potenza >100) {
                                        tot += 100*3.19;
                                        tot += peuro*4.79;
                                }

                                break;

                        case 2:
                                if(potenza <= 100 ) {
                                        tot += potenza*3.08;
                                }
                                else if(potenza >100) {
                                        tot = 100*3.08;
                                        tot += peuro*4.62;
                                }

                                break;

                        case 3:
                                if(potenza <= 100 ) {
                                        tot += potenza*2.97;
                                }
                                else if(potenza >100) {
                                        tot += 100*2.97;
                                        tot += peuro*4.46;
                                }
                                break;

                        default:
                                if(potenza <= 100 ) {
                                        tot += potenza*2.84;
                                }
                                else if(potenza >100) {
                                        tot += 100*2.84;
                                        tot += peuro*4.26;
                                }

                                break;

                }

                return tot;

        }



        public String toString()
        {
                return "Marca: "+this.marca+"\nModello: "+this.modello+"\nCilindrata: "+this.cilindrata+"\nPotenza: "+this.potenza+"\nEuro: "+this.euro+"\nPosti: "+this.posti+"\nPorte: "+this.porte+"\nTarga: "+this.targa+"\nCavalli: "+this.potenzaCavalli()+"\nImporto emissioni: "+this.importoTassa();
        }

}
