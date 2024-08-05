package com.maheshtech.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Entity
@ToString
@NoArgsConstructor
@Getter
@Setter
@Table
public class Product {
	@Id
	@GeneratedValue(generator = "increment")
	private Integer product_ID;
	private String product_name;
	private Integer product_price;
	private String product_desc;
	private Integer quantity;
	@XmlTransient
	private Integer discontinued;
}
