package com.example.demo.app;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


//Bean。フォームに入力した内容がパラメータごとに格納される。
//プロパティのアクセス修飾子はprivateとする。
//ゲッター、セッターが必要。
//プロパティはBeanクラスに格納され、メソッドを介してこれらの属性を取り出し、設定する。
public class InquiryForm {
	
	@NotBlank(message="名前の入力は必須です")
	private String name;
	@NotBlank(message="メールアドレスの入力は必須です")
	@Email(message="正しい表記で記入してください")
	private String email;
	@NotBlank(message="問い合わせの入力は必須です")
	 @Size(max = 20)
	private String text;
	
	
	public InquiryForm() {
		
	}
	
	//セッター、ゲッターを作る
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	

}
