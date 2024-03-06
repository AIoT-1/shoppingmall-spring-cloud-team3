package com.nhnacademy.shoppingmall.enitiy;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "address")
@DynamicInsert
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    @Column(name = "type")
    private String type;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "address")
    private String address;
    @Column(name = "address_detail")
    private String addressDetail;
    @Column(name = "default_yn")
    private String defaultYn;
}
