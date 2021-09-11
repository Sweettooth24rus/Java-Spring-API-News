package TestSpring;

import TestSpring.News.News;
import TestSpring.News.NewsRepository;
import TestSpring.NewsType.NewsType;
import TestSpring.NewsType.NewsTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabaseType(NewsTypeRepository repositoryType) {

        return args -> {
            repositoryType.save(new NewsType("Nazvanie1", 1));
            repositoryType.save(new NewsType("Nazvanie2", 2));
        };
    }

    @Bean
    CommandLineRunner initDatabase(NewsRepository repository) {

        return args -> {
            repository.save(new News("Vnimanie", "Korotkoe", "Dlinnoe", 1L));
            repository.save(new News("Vnimanie1", "Korotkoe2", "Dlinnoe3", 2L));
        };
    }
}