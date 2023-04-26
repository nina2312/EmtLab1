package com.example.labemt.config;


import com.example.labemt.model.enumerations.Category;
import com.example.labemt.service.AuthorService;
import com.example.labemt.service.BookService;
import com.example.labemt.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

import static com.example.labemt.model.enumerations.Category.*;

@Component
public class DataInitializer {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public DataInitializer(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    private String randomizeCountry(int i){
        if(i==0) return "Europe";
        else if(i==1) return "South America";
        else if(i==2) return "South America";
        else if(i==3) return "North America";
        else if(i==4) return "Asia";
        else return "Australia";
    }

    private String randomizeName(int i){
        if(i==0) return "Stephen";
        else if(i==1) return "Elizabeth";
        else if(i==2) return "Colleen";
        else if(i==3) return "Jay";
        else if(i==4) return "Danielle";
        else return "Jenny";
    }

    private String randomizeSurname(int i){
        if(i==0) return "King";
        else if(i==1) return "Gilbert";
        else if(i==2) return "Hoover";
        else if(i==3) return "Shetty";
        else if(i==4) return "Steel";
        else return "Jackson";
    }

    private String randomizeBook(int i){
        if(i==0) return "Elevation";
        else if(i==1) return "Eat, pray, love";
        else if(i==2) return "It Ends with Us";
        else if(i==3) return "Think Like a Monk";
        else if(i==4) return "Worthy Opponents";
        else return "Pineapple Street";
    }

    @PostConstruct
    public void initData() {
        Random random=new Random();
       // for (int i = 1; i < 6; i++) {

          //  this.countryService.create( this.randomizeCountry(random.nextInt(6)),"Country: " + i);
       // }
        this.countryService.create("Makedonija", "Evropa");
        this.countryService.create("Srbija", "Evropa");
        this.countryService.create("Indija", "Azija");
        this.countryService.create("Laos", "Afrika");

        //for (int i = 1; i < 6; i++) {

           // this.authorService.create(this.randomizeName(random.nextInt(6)),  this.randomizeSurname(random.nextInt(6)), this.countryService.listAll().get((i-1)%5).getId());
      //  }
        this.authorService.create("Marija","Stojanovska", 1L);
        this.authorService.create("Angela","Ristevska", 1L);
        this.authorService.create("Sussie","Cameron", 2L);
        this.authorService.create("Christine","Davies", 3L);
        this.authorService.create("Mario","Smith", 4L);


        /*for (int i = 1; i < 6; i++) {
            Category[] categories = Category.values();
            //Random random = new Random();
            Category randomCategory = categories[random.nextInt(categories.length)];
            int bookCopies = i;
            this.bookService.save(this.randomizeBook(random.nextInt(6)), randomCategory ,this.authorService.findAll().get((i-1)%5).getId(), i);
        }*/
        this.bookService.save("Ice Cube",FANTASY,1L, 20);
        this.bookService.save("Flower",DRAMA,2L, 20);
        this.bookService.save("A mountain",THRILER,3L, 20);
        this.bookService.save("Autobiography",NOVEL,4L, 20);
        this.bookService.save("My life",BIOGRAPHY,5L, 20);
        this.bookService.save("A star is born",FANTASY,5L, 20);

    }

}
