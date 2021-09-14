package ru.netology.hibernate.repository.PersonEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "persons")
public class Person {

    @EmbeddedId
    private PersonPK personPK;

    @Column(name = "phone_number", length = 25)
    private String phoneNumber;

    @Column(name = "city_of_living", length = 50)
    private String cityOfLiving;



}
