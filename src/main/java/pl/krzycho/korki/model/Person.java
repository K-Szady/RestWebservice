package pl.krzycho.korki.model;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_person")
    @SequenceGenerator(sequenceName = "SQ_person",allocationSize = 1, name ="SQ_person" )
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SUR_NAME")
    private String surName;
    @Column(name = "PESEL")
    private int pesel;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Person(String name, String surName, int pesel, Gender gender) {
        this.name = name;
        this.surName = surName;
        this.pesel = pesel;
        this.gender = gender;
    }

    public Person(){ ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return   name + ","  + surName + ","  + pesel+ "," +  gender;
    }
}
