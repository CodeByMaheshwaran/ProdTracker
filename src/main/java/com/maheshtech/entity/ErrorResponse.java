package com.maheshtech.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
private String message;
private Object data;
}
