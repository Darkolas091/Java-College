package hr.java.vjezbe.entitet;

public sealed interface Online permits Ispit{
    String software = "LMS";

    public default String programZaOnline(String program)
    {
        return software;
    }

}
