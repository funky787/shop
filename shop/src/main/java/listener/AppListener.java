package listener;

import model.Product;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConcurrentHashMap<Integer, Product> catalog = new ConcurrentHashMap<>();

        catalog.put(1, new Product(1, "Приора", 500000, 3, "priora.jpg", "Лобовуха в хлам, таз сидит низко\n" +
                "Корпус по асфальту высекает искры\n" +
                "Копы на хвосте, мы в зоне риска\n" +
                "Я вообще не местный, да и без прописки"));
        catalog.put(2, new Product(2, "УАЗ", 300000, 5, "YAZ.jpg", "советский легендарный внедорожник повышенной проходимости\n"+
                "изначально военного назначения"));
        catalog.put(3, new Product(3, "Ведро на колесах", 3000, 10, "vedro.jpg", "Ведро с болтами сталинских времен"));
        catalog.put(4, new Product(4, "Porshe Panamera", 20000000, 7, "panamera.jpg", "Спортивный автомобиль бизнес-класса с мощным двигателем"));
        catalog.put(5, new Product(5, "EVA-коврик", 1500, 8, "evak.jpg", "Автомобильные коврики EVA удерживают до 1.5 литра воды\n"+
                "грязи и пыли, сохраняя в чистоте салон"));

        sce.getServletContext().setAttribute("catalog", catalog);
    }
}