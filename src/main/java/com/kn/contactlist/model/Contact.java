package com.kn.contactlist.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CONTACT", schema = "PUBLIC")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME", length=100, nullable=false)
    private String name;

    @Column(name="URL", length=200, nullable=false)
    private String url;

    public Contact(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
