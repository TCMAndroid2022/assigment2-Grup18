package cat.tecnocampus.mobileapps.practica2.oriacbonvehivilla.joanparpalberengue.marcbadalvalltori.grup_18;

public class GestorJuego {
    private String palabra;
    private boolean estado[];
    private int intentos = 0;
    private int puntuacion = 0;
    private boolean juegoCompletado = false;


    public GestorJuego(String palabra) {
        this.palabra = palabra;
        estado = new boolean[palabra.length()];
        for(int i = 0; i<palabra.length();i++){
            estado[i] = false;
        }

    }

    public void anadirLetra(char letra) {
        intentos++;
        for(int i = 0; i<palabra.length();i++){
            if(palabra.charAt(i) == letra){
                estado[i] = true;
            }
        }
    }

    public void resolver(String cadena ){
        juegoCompletado = true;
        if(cadena.equalsIgnoreCase(this.palabra)){
            int numeroLetras = palabra.length();
            puntuacion = (int)((double)(numeroLetras - intentos) / (double )numeroLetras * 10.0);
        } else {
            puntuacion = 0;
        }

    }

    public String getCadenaPanel() {
        String cadena = "";
        for(int i = 0; i<palabra.length();i++){
            if(estado[i] == true) {
                cadena += palabra.charAt(i);
            }
            else {
                cadena += " _ ";
            }
        }
        return cadena;
    }

    public boolean terminado() {
        if(juegoCompletado == true){
            return true;
        }


        for(int i = 0; i<palabra.length();i++){
            if(estado[i] == false){
                return false;
            }
        }
        return true;
    }

    public int getPuntuacion(){
        return puntuacion;
    }

}
