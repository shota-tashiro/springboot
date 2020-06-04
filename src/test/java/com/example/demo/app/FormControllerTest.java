package com.example.demo.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.demo.domain.service.InquiryService;


//コントローラーのテストをする。


@ExtendWith(SpringExtension.class)           //拡張方法
@DisplayName("コントローラーのテスト")              //テストに名前を付けられる
public class FormControllerTest {
	
	private MockMvc mockMvc;                 //MockMvcクラスを宣言。
	
	
	
	@Mock                                    //サービスクラスをモックにしてテスト範囲外にする。(テスト対象のクラス内で呼び出すクラス（依存クラス）をモック化する)
	InquiryService service;
	
	
	@InjectMocks                              //クラスのインスタンスを作成し@Mockで作成されたモックをこのインスタンスに挿入する。
	FormController formController;         //テストするコントローラーのインスタンスを生成する。(今回はコントローラー）
	
	
	@BeforeEach                               //テストの前提条件(テスト前に行う処理）を記述する。
	public void setup() {
		MockitoAnnotations.initMocks(this);    // 各テストの実行前にモックオブジェクトを初期化する
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");
 
        this.mockMvc = MockMvcBuilders.standaloneSetup(formController)
                                 .setViewResolvers(viewResolver)
                                 .build();
		//テスト対象のControllerクラスにアクセスしたときの動作を検証することができます。
	}
	 
	
	
	@Test                                      //テストするメソッドに付ける。
	@DisplayName("新規入力ページにアクセス")
	void 新規入力ページにGETでアクセス() throws Exception{           //テストメソッド名は日本語でOK。わかりやすく。
		this.mockMvc.perform(get("/form"))
	    .andExpect(status().isOk())
	    .andExpect(model().attribute("title","入力画面"))
	    .andExpect(model().attribute("headLine","入力してください"))
	    .andExpect(view().name("form"));
		
	}
	
	@Test
	@DisplayName("フォームに問題が無い場合、確認画面に遷移する")
	void 確認画面にPOSTでアクセスしてフォームに問題が無い場合は確認画面に遷移する() throws Exception{
		this.mockMvc.perform(
				post("/confirm").param("name","taro").param("email","taro@gmail.com").param("text","問い合わせ１"))
		.andExpect(model().hasNoErrors())                  //フォームに問題が無いことを確認。（paramで指定した値が変数に入っているか確認）
		.andExpect(status().isOk())                        //ステータス200でレスポンスがあることを確認。
		.andExpect(model().attribute("title","確認画面"))   //titleに"確認画面"が入っていることを確認。
		.andExpect(view().name("confirm"));                //confirmビューが返されることを確認。
	}
	
	
	
	@Test
	@DisplayName("フォームに問題がある場合、入力画面に遷移する")
	void 確認画面にPOSTでアクセスしてフォームに問題がある場合は入力画面に遷移する() throws Exception{
		this.mockMvc.perform(
				post("/confirm").param("name", "").param("email", "").param("text", ""))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(model().attribute("title","入力画面"))
		.andExpect(view().name("form"));
	}
	
	@Test
	@DisplayName("確認画面で戻るボタンが押下された場合、入力画面に遷移する")
	void 入力画面にPOSTでアクセス() throws Exception {
		this.mockMvc.perform(
				post("/form").param("name", "jiro").param("email", "taro@gmail.com").param("text", "問い合わせ１"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("title","入力画面"))
		.andExpect(model().attribute("headLine","入力してください"))
		.andExpect(view().name("form"));
		
	}
	
	
	@Test
	@DisplayName("確認画面で送信ボタンが押下された場合、一覧画面に遷移する")
	void 一覧画面にPOSTでアクセス() throws Exception {
		this.mockMvc.perform(post("/index"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("title","一覧画面"))
		.andExpect(view().name("index"));
	}
	
	
	
	

}
