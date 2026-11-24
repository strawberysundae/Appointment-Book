import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
        throws FileNotFoundException {

        File f = new File("schedules.txt");
        Scanner s = new Scanner(f);

        AppointmentBook[] teachers =
            new AppointmentBook[100];

        for (int i = 0; i < teachers.length; i++) {
            teachers[i] = new AppointmentBook(s);
        }

        f = new File("requests.txt");
        s = new Scanner(f);

        int successful = 0;

        while (s.hasNextInt()) {
            int teacher = s.nextInt();
            int startPeriod = s.nextInt();
            int endPeriod = s.nextInt();
            int duration = s.nextInt();

            if (teachers[teacher].makeAppointment(
                    startPeriod,
                    endPeriod,
                    duration)) {

                successful++;
            }
        }

        System.out.println(successful);
    }
}

