package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dao.InquiryDao;

import com.example.demo.domain.entity.Inquiry;




@Service
public class InquiryServiceImpl implements InquiryService{
	
	
	//daoをインスタンス化してそこにサービスで定義した処理のメソッドを埋め込んでいく
	private InquiryDao dao;
	
	//daoクラスのインスタンスをnewせずに使える。
	@Autowired
	public InquiryServiceImpl(InquiryDao dao) {
		this.dao = dao;
	}
	
	
	//インターフェースのメソッドのオーバーライド
	@Override
	public void save(Inquiry inquiry) {      //インターフェースで指定したメソッドを実装する。
		dao.save(inquiry);                   //内容はdaoクラスのメソッドを入れる。
	}
	
	@Override
	public List<Inquiry> findAll(){
		return dao.findAll();
	}
	
	@Override
	public Inquiry findById(Integer id) {
		return dao.findById(id).get();
	}
	
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
	
	
}