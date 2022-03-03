package com.tabram.coffemaker;

import com.tabram.coffemaker.coffee.Cappuccino;

import javax.persistence.*;


public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.tabram.coffeemaker.jpa");

        User user1 = new User();
        user1.setName("Zbyszek");
        User user2 = new User();
        user2.setName("Geralt");

        String cappuccino = new Cappuccino().toString();

        System.out.println(cappuccino);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(user1);
        entityManager.persist(user2);

        entityManager.getTransaction().commit();

        entityManagerFactory.close();

//        CoffeeMaker coffeeMaker1 = new CoffeeMaker();
//        coffeeMaker1.machineAction.turnOnCoffeeMaker(coffeeMaker1);
//        coffeeMaker1.status.checkStatus();
//        coffeeMaker1.log();
    }
}
