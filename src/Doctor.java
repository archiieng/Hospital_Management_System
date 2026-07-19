public class Doctor extends Person {
    String doctorId;     // auto-generated: "DOC-001"
    String specialty;    // "cardiology", "neurology", etc.
    boolean isAvailable = true;

    public Doctor(String name, int age, String phone, String doctorId, String specialty) {
        super(name, age, phone);
        this.doctorId = doctorId;
        this.specialty = specialty;
    }

    public void InfoDoctor() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Phone: " + phone);
        System.out.println("DoctorId: " + doctorId);
        System.out.println("Specialty: " + specialty);

    }
}
