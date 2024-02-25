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

        try {
            FileWriter writer = new FileWriter("output.txt");
            Random random = new Random();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите количество попыток игры: ");
            int attempts = scanner.nextInt();

            for (int i = 0; i < 10; i++) {
                int randomNumber = random.nextInt(attempts);
                Toy selectedToy = null;
                if (randomNumber < 2) {
                    selectedToy = queue.stream().filter(t -> t.id == 1).findFirst().orElse(null);
                } else if (randomNumber < 4) {
                    selectedToy = queue.stream().filter(t -> t.id == 2).findFirst().orElse(null);
                } else {
                    selectedToy = queue.stream().filter(t -> t.id == 3).findFirst().orElse(null);
                }
                if (selectedToy != null) {
                    writer.write(selectedToy.id + " " + selectedToy.name + "\n");
                    selectedToy.weight--;
                    if (selectedToy.weight <= 0) {
                        queue.remove(selectedToy);
                    } else {
                        queue.add(selectedToy);
                    }
                    if (random.nextInt(10) > 8) {
                        System.out.println("Поздравляем! Вы выиграли: " + selectedToy.name + " цена " + selectedToy.price);
                    } else {
                        System.out.println("К сожалению, вы проиграли.");
                    }
                }
            }
            writer.close();
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}


