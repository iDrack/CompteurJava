import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class CompteurTableau{
    public final int TAILLE_INITIALE = 100;
    private int taille;             //Taille limite du tableau
    private int nbMots;             //Nombre de mots dans le tableau, ne compte que les mots de plus de 4 lettres
    private int nbMotsTotal;        //Nombre de mots
    private Mot[] elements;

    public void addOccurence(String mot){
        int trouve = recherche(mot);
        if(trouve == -1){
            //Si il n'existe pas deja
            if(nbMots >= taille-1){
                Mot[] tmp = {};
                taille = taille*2;
                this.elements = new Mot[taille];
                System.arraycopy(this.elements, 0, tmp, 0, this.elements.length);
                this.elements = tmp;
            }
            Mot m = new Mot(mot);
            elements[nbMots] = m;
            nbMots++;
        }
        else{
            elements[trouve].nouvelleOccurence();
        }
    }

    public CompteurTableau(String fichierTexte){
        this.taille = TAILLE_INITIALE;
        this.nbMots = 0;
        this.elements = new Mot[taille];
        this.compte(fichierTexte);
    }

    public int recherche(String mot){
        for(int i = 0; i < nbMots;i-=-1){
            if(Objects.equals(this.elements[i].getMot(), mot)) return i;
        }
        return -1;
    }

    public void trier(){
        for(int i = 0; i<this.nbMots;i++){
            Mot min = this.elements[i];
            int minId = i;
            for(int j = i+1; j<this.nbMots; j++){
                if(this.elements[j].getOccurence() > min.getOccurence()){
                    min = this.elements[j];
                    minId = j;
                }
            }
            Mot tmp = this.elements[i];
            this.elements[i] = min;
            this.elements[minId] = tmp;
        }
    }

    public void compte(String fichierTexte){
        try {
            Scanner scanner = new Scanner(new File(fichierTexte));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tabLine = line.split("\\s");

                for(int i = 0;i < tabLine.length; i++){
                    String mot = tabLine[i].toLowerCase();
                    //Gere les signes de ponctuations a retirer
                    if(mot.indexOf('.') > -1) mot = mot.replace(".", "");
                    if(mot.indexOf(',') > -1) mot = mot.replace(",", "");
                    if(mot.indexOf(':') > -1) mot = mot.replace(":", "");
                    if(mot.indexOf(')') > -1) mot = mot.replace(")", "");
                    if(mot.indexOf('(') > -1) mot = mot.replace("(", "");
                    if(mot.indexOf('}') > -1) mot = mot.replace("}", "");
                    if(mot.indexOf('{') > -1) mot = mot.replace("{", "");
                    if(mot.indexOf(']') > -1) mot = mot.replace("]", "");
                    if(mot.indexOf('[') > -1) mot = mot.replace("[", "");
                    if(mot.indexOf('-') > -1) mot = mot.replace("-", "");
                    if(mot.indexOf(';') > -1) mot = mot.replace(";", "");
                    if(mot.indexOf('\"') > -1) mot = mot.replace("\"", "");


                    if (mot.length()>4){
                       addOccurence(mot);
                    }
                    nbMotsTotal ++;
                }
            }
            scanner.close();

        }catch (Exception e){
            System.exit(1);
        }
    }


    public static void main(String[] args){
        if(args.length<1) System.out.println("manque le nom de fichier");
        else {
            System.out.println("Fichier : "+args[0]);
            CompteurTableau ct = new CompteurTableau(args[0]);
            System.out.println("Nombre de mots : "+ct.nbMotsTotal);
            System.out.println("Nombre de mots de taille > 4 : "+ct.nbMots);
            System.out.println("----------");
            System.out.println("Mots les plus fréquents :");
            ct.trier();
            for (int i = 0; i< ct.nbMots-1; i++){
                System.out.println(ct.elements[i].getOccurence()+" "+ct.elements[i].getMot());
            }
        }
    }
}
