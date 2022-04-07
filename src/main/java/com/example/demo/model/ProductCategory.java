package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductCategory extends BaseBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long Seq;

	@Column(name = "TYPE")
	private String type;

	/**
	 * 分類名稱
	 */
	private String name;
	
	@Column(name = "ENABLE")
	private String enable;

	/**
	 * 建立日期
	 */
	private Date createdDate;

	/**
	 * 變更日期
	 */
	private Date updateDate;

	/**
	 * 建立人員
	 */
	private String createUser;

	/**
	 * 變更人員
	 */
	private String updateUser;

}
