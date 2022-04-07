package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

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
public class ProductDetial extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long Seq;

	/**
	 * 分類編號
	 */
	private String categoryType;

	/**
	 * 分類名稱
	 */
	private String name;

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

	/**
	 * 是否顯示
	 */
	private String display;
}
