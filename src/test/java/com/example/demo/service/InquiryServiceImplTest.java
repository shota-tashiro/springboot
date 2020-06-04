package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.domain.dao.InquiryDao;
import com.example.demo.domain.entity.Inquiry;
import com.example.demo.domain.service.InquiryServiceImpl;



//サービスクラスのテスト




@ExtendWith(SpringExtension.class)           
@DisplayName("サービスのテスト") 
public class InquiryServiceImplTest {
	
	
	//サービスクラスが依存しているdaoクラスをモックにする。
	@Mock
	InquiryDao dao;
	
	//テストするクラスを指定する。
	@InjectMocks
	InquiryServiceImpl service;
	
	
	//メソッドごとにテストする。
	@Test                                      //テストするメソッドに付ける。
	@DisplayName("findAllメソッドのテスト")
	void findAll() {
		Inquiry inquiry1 = new Inquiry();
	    Inquiry inquiry2 = new Inquiry();
	    Inquiry inquiry3 = new Inquiry();
		List<Inquiry> list = new ArrayList<>();
		
		list.add(inquiry1);
		list.add(inquiry2);
		list.add(inquiry3);
		
		
		//daoクラスのメソッドを呼び出していることをテストする。
		
		//daoクラスのfindAllメソッドの戻り値をlistにする。
		when(dao.findAll()).thenReturn(list);      //daoクラスのfindAllメソッドの戻り値を任意に指定できる。
		assertEquals(list,dao.findAll());
		
		List<Inquiry> testList = service.findAll();
		
		//dao(モック)オブジェクトが何回findAllメソッドを呼び出したか確認する。
		verify(dao,times(2)).findAll();
		
		//配列testListの大きさが３と等しいか確認する。
		assertEquals(3,testList.size());
		
		
	}
	
	@Test                                      //テストするメソッドに付ける。
	@DisplayName("saveメソッドのテスト")
	void save() {
		Inquiry inquiry = new Inquiry();
		
		//savaメソッドを実行する。
		service.save(inquiry);                 //serviceのsaveメソッドが呼び出さるという事はdao.saveが実行されるという事。
		
		//dao(モック)オブジェクトが何回saveメソッドを呼び出したかを確認する。
		verify(dao,times(1)).save(inquiry);
	}
	
	
	
	
	

}
