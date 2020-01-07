import java.io.File;
import java.util.Scanner;

public class CompteurHash extends Compteur{
    public final int TAILLE_INITIALE = 100000;
    private Mot[] elements;

    @Override
    public void addOccurence(String mot) {
        if(elements == null){
            elements = new Mot[TAILLE_INITIALE];
        }
        //Ajout du mot dans la table
        Mot m = new Mot(mot);
        inserer(m,hashage(m));
    }

    public CompteurHash(String fichierTexte){
        super(fichierTexte);
    }

    //Les fonctions de gestion de la hash table
    private int hashage(Mot m){
        //Fonction de hashage, additionne tous les valeur alphabétique des lettres du mot pour créer sa cle
        String[] tmp = m.getMot().split("");
        int somme = 0;
        for(int i = 0;i<tmp.length;i++){
            somme += charToInt(tmp[i].toCharArray()[0]);
        }
        return somme;
    }

    private void inserer(Mot m, int key){
        //Insere dans la table eleemnts le Mot m, de cle key
        if(recherche(key) == null){
            //Cas ou la cle est libre
            //On insére m
            elements[key] = m;
            nbMots++;
            return;
        }
        if(recherche(key).getMot().equals(m.getMot())){
            //Cas ou la cle correspond
            elements[key].nouvelleOccurence();
        }else{
            //Cas ou la cle a deja ete prise
            int newKey = key+(TAILLE_INITIALE/2);
            inserer(m,newKey);
        }

    }

    private Mot recherche(int key){
        //Retourne le mot de cle key si il existe dans la table elements
        if(appartient(key)){
            return elements[key];
        }else return null;
    }

    private boolean appartient(int key){
        //Recherche dans la table elements si un mot est associé a la cle key
        return elements[key] != null;
    }

    private int charToInt(char x){return (int)x - (int)'a'+1;}

    private void affichage(){
        boolean trouve = false;
        int max = -1;
        do {
            if(!trouve) {
                for (int i = 0; i < elements.length; i++) {
                    if (elements[i] != null) {
                        if (elements[i].getOccurence() > max) {
                            max = elements[i].getOccurence();
                            trouve = true;
                        }
                    }
                }
            }
            if(trouve){
                for (int i = 0; i < elements.length; i++) {
                    if(elements[i] != null){
                        if(elements[i].getOccurence() == max){
                            System.out.println(elements[i].getOccurence()+" "+elements[i].getMot());
                        }
                    }
                }
                max--;
            }

        }while(max != 0);
    }

    public static void main(String[] args){
        if(args.length != 1){
            System.out.println("Manque le fichier.");
            System.exit(1);
        }

        System.out.println("Fichier : "+args[0]);
        CompteurHash ch = new CompteurHash(args[0]);
        System.out.println("Nombre de mots : "+ch.nbMotsTotal);
        System.out.println("Nombre de mots de taille > 4 : "+ch.nbMots);
        System.out.println("----------");
        System.out.println("Mots les plus fréquents :");
        ch.affichage();
    }

}
