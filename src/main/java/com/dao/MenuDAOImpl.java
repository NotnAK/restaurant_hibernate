package com.dao;

import com.entity.MenuItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuDAOImpl implements MenuDAO {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public MenuDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("menuHibernate");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void addDish(MenuItem menuItem) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(menuItem);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void deleteDish(MenuItem menuItem) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(menuItem);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void updateDish(MenuItem menuItem) {
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(menuItem);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    @Override
    public List<MenuItem> getAllDishes() {
        return entityManager.createQuery("SELECT m FROM MenuItem m").getResultList();
    }

    @Override
    public List<MenuItem> getDishesByPriceRange(double minPrice, double maxPrice) {
        return entityManager.createQuery("SELECT m FROM MenuItem m WHERE m.price BETWEEN :min AND :max")
                .setParameter("min", minPrice)
                .setParameter("max", maxPrice)
                .getResultList();
    }

    @Override
    public List<MenuItem> getMenuItemsWithDiscount() {
        return entityManager.createQuery("SELECT m FROM MenuItem m WHERE m.discount = true").getResultList();
    }

    @Override
    public List<MenuItem> getMenuItemsWithinWeight(double maxWeight) {
        List<MenuItem> menuItems = entityManager.createQuery("SELECT m FROM MenuItem m ORDER BY m.weight ASC").getResultList();
        List<MenuItem> result = new ArrayList<>();
        double currentWeight = 0;

        for (MenuItem item : menuItems) {
            if (currentWeight + item.getWeight() <= maxWeight) {
                result.add(item);
                currentWeight += item.getWeight();
            }
        }
        return result;

    }
    @Override
    public MenuItem getApartmentById(int id) {
        return entityManager.find(MenuItem.class, id);
    }
}
