package Implementation;

public class FilterChecks {

    static boolean filterPause = false;   //turned true when email filtering is completed
    static boolean noPauseFilter = true; //turned true when there is no internet connection- used for pausing the email filtration
    static boolean stopSplitting = false;    //turned true when email splitting has completed

    public static String FILENAME = null;
    public static String FOLDERPATH = null;
    static boolean connectionLost = false;   // false means connection is not lost.
    public static int selectedThreadNumber = 0;

}
