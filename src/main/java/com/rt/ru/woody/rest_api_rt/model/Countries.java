package com.rt.ru.woody.rest_api_rt.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "Countries")
@Table(name = "country_data")
public class Countries{

    @Id
    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "PHONE_CODES")
    private String phoneCodes;


    public Countries(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }
}
