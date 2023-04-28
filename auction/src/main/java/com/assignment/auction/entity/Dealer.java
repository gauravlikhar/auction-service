/**
 * @author yukti.gupta
 * @date 27/04/23
 * @project_name auction
 **/

package com.assignment.auction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dealer")
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long dealerId;

    @Column(name = "name", columnDefinition = "varchar(45)")
    private String name;

    @Column(name = "id", columnDefinition = "char(40)")
    private String phone;

    @Column(name = "email_id", columnDefinition = "varchar(255)")
    private String emailId;

}
