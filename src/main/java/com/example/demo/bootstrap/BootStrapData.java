package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private final InhousePartRepository inhousePartRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;
    private final ProductRepository productRepository;

    public BootStrapData(InhousePartRepository inhousePartRepository, OutsourcedPartRepository outsourcedPartRepository, ProductRepository productRepository) {
        this.inhousePartRepository = inhousePartRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (inhousePartRepository.count() == 0 && outsourcedPartRepository.count() == 0 && productRepository.count() == 0) {
            InhousePart cheese = new InhousePart("Cheese", 1.00, 10);
            cheese.setMinInv(2);
            cheese.setMaxInv(20);

            InhousePart beef = new InhousePart("Beef", 2.50, 10);
            beef.setMinInv(2);
            beef.setMaxInv(20);

            InhousePart peperoni = new InhousePart("Pepperoni", 0.75, 10);
            peperoni.setMinInv(2);
            peperoni.setMaxInv(20);

            OutsourcedPart chicken = new OutsourcedPart("Chicken", 1.00, 10);
            chicken.setMinInv(2);
            chicken.setMaxInv(20);
            chicken.setCompanyName("Chicken Supplier");

            OutsourcedPart mixVeggies = new OutsourcedPart("Mix Veggies", 0.75, 10);
            mixVeggies.setMinInv(2);
            mixVeggies.setMaxInv(20);
            mixVeggies.setCompanyName("Veggie Supplier");

            Product cheesePizza = new Product("Cheese Pizza", 10.00, 5);
            Product peperoniPizza = new Product("Pepperoni Pizza", 10.00, 5);
            Product beefPizza = new Product("Beef Pizza", 10.00, 5);
            Product chickenPizza = new Product("Chicken Pizza", 10.00, 5);
            Product veggiePizza = new Product("Veggie Pizza", 8.00, 10);

            inhousePartRepository.save(cheese);
            inhousePartRepository.save(beef);
            inhousePartRepository.save(peperoni);

            outsourcedPartRepository.save(chicken);
            outsourcedPartRepository.save(mixVeggies);

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

            System.out.println("Started in Bootstrap");
            System.out.println("Number of Products: " + productRepository.count());
            System.out.println(productRepository.findAll());
            System.out.println("Number of Parts: " + (inhousePartRepository.count() + outsourcedPartRepository.count()));
            System.out.println("In-house Parts: " + inhousePartRepository.findAll());
            System.out.println("Outsourced Parts: " + outsourcedPartRepository.findAll());
        }
    }
}