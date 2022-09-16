package learn.hoge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController //Return your name
public class HodgepodgeController {

    static int sheepCount = 0;
    static ArrayList<String> todos = new ArrayList<>();

    @GetMapping("/name")              // 2
    public String getName() {
        return "Karen McQuaid";    // 3
    }

    @GetMapping("/current/time")//what time  is it
    public LocalDateTime getTime() {

        return LocalDateTime.now();

    }

    @GetMapping("/greet/{name}")//Greeting
    public String greeting(@PathVariable String name) {

        return "Hello, Dr." + name;

    }

    @GetMapping("/sheep")//Counting sheep
    public int countingSheep() {

        return sheepCount++;

    }

    @GetMapping("/todo")//Counting sheep
    public ArrayList<String> todos() {

        return todos;

    }


}
