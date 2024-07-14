package org.example.idi2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("org.example.idi2.modelo.repository")
public class Idi2Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Idi2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Aquí puedes implementar la lógica de inicialización si es necesario
    }
}