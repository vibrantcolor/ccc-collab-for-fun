package com.webnyo.collab;

import com.webnyo.collab.data.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class CollabApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollabApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataPreloader(MovieRepository repo) {
        return args -> {
            Movie theLordOfTheRings = new Movie("The Lord of The Swings", "2001");
            Movie dieHard = new Movie("Die Soft", "1988");
            Movie pulpFiction = new Movie("Pulp Friction", "1994");
            Movie backToTheFuture = new Movie("Back to the AI", "1985");
            Movie lionKing = new Movie("The Zebra King", "1994");

            repo.save(theLordOfTheRings);
            repo.save(dieHard);
            repo.save(pulpFiction);
            repo.save(backToTheFuture);
            repo.save(lionKing);
        };
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}
