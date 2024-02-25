package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class ToyQueue {
    public static void main(String[] args) {
        PriorityQueue<Toy> queue = new PriorityQueue<>((t1, t2) -> t2.weight - t1.weight);
        queue.add(new Toy(1, "конструктор", 2, 50));
        queue.add(new Toy(2, "Робот", 2, 100));
        queue.add(new Toy(3, "кукла", 6, 25));

        try (FileWriter writer = new FileWriter("output.txt");
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Введите количество попыток игры: ");
            int attempts = scanner.nextInt();

            playGame(attempts, queue, writer);

        } catch (IOException e) {
            System.out.println("Произошла ошибка при работе с файлом.");
            e.printStackTrace();
        }
    }

    private static void playGame(int attempts, PriorityQueue<Toy> queue, FileWriter writer) throws IOException {
        Random random = new Random();
        for (int i = 0; i < attempts; i++) {
            int randomNumber = random.nextInt(100);
            Toy selectedToy = selectToy(randomNumber, queue);
            if (selectedToy != null) {
                writer.write(selectedToy.id + " " + selectedToy.name + "\n");
                selectedToy.weight--;
                if (selectedToy.weight <= 0) {
                    queue.remove(selectedToy);
                } else {
                    queue.add(selectedToy);
                }
                ToyResultPrinter.printResult(selectedToy);
            }
        }
    }

    private static Toy selectToy(int randomNumber, PriorityQueue<Toy> queue) {
        if (randomNumber < 10) {
            return queue.stream().filter(t -> t.id == 1).findFirst().orElse(null);
        } else if (randomNumber < 16) {
            return queue.stream().filter(t -> t.id == 2).findFirst().orElse(null);
        } else if (randomNumber < 24) {
            return queue.stream().filter(t -> t.id == 3).findFirst().orElse(null);
        } else {
            System.out.println("К сожалению, вы проиграли.");
            return null;
        }
    }
}


