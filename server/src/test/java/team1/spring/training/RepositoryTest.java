package team1.spring.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import team1.spring.training.models.File;
import team1.spring.training.repository.FileRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FileRepository repository;

    File file1 = new File("chen.txt", "uploads//chen.txt","11.2.4444");
//H2

    @Test
    public void testFindByName() {
       File file = new File();
        file.setName("chen.txt");
        entityManager.persist(file1);
        List<File> files = repository.findByName("chen.txt");
        assertEquals(1, files.size());
        assertThat(files).extracting(File::getName).containsOnly("chen.txt");
    }

    //CREATE & UPDATE
    @Test
    public void testCreate() {
        //create
        repository.save(file1);
        assertThat(repository.count()).isEqualTo(1);
        //update
        assertEquals("chen.txt", file1.getName());
        file1.setName("avital.txt");
        repository.save(file1);
        assertEquals("avital.txt", file1.getName());
    }

    //DELETE
    @Test
    public void testDelete() {
        repository.save(file1);
        repository.delete(file1);
        assertThat(repository.count()).isEqualTo(0);
    }
}
