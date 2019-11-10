package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIntegrationTest {

    @Autowired
    UnitOfMeasureRepository repository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findByDescriptionTeaspoon() {
        Optional<UnitOfMeasure> optionalUnitOfMeasure = repository.findByDescription("Teaspoon");
        assertTrue(optionalUnitOfMeasure.isPresent());
        assertEquals("Teaspoon", optionalUnitOfMeasure.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() {
        Optional<UnitOfMeasure> optionalUnitOfMeasure = repository.findByDescription("Cup");
        assertTrue(optionalUnitOfMeasure.isPresent());
        assertEquals("Cup", optionalUnitOfMeasure.get().getDescription());
    }
}