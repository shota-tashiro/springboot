package com.example.demo.app;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.entity.Inquiry;
import com.example.demo.domain.service.InquiryService;

@Controller
public class FormController {
	
	
	//サービスクラスのインスタンスをnewせずに使用できる。
	@Autowired
	private InquiryService service;
	
	
	//一覧画面に遷移
	//一覧画面がトップページ扱い
		@RequestMapping(value="index",method = RequestMethod.GET)
		public String index(Model model) {
			List<Inquiry> list = service.findAll();
			model.addAttribute("title","一覧画面");
			model.addAttribute("inquityList",list);
			return "index";
		}
		
		
	//新規入力画面に遷移[form]
	@RequestMapping(value = "form",method = RequestMethod.GET)
	public String form(InquiryForm inquiryForm,Model model) {   //InquiryForm inquiryForm無いとなんでダメ？
		model.addAttribute("title","入力画面");
		model.addAttribute("headLine","入力してください");
		return "form";
	}
	
	
	
	//確認画面に遷移
	//この段階ではデータベースに値は保存されない→エンティティのInquiryではなく、BeanのInquiryFormの値を表示する
	@RequestMapping(value = "confirm",method = RequestMethod.POST)
	public String confirm(@Validated InquiryForm inquiryForm,BindingResult result, Model model) {
		//バリデーションエラーが発生した場合、入力画面に遷移する。
		if(result.hasErrors()) {  //もし（バリデーション）エラーが発生したら。
			model.addAttribute("title","入力画面");
			model.addAttribute("headLine","正しく入力しなおしてください");
			return "form";
		}
		//バリデーションエラーが発生しなかった場合、確認画面に遷移する。
		model.addAttribute("title","確認画面");
//		model.addAttribute("inquiyForm",inquiryForm);   //メソッドの引数に指定しているので記述が無くてもinquiryFormが渡される。
		return "confirm";
		
	}
	
	
//	確認画面から戻るボタンで入力画面に遷移する。
	@RequestMapping(value = "form",method = RequestMethod.POST)
	public String BackForm(InquiryForm inquiryForm,Model model) {
		model.addAttribute("title","入力画面");
		model.addAttribute("headLine","入力してください");
		return "form";
	}
	
	
	
	//確認画面から送信ボタンで一覧画面に遷移する。
	@RequestMapping(value="index",method = RequestMethod.POST)
	public String list(Inquiry inquiry,InquiryForm inquiryForm,Model model) {
		inquiry.setName(inquiryForm.getName());          //inquiryForm(Bean)に格納されているデータをゲッターで取り出してinquiry(エンティティ)にセッターで格納する。
		inquiry.setEmail(inquiryForm.getEmail());
		inquiry.setText(inquiryForm.getText());
		service.save(inquiry);                           //サービスクラスのインスタンスでsavaメソッドを使ってエンティティの情報をdaoに保存してDBにアクセスする。
		List<Inquiry> list = service.findAll();          //
		model.addAttribute("title","一覧画面");
		model.addAttribute("inquiryList",list);
		return "index";
	}
	
	
	//一覧画面からIDリンクを押下して詳細画面へ遷移する。
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,Model model) {
		Inquiry inquiry = service.findById(id);
		model.addAttribute("title","詳細画面");
		model.addAttribute("headLine","詳細情報");
		model.addAttribute("inquiryShow",inquiry);
		return "show";
	}
	

	//詳細画面から削除ボタンを押下して一覧画面にリダイレクト。　　　　　　　　　　なんでリダイレクト？
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	public String del(@PathVariable Integer id) {
		service.deleteById(id);
		return "redirect:/index";
	}
	
	//詳細画面から編集ボタンを押下して編集画面に遷移する。
	@RequestMapping(value="{id}/edit",method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,InquiryForm inquiryFrom,Model model) {    //InquiryFormいる？
		Inquiry inquiry = service.findById(id);
		model.addAttribute("title","編集画面");
		model.addAttribute("headLine","編集してください");
		model.addAttribute("inquiryEdit",inquiry);
		return "edit";
	}
	
	//編集画面で更新ボタンを押下して詳細画面に遷移する。
	@RequestMapping(value="{id}",method = RequestMethod.PUT)
	public String update(@Validated InquiryForm inquiryForm,BindingResult result,@PathVariable Integer id,@ModelAttribute Inquiry inquiry,Model model) {
		if (result.hasErrors())
		{
			Inquiry inquiryEdit = service.findById(id);
			model.addAttribute("title","編集画面");
			model.addAttribute("headLine","正しく編集してください");
			model.addAttribute("inquiryEdit",inquiryEdit);
			return "edit";
		}
		inquiry.setId(id);
		service.save(inquiry);
		model.addAttribute("title","詳細画面");
		model.addAttribute("headLine","詳細情報");
		model.addAttribute("inquiryShow",inquiry);
		return "show";
	}

	
	

}


//get:index　最初にアクセスする一覧画面
//post:index　確認画面で送信を押した後に遷移する一覧画面
//get:
//post:form　確認画面から戻るボタンで遷移する入力画面（入力データ持越し）
//post:confirm　入力画面で確認ボタンを押した後に遷移する確認画面
//　
