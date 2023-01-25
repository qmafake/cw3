package com.example.cw3;

import com.example.data.models.Author;
import com.example.data.models.Book;
import com.example.data.models.Publisher;
import com.example.data.repositories.AuthorRepository;
import com.example.data.repositories.BookRepository;
import com.example.data.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication(scanBasePackages="com.example")
@EntityScan("com.example.data.models")
@EnableJpaRepositories("com.example.data.repositories")

public class Cw3Application implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(Cw3Application.class, args);

    }

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public Cw3Application(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);
        System.out.println("Publisher Count: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);


        System.out.println("Eric = " + eric);
        System.out.println("ddd = " + ddd);

        System.out.println("Save author Eric details");
        authorRepository.save(eric);
//        bookRepository.save(ddd); // Above line already invoke an INSERT INTO both author & book tables. So this
                                    // line causes a PK violation. as it does the same
        publisherRepository.save(publisher);

        System.out.println("Author count: " + authorRepository.count() );        // findAll().get(0));
        System.out.println("Books count: " + bookRepository.count() );


        //        List author_select = authorRepository.findAllAuthoustordWithJpql();

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "393949459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        Thread.sleep(3000);

        System.out.println("Save author Rod details - NOT WORKING PROPERLY");

//        authorRepository.save(rod); //TODO: The PK is not incrementing causing a PK violation
//        bookRepository.save(noEJB);

        System.out.println("Author count: " + authorRepository.count() );        // findAll().get(0));
        System.out.println("Books count: " + bookRepository.count() );

        System.out.println("Total Number of Books = " + bookRepository.count() );

        System.out.println("Test" + authorRepository.findAll());
        System.out.println("Test" + bookRepository.findAll());
    }

}
