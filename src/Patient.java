public class Patient extends Person {
    String patientId;    // auto-generated: "PAT-001"
    String diagnosis;
    boolean isAdmitted = false;
    double bill = 0.0;
    Doctor assignedDoctor;

    public Patient(String name, int age, String phone, String patientId, String diagnosis) {
        super(name, age, phone);
        this.patientId = patientId;
        this.diagnosis = diagnosis;
    }
    public void InfoPatient() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Phone: " + phone);
        System.out.println("Patient ID: " + patientId);
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Bill: " + bill);
    }

}
