package learn;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        StudentDataStore ds = new StudentDataStore();
        List<Student> students = ds.all();

        // 0. Print all students
        // iteration solution
//        for (Student student : students) {
//            System.out.println(student);
//        }

        // stream solution
        //students.stream().forEach(System.out::println);

        // 1. Print students from Argentina

//        students.stream()
//                .filter(student -> student.getCountry().equalsIgnoreCase("Argentina"))
//                .forEach(System.out::println);


        // 2. Print students whose last names starts with 'T'.

//        students.stream()
//                .filter(student -> student.getLastName().startsWith("B")) // <-- this changed
//                .forEach(System.out::println);


        // 3. Print students from Argentina, ordered by GPA
//        students.stream()
//                .filter(student -> student.getCountry().equalsIgnoreCase("Argentina"))
//                .sorted(Comparator.comparing(Student::getGpa))
//                .forEach(System.out::println);



        // 4. Print the bottom 10% (100 students) ranked by GPA.

//        students.stream()
//                .sorted(Comparator.comparing(Student::getGpa))
//                .limit(100)
//                .forEach(System.out::println);



        // 5. Print the 4th - 6th ranked students by GPA from Argentina
//        students.stream()
//                .filter(student -> student.getCountry().equalsIgnoreCase("Argentina"))
//                .sorted(Comparator.comparing(Student::getGpa).reversed())
//
//                //.limit(6)
//                .forEach(System.out::println);
        //**************************************************************************************************


        // 6. Is anyone from Maldives?

//        Optional<Student>  maldive = students.stream()
//                .filter(student -> student.getCountry().equalsIgnoreCase("Maldives"))
//                .findFirst();
//
//
//        if (maldive.isPresent()) {
//            Student maldiveStudent = maldive.get();
//            System.out.println("Found a player from Maldive: " + maldiveStudent.getLastName() + ", " + maldiveStudent.getFirstName());
//        } else {
//            System.out.println("There are no players from Maldive.");
//        }

        // 7. Does everyone have a non-null, non-empty email address?
        students.stream()
                .filter(student -> student.getEmailAddress().equals(0))

                .forEach(System.out::println);


        // 8. Print students who are currently registered for 5 courses.

//        students.stream()
//                .filter(student -> student.getRegistrations().size() == 5)
//                .forEach(System.out::println);


        // 9. Print students who are registered for the course "Literary Genres".

        // 10. Who has the latest birthday? Who is the youngest?

        // 11. Who has the highest GPA? There may be a tie.

        // 12. Print every course students are registered for, including repeats.

        // 13. Print a distinct list of courses students are registered for.

        // 14. Print a distinct list of courses students are registered for, ordered by name.

        // 15. Count students per country.

        // 16. Count students per country. Order by most to fewest students.

        // 17. Count registrations per course.

        // 18. How many registrations are not graded (GradeType == AUDIT)?

        // 19. Create a new type, StudentSummary with fields for Country, Major, and IQ.
        //     Map Students to StudentSummary, then sort and limit by IQ (your choice of low or high).

        // 20. What is the average GPA per country (remember, it's random fictional data).

        // 21. What is the maximum GPA per country?

        // 22. Print average IQ per Major ordered by IQ ascending.

        // 23. STRETCH GOAL!
        // Who has the highest pointPercent in "Sacred Writing"?
    }
}