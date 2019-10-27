package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    CategoryRepository categoryRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;
    RecipeRepository recipeRepository;

    public DataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    private Set<Recipe> createRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        Optional<UnitOfMeasure> teaspoonOpt = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaspoonOpt.isPresent()) {
            throw new RuntimeException("Excected UOM not found");
        }
        UnitOfMeasure teaspoon = teaspoonOpt.get();
        Optional<UnitOfMeasure> tablespoonOpt = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tablespoonOpt.isPresent()) {
            throw new RuntimeException("Excected UOM not found");
        }
        UnitOfMeasure tablespoon = tablespoonOpt.get();
        Optional<UnitOfMeasure> dashOpt = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashOpt.isPresent()) {
            throw new RuntimeException("Excected UOM not found");
        }
        UnitOfMeasure dash = dashOpt.get();
        Optional<UnitOfMeasure> cupOpt = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupOpt.isPresent()) {
            throw new RuntimeException("Excected UOM not found");
        }
        UnitOfMeasure cup = cupOpt.get();
        Optional<UnitOfMeasure> eachOpt = unitOfMeasureRepository.findByDescription("Each");
        if (!eachOpt.isPresent()) {
            throw new RuntimeException("Excected UOM not found");
        }
        UnitOfMeasure each = eachOpt.get();
        Optional<UnitOfMeasure> pintOpt = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintOpt.isPresent()) {
            throw new RuntimeException("Excected UOM not found");
        }
        UnitOfMeasure pint = pintOpt.get();
        Optional<Category> mexicanOpt = categoryRepository.findByDescription("Mexican");
        if (!mexicanOpt.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }
        Category mexican = mexicanOpt.get();
        Optional<Category> americanOpt = categoryRepository.findByDescription("American");
        if (!americanOpt.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }
        Category american = americanOpt.get();

        Recipe guacamole = new Recipe();
        recipeSet.add(guacamole);
        guacamole.addCategory(mexican);
        guacamole.addCategory(american);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setCookTime(0);
        guacamole.setPrepTime(10);
        guacamole.setServings(4);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        String source = "Simple Recipes";
        guacamole.setSource(source);
        guacamole.setDescription("Perfect guacamole");
        guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "Variations\n" +
                "\n" +
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!\n");

        guacamole.addIngredient(new Ingredient("Avocado", BigDecimal.valueOf(2L), each));
        guacamole.addIngredient(new Ingredient("Salt", BigDecimal.valueOf(1.5), teaspoon));
        guacamole.addIngredient(new Ingredient("Lime juice", BigDecimal.ONE, tablespoon));
        guacamole.addIngredient(new Ingredient("Minced red onion", BigDecimal.valueOf(2L), tablespoon));
        guacamole.addIngredient(new Ingredient("Serrano chili", BigDecimal.valueOf(2L), each));
        guacamole.addIngredient(new Ingredient("Cilantro", BigDecimal.valueOf(2L), tablespoon));
        guacamole.addIngredient(new Ingredient("Black pepper", BigDecimal.ONE, dash));
        guacamole.addIngredient(new Ingredient("Tomato", BigDecimal.valueOf(0.5), each));

        Notes notes = new Notes();
        notes.setRecipeNotes("blabla");
        guacamole.setNotes(notes);

        Recipe taco = new Recipe();
        recipeSet.add(taco);
        taco.setSource(source);
        taco.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        taco.setDescription("Spicy grilled chicken tacos");
        taco.setCookTime(15);
        taco.setPrepTime(20);
        taco.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");

        taco.setServings(6);
        taco.setDifficulty(Difficulty.MODERATE);
        taco.addCategory(mexican);
        taco.addCategory(american);

        Notes notes2 = new Notes();
        notes2.setRecipeNotes("blahblah");
        taco.setNotes(notes2);

        taco.addIngredient(new Ingredient("Ancho chili powder", BigDecimal.valueOf(2L), tablespoon));
        taco.addIngredient(new Ingredient("Dried oregano", BigDecimal.ONE, teaspoon));
        taco.addIngredient(new Ingredient("Dried curmin", BigDecimal.ONE, teaspoon));
        taco.addIngredient(new Ingredient("Sugar", BigDecimal.ONE, teaspoon));
        taco.addIngredient(new Ingredient("Salt", BigDecimal.valueOf(0.5), teaspoon));
        taco.addIngredient(new Ingredient("Clove garlic, finely chopped", BigDecimal.ONE, each));
        taco.addIngredient(new Ingredient("Finely grated orange zest", BigDecimal.ONE, tablespoon));
        taco.addIngredient(new Ingredient("Freshly-squeezed orange juice", BigDecimal.valueOf(3L), tablespoon));
        taco.addIngredient(new Ingredient("Olive oil", BigDecimal.valueOf(2L), tablespoon));
        taco.addIngredient(new Ingredient("Skinless, boneless chicken thigh", BigDecimal.valueOf(6L), each));
        taco.addIngredient(new Ingredient("Small corn tortilla", BigDecimal.valueOf(8L), each));
        taco.addIngredient(new Ingredient("Small baby arugula", BigDecimal.valueOf(3L), cup));
        taco.addIngredient(new Ingredient("Medium ripe sliced avocado", BigDecimal.valueOf(2L), each));
        taco.addIngredient(new Ingredient("Radish", BigDecimal.valueOf(4L), each));
        taco.addIngredient(new Ingredient("Cherry tomato", BigDecimal.valueOf(0.5), pint));
        taco.addIngredient(new Ingredient("Red onion", BigDecimal.valueOf(0.25), each));
        taco.addIngredient(new Ingredient("Roughly chopped cilnatro", BigDecimal.ONE, each));
        taco.addIngredient(new Ingredient("Sour cream", BigDecimal.valueOf(0.5), cup));
        taco.addIngredient(new Ingredient("Lime", BigDecimal.ONE, each));
        return recipeSet;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(createRecipes());
    }
}