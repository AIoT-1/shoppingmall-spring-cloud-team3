package com.nhnacademy.shoppingmall.enitiy;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    @Setter
    private Integer price;
    @Column(name = "order_date")
    @CreationTimestamp
    private LocalDateTime orderDate;
    @Column(name = "ship_date")
    private LocalDateTime shipDate;

    @Builder
    private Order(User user, Address address) {
        this.user = user;
        this.address = address.getZipCode() + " | " + address.getAddress() + " | " + address.getAddressDetail();
        this.price = 0;
    }

    public void increaseOrderPrice(int orderPrice) {
        this.price += orderPrice;
    }

}
