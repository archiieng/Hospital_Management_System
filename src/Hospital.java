import java.util.Scanner;

public class Hospital {
    Patient[] patients = new Patient[20];
    Doctor[] doctors = new Doctor[10];
    int patientCount = 0;
    int doctorCount = 0;
    String hospitalName;

    Hospital(String hospitalName){
        this.hospitalName = hospitalName;
    }

    class InvalidAgeException extends Exception {
        InvalidAgeException(String message) {
            super(message);
        }
    }

    class HospitalFullException extends Exception {
        HospitalFullException(String message) {
            super(message);
        }
    }

    class PatientAlreadyAdmittedException extends Exception {
        PatientAlreadyAdmittedException(String message) {
            super(message);
        }
    }

    class DischargeAdmittedPatient extends Exception {
        DischargeAdmittedPatient(String message) {
            super(message);
        }
    }

    class AssignedDoctorPatient extends Exception {
        AssignedDoctorPatient(String message) {
            super(message);
        }
    }


    void AddDoctor(Scanner scanner) throws InvalidAgeException, HospitalFullException {
        if(doctorCount == doctors.length){
            throw new HospitalFullException("Hospital Full! Cannot Add Doctor");
        }
        System.out.print("Enter the doctor's name: ");
        String doctorName = scanner.next();

        System.out.print("Enter the doctor's age: ");
        int doctorAge = scanner.nextInt();
        if(doctorAge < 0 || doctorAge > 150){
            throw new InvalidAgeException("Age " + doctorAge + " is not valid");
        }

        System.out.print("Enter the doctor's phone number: ");
        String doctorPhone = scanner.next();

        String doctorId = String.format("DOC-%03d", doctorCount + 1);

        System.out.print("Enter the doctor's specialty: ");
        String doctorSpecialty = scanner.next();

        if(doctorName.isEmpty() || doctorPhone.isEmpty() || doctorSpecialty.isEmpty()){
            System.out.println("Invalid input!");
            return;
        }
        doctors[doctorCount] = new Doctor(doctorName, doctorAge, doctorPhone, doctorId, doctorSpecialty );
        doctorCount++;

        System.out.println("Doctor has been added!");
    }
    void AddPatient(Scanner scanner) throws InvalidAgeException, HospitalFullException {
        if(patientCount == patients.length){
            throw new HospitalFullException("Hospital Full! Cannot Add Patient");
        }
        System.out.print("Enter patient's name: ");
        String patientName = scanner.next();

        System.out.print("Enter patient's age: ");
        int patientAge = scanner.nextInt();
        scanner.nextLine();

        if(patientAge < 0 || patientAge > 150){
            throw new InvalidAgeException("Age " + patientAge + " is not valid");
        }

        System.out.print("Enter patient's phone number: ");
        String patientPhone = scanner.nextLine();

        String patientID = String.format("DOC-%03d", patientCount + 1);

        System.out.print("Diagnosis for the patient: ");
        String patientDiagnosis = scanner.nextLine();

        if(patientName.isEmpty() || patientPhone.isEmpty() || patientDiagnosis.isEmpty()){
            System.out.println("Invalid input!");
            return;
        }
        patients[patientCount] = new Patient(patientName, patientAge, patientPhone, patientID, patientDiagnosis);
        patientCount++;

        System.out.println("Patient has been added!");
    }
    void AdmitPatient(Scanner scanner) throws PatientAlreadyAdmittedException{
        Doctor availableDoctor = null;
        if(patientCount == 0){
            System.out.println("There is no patient");
            return;
        }
        System.out.println("Admit the patient");
        System.out.println("=====================");

        for(int i = 0; i < patientCount; i++){
            System.out.println(i + 1 + patients[i].name + " - " + patients[i].diagnosis);
        }
        System.out.print("Choose the patient: ");
        int  choice = scanner.nextInt();
        scanner.nextLine();

        if(choice < 0 || choice > patientCount){
            System.out.println("Invalid input!");
            return;
        }
        if(patients[choice - 1].isAdmitted){
            throw new PatientAlreadyAdmittedException(patients[choice - 1].name +  " is patient already admitted");
        }
        for(int i = 0; i < doctorCount; i++){
            if(doctors[i].isAvailable){
                availableDoctor = doctors[i];
                break;
            }
        }
        if(availableDoctor == null){
            System.out.println("No doctors is available");
            return;
        }
        patients[choice - 1].isAdmitted = true;
        patients[choice - 1].assignedDoctor = availableDoctor;
        availableDoctor.isAvailable = false;

        System.out.println(patients[choice - 1].name + " has been admitted!");
        System.out.println("Assigned doctor " + availableDoctor.name);
    }
    void DischargePatient(Scanner scanner) throws DischargeAdmittedPatient{
        Doctor availableDoctor = null;
        if(patientCount == 0){
            System.out.println("There is no patient");
            return;
        }
        System.out.println("Discharge the patient");
        System.out.println("=====================");
        for(int i = 0; i < patientCount; i++){
            System.out.println(i + 1 + " - " + patients[i].name + " - " + patients[i].diagnosis + patients[i].isAdmitted);
        }
        System.out.print("Choose the patient: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice < 0 || choice > patientCount){
            System.out.println("Invalid input!");
            return;
        }
        if(patients[choice - 1].isAdmitted == false){
            throw new DischargeAdmittedPatient(patients[choice - 1].name +   " is not  admitted");
        }
        if(patients[choice - 1].assignedDoctor != null){
            patients[choice - 1].assignedDoctor.isAvailable = true;
            System.out.println("Doctor " + patients[choice - 1].assignedDoctor.name + " is available");
        }
        patients[choice - 1].isAdmitted = false;
        patients[choice - 1].assignedDoctor = null;

        System.out.println(patients[choice - 1].name + " has been discharged!");

    }
    void AssignDoctorforPatient(Scanner scanner) throws AssignedDoctorPatient{
        if(patientCount == 0){
            System.out.println("There is no patient");
            return;
        }
        System.out.println("====Assign doctor for the patient====");

        for(int i = 0; i < patientCount; i++){
            if(patients[i].isAdmitted) {
                String currentDoctor = patients[i].assignedDoctor != null ? patients[i].assignedDoctor.name : "none";
                System.out.println((i+1) + " - " + patients[i].name + " | Current doctor: " + currentDoctor);
            }
        }

        System.out.print("Choose admitted patient: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice < 1 || choice > patientCount){
            System.out.println("Invalid input!");
            return;
        }
        for(int i = 0; i < doctorCount; i++){
            if(doctors[i].isAvailable){
                System.out.println(i + 1 + " - " + doctors[i].name + " is available");
            }
        }
        System.out.print("Choose doctor for the patient: ");
        int choice2 = scanner.nextInt();
        scanner.nextLine();
        if(choice2 < 1 || choice2 > doctorCount){
            System.out.println("Invalid input!");
            return;
        }
        if(!doctors[choice2 - 1].isAvailable) {
            throw new AssignedDoctorPatient(doctors[choice2 - 1].name + " is not available!");
        }
        if(patients[choice - 1].assignedDoctor != null) {
            patients[choice - 1].assignedDoctor.isAvailable = true;
            System.out.println("Released: " + patients[choice -1].assignedDoctor.name);
        }

        patients[choice - 1].assignedDoctor = doctors[choice2 - 1];
        doctors[choice2 - 1].isAvailable = false;

        System.out.println("The doctor name: " + doctors[choice2 - 1].name + " - " + " was assigned to the patient: " + patients[choice - 1].name);
    }
    void ShowAllPatients(Scanner scanner){
        System.out.println("====the data about patients====");
        if(patientCount == 0){
            System.out.println("There is no patient");
        } else{
            for(int i = 0; i < patientCount; i++){
                patients[i].InfoPatient();
                System.out.println("=====================");
            }
        }

    }
    void ShowAllDoctors(Scanner scanner){
        System.out.println("====yhe data about doctors====");
        if(doctorCount == 0){
            System.out.println("There is no doctor");
        } else{
            for(int i = 0; i < doctorCount; i++){
                doctors[i].InfoDoctor();
                System.out.println("=====================");
            }
        }
    }
    void AllAdmittedPatients(Scanner scanner){
        System.out.println("====All Admitted Patients====");
        if(patientCount == 0){
            System.out.println("There is no patient");
            return;
        }
        for(int i = 0; i < patientCount; i++){
            if(patients[i].isAdmitted){
                System.out.println(i + 1 + " - " + patients[i].name + " is admitted");
            }
        }
    }
    void AddToBill(Scanner scanner){
        System.out.println("====Adding a patient to the bill====");
        if(patientCount == 0){
            System.out.println("There is no patient");
            return;
        }
        for(int i = 0; i < patientCount; i++){
            System.out.println(i + 1 + " - " + patients[i].name);
        }

        System.out.print("Choose the patient for bill: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice < 1 || choice > patientCount){
            System.out.println("Invalid input!");
            return;
        }

        System.out.println("======Services====");
        System.out.println("1. - Basic Check Health: 56.0 ");
        System.out.println("2. - Blood check: 34.5");
        System.out.println("3. - Cancer Screening: 68.0");
        System.out.println("4. - Eye and Hearing exams: 52.5");

        System.out.print("Choose the service: ");
        int service = scanner.nextInt();
        scanner.nextLine();

        double cost;
        String serviceName;
        if(service == 1) {
            cost = 56.0;
            serviceName = "Basic Health Check";
        } else if(service == 2) {
            cost = 34.5;
            serviceName = "Blood Test";
        } else if(service == 3) {
            cost = 68.0;
            serviceName = "Cancer Screening";
        } else if(service == 4) {
            cost = 52.5;
            serviceName = "Eye and Hearing Exam";
        } else {
            System.out.println("Invalid service choice.");
            return;
        }
        patients[choice - 1].bill += cost;
        System.out.println("Added: " + serviceName + " $" + cost);
        System.out.println(patients[choice - 1].name + "'s total bill: $" + patients[choice - 1].bill);
    }

}
