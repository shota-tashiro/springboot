package com.example.demo.domain.service;

import com.example.demo.domain.entity.Inquiry;

import java.util.List;



//インターフェースでdaoクラスのメソッドと同じ名前のメソッドを定義しておく。実装先（impl）で使う。
public interface InquiryService {
	public void save(Inquiry inquiry);
	
	public List<Inquiry> findAll();
	
	public Inquiry findById(Integer id);
	
	public void deleteById(Integer id);
}
