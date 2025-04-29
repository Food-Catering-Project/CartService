package com.example.CartService.Entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "owners")
@Builder
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String number;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String panCard;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public Owner() {
    }

    public Owner(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Owner(Long ownerId, String name, String number, String address, String password, String panCard) {
        this.ownerId = ownerId;
        this.name = name;
        this.number = number;
        this.address = address;
        this.password = password;
        this.panCard = panCard;
    }
}