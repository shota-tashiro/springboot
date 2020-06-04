package com.example.demo.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entity.Inquiry;



                                                  //エンティティとプライマリーキーのデータ型
public interface InquiryDao extends JpaRepository<Inquiry, Integer> {
	
	//JpaRepositoryクラスのメソッドを継承する。
	//findAll()
	//save(entity)
	//deleteById(id)
	//findById(id)
	}