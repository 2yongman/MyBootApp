package com.tuyano.springboot.mybootapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "msgdata")
@Getter @Setter
public class MsgData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private Long id;

    @Column
    private String title;

    @Column
    private String message;

    @ManyToOne
    private MyData myData;


}
