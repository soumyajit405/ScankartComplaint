package com.scankart.app.feature.complaint;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class ComplaintTypePlDto  {

	private int id;

	private String description;

	private String name;

	private String status;

}