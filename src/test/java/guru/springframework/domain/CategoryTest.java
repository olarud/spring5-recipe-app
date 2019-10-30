package guru.springframework.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CategoryTest {
    public static final Long ID = 4L;
    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() throws Exception {
        category.setId(ID);
        Assert.assertEquals(ID, category.getId());
    }
}
