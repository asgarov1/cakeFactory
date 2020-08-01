package com.asgarov.liveproject.cakefactory.domain;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String email;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (getEmail() != null ? !getEmail().equals(account.getEmail()) : account.getEmail() != null) return false;
        if (getPassword() != null ? !getPassword().equals(account.getPassword()) : account.getPassword() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(account.getFirstName()) : account.getFirstName() != null)
            return false;
        return getLastName() != null ? getLastName().equals(account.getLastName()) : account.getLastName() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        return result;
    }
}
