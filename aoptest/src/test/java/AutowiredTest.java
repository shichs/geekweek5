import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.SpringAutowiredConfig;
import service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAutowiredConfig.class)
public class AutowiredTest {

    @Autowired
    private UserService userService;
    @Test
    public void springTest() {
        userService.run();
    }

}
