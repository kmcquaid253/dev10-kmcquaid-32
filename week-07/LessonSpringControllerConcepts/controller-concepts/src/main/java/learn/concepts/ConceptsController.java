package learn.concepts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                   // 1
public class ConceptsController {

    @GetMapping("/")              // 2
    public String helloWorld() {
        return "Hello world.";    // 3

        /*
        1.  - Both @RestController and @Controller are valid Spring MVC controllers.
            - @RestController is used for JSON content -- HTTP services.
            - @Controller is general-purpose, but is commonly used for HTML content

        2.  - All annotations that end with Mapping.
            - @GetMapping("/") indicates our helloWorld method can handle GET requests on the "/" relative path EX: http://localhost:8080/
            - single slash route also handles a host request with no relative path. EX:. http://localhost:8080.

        3.  - Any return value is included in the HTTP response body.
            -  A void method still returns an HTTP response
 */



        }
    @PostMapping("/urlencoded")
    public void readFromBody(String name, int age, boolean likesCookies) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Likes Cookies?: " + likesCookies);


    }

    @GetMapping("/get")
    public List<String> doGet() {
        return List.of("apple", "orange", "pear", "grape");
    }

    @PostMapping("/post")
    public ResponseEntity<Pet> doPost(@RequestBody Pet pet) {
        if (!isValid(pet)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @PutMapping("/put")
    public ResponseEntity<Void> doPut(@RequestBody Pet pet) {
        if (pet.getPetId() != 15) {
            return ResponseEntity.notFound().build();
        } else if (!isValid(pet)) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    private boolean isValid(Pet pet) {
        return pet != null
                && pet.getName() != null
                && pet.getName().trim().length() > 0;
    }
}
