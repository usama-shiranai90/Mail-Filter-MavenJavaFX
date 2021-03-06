package Implementation;

import Utility.Utilities;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailFilter {

    private int totalEmails = 0;
    private final Object firstKey = new Object();
    private final Object secondKey = new Object();
    private final Object thirdKey = new Object();
    private final ArrayList<String> emails = new ArrayList<>();
    private final ArrayList<String> filteredEmails = new ArrayList<>();
    ArrayList<String> domainList = new ArrayList<>();//stores domainList from input file
    private final ArrayList<String> files = new ArrayList();
    private final String verifiedEmails = "VerifiedEmails";
    boolean completed = false;
    //    ArrayList<String> emailList = new ArrayList<>();//stores emailList from input file

    public void filterThemAll(int hop) {
        try {
            synchronized (firstKey) {
                if (!FilterChecks.stopSplitting) {
                    emails.forEach((data) -> {
                        String emailByLine = data;
                        emailByLine = emailByLine.replaceFirst("^*?@", " ");   /*               checks for  and remove a preceding characters , e.g followed by any sequence of chars    followed by a '@' ,  from the beginning of the string  */
                        int index = emailByLine.indexOf(" ");
                        emailByLine = emailByLine.substring(index + 1);
                        domainList.add(emailByLine);
                    });
                    System.out.println("Domain Size: " + domainList.size());
                    FilterChecks.stopSplitting = true;
                }
            }

            int domainCounter;
            int threadRunCount = 1;
            if (FilterChecks.selectedThreadNumber == 1 && threadRunCount == 1) {
                domainCounter = hop;
                threadRunCount++;
            } else {
                domainCounter = hop;
            }

            ProcessBuilder processBuilder = new ProcessBuilder();

            while (domainCounter < (domainList.size())) {


                if (!filteredEmails.contains(emails.get(domainCounter))) {
                    processBuilder.command("cmd.exe", "/c", "nslookup -type=mx  " + domainList.get(domainCounter) + " | findstr \"mail exchanger =\"");
                } else {
                    if (!FilterChecks.connectionLost)
                        domainCounter += hop;
                    continue;
                }
                if (FilterChecks.selectedThreadNumber == 1 && threadRunCount > 1) {
                    hop = 1;
                }
                Process process = processBuilder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    if (line.contains(domainList.get(domainCounter).toString()) && !filteredEmails.contains(emails.get(domainCounter))) {
                        if (!line.contains("mail exchanger = " + domainList.get(domainCounter))) {
//                            hopCount = 1;

                            String combinedExchanger = null;
                            if (line.contains("mail exchanger = localhost")) {
                                combinedExchanger = "localhost";
                            } else {
                                String[] mailExchanger = line.split("\\.");
                                combinedExchanger = mailExchanger[mailExchanger.length - 2];
                            }
                            String fileDirectory = System.getProperty("user.dir");
                            synchronized (secondKey) {
                                createFile(fileDirectory, combinedExchanger);
                                String details = emails.get(domainCounter) + "\n";
                                writeToFile(fileDirectory, combinedExchanger, details);
                                filteredEmails.add(emails.get(domainCounter));
                            }

                            break;
                        } else {
                            if (!filteredEmails.contains(emails.get(domainCounter))) {
                                String errorsDirectory = System.getProperty("user.dir");
                                synchronized (secondKey) {
                                    createFile(errorsDirectory, "Invalid Domains");
                                    writeToFile(errorsDirectory, "Invalid Domains", emails.get(domainCounter).toString() + "\n");
                                    filteredEmails.add(emails.get(domainCounter));

                                }
                            }

                        }
                    } else if (!filteredEmails.contains(emails.get(domainCounter))) {
                        String errorsDirectory = System.getProperty("user.dir");
                        synchronized (thirdKey) {
                            createFile(errorsDirectory, "Non-Existent Domains");
                            writeToFile(errorsDirectory, "Non-Existent Domains", emails.get(domainCounter).toString() + "\n");
                            filteredEmails.add(emails.get(domainCounter));

                        }
                    }

                }
                if (!FilterChecks.connectionLost)
                    domainCounter += hop;

                if (Thread.currentThread().getName().equalsIgnoreCase("Thread-1") && FilterChecks.selectedThreadNumber != 1)
                    break;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Internet is not connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(filteredEmails.size() == emails.size() && !completed){
            Utilities utilities = new Utilities();
            Platform.runLater(() ->  utilities.erorrmessageBox(new Stage(), "Email Filtration Completed Successfully"));
            completed = true;
        }

    }

    public int getTotalEmails() {
        return totalEmails;
    }

    public int totalNumberOfEmails() {

        File myObj = new File(FilterChecks.FILENAME);
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                emails.add(data);
                totalEmails++;
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return totalEmails;
    }

    private void createFile(String path, String nameOfFile) {
        try {
            File file = new File(String.format("%s\\%s", path, verifiedEmails));

            if (!file.isDirectory()) {
                file.mkdir();
                file = new File(String.format("%s\\%s\\%s.txt", path, verifiedEmails, nameOfFile));
            } else
                file = new File(String.format("%s\\%s\\%s.txt", path, verifiedEmails, nameOfFile));

            if (!files.contains(nameOfFile)) {
//                System.out.println("File " + nameOfFile + " exists therefore rewriting it.");
                file.delete();
                file.createNewFile();
                files.add(nameOfFile);
            } else {
//                System.out.println("File already exists");
            }


        } catch (IOException e) {
            System.out.println("An error occurred while creating the file " + nameOfFile);
            e.printStackTrace();
        }
    }

    private void writeToFile(String path, String nameOfFile, String line) {
        try {
            File file = new File(String.format("%s\\%s\\%s.txt", path, verifiedEmails, nameOfFile));
//            System.out.println("filepath = " + file);
            if (file.exists()) {
                FileWriter myWriter = new FileWriter(String.format("%s\\%s\\%s.txt", path, verifiedEmails, nameOfFile), true);
                myWriter.write(line);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } else {
                System.out.println("File " + nameOfFile + " does not exist");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}