
package com.itheima.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer oid;
    private Integer uid;
    private String username;
    private Integer pid;
    private String pname;
    private Double pprice;
    private Integer number;
}
