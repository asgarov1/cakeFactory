package com.asgarov.liveproject.cakefactory.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String email;

    @Column
    private String addressLine;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String zip;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (getEmail() != null ? !getEmail().equals(address.getEmail()) : address.getEmail() != null) return false;
        if (getAddressLine() != null ? !getAddressLine().equals(address.getAddressLine()) : address.getAddressLine() != null)
            return false;
        if (getCountry() != null ? !getCountry().equals(address.getCountry()) : address.getCountry() != null)
            return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;
        return getZip() != null ? getZip().equals(address.getZip()) : address.getZip() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + (getAddressLine() != null ? getAddressLine().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getZip() != null ? getZip().hashCode() : 0);
        return result;
    }
}

