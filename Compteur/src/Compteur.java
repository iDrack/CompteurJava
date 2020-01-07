import java.io.File;
import java.util.Scanner;

public abstract class Compteur {
    private String nomFichier;
    public int nbMots,nbMotsTotal;
    public abstract void addOccurence(String mot);

    public Compteur(String fichierTexte){
        nomFichier = fichierTexte;
        nbMots = 0;
        nbMotsTotal = 0;
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
                        //N'ajoute le mot que si sa longueur est d'au moins 5
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
}
