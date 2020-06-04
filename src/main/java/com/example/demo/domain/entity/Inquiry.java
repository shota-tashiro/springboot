package com.example.demo.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity                            //エンティティクラスであることを示す
@Table(name="form")                //テーブル名を明示的に指定（指定しない場合はクラス名がテーブル名になる）
public class Inquiry {
	
	@Id                            //主キーに指定。属性またはgetterメソッドにつける。
	@GeneratedValue(strategy=GenerationType.IDENTITY)                   //主キーの値が自動生成
	private Integer id;     //privateにしないといけない？
	@NotEmpty                 //エンティティクラスにバリデーションの設定をする
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	@Size(max = 20)
	private String text;
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
//	@Column　　　　　　　　　　　　　　　　　　　　　　　//属性またはgetterメソッドに、@Columnアノテーションを付加することにより、カラム名やnullableか否かなど、データベースのカラムに関する情報を指定できる。
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
