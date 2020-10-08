package penguin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import penguin.web.modle.ProfileForm;

@Controller
@RequestMapping("profile")
//「このクラスは "name" という名前でセッションに情報を保存します」という宣言
//@SessionAttributes(names = "name")
//request.setSession("profileForm", setProfileForm()):セッションに情報を保存するキー名
@SessionAttributes("profileForm")
public class ProfileController {
	//このメソッドの復帰値が "name" という名前のセッション情報に保存される
    //@ModelAttribute("name")
    //public String setName(String name) {
    //    return name;
    //}
	//フォームをHTTPセッションに保存する
    @ModelAttribute("profileForm")
    public ProfileForm setProfileForm() {
        return new ProfileForm();
    }	
    
    //@RequestMapping("name")
    @GetMapping("name")
    //"src/main/resources/templates/" + コントローラーメソッドの復帰値 + ".html"を表示する
    public String name() {
        return "name";
    }
    
    //@RequestMapping(path = "age", method = RequestMethod.POST)
    @PostMapping("age")
    //@RequestParamアノテーション: 画面から送信されたフォームデータを受け取るためのもので、
    //受け取るフォームデータの名前を属性
    //Model: 次の画面に渡すデータを設定するためのオブジェクト
    //public String age(@RequestParam("name") String name, Model model) {
    //public String age(@RequestParam("name") String name) {
    //public String age(ProfileForm profileForm) {
    public String age(@Validated ProfileForm profileForm, BindingResult result) {
        //model.addAttribute("name", name);
    	//setName(name);
    	
    	//profileFormが使われていないのになぜ引数にする必要？
    	//引数に指定することで送信データがHTTPセッションに保存されているフォームオブジェクトに設定されるためです
    	
    	//@Validatedアノテーション、フォームクラスに指定したバリデーションアノテーションに従って、バリデーションが実行され
    	//BindingResult result、フォームとバリデーションの結果も格納されてる
        if (result.hasErrors()) {
            return "name";
        }    	
        return "age";
    }
    
    //@RequestMapping(path = "hello", method = RequestMethod.POST)
    //public String hello(@RequestParam("age") String age, 
    //		            Model model) {
    //public String hello(@RequestParam("age") String age, 
	//                    Model model,
	//                    SessionStatus sessionStatus) {
    //	//このクラスで管理しているHTTPセッションを破棄
    //	sessionStatus.setComplete();
    //    model.addAttribute("age", age);
    //    return "hello";
    //}
    @PostMapping("hello")
    public String hello(ProfileForm profileForm, 
    		            SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "hello";
    }   
	                    	
}