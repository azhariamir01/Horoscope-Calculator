import java.util.List;
import java.time.LocalDate;

public class Horoscope {
    private StudentsInterface _student;
    private final String vowels = "aeiou";

    public Horoscope(StudentsInterface student){
        this._student = student;
    }

    public String getResult(Students student){
        String name = student.getName().toLowerCase();
        int counter = 0;
        String strC;

        for(char ch : name.toCharArray()){
            strC = Character.toString(ch);

            if (vowels.contains(strC))
                counter++;
        }

        LocalDate date = LocalDate.now();
        int day = date.getDayOfMonth();


        if((counter != 0) && (day % counter == 0))
            return "good";
        return "bad";
    }

    public String getMessages(Students student){
        String result = getResult(student);

        return student.getName() + " is going to have a " + result + " day today";
    }

    public void printResults(){
        List<Students> allStudents = _student.getStudents();

        if(allStudents.isEmpty()){
            System.out.println("No students");
            return;
        }

        for(Students s: allStudents){
            System.out.println(getMessages(s));
        }
    }

}