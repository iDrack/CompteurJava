public class CompteurTest extends Compteur {

    public void addOccurence(String mot){
        System.out.println(mot);
    }

    public CompteurTest(String f){
        super(f);
    }

    public static void main(String[] args){
        if(args.length<1) System.out.println("manque le nom de fichier");
        else {
            CompteurTest c = new CompteurTest(args[0]);
        }
    }
}
