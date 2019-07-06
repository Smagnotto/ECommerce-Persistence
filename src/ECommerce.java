import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.fiap")
public class ECommerce {

	public static void main(String[] args) {
		SpringApplication.run(ECommerce.class, args);
	}
}
