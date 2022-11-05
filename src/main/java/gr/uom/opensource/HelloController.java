package gr.uom.opensource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/{user}")
    public String sayHello(@PathVariable String user) {
        return "Hello " + user;
    }

    @GetMapping("/user")
    public String sayHelloWithParam(@RequestParam String name) {
        return "Hello " + name;
    }
}
