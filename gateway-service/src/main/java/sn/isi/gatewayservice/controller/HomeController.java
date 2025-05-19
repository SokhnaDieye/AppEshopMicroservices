package sn.isi.gatewayservice.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public Mono<String> home() {
        return Mono.just("""
                <!DOCTYPE html>
                <html>
                <head>
                    <title>API Gateway</title>
                    <style>
                        body { font-family: Arial, sans-serif; margin: 40px; }
                        h1 { color: #333; }
                        .endpoint { background-color: #f4f4f4; padding: 10px; margin-bottom: 10px; border-radius: 5px; }
                    </style>
                </head>
                <body>
                    <h1>API Gateway</h1>
                    <p>Bienvenue sur la passerelle API du système de microservices.</p>
                    
                    <h2>Endpoints disponibles :</h2>
                    <div class="endpoint"><a href="http://localhost:8088/api/users">  → Service Utilisateur </a></div>
                    <div class="endpoint"><a href="http://localhost:8088/api/products"> → Service Produit </a> </div>
                    <div class="endpoint"><a href="http://localhost:8088/api/categories"> → Service Categories </a> </div>
                    <div class="endpoint"><a href="http://localhost:8088/api/orders/">  → Service Commande</a></div>
                </body>
                </html>
                """);
    }
}