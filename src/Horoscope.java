import java.util.List;
import java.time.LocalDate;

public class Horoscope {
    private StudentsInterface _student;
    private final String vowels = "aeiou";

    public Horoscope(StudentsInterface student){
        this._student = student;
    }

    public String getVerdict(Students student){
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

    public String getHoroscopeMessage(Students student){
        String result = getVerdict(student);

        return student.getName() + " is going to have a " + result + " day today";
    }

    public void printAllHoroscopeResult(){
        List<Students> allStudents = _student.getAllStudents();

        if(allStudents.isEmpty()){
            System.out.println("No students");
            return;
        }

        for(Students s: allStudents){
            System.out.println(getHoroscopeMessage(s));
        }
    }

}