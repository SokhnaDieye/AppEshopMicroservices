package sn.isi.productservice.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reference;
    private String designation;
    private double prix;
    private int quantite;

    @ManyToOne
    private Category categorie;
}

