package service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ProductService;
import service.UserService;

@Component
@Data
public class UserServiceImpl implements UserService {
    @Autowired
    private ProductService productService;

    public void run() {
        System.out.println("--------user service run--------");
    }

    public void runProduct() {
        productService.run();
    }
}
