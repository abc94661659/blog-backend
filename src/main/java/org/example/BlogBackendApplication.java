package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class BlogBackendApplication  {
// implements CommandLineRunner
    // @Autowired
    // private PasswordMigration passwordMigration;
    public static void main(String[] args) {
        SpringApplication.run(BlogBackendApplication.class, args);
    }

    /*@Override
    public void run(String... args) throws Exception {
        passwordMigration.migratePasswords();
    }*/

}
