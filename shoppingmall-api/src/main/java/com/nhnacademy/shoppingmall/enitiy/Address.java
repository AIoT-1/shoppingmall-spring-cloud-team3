package com.nhnacademy.shoppingmall.enitiy;

import com.nhnacademy.shoppingmall.dto.AddressDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Setter
    private String defaultYn;
    @Builder
    public Address(User user, String type, String zipCode, String address, String addressDetail, String defaultYn) {
        this.user = user;
        this.type = type;
        this.zipCode = zipCode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.defaultYn = defaultYn;
    }

    public void update(AddressDto.Update.Request request){
        this.type = request.getType();
        this.zipCode = request.getZipCode();
        this.address = request.getAddress();
        this.addressDetail = request.getAddressDetail();
    }

}
