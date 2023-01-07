package Src.Exceptions;
public class NoSuchImageException extends RuntimeException {

    public NoSuchImageException () {
        super("La tuile crée n'a aucune image correspondante, Obligation d'en créer une nouvelle") ; 
    }
}
