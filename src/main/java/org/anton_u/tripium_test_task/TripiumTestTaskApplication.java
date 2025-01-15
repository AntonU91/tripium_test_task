package org.anton_u.tripium_test_task;

import org.anton_u.tripium_test_task.comands_handler.ConsoleCommandHandler;
import org.anton_u.tripium_test_task.util.FakeDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class TripiumTestTaskApplication implements CommandLineRunner {

    private final ConsoleCommandHandler consoleCommandHandler;
    private final FakeDataGenerator fakeDataGenerator;

    @Autowired
    public TripiumTestTaskApplication(ConsoleCommandHandler consoleCommandHandler, FakeDataGenerator fakeDataGenerator) {
        this.consoleCommandHandler = consoleCommandHandler;
        this.fakeDataGenerator = fakeDataGenerator;
    }

    public static void main(String[] args) {
        SpringApplication.run(TripiumTestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        fakeDataGenerator.fillInData(); // Uncomment for generating fake data
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi, there! Please enter the console command to retrieve data\n");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.matches("\\s+exit\\s+")) {
                break;
            }
            System.out.println(consoleCommandHandler.getResponse(input));
        }
        System.out.println("Bye-bye...");
    }

}
