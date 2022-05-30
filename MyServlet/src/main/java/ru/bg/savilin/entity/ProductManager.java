package ru.bg.savilin.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ProductManager {

    private List<Product> productBag;
    private AtomicLong id;

    public ProductManager(){
        productBag = new ArrayList<>();
        id = new AtomicLong(0);
    }

    public void add(Product product){
        product.setId(id.incrementAndGet());
        productBag.add(product);
    }

    public void remove(int id){
        productBag.remove(id);
    }

    public Product get(int id){
        return productBag.get(id);
    }

    public int size(){
        return productBag.size();
    }
}
