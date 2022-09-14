package learn.concepts;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")    //@RequestMapping annotation can be applied to a controller class.
                            //This creates a path prefix for all handlers in the class.

                            // In addition, some projects serve both HTML content and JSON content.
                            // To differ between the two, a project might add an /api prefix (or the like) to all HTTP service controllers.
public class PetController {

    @GetMapping
    public void findAll() {
    }

    @GetMapping("/{petId}")
    public void findById(@PathVariable int petId) {
    }

    @PostMapping
    public void create(@RequestBody Pet pet ) {  //<-- The @RequestBody annotation tells Spring MVC to look in the request body for the content
                                                // in the same way @PathVariable tells Spring MVC to look in the request path.

        System.out.println(pet);
    }

    @PutMapping("/{petId}")
    public void update(@PathVariable int petId, @RequestBody Pet pet) {
        System.out.println(pet);
    }

    @DeleteMapping("/{petId}")
    public void delete(@PathVariable int petId) {
    }
}
