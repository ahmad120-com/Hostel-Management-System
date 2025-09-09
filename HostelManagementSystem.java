import java.io.*;
import java.util.*;

class HostelManagementSystem {
    public static void main(String[] args) {
        createFiles();
        createRoomOccupancyFile();
        boolean exitProgram = false;
        Scanner input = new Scanner(System.in);

        while (!exitProgram) {
            System.out.println(" --------------------------------------- ");
            System.out.println("|--Welcome to Hostel management system--|");
            System.out.println("|              MAIN MENU                |");
            System.out.println("|---------------------------------------|");
            System.out.println("|           ---- LOGIN ----             |");
            System.out.println("|  Enter #1 -> Admin Portal             |");
            System.out.println("|  Enter #2 -> Hostelite Portal         |");
            System.out.println("|---------------------------------------|");
            System.out.println("|            ---- SIGN UP ----          |");
            System.out.println("|  Enter #3 -> create a new application |");
            System.out.println("|---------------------------------------|");
            System.out.println("|  Enter #4 -> View Hostel Rules        |");
            System.out.println("|  Enter #0 -> Exit Program             |");
            System.out.println(" ---------------------------------------");
            System.out.print(" Enter you choice: ");
            String userInput1 = input.next();

            if (userInput1.equals("1")) {
                adminPortal();
            } else if (userInput1.equals("2")) {
                hostelitePortal();
            } else if (userInput1.equals("3")) {
                new_application();
            } else if (userInput1.equals("4")) {
                hostelRules();
            } else if (userInput1.equals("0")) {
                exitProgram = true;
            } else {
                System.out.println("Invalid choice.");
            }


        }
    }

    public static void createFiles() {
        String[] fileNames = {
                "application.txt",
                "roomOccupancy.txt",
                "visitorLog.txt",
                "notification.txt",
                "request.txt",
                "fee.txt"
        };

        for (String fileName : fileNames) {
            File file = new File(fileName);
            try {
                if (file.createNewFile()) {
                    System.out.println(fileName + " created successfully!");
                } else {
                    System.out.println(fileName + " already exists.");
                }
            } catch (IOException e) {
                System.err.println("An error occurred while creating " + fileName + ": " + e.getMessage());
            }
        }
    }

    public static void createRoomOccupancyFile() {
        String fileName = "roomOccupancy.txt";
        File file = new File(fileName);

        try {
            // Check if the file is empty
            if (file.length() == 0) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    for (int i = 1; i <= 20; i++) {
                        writer.write("" + i + ",Vacant,Vacant");
                        writer.newLine();
                    }
                    System.out.println("roomOccupancy.txt has been created and populated.");
                } catch (IOException e) {
                    System.err.println("An error occurred while writing to the file: " + e.getMessage());
                }
            } else {
                System.out.println("The file is not empty. No changes were made.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred while checking the file: " + e.getMessage());
        }
    }

    public static void hostelRules() {
        System.out.println("HOSTEL RULES:");
        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("| 1. Quiet hours: Respect designated quiet hours to ensure everyone can rest peacefully.                                                  |");
        System.out.println("| 2. No smoking: Most hostels have no-smoking policies inside the building for the comfort and safety of all guests.                      |");
        System.out.println("| 3. Clean up after yourself: Keep common areas tidy and clean up after using the kitchen or any shared facilities.                       |");
        System.out.println("| 4. Respect others' privacy: Avoid disturbing other guests and respect their personal space and belongings.                              |");
        System.out.println("| 5. No drugs or illegal activities: Hostels typically have strict policies against drug use and any illegal activities on the premises.  |");
        System.out.println("| 7. Lockers: Use provided lockers to secure your valuables and always lock your dorm room when you leave.                                |");
        System.out.println("| 8. Guests: Inform the hostel staff if you plan to have guests over, and ensure they adhere to hostel rules.                             |");
        System.out.println("| 9. Use of facilities: Be mindful of other guests when using shared facilities like bathrooms, showers, and the kitchen.                 |");
        System.out.println("| 10. Respect the staff: Treat hostel staff with kindness and respect their authority in enforcing hostel rules.                          |");
        System.out.println("| 11. No pets: Most hostels do not allow pets, so make sure to leave your furry friends at home.                                          |");
        System.out.println("| 12. Socialize responsibly: Be considerate of others and avoid disturbing them during late-night socializing.                            |");
        System.out.println("| 13. Be mindful of energy and water usage: Conserve resources by turning off lights, fans, and taps when not in use.                     |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

    }

    public static void adminPortal() {
        int loginStatus = adminLogIn();
        Scanner input = new Scanner(System.in);
        boolean exitProgram = false;
        if (loginStatus == 2) {
            while (!exitProgram) {
                System.out.println(" ------------------------------------------- ");
                System.out.println("|          ---- ADMIN PORTAL ----           |");
                System.out.println("| Enter #1 -> view all applications         |");
                System.out.println("| Enter #2 -> see all notifications         |");
                System.out.println("| Enter #3 -> Review Hostel Fee Submissions |");
                System.out.println("| Enter #4 -> Review all Room Occupation    |");
                System.out.println("| Enter #5 -> Visitor Log and Details       |");
                System.out.println(" ------------------------------------------- ");
                System.out.println("| Enter #0 -> Return to Main Menu           |");
                System.out.println(" ------------------------------------------- ");
                try {
                    System.out.print("Choice: ");
                    int userInput = input.nextInt();
                    switch (userInput) {
                        case 1:
                            applications();
                            break;
                        case 2:
                            adminNotificationManagement();
                            break;
                        case 3:
                            reviewFeeSubmission();
                            break;
                        case 4:
                            currentRoomResidents();
                            break;
                        case 5:
                            visitorDetails();
                            break;
                        case 0:
                            exitProgram = true;
                            break;
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input. Please enter a number.");
                    input.next();
                }
            }
        } else {
            System.out.println("You have already tried to login 5 times. Please try again later.");
        }
    }

    public static int adminLogIn(){
        int flag = 1;
        Scanner input = new Scanner(System.in);
        int count = 0;


        while (flag == 1) {
            System.out.print(" Enter the Login password: ");
            String userInput = input.nextLine();
            String password = "!@#$%^&*()";
            count++;
            if (userInput.equals(password))
                flag = 2;
            else
                System.out.println("Invalid Password. Please Try again.");

            if (count == 5)
                flag = 3;

        }
        return flag;
    }

    public static void hostelitePortal() {
        boolean loopFlag = false;
        Scanner input = new Scanner(System.in);

        System.out.print(" Enter your hostel ID: ");
        String userInHostelID = input.next();
        boolean pass2 = hosteliteLogIn(userInHostelID);

        while (!loopFlag){
            if (pass2) {
                System.out.println("--------------------------------------------------");
                System.out.println("|           ---- HOSTELITE PORTAL ----           |");
                System.out.println("| Enter #1 -> View Application                   |");
                System.out.println("| Enter #2 -> Fee Submission Status              |");
                System.out.println("| Enter #3 -> Request to change or vacant Room   |");
                System.out.println("| Enter #4 -> Hostel Rules and GuideLines        |");
                System.out.println("| Enter #5 -> Complaints/ Requests               |");
                System.out.println("--------------------------------------------------");
                System.out.println("| Enter #0 -> Return to Main Menu                |");
                System.out.println("--------------------------------------------------");

                System.out.print("Choice: ");
                String userInput3 = input.next();

                switch (userInput3) {
                    case "1":
                        displayApplication(userInHostelID);
                        continue;
                    case "2":
                        studentFeeSubmission(userInHostelID);
                        continue;
                    case "3":
                        roomChangeOrVacant(userInHostelID);
                        continue;
                    case "4":
                        hostelRules();
                        continue;
                    case "5":
                        hosteliteNotificationSubmission(userInHostelID);
                        continue;
                    case "0":
                        loopFlag = true;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            } else {
                break;
            }
        }


    }

    public static boolean hosteliteLogIn(String userInHostelID){
        boolean flag = false;
        boolean passFlag = false;

        try{
            File file = new File("application.txt");
            Scanner reader = new Scanner(file);
            Scanner input = new Scanner(System.in);



            System.out.print("Enter your password: ");
            String userInPassword = input.nextLine();
            while (reader.hasNext()) {
                String[] line = (reader.nextLine().split(","));
                String hostelID = line[0];
                String password = line[1];

                if (userInHostelID.equals(hostelID)){
                    if (userInPassword.equals(password)) {
                        flag = true;
                        System.out.println("LoggedIn successfully");
                        System.out.println();
                    }
                }
            }
            if (!flag)
                System.out.println("Invalid ID or password ");
            reader.close();
        }
        catch (IOException ex){
            System.out.println("Error in pass try");
        }

        return flag;
    }

    public static void visitorDetails() {

        boolean flag = false;
        Scanner input = new Scanner(System.in);

        try {
            while (!flag) {
                int count = 1;
                System.out.println(" ------------------------------------------ ");
                System.out.println("|         ---- Visitor Log ----           | ");
                System.out.println(" ------------------------------------------ ");
                System.out.println("| Enter #1 -  View Previous Visitors      | ");
                System.out.println("| Enter #2 -  Log Visitor                 | ");
                System.out.println("| Enter #0 -  Return to Admin Portal      | ");
                System.out.println(" ------------------------------------------ ");
                System.out.print("Choice: ");

                int choice = input.nextInt();
                input.nextLine(); // consume the newline character
                File file = new File("visitorLog.txt");

                if (choice == 1) {
                    try (Scanner reader = new Scanner(file)) {
                        while (reader.hasNext()) {
                            String[] visitorDetailsList = reader.nextLine().split(",");
                            System.out.println("----------------------------------------------");
                            System.out.println("Visitor Number " + count);
                            System.out.println("----------------------------------------------");
                            System.out.println("Name               : " + visitorDetailsList[0]);
                            System.out.println("Date of Visit      : " + visitorDetailsList[1]);
                            System.out.println("Phone number       : " + visitorDetailsList[2]);
                            System.out.println("Visiting ID number : " + visitorDetailsList[3]);
                            System.out.println("----------------------------------------------");
                            System.out.println();
                            count++;
                        }
                        System.out.println("Total Number of Visitors: " + (count - 1));
                    } catch (IOException ex) {
                        System.out.println("Error reading file: " + ex.getMessage());
                    }
                } else if (choice == 2) {
                    System.out.println();

                    System.out.print("Enter Visitors Full Name: ");
                    String name1 = input.nextLine();
                    while (!isValidName(name1)) {
                        System.out.print("Please Enter a Valid Full Name: ");
                        name1 = input.nextLine();
                    }

                    System.out.print("Enter the date of your visit in DD-MM-YYYY format: ");
                    String bDate = input.nextLine();
                    while (!isValidNumericInput(bDate) || bDate.length() != 10 || bDate.charAt(2) != '-' || bDate.charAt(5) != '-' || bDate.charAt(0) > '3' || bDate.charAt(1) > '9' || bDate.charAt(3) > '1' || (bDate.charAt(3) == '1' && bDate.charAt(4) > '2')) {
                        System.out.println("Invalid input. Please enter date in DD-MM-YYYY format: ");
                        bDate = input.nextLine();
                    }


                    System.out.print("Contact Number (digits only): ");
                    String phoneNumber = input.nextLine();
                    while (!isValidNumericInput(phoneNumber)) {
                        System.out.print("Invalid input. Please enter contact number (digits only): ");
                        phoneNumber = input.nextLine();
                    }

                    System.out.print("Visiting Student ID number: ");
                    String idNumber = input.nextLine();
                    while (!isValidNumericInput(idNumber)) {
                        System.out.print("Invalid input. Please enter visiting ID number (digits only): ");
                        idNumber = input.nextLine();
                    }

                    try (FileWriter fileWriter = new FileWriter(file, true);
                         PrintWriter writer = new PrintWriter(fileWriter)) {
                        writer.println(name1 + "," + bDate + "," + phoneNumber + "," + idNumber);
                        System.out.println("Visitor logged successfully.");
                    } catch (IOException ex) {
                        System.out.println("Error writing to file: " + ex.getMessage());
                    }

                } else if (choice == 0) {
                    flag = true;
                    break;


                } else {
                    System.out.println("Enter a Valid Number.");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: Enter a valid number." );
        }
    }

    public static void new_application() {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("To create a new application please enter the following information.");
        System.out.println("-------------------------------------------------------------------");

        System.out.print("Please enter your full name: ");
        String name = input.nextLine();
        while (!isValidName(name)) {
            System.out.println("Invalid name. Please enter again: ");
            name = input.nextLine();
        }

        System.out.print("Enter your date of birth in DD-MM-YYYY format: ");
        String bDate = input.nextLine();
        while (!isValidNumericInput(bDate) || bDate.length() != 10 || bDate.charAt(2) != '-' || bDate.charAt(5) != '-' || bDate.charAt(0) > '3' || bDate.charAt(1) > '9' || bDate.charAt(3) > '1' || (bDate.charAt(3) == '1' && bDate.charAt(4) > '2')) {
            System.out.println("Invalid input. Please enter date of birth in DD-MM-YYYY format: ");
            bDate = input.nextLine();
        }

        System.out.print("Enter your CNIC number in DDDDD-DDDDDDD-D format: ");
        String cnic = input.nextLine();
        while (!isValidNumericInput(cnic) || cnic.length() != 15 || cnic.charAt(5) != '-' || cnic.charAt(13) != '-') {
            System.out.println("Invalid input. Please Enter your CNIC number in DDDDD-DDDDDDD-D format: ");
            cnic = input.nextLine();
        }

        System.out.print("Contact Number (digits only): ");
        String phoneNumber = input.nextLine();
        while (!isValidNumericInput(phoneNumber)) {
            System.out.println("Invalid input. Please enter contact number (digits only): ");
            phoneNumber = input.nextLine();
        }

        System.out.print("Guardian's Name: ");
        String gName = input.nextLine();
        while (!isValidName(gName)) {
            System.out.println("Invalid name. Please enter again: ");
            gName = input.nextLine();
        }

        System.out.print("Guardian's Contact Number (digits only): ");
        String gPhoneNumber = input.nextLine();
        while (!isValidNumericInput(gPhoneNumber)) {
            System.out.println("Invalid input. Please enter guardian's contact number (digits only): ");
            gPhoneNumber = input.nextLine();
        }



        String hostelID = String.valueOf(idCreator());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Your Hostel ID is: " + hostelID);
        System.out.println("NOTE: Don't forget your hostel ID. Kindly note it down somewhere.");
        System.out.println("-------------------------------------------------------------------");

        System.out.print("Create a new password to log in: ");
        String password = input.nextLine();

        String roomNumber = "";
        try {
            File file1 = new File("roomOccupancy.txt");
            Scanner reader = new Scanner(file1);
            String roomData = "";
            String[][] rooms = new String[20][3];
            int index = 0;

            while (reader.hasNext()) {
                String roomLine = reader.nextLine();
                String[] roomDetails = roomLine.split(",");
                rooms[index][0] = roomDetails[0];
                rooms[index][1] = roomDetails[1];
                rooms[index][2] = roomDetails[2];
                index++;
                roomData += roomLine + "\n";

                System.out.println("-------------------------------------------------------------------");
                System.out.println("Room Number " + roomDetails[0]);
                System.out.println("Left Bed: " + (roomDetails[1].equals("Vacant") ? "Available. " : "Unavailable. "));
                System.out.println("Right Bed: " + (roomDetails[2].equals("Vacant") ? "Available." : "Unavailable."));
                System.out.println("-------------------------------------------------------------------");
                System.out.println();
            }
            reader.close();

            System.out.print("Enter the room number you want to occupy: ");
            roomNumber = input.nextLine();
            boolean validRoom = false;

            while (!validRoom) {
                for (int i = 0; i < rooms.length; i++) {
                    if (rooms[i][0].equals(roomNumber) && (rooms[i][1].equals("Vacant") || rooms[i][2].equals("Vacant"))) {
                        if (rooms[i][1].equals("Vacant")) {
                            rooms[i][1] = hostelID;
                        } else {
                            rooms[i][2] = hostelID;
                        }
                        validRoom = true;
                        break;
                    }
                }
                if (!validRoom) {
                    System.out.print("Invalid or unavailable room number. Please enter a valid room number: ");
                    roomNumber = input.nextLine();
                }
            }

            FileWriter roomWriter = new FileWriter(file1);
            for (int i = 0; i < rooms.length; i++) {
                roomWriter.write(rooms[i][0] + "," + rooms[i][1] + "," + rooms[i][2] + "\n");
            }
            roomWriter.close();

        } catch (IOException ex) {
            System.out.println("Error in reading or writing file: " + ex.getMessage());
        }

        System.out.println();
        generateInvoice();
        System.out.println();

        // Prompt the user if they have paid the fee or not
        String feeStatus = " ";
        boolean validInput = false;
        while (!validInput) {
            System.out.print("What is your fee status (Paid/Unpaid): ");
            feeStatus = input.nextLine().toLowerCase();
            if (feeStatus.equals("paid") || feeStatus.equals("unpaid")) {
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'paid' or 'unpaid'.");
            }
            if (feeStatus.equals("unpaid")) {
                System.out.println("Please make sure to submit your fee within 5 days to avoid penalties.");
            }
        }

        try {
            // Save fee payment status along with hostel ID in the fee file
            try (PrintWriter writer = new PrintWriter(new FileWriter("fee.txt", true))) {
                writer.println(hostelID + "," + feeStatus);
                System.out.println("Fee payment status saved successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while saving fee payment status: " + e.getMessage());
            }

            // Save application details
            FileWriter file = new FileWriter("application.txt", true);
            PrintWriter writer = new PrintWriter(file);
            writer.println(hostelID + "," + password + "," + name + "," + bDate + "," + cnic + "," + phoneNumber + "," + gName + "," + gPhoneNumber + "," + roomNumber);
            System.out.println("Your account has been created");
            System.out.println();
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error in writing: " + ex.getMessage());
        }
    }

    public static boolean isValidName(String str) {
        for (int i =0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isLetter(c) &&  c != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidNumericInput(String str) {
        for (int i =0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && c!='-' ) {
                return false;
            }
        }
        return true;
    }

    public static int idCreator(){
        int hostelID = (int)(Math.random()*1000);
        try {

            File file = new File("application.txt");
            Scanner reader = new Scanner(file);
            String[] application = new String[8];

            boolean flag;
            while (true) {
                flag = true;
                while (reader.hasNext()) {
                    application = reader.nextLine().split(",");
                    if (application[0].equals(String.valueOf(hostelID))) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    hostelID = (int) (Math.random() * 1000);
                }
                else
                    break;
            }

        }
        catch (IOException ex){
            System.out.println("Error in creating hostel ID");
        }
        return hostelID;
    }

    public static void displayApplication(String userInHostelID) {
        try (Scanner reader = new Scanner(new File("application.txt"))) {
            while (reader.hasNext()) {
                String[] application = (reader.nextLine().split(","));
                if (userInHostelID.equals(application[0])) {
                    System.out.println(" --------------------------------------------------- ");
                    System.out.println("Hostel ID       : " + application[0]);
                    System.out.println("Password        : " + application[1]);
                    System.out.println("Name            : " + application[2]);
                    System.out.println("Date of birth   : " + application[3]);
                    System.out.println("CNIC            : " + application[4]);
                    System.out.println("Contact Number  : " + application[5]);
                    System.out.println("Guardian's Name : " + application[6]);
                    System.out.println("Guardian's phone number: " + application[7]);
                    System.out.println("Room Number     : " + application[8]);
                    System.out.println(" --------------------------------------------------- ");
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading application file: " + ex.getMessage());
        }
    }

    public static void reviewFeeSubmission() {
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("--------------------------------------------------");
            System.out.println("|     ---- ADMIN FEE REVIEW PORTAL ----          |");
            System.out.println("| Enter #1 -> View All Fee Submissions           |");
            System.out.println("| Enter #2 -> Change Fee Status of a Student     |");
            System.out.println("--------------------------------------------------");
            System.out.println("| Enter #0 -> Return to Admin Menu               |");
            System.out.println("--------------------------------------------------");

            System.out.print("Choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    viewAllFeeSubmissions();
                    break;
                case 2:
                    changeFeeStatus();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Input. Please enter a number.");
                    break;
            }
        }
    }

    public static void viewAllFeeSubmissions() {
        try {
            File file = new File("fee.txt");
            Scanner reader = new Scanner(file);
            System.out.println("HostelID, FeeStatus");
            System.out.println("-------------------");

            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the fee file: " + e.getMessage());
        }
        System.out.println("-------------------");
    }

    public static void changeFeeStatus() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter HostelID: ");
            String hostelID = input.nextLine();

            String newStatus = "";
            boolean validStatus = false;
            while (!validStatus) {
                System.out.print("Enter new FeeStatus (paid/unpaid): ");
                newStatus = input.nextLine().toLowerCase();
                if (newStatus.equals("paid") || newStatus.equals("unpaid")) {
                    validStatus = true;
                } else {
                    System.out.println("Invalid status. Please enter 'paid' or 'unpaid'.");
                }
            }

            File file = new File("fee.txt");
            String[] lines;

            // Read the file and count the number of lines
            int numOfLines = 0;
            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextLine()) {
                    reader.nextLine();
                    numOfLines++;
                }
            }

            lines = new String[numOfLines];

            // Read the file again and update the lines
            boolean found = false;
            try (Scanner reader = new Scanner(file)) {
                int index = 0;
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] parts = line.split(",");
                    if (parts[0].equals(hostelID)) {
                        line = hostelID + "," + newStatus;
                        found = true;
                    }
                    lines[index++] = line;
                }
            }

            // Write the modified lines back to the file
            if (found) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                    for (String line : lines) {
                        writer.println(line);
                    }
                    System.out.println("Fee status updated successfully.");
                }
            } else {
                System.out.println("Hostel ID not found. Fee status not updated.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the fee file: " + e.getMessage());
        }

    }

    public static void studentFeeSubmission(String hostelID) {
        try {
            File file = new File("fee.txt");
            Scanner reader = new Scanner(file);
            String feeStatus = "";
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(hostelID)) {
                    feeStatus = parts[1];
                    break;
                }
            }
            reader.close();

            if (!feeStatus.isEmpty()) {
                System.out.println("Your fee status: " + feeStatus);
            } else {
                System.out.println("No fee record found for your hostel ID.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the fee file: " + e.getMessage());
        }


    }

    public static void roomChangeOrVacant(String hostelID) {
        Scanner input = new Scanner(System.in);
        System.out.println(" ------------------------------------------------- ");
        System.out.println("| Enter #1 - Shift Room                           |");
        System.out.println("| Enter #2 - Vacant Room                          |");
        System.out.println(" ------------------------------------------------- ");
        System.out.println("NOTE: Please inform 1 month prior to vacate the room");
        System.out.println(" ------------------------------------------------- ");
        System.out.print("Choice: ");
        String userChoice = input.nextLine();

        if (userChoice.equals("1")) {
            int currRoomNo;
            while (true) {
                try {
                    System.out.println("Which room you are currently in: ");
                    currRoomNo = input.nextInt();
                    input.nextLine();  // Consume newline

                    if (checkRoomOccupancy(hostelID, currRoomNo, 0)) {
                        break;
                    } else {
                        System.out.println("Invalid current room number. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid room number.");
                    input.nextLine();  // Clear invalid input
                }
            }

            int reqRoomNo;
            while (true) {
                try {
                    System.out.println("Which room you want to change to: ");
                    reqRoomNo = input.nextInt();
                    input.nextLine();  // Consume newline

                    if (!checkRoomOccupancy(hostelID, 0, reqRoomNo)) {
                        break;
                    } else {
                        System.out.println("Requested room is already occupied. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid room number.");
                    input.nextLine();  // Clear invalid input
                }
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter("request.txt", true))) {
                writer.println(hostelID + "," + currRoomNo + "," + reqRoomNo);
                System.out.println("Room change request submitted.");
            } catch (IOException ex) {
                System.out.println("There is an error processing the request: " + ex.getMessage());
            }

        } else if (userChoice.equals("2")) {
            int currRoomNo;
            while (true) {
                try {
                    System.out.println("What is your current room number: ");
                    currRoomNo = input.nextInt();
                    input.nextLine();  // Consume newline

                    if (checkRoomOccupancy(hostelID, currRoomNo, 0)) {
                        break;
                    } else {
                        System.out.println("Invalid current room number. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid room number.");
                    input.nextLine();  // Clear invalid input
                }
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter("request.txt", true))) {
                writer.println(hostelID + "," + currRoomNo);
                System.out.println("Vacate request submitted.");
            } catch (IOException ex) {
                System.out.println("There is an error processing the request: " + ex.getMessage());
            }

        } else {
            System.out.println("Invalid choice");
        }
    }

    private static boolean checkRoomOccupancy(String hostelID, int currRoomNo, int reqRoomNo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("roomOccupancy.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int roomNo = Integer.parseInt(parts[0].trim());
                String occupant1 = parts[1].trim();
                String occupant2 = parts[2].trim();

                // Check if the current room number matches and if the user is one of the occupants
                if (currRoomNo != 0 && roomNo == currRoomNo && (occupant1.equals(hostelID) || occupant2.equals(hostelID))) {
                    return true; // User is occupying the current room
                }

                // Check if the requested room number is vacant
                if (reqRoomNo != 0 && roomNo == reqRoomNo && !occupant1.equals("Vacant") && !occupant2.equals("Vacant")) {
                    return true; // Requested room is not vacant
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading room occupancy file: " + ex.getMessage());
        }
        return false; // User is not occupying the current room or requested room is vacant
    }

    public static void applications(){
        int count = 1;
        try{
            File file = new File("application.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                System.out.println();
                System.out.println("Application "+(count++));
                System.out.println("---------------------------------------");

                String[] application = reader.nextLine().split(",");
                System.out.println("Hostel ID "+application[0]);
                System.out.println("Password: "+application[1]);
                System.out.println("---------------------------------------");
                System.out.println("Name               : " + application[2]);
                System.out.println("Date of birth      : " + application[3]);
                System.out.println("CNIC               : " + application[4]);
                System.out.println("Contact Number     : " + application[5]);
                System.out.println("Guardian's Name    : " + application[6]);
                System.out.println("Guardian's phone no: " + application[7]);
                System.out.println("Current Room Number: " + application[8]);
                System.out.println("---------------------------------------");
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println("error");
        }
    }

    public static void currentRoomResidents() {
        int count = 1;
        File file = new File("roomOccupancy.txt");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] roomShiftDetails = reader.nextLine().split(",");
                System.out.println("ROOM NUMBER " + roomShiftDetails[0] + " : " + roomShiftDetails[1] + " , " + roomShiftDetails[2] );
                count++;
            }
            reader.close();
        } catch(IOException ex){
            System.out.println("Error in reading fee submission file" + ex.getMessage());
        }

    }

    public static void generateInvoice() {
        int accommodationFee = 25000;
        int hostelSecurityFee = 5000;
        int internetFee = 2000;
        int damageDeposit = 3000;
        int lockersFee = 1500;
        int breakfastFee = 6000;
        int laundryFee = 4000;
        int utilitiesFee = 3000;
        int activityFee = 2000;

        int total = accommodationFee + hostelSecurityFee + internetFee +
                damageDeposit + lockersFee + breakfastFee + laundryFee +
                utilitiesFee + activityFee;

        System.out.println("Hostel Fee Invoice:");
        System.out.println("----------------------------------");
        System.out.println("Accommodation Fee     : PKR " + accommodationFee);
        System.out.println("Hostel Security Fee   : PKR " + hostelSecurityFee);
        System.out.println("Internet Fee          : PKR " + internetFee);
        System.out.println("Damage Deposit        : PKR " + damageDeposit);
        System.out.println("Lockers Fee           : PKR " + lockersFee);
        System.out.println("Breakfast Fee         : PKR " + breakfastFee);
        System.out.println("Laundry Fee           : PKR " + laundryFee);
        System.out.println("Utilities Fee         : PKR " + utilitiesFee);
        System.out.println("Activity Fee          : PKR " + activityFee);
        System.out.println("----------------------------------");
        System.out.println("Total                 : PKR " + total);
    }

    public static void adminNotificationManagement() {

        Scanner input = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println(" -------------------------------------------------- ");
            System.out.println("|       --- Admin Notification Management ---      |");
            System.out.println("| Enter #1 - View Room Shift and Vacating Requests |");
            System.out.println("| Enter #2 - View Notifications/Complaints         |");
            System.out.println("| Enter #3 - Remove Notification/complaints        |");
            System.out.println("--------------------------------------------------");
            System.out.println("| Enter #0 - Return to Admin Portal.               |");
            System.out.println(" -------------------------------------------------- ");
            System.out.print("Choice: ");

            int choice = input.nextInt();
            input.nextLine();

            if(choice == 1){
                viewRoomShiftRequests();
            }else if (choice == 2){
                viewNotifications();
            }else if (choice == 3){
                System.out.print("Enter the number of the notification you want to remove: ");
                int lineNumber = input.nextInt();
                input.nextLine();
                removeNotification(lineNumber);
            }else if (choice == 0){
                exit = true;
            }else{
                System.out.println("Enter a valid number.");
            }
        }
        System.out.println("Exiting admin notification management");
    }

    public static void viewRoomShiftRequests() {
        int count = 1;
        boolean hasRequests = false;

        System.out.println("Room Shift and Vacating Requests:");
        try {
            File file = new File("request.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                hasRequests = true; // Set flag to true if a line is read
                String[] requests = reader.nextLine().split(",");
                System.out.print("Request " + count + ": ");
                if (requests.length == 3) {
                    System.out.println("Student with hostel ID " + requests[0] + " who lives in room No. " + requests[1] + " wants to change his room to room No. " + requests[2]);
                } else if (requests.length == 2) {
                    System.out.println("Hostelite with hostel ID " + requests[0] + " who lives in room No. " + requests[1] + " will vacate his room after one month");
                }
                count++;
            }
            reader.close();

            // Check if no requests were found
            if (!hasRequests) {
                System.out.println("There are no requests at the moment.");
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        System.out.println();
    }

    public static void viewNotifications() {
        boolean hasNotifications = false; // Flag to check if there are any notifications
        System.out.println();

        try (Scanner reader = new Scanner(new File("notification.txt"))) {
            int count = 1;
            while (reader.hasNextLine()) {
                hasNotifications = true; // Set flag to true if a line is read
                String[] notification = reader.nextLine().split(",");
                System.out.println("Notification " + count);
                System.out.println("Hostel ID: " + notification[0]);
                System.out.println("Message  : " + notification[1]);
                System.out.println("----------------------------------------------");
                count++;
            }

            // Check if no notifications were found
            if (!hasNotifications) {
                System.out.println("There are no notifications at the moment.");
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }

    public static void removeNotification(int lineNumber){
        try(Scanner reader = new Scanner(new File("notification.txt"))){
            StringBuilder sb = new StringBuilder();
            int currentLine = 0;

            while (reader.hasNextLine()){
                currentLine++;
                String line = reader.nextLine();
                if (currentLine != lineNumber){
                    sb.append(line).append("\n");
                }
            }

            try(PrintWriter writer = new PrintWriter(new FileWriter("notification.txt"))){
                writer.print(sb);
            }catch (IOException ex){
                System.out.println("Error writing to file: " + ex.getMessage());
            }

            if(lineNumber > 0 && lineNumber <= currentLine){
                System.out.println("Notification removed successfully.");
            }else{
                System.out.println("No Notification to remove.");
            }
        }catch(IOException ex){
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }

    public static void  hosteliteNotificationSubmission(String hostelID){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your message: ");
        String message = input.nextLine();

        try(FileWriter fileWriter = new FileWriter("notification.txt", true);
            PrintWriter writer = new PrintWriter(fileWriter)){
            writer.println(hostelID + "," + message);
            System.out.println("Notification submitted successfully.");
        }catch (IOException ex){
            System.out.println("Error writing to file: " + ex.getMessage());
        }
        System.out.println("");
    }

}