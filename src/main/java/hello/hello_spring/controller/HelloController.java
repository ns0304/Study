package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
  @GetMapping("hello")
  public String hello(Model model){
    model.addAttribute("data","hello!!");
    return "hello";
  }
  @GetMapping("hello-mvc")
  public String helloMvc(@RequestParam("name") String name, Model model){
    model.addAttribute("name", name);
    return "hello-template";
  }
  @GetMapping("hello-string")
  @ResponseBody//http 바디 부분에  return "hello " + name;를 직접 넣겠다는 의미
  public String helloString(@RequestParam("name") String name){

    return "반갑도다 " + name;
  }
  @GetMapping("hello-api")
  @ResponseBody
  public Hello helloApi(@RequestParam("name") String name){
    Hello hello = new Hello();
    hello.setName(name);
    return hello;
  }

  static class Hello{
    private String name;

    public String getName() {//alt+insert키를 누르면 게터 세터 메서드
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

}
