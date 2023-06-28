package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {



        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }
        if (partRepository.count() == 0 && productRepository.count() == 0) {
            Part cheese = new Part("Cheese", 1.00, 10);
            Part beef = new Part("Beef", 2.50, 10);
            Part peperoni = new Part("Peperoni", .75, 10);
            Part chicken = new Part("Chicken", 1.00, 10);
            Part mixVeggies = new Part("Veggies", .75, 10);

            Product cheesePizza = new Product("Cheese Pizza", 10.00, 5);
            Product peperoniPizza = new Product("Peperoni Pizza", 10.00, 5);
            Product beefPizza = new Product("Beef Pizza", 10.00, 5);
            Product chickenPizza = new Product("Chicken Pizza", 10.00, 5);
            Product veggiePizza = new Product("Veggie Pizza", 8.00, 10);

            partRepository.save(cheese);
            partRepository.save(beef);
            partRepository.save(peperoni);
            partRepository.save(chicken);
            partRepository.save(mixVeggies);

            productRepository.save(cheesePizza);
            productRepository.save(peperoniPizza);
            productRepository.save(beefPizza);
            productRepository.save(chickenPizza);
            productRepository.save(veggiePizza);

            cheese.getProducts().add(cheesePizza);
            cheesePizza.getParts().add(cheese);
            beef.getProducts().add(beefPizza);
            beefPizza.getParts().add(beef);
            peperoni.getProducts().add(peperoniPizza);
            peperoniPizza.getParts().add(peperoni);
            chicken.getProducts().add(chickenPizza);
            chickenPizza.getParts().add(chicken);
            mixVeggies.getProducts().add(veggiePizza);
            veggiePizza.getParts().add(mixVeggies);
        }











        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
