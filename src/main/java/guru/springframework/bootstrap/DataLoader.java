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
        guacamole.getCategories().add(mexican);
        guacamole.getCategories().add(american);
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

        Set<Ingredient> ingredients = new HashSet<>();

        Ingredient avocado = new Ingredient();
        avocado.setRecipe(guacamole);
        avocado.setAmount(BigDecimal.valueOf(2L));
        avocado.setDescription("Avocado");
        avocado.setUom(each);
        ingredients.add(avocado);

        Ingredient salt = new Ingredient();
        salt.setRecipe(guacamole);
        salt.setAmount(BigDecimal.valueOf(1.5));
        salt.setDescription("Salt");
        salt.setUom(teaspoon);
        ingredients.add(salt);

        Ingredient limeJuice = new Ingredient();
        limeJuice.setRecipe(guacamole);
        limeJuice.setAmount(BigDecimal.ONE);
        limeJuice.setDescription("Lime juice");
        limeJuice.setUom(tablespoon);
        ingredients.add(limeJuice);

        Ingredient redOnion = new Ingredient();
        redOnion.setRecipe(guacamole);
        redOnion.setDescription("Minced red onion");
        redOnion.setAmount(BigDecimal.valueOf(2L));
        redOnion.setUom(tablespoon);
        ingredients.add(redOnion);

        Ingredient chili = new Ingredient();
        chili.setRecipe(guacamole);
        chili.setDescription("Serrano chili");
        chili.setAmount(BigDecimal.valueOf(2L));
        chili.setUom(each);
        ingredients.add(chili);

        Ingredient cilantro = new Ingredient();
        cilantro.setRecipe(guacamole);
        cilantro.setDescription("Cilantro");
        cilantro.setAmount(BigDecimal.valueOf(2L));
        cilantro.setUom(tablespoon);
        ingredients.add(cilantro);

        Ingredient blackPepper = new Ingredient();
        blackPepper.setRecipe(guacamole);
        blackPepper.setDescription("Black pepper");
        blackPepper.setAmount(BigDecimal.ONE);
        blackPepper.setUom(dash);
        ingredients.add(blackPepper);

        Ingredient tomato = new Ingredient();
        tomato.setRecipe(guacamole);
        tomato.setDescription("Tomato");
        tomato.setAmount(BigDecimal.valueOf(0.5));
        tomato.setUom(each);
        ingredients.add(tomato);

        guacamole.setIngredients(ingredients);
        Notes notes = new Notes();
        notes.setRecipe(guacamole);
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
        taco.getCategories().add(mexican);
        taco.getCategories().add(american);

        Notes notes2 = new Notes();
        notes2.setRecipe(taco);
        notes2.setRecipeNotes("blahblah");
        taco.setNotes(notes2);

        Set<Ingredient> ingredients2 = new HashSet<>();
        Ingredient chiliPowder = new Ingredient();
        chiliPowder.setRecipe(taco);
        chiliPowder.setDescription("Ancho chili powder");
        chiliPowder.setAmount(BigDecimal.valueOf(2L));
        chiliPowder.setUom(tablespoon);
        ingredients2.add(chiliPowder);

        Ingredient oregano = new Ingredient();
        oregano.setRecipe(taco);
        oregano.setDescription("Dried oregano");
        oregano.setAmount(BigDecimal.ONE);
        oregano.setUom(teaspoon);
        ingredients2.add(oregano);

        Ingredient curmin = new Ingredient();
        curmin.setRecipe(taco);
        curmin.setDescription("Dried curmin");
        curmin.setAmount(BigDecimal.ONE);
        curmin.setUom(teaspoon);
        ingredients2.add(curmin);

        Ingredient sugar = new Ingredient();
        sugar.setRecipe(taco);
        sugar.setDescription("Sugar");
        sugar.setAmount(BigDecimal.ONE);
        sugar.setUom(teaspoon);
        ingredients2.add(sugar);

        Ingredient salt2 = new Ingredient();
        salt2.setRecipe(taco);
        salt2.setDescription("Salt");
        salt2.setAmount(BigDecimal.valueOf(0.5));
        salt2.setUom(teaspoon);
        ingredients2.add(salt2);

        Ingredient garlic = new Ingredient();
        garlic.setRecipe(taco);
        garlic.setDescription("Clove garlic, finely chopped");
        garlic.setAmount(BigDecimal.ONE);
        garlic.setUom(each);
        ingredients2.add(garlic);

        Ingredient orangeZest = new Ingredient();
        orangeZest.setRecipe(taco);
        orangeZest.setDescription("Finely grated orange zest");
        orangeZest.setAmount(BigDecimal.ONE);
        orangeZest.setUom(tablespoon);
        ingredients2.add(orangeZest);

        Ingredient orangeJuice = new Ingredient();
        orangeJuice.setRecipe(taco);
        orangeJuice.setDescription("Freshly-squeezed orange juice");
        orangeJuice.setAmount(BigDecimal.valueOf(3L));
        orangeJuice.setUom(tablespoon);
        ingredients2.add(orangeJuice);

        Ingredient oliveOil = new Ingredient();
        oliveOil.setRecipe(taco);
        oliveOil.setDescription("Olive oil");
        oliveOil.setAmount(BigDecimal.valueOf(2L));
        oliveOil.setUom(tablespoon);
        ingredients2.add(oliveOil);

        Ingredient chickenThigh = new Ingredient();
        chickenThigh.setRecipe(taco);
        chickenThigh.setDescription("Skinless, boneless chicken thigh");
        chickenThigh.setAmount(BigDecimal.valueOf(6L));
        chickenThigh.setUom(each);
        ingredients2.add(chickenThigh);

        Ingredient tortilla = new Ingredient();
        tortilla.setRecipe(taco);
        tortilla.setDescription("Small corn tortilla");
        tortilla.setAmount(BigDecimal.valueOf(8L));
        tortilla.setUom(each);
        ingredients2.add(tortilla);

        Ingredient arugula = new Ingredient();
        arugula.setRecipe(taco);
        arugula.setDescription("Small baby arugula");
        arugula.setAmount(BigDecimal.valueOf(3L));
        arugula.setUom(cup);
        ingredients2.add(arugula);

        Ingredient avocado2 = new Ingredient();
        avocado2.setRecipe(taco);
        avocado2.setDescription("Medium ripe sliced avocado");
        avocado2.setAmount(BigDecimal.valueOf(2L));
        avocado2.setUom(each);
        ingredients2.add(avocado2);

        Ingredient radish = new Ingredient();
        radish.setRecipe(taco);
        radish.setDescription("Radish");
        radish.setAmount(BigDecimal.valueOf(4L));
        radish.setUom(each);
        ingredients2.add(radish);

        Ingredient cherryTomato = new Ingredient();
        cherryTomato.setRecipe(taco);
        cherryTomato.setDescription("Cherry tomato");
        cherryTomato.setAmount(BigDecimal.valueOf(0.5));
        cherryTomato.setUom(pint);
        ingredients2.add(cherryTomato);

        Ingredient redOnion2 = new Ingredient();
        redOnion2.setRecipe(taco);
        redOnion2.setDescription("Red onion");
        redOnion2.setAmount(BigDecimal.valueOf(0.25));
        redOnion2.setUom(each);
        ingredients2.add(redOnion2);

        Ingredient cilantro2 = new Ingredient();
        cilantro2.setRecipe(taco);
        cilantro2.setDescription("Roughly chopped cilnatro");
        cilantro2.setAmount(BigDecimal.ONE);
        cilantro2.setUom(each);
        ingredients2.add(cilantro2);

        Ingredient sourCream = new Ingredient();
        sourCream.setRecipe(taco);
        sourCream.setDescription("Sour cream");
        sourCream.setAmount(BigDecimal.valueOf(0.5));
        sourCream.setUom(cup);
        ingredients2.add(sourCream);

        Ingredient lime = new Ingredient();
        lime.setRecipe(taco);
        lime.setDescription("Lime");
        lime.setAmount(BigDecimal.ONE);
        lime.setUom(each);
        ingredients2.add(lime);
        taco.setIngredients(ingredients2);
        return recipeSet;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
       recipeRepository.saveAll(createRecipes());
    }
}