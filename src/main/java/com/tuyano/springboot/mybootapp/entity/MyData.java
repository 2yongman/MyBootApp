package com.tuyano.springboot.mybootapp.entity;

import com.tuyano.springboot.mybootapp.validate.Phone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "mydata")
public class MyData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private Long id;

    @Column(length = 50,nullable = false)
    @NotEmpty(message = "공백 불가")
    private String name;

    @Column
    @Min(value = 0,message = "0이상")
    @Max(value = 200, message = "200이하")
    private int age;

    @Column(length = 200)
    @Email(message = "메일 주소만")
    private String email;

    @Column(nullable = true)
    @Phone(onlyNumber = true)
    private String memo;

    @OneToMany(cascade = CascadeType.ALL)
    @Column
    private List<MsgData> msgdatas;
}
