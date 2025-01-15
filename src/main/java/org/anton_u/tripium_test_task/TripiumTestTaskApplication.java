package org.anton_u.tripium_test_task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anton_u.tripium_test_task.comands_handler.CommandHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class TripiumTestTaskApplication implements CommandLineRunner {


    private final CommandHandler commandHandler;

    public static void main(String[] args) {
        SpringApplication.run(TripiumTestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        log.info("Hi, there! Please enter the console command to retrieve data");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            log.info(commandHandler.dispatch(input));
        }
    }
}
