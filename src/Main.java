import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital("Archi Hospital");

        System.out.println("Hello World");

        boolean ready = true;
        while(ready){
            System.out.println("1. Add Doctor");
            System.out.println("2. Add patient");
            System.out.println("3. Admit patient");
            System.out.println("4. Discharge patient");
            System.out.println("5. Assign doctor for patient");
            System.out.println("6. Show all patients");
            System.out.println("7. Show all doctors");
            System.out.println("8. Show admitted patients");
            System.out.println("9. Add to patient bill");
            System.out.println("10. Show patient bill");
            System.out.println("11. Show available doctors");
            System.out.println("12. Exit");

            System.out.print("Choose your choice: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1 -> {
                    try{
                        hospital.AddDoctor(scanner);
                    } catch (Hospital.InvalidAgeException e){
                        System.out.println("Age Error: " + e.getMessage());
                    } catch(Hospital.HospitalFullException e){
                        System.out.println("Hospital Full: " + e.getMessage());
                    }
                }
                case 2 -> {
                    try{
                        hospital.AddPatient(scanner);
                    } catch(Hospital.InvalidAgeException e){
                        System.out.println("Age Error: " + e.getMessage());
                    } catch(Hospital.HospitalFullException e){
                        System.out.println("Hospital Full: " + e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        hospital.AdmitPatient(scanner);
                    } catch (Hospital.PatientAlreadyAdmittedException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 4 -> {
                    try{
                        hospital.DischargePatient(scanner);
                    } catch (Hospital.DischargeAdmittedPatient e){
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 5 -> {
                    try{
                        hospital.AssignDoctorforPatient(scanner);
                    } catch (Hospital.AssignedDoctorPatient e){
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 6 -> hospital.ShowAllPatients(scanner);
                case 7 -> hospital.ShowAllDoctors(scanner);
                case 8 -> hospital.AllAdmittedPatients(scanner);
                case 9 -> hospital.AddToBill(scanner);
            }

        }

        scanner.close();
    }
}