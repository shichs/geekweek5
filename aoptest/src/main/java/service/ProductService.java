package service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {
    private int id;
    private String name;
    public void run() {
        System.out.println("-------product service run--------");
    }
}
