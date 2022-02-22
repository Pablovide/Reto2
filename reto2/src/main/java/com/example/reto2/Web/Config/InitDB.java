package com.example.reto2.Web.Config;

import java.util.Date;

import com.example.reto2.Repositories.Entities.OrderEntity;
import com.example.reto2.Repositories.Entities.OrderProductEntity;
import com.example.reto2.Repositories.Entities.ProductEntity;
import com.example.reto2.Repositories.Interfaces.OrderProductRepository;
import com.example.reto2.Repositories.Interfaces.OrderRepository;
import com.example.reto2.Repositories.Interfaces.ProductRepository;
import com.example.reto2.Service.Enums.OrderStatus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDB {
    @Bean
    CommandLineRunner initDatabase(OrderRepository orderRepos, ProductRepository productRepos, OrderProductRepository orderProductRepos){
        return args -> {
            // orderRepos.save(new OrderEntity(new Date(),"Matthus Manntschke", OrderStatus.CANCELLED.toString()));
            // orderRepos.save(new OrderEntity(new Date(),"Avis Lovewell", OrderStatus.DELIVERED.toString()));
            // orderRepos.save(new OrderEntity(new Date(),"Marthe Jerson", OrderStatus.CANCELLED.toString()));
            // orderRepos.save(new OrderEntity(new Date(),"Xerxes Lapham", OrderStatus.SHIPPED.toString()));
            // orderRepos.save(new OrderEntity(new Date(),"Brandice Loughhead", OrderStatus.ACCEPTED.toString()));
            productRepos.save(new ProductEntity("TV Set", 300, "https://www.amazon.es/Hisense-UHD-2020-43AE7000F-Resoluci%C3%B3n/dp/B08553S69P/ref=sr_1_7?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=televisor&qid=1639057872&sr=8-7&th=1"));
            productRepos.save(new ProductEntity("Game Console", 200, "https://www.amazon.es/Nintendo-Switch-Lite-Consola-Estandar/dp/B07V5JJHK4/ref=sr_1_9?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=379R66AG2DEWV&keywords=nintendo+switch&qid=1639057941&sprefix=nintendo+swi%2Caps%2C180&sr=8-9"));
            productRepos.save(new ProductEntity("Sofa", 100, "https://www.amazon.es/Miriam-Sof%C3%A1-Esquina-Color-Burdeos/dp/B08H99PYKT/ref=sr_1_12?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=sofa&qid=1639057890&sr=8-12&th=1"));
            productRepos.save(new ProductEntity("Icecream", 5, "https://www.amazon.es/Ben-Jerrys-Cookie-Dough-Helado/dp/B002VLAS6M/ref=sr_1_8_0o_DIA?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=helado&qid=1639057963&sr=8-8"));
            productRepos.save(new ProductEntity("Beer", 3, "https://www.amazon.es/Potter-Cerveza-Mantequilla-Alcohol-Horrocrux/dp/B07M61MT8F/ref=sr_1_3?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=cerveza&qid=1639058003&refinements=p_36%3A831296031&rnid=831274031&s=grocery&sr=1-3"));
            productRepos.save(new ProductEntity("Phone", 500, "https://www.amazon.es/Plateado-Pantalla-almacenamiento-cu%C3%A1druple-garant%C3%ADa/dp/B08HJGQ9ZS/ref=sr_1_4?keywords=movil&qid=1639058031&refinements=p_36%3A45000-55000&rnid=1323854031&sr=8-4&th=1"));
            // orderProductRepos.save(new OrderProductEntity(1, 3, 8));
            // orderProductRepos.save(new OrderProductEntity(2, 1, 1));
            // orderProductRepos.save(new OrderProductEntity(3, 2, 10));
            // orderProductRepos.save(new OrderProductEntity(4, 6, 3));
            // orderProductRepos.save(new OrderProductEntity(5, 5, 20));
        };
    }
}
