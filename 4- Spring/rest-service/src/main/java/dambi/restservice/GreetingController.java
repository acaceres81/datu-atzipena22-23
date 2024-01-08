package dambi.restservice;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Hay que poner esta anotacion para que funcione
@RestController
public class GreetingController {

    //estos son los parametros del objeto que se van a mandar por defecto
    private String template = "Hello, %s!";
    //cada vez que recargues el ID aumenta (el primer import)
	private final AtomicLong counter = new AtomicLong();

    // lo que hay que poner para que salga el mensaje cuando ponemos 
    @GetMapping("/greeting")
    //Al poner los @RequestParam decimos lo que pasa si ponemos valores en el navegador. Si ponemos ?name=Jon pondrá Hello, Jon! 
    //Si ponemoes ?hizkuntza=eus pondrá kaixo, Mundua! si ponemos ?hizkuntza=eus&name=Jon, pondra Kaixo, Jon!
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name, @RequestParam(value = "hizkuntza", defaultValue = "Hello") String hizkuntza) {
        if(hizkuntza.equals("eus")){
            template = "Kaixo, %s!";
            if(name.equals("World")){
                name = "Mundua";
            }            
        }else if(hizkuntza.equals("es")){
            template = "Hola, %s!";
            if(name.equals("World")){
                name = "Mundo";
            }
        }
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
}
