package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "kullanicilar")
@Data
@Getter
@Setter
public class User extends BaseEntity {
    //burada base classı extend oldu ve user oradakı entıtylerı kullanacak

    @Id
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_gen" , initialValue = 1 , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ISIM" , length = 100)
    private String firstName;

    @Column(name = "SOYISIM" , length = 100)
    private String lastName;
}
