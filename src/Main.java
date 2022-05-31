import java.util.Scanner;

public class Main {

    public static void main(String[] arg){
        String xmlFileName = "src/students.xml";
        StudentsXML fileXML = new StudentsXML(xmlFileName);

        Horoscope horoscope;

        boolean sw = true;

        while(sw){
            System.out.println("MENU");
            System.out.println("Select an option:");
            System.out.println("1. Get horoscope result for all of the students in the XML file");
            System.out.println("2. Add a student to the XML file");
            System.out.println("0. Quit");
            System.out.println("Enter your choice: ");

            int option;
            Scanner keyboard = new Scanner(System.in);
            option = Integer.parseInt(keyboard.nextLine());

            switch(option){
                case 1:{
                    horoscope = new Horoscope(fileXML);
                    horoscope.printResults();
                    break;
                }


                case 2:{
                    System.out.println("Student's name: ");
                    String sname = keyboard.nextLine();
                    fileXML.addStudent(sname);
                    break;
                }

                default:{
                    sw = false;
                    break;
                }
            }

            System.out.println();
        }
    }

}