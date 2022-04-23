package com.rt.ru.woody.rest_api_rt.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity(name = "Countries")
@Table(name = "country_data",
       indexes = @Index(name = "full_name_index", columnList = "FULL_NAME_TO_LOWER", unique = true))
public class Countries{

    @Id
    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "FULL_NAME_TO_LOWER")
    private String fullNameToLower;

    @Column(name = "PHONE_CODE")
    private String phoneCode;

    public Countries(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }
}
