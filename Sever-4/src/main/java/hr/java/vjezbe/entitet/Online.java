package hr.java.vjezbe.entitet;

/**
 * Interface za ispite koji se održavaju online.
 */
public sealed interface Online permits Ispit{
    String software = "LMS";

    public default String programZaOnline(String program)
    {
        return software;
    }

}
