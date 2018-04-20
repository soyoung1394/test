package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductDaoTest {
    ProductDao productDao;
    DaoFactory daoFactory;

    @Before
    public void setup() {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(DaoFactory.class);
        productDao=applicationContext.getBean("productDao", ProductDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Product product=new Product();
        product.setTitle("b");
        product.setPrice(100);
        Long id=productDao.insert(product);

        Product insertProduct=productDao.get(id);
        assertThat(insertProduct.getId(),is(id));
        assertThat(insertProduct.getTitle(),is(product.getTitle()));
        assertThat(insertProduct.getPrice(),is(product.getPrice()));
    }
    @Test
    public void update() throws SQLException, ClassNotFoundException {
        Product product=new Product();
        product.setTitle("b");
        product.setPrice(100);
        Long id=productDao.insert(product);

        product.setId(id);
        product.setTitle("c");
        product.setPrice(100);
        productDao.update(product);

        Product updateProduct=productDao.get(id);
        assertThat(updateProduct.getId(),is(id));
        assertThat(updateProduct.getTitle(),is(product.getTitle()));
        assertThat(updateProduct.getPrice(),is(product.getPrice()));

    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        Product product=new Product();
        product.setTitle("b");
        product.setPrice(100);
        Long id=productDao.insert(product);

        productDao.delete(id);
        Product deleteProduct=productDao.get(id);
        assertThat(deleteProduct, nullValue());


    }
}
