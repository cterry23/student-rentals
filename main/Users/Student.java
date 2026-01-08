package Users;

public class Student extends User {
    //unique attribute to student usertype
    private String university;

    //calls base user constructor via super, adds unique attribute
    public Student(String name, String email, String telNo, String university) {
        super(name, email, telNo);
        this.university = university;
    }

    //getters
    public String getUniversity() {
        return university;
    }

    @Override
    public String getUserType() {
        return "Student";
    }

}