package tasks;

import java.nio.LongBuffer;
import java.util.*;
import java.io.PrintWriter;

public class IPCTrainers {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        //long start = System.currentTimeMillis();
        int trainersNum = in.nextInt();
        int days = in.nextInt();

        Trainer[] trainers = new Trainer[trainersNum];
        long totalUnhappiness = 0;
        for (int i = 0; i < trainersNum; i++) {
            trainers[i] = new Trainer(in.nextInt(), in.nextInt(), in.nextInt());
            totalUnhappiness += trainers[i].getTotalUnhappiness();
        }
        Arrays.sort(trainers, Comparator.comparingInt(Trainer::getUnhappiness).reversed());

        BitSet availableDays = new BitSet(days + 1);
        availableDays.set(1, days + 1);
        for (int t = 0; t < trainersNum && ! availableDays.isEmpty(); t++) {
            for (int lessonDay = availableDays.nextSetBit(trainers[t].getArrivalDay());
                 lessonDay >= 0 && trainers[t].getRemainingLessons() > 0;
                 lessonDay = availableDays.nextSetBit(lessonDay)) {
                trainers[t].erogate();
                totalUnhappiness -= trainers[t].getUnhappiness();
                availableDays.clear(lessonDay);
            }
        }

        out.println(totalUnhappiness);
        //out.println("Case " + testNumber + ": " + (System.currentTimeMillis() - start) + " ms");
    }

    private static final class Trainer {
        private final int arrivalDay;
        private final int desiredLessons;
        private final int unhappiness;

        private int remainingLessons;


        Trainer(int arrivalDay, int desiredLessons, int unhappyness) {
            this.arrivalDay = arrivalDay;
            this.desiredLessons = desiredLessons;
            this.unhappiness = unhappyness;

            this.remainingLessons = desiredLessons;
        }

        int getArrivalDay() {
            return arrivalDay;
        }

        int getRemainingLessons() {
            return remainingLessons;
        }

        int getUnhappiness() {
            return unhappiness;
        }

        int getTotalUnhappiness() {
            return unhappiness * (remainingLessons);
        }

        void erogate() {
            --remainingLessons;
        }

        @Override
        public String toString() {
            return "Trainer{" +
                   "arrivalDay=" + arrivalDay +
                   ", desiredLessons=" + desiredLessons +
                   ", unhappiness=" + unhappiness +
                   ", remainingLessons=" + remainingLessons +
                   '}';
        }
    }
}
