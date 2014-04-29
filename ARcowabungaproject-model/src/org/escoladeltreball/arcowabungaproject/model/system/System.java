package org.escoladeltreball.arcowabungaproject.model.system;

import java.util.HashSet;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.model.Drink;
import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Offer;
import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
import org.escoladeltreball.arcowabungaproject.model.ShoppingCart;
import org.escoladeltreball.arcowabungaproject.model.dao.DAOFactory;

public class System {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private String role;
    private System instance;
    private Set<Pizza> predefinedPizzas;
    private Set<Pizza> customSavedPizzas;
    private Set<Pizza> customTemporaryPizzas;
    private Set<Order> ordersSaved;
    private Set<Ingredient> ingredients;
    private Set<Drink> drinks;
    private Set<Offer> offers;
    private ShoppingCart shoppingCart;
    private DAOFactory dao;

    // ====================
    // CONSTRUCTORS
    // ====================
    private System(DAOFactory dao) {
	this.dao = dao;
    }

    // ====================
    // PUBLIC METHODS
    // ====================
    public System getInstance(DAOFactory dao) {
	if (instance == null) {
	    instance = new System(dao);
	}
	return instance;
    }

    public boolean addPredefinedPizzas(Pizza pizza) {
	if (pizza != null) {
	    if (predefinedPizzas == null) {
		predefinedPizzas = new HashSet<Pizza>();
	    }
	    return predefinedPizzas.add(pizza);
	}
	return false;
    }

    public boolean addCustomSavedPizzas(Pizza pizza) {
	if (pizza != null) {
	    if (customSavedPizzas == null) {
		customSavedPizzas = new HashSet<Pizza>();
	    }
	    return customSavedPizzas.add(pizza);
	}
	return false;
    }

    public boolean addCustomTemporaryPizzas(Pizza pizza) {
	if (pizza != null) {
	    if (customTemporaryPizzas == null) {
		customTemporaryPizzas = new HashSet<Pizza>();
	    }
	    return customTemporaryPizzas.add(pizza);
	}
	return false;
    }

    public boolean addOrderSaved(Order order) {
	if (order != null) {
	    if (ordersSaved == null) {
		ordersSaved = new HashSet<Order>();
	    }
	    return ordersSaved.add(order);
	}
	return false;
    }

    public boolean addIngredients(Ingredient ingredient) {
	if (ingredient != null) {
	    if (ingredients == null) {
		ingredients = new HashSet<Ingredient>();
	    }
	    return ingredients.add(ingredient);
	}
	return false;
    }

    public boolean addDrink(Drink drink) {
	if (drink != null) {
	    if (drinks == null) {
		drinks = new HashSet<Drink>();
	    }
	    return drinks.add(drink);
	}
	return false;
    }

    public boolean addOffer(Offer offer) {
	if (offer != null) {
	    if (offers == null) {
		offers = new HashSet<Offer>();
	    }
	    return offers.add(offer);
	}
	return false;

    }

    public boolean removePredefinedPizzas(Pizza pizza) {
	if (pizza != null) {
	    return predefinedPizzas.remove(pizza);
	}
	return false;
    }

    public boolean removeCustomSavedPizzas(Pizza pizza) {
	if (pizza != null) {
	    return customSavedPizzas.remove(pizza);
	}
	return false;
    }

    public boolean removeCustomTemporaryPizzas(Pizza pizza) {
	if (pizza != null) {
	    return customTemporaryPizzas.remove(pizza);
	}
	return false;
    }

    public boolean removeOrdersSaved(Order order) {
	if (order != null) {
	    return ordersSaved.remove(order);
	}
	return false;
    }

    public boolean removeIngredients(Ingredient ingredient) {
	if (ingredient != null) {
	    return ingredients.remove(ingredient);
	}
	return false;
    }

    public boolean removeDrink(Drink drink) {
	if (drink != null) {
	    return drinks.remove(drink);
	}
	return false;
    }

    public boolean removeOffer(Offer offer) {
	if (offer != null) {
	    return offers.remove(offer);
	}
	return false;
    }

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================

    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public Set<Pizza> getPredefinedPizzas() {
	return predefinedPizzas;
    }

    public void setPredefinedPizzas(Set<Pizza> predefinedPizzas) {
	this.predefinedPizzas = predefinedPizzas;
    }

    public Set<Pizza> getCustomSavedPizzas() {
	return customSavedPizzas;
    }

    public void setCustomSavedPizzas(Set<Pizza> customSavedPizzas) {
	this.customSavedPizzas = customSavedPizzas;
    }

    public Set<Pizza> getCustomTemporaryPizzas() {
	return customTemporaryPizzas;
    }

    public void setCustomTemporaryPizzas(Set<Pizza> customTemporaryPizzas) {
	this.customTemporaryPizzas = customTemporaryPizzas;
    }

    public Set<Order> getOrdersSaved() {
	return ordersSaved;
    }

    public void setOrdersSaved(Set<Order> ordersSaved) {
	this.ordersSaved = ordersSaved;
    }

    public Set<Ingredient> getIngredients() {
	return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
	this.ingredients = ingredients;
    }

    public Set<Drink> getDrinks() {
	return drinks;
    }

    public void setDrinks(Set<Drink> drinks) {
	this.drinks = drinks;
    }

    public Set<Offer> getOffers() {
	return offers;
    }

    public void setOffers(Set<Offer> offers) {
	this.offers = offers;
    }

    public ShoppingCart getShoppingCart() {
	return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
	this.shoppingCart = shoppingCart;
    }
}
