package Implementation;

public class FilterChecks {

    static boolean filterPause = false;   //turned true when email filtering is completed

    static boolean noPauseFilter = true; //turned true when there is no internet connection- used for pausing the email filtration

    static int interruptedThreads = 0;    //turned false when email splitting has completed

    public static String FILENAME = null;

    static boolean connectionLost = false;

    public static int selectedThreadNumber = 0 ;
}
