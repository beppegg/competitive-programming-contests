package tasks;

import java.util.*;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

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
        Arrays.sort(trainers, Comparator.comparingInt(Trainer::getArrivalDay));

       for (int i = 0; i < days; i++) {
           long maxUnhappiness = 0;
           int maxUnhappinessIdx = -1;
           for (int t = 0; t  < trainersNum && trainers[t].getArrivalDay() <= i + 1; t++) {
               if (trainers[t].getRemainingLessons() > 0 && trainers[t].getUnhappiness() > maxUnhappiness) {
                   maxUnhappiness = trainers[t].getUnhappiness();
                   maxUnhappinessIdx = t;
               }
           }
           if (maxUnhappinessIdx >= 0) {
                trainers[maxUnhappinessIdx].erogate();
                totalUnhappiness -= trainers[maxUnhappinessIdx].getUnhappiness();
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

        public int getId() {
            return id;
        }

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
