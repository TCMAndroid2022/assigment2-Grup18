package cat.tecnocampus.mobileapps.practica2.oriacbonvehivilla.joanparpalberengue.marcbadalvalltori.grup_18;

public class GestorDeJoc {
    private String paraula;
    private boolean estat[];
    private int intents = 0;
    private int puntuacio = 0;
    private boolean jocComplet = false;


    public GestorDeJoc(String palabra) {
        this.paraula = palabra;
        estat = new boolean[palabra.length()];
        for(int i = 0; i<palabra.length();i++){
            estat[i] = false;
        }

    }


    public void afegirLletra(char letra) {
        intents++;
        for(int i = 0; i<paraula.length();i++){
            if(paraula.charAt(i) == letra){
                estat[i] = true;
            }
        }
    }

    public void resolver(String cadena ){
        jocComplet = true;
        if(cadena.equalsIgnoreCase(this.paraula)){
            int numeroLetras = paraula.length();
            puntuacio = (int)((double)(numeroLetras - intents) / (double )numeroLetras * 10.0);
        } else {
            puntuacio = 0;
        }

    }

    public String getCadenaPanel() {
        String cadena = "";
        for(int i = 0; i<paraula.length();i++){
            if(estat[i] == true) {
                cadena += paraula.charAt(i);
            }
            else {
                cadena += " _ ";
            }
        }
        return cadena;
    }

    public boolean finalitzat() {
        if(jocComplet == true){
            return true;
        }


        for(int i = 0; i<paraula.length();i++){
            if(estat[i] == false){
                return false;
            }
        }
        return true;
    }

    public int getPuntuacion(){
        return puntuacio;
    }

}
