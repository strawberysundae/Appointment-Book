import java.util.Scanner;

public class AppointmentBook {
    private boolean[][] schedule;

    public AppointmentBook(Scanner s) {
        schedule = new boolean[8][60];

        for (int period = 0; period < 8; period++) {
            for (int minute = 0; minute < 60; minute++) {
                schedule[period][minute] = s.nextBoolean();
            }
        }
    }

    private boolean isMinuteFree(int period, int minute) {
        return schedule[period - 1][minute];
    }

    private void reserveBlock(int period, int startMinute, int duration) {
        for (int minute = startMinute;
             minute < startMinute + duration;
             minute++) {

            schedule[period - 1][minute] = false;
        }
    }

    public int findFreeBlock(int period, int duration) {
        int consecutive = 0;

        for (int minute = 0; minute < 60; minute++) {

            if (isMinuteFree(period, minute)) {
                consecutive++;

                if (consecutive == duration) {
                    return minute - duration + 1;
                }
            }
            else {
                consecutive = 0;
            }
        }

        return -1;
    }

    public boolean makeAppointment(
        int startPeriod,
        int endPeriod,
        int duration
    ) {
        for (int period = startPeriod;
             period <= endPeriod;
             period++) {

            int startMinute = findFreeBlock(period, duration);

            if (startMinute != -1) {
                reserveBlock(period, startMinute, duration);
                return true;
            }
        }

        return false;
    }
}

