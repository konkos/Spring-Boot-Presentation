package gr.uom.opensource;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/{user}")
    public String sayHello(@PathVariable String user){
        return "Hello " + user;
    }
}
