package com.maheshtech.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@Setter
@Getter
@XmlRootElement(name = "Products")
public class ProductList {

	@XmlElement(name = "product")
	private List<Product> products;
}
