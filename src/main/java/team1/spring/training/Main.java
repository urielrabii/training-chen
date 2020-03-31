package team1.spring.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main{

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // bind stdout to logger
        SpringApplication.run(Main.class,args);
    }
}
