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
        for (int i = 0; i < trainersNum; i++) {
            trainers[i] = new Trainer(in.nextInt(), in.nextInt(), in.nextInt());
        }
        Arrays.sort(trainers, Comparator.comparingInt(Trainer::getArrivalDay));

        SortedSet<Trainer> availableTrainer = new TreeSet<>(Comparator.comparingInt(Trainer::getUnhappyness)
                                                                      .thenComparingInt(Trainer::getId)
                                                                      .reversed());
        for (int i = 0; i < days; i++) {
            for (int t = 0; t < trainersNum; t++) {
                if (trainers[t].getArrivalDay() == i + 1) {
                    availableTrainer.add(trainers[t]);
                } else if (trainers[t].getArrivalDay() > i + 1) {
                    break;
                }
            }
            if (availableTrainer.isEmpty() == false) {
                final Trainer trainer = availableTrainer.first();
                trainer.erogate();
                if (0 == trainer.getRemainingLessons()) {
                    availableTrainer.remove(trainer);
                }
            }
        }

        out.println(availableTrainer.stream().mapToInt(Trainer::getTotalUnhappyness).sum());
        //out.println("Case " + testNumber + ": " + (System.currentTimeMillis() - start) + " ms");
    }

    private static final class Trainer {
        private static final AtomicInteger GENERATOR = new AtomicInteger();

        private final int id;
        private final int arrivalDay;
        private final int desiredLessons;
        private final int unhappyness;

        private int erogatedLessons = 0;

        public int getId() {
            return id;
        }

        public Trainer(int arrivalDay, int desiredLessons, int unhappyness) {
            id = GENERATOR.getAndIncrement();

            this.arrivalDay = arrivalDay;
            this.desiredLessons = desiredLessons;
            this.unhappyness = unhappyness;
        }

        public int getArrivalDay() {
            return arrivalDay;
        }

        public int getRemainingLessons() {
            return desiredLessons - erogatedLessons;
        }

        public int getUnhappyness() {
            return unhappyness;
        }

        public int getTotalUnhappyness() {
            return unhappyness * (desiredLessons - erogatedLessons);
        }

        public void erogate() {
            ++erogatedLessons;
        }

        @Override
        public String toString() {
            return "Trainer{" +
                   "arrivalDay=" + arrivalDay +
                   ", desiredLessons=" + desiredLessons +
                   ", unhappyness=" + unhappyness +
                   ", erogatedLessons=" + erogatedLessons +
                   '}';
        }
    }
}
