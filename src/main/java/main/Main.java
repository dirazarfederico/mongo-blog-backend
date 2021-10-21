package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import services.MongoPage;
import services.MongoPost;
import web.WebAPI;

public class Main {
	
	public static void main(String[] args) {
		
		String mongo = "mongodb://localhost:27017";
		
		MongoPage pages = new MongoPage(mongo);
		MongoPost posts = new MongoPost(mongo);
		
		WebAPI webApi = new WebAPI(pages, posts);
		
		webApi.start();
		
//		String title, resume, text, author;
		
		//Post
		
//		List<String> tags = new ArrayList<String>(), relatedLinks = new ArrayList<String>();
//		
//		tags.add("inversiones");
//		tags.add("finanzas personales");
//		
//		relatedLinks.add("https://medium.com/the-crunch/what-robert-kiyosakis-cash-flow-quadrant-taught-me-about-business-dc488ffa7e89");
//		relatedLinks.add("https://www.amazon.com/Rich-Dads-CASHFLOW-Quadrant-Financial/dp/1612680054");
//		
//		title = "El cuadrante del flujo de dinero";
//		resume = "Según Robert Kiyosaki...";
//		text = "Segú Robert Kiyosaki, el cuadrante del flujo de dinero explica los 4 caminos posibles para generar ingresos. Tenemos la posibilidad de ser Empleados (Employee), Especialistas (Specialists), Emprendedores (Business owner) e Inversores (Investor). Contrario al saber popular, las rutas de empleado y especialista son las que tienen más impuestos, y por ello las más difíciles para alcanzar la riqueza. Ser un emprendedor (Más de 500 empleados) y ser un inversor tienen menos impuestos y más potencial de ganancia, pero requieren tomar más riesgos o conocer las personas indicadas.";
//		author = "Federico Dirazar";
//		
//		posts.insertPost(title, resume, text, tags, relatedLinks, author);
		
		//-----Page-----
//		
//		title = "¿Por qué el dinero no trae la felicidad?";
//		
//		text = "Tanto el tener poco como tener mucho dinero generan dolores de cabeza. Uno puede pensar ¿Qué problema tiene una persona con exceso de dinero? La respuesta es una palabra que todos los latinoamericanos conocemos bien: \\\"Inflación\\\". La inflación es un fenómeno monetario, cuyo origen varía según el autor. Básicamente la inflación ocurre porque los bancos centrales de distintos países pueden imprimir dinero sin un respaldo como el oro; así crece la oferta de dinero y la demanda siempre es la misma, por lo tanto los precios suben. Veámoslo con un ejemplo: Si yo vendo un kilo de manzanas a 100 pesos y el banco central imprime dinero, al cabo de los años, el precio del kilo de manzanas aumentará a 200 pesos, porque hay más pesos en circulación, por lo tanto cada peso vale menos. Es decir, el dinero que tanto nos cuesta conseguir, pierde valor con el paso del tiempo.\\nPor su parte, el no tener dinero es el problema más obvio y creería que el más común también. En este caso, debemos organizar nuestros hábitos de consumo para hacer rendir el dinero y progresar económicamente. Por ejemplo, aplicando el concepto de páguese primero, es decir, cuando llegue su sueldo, separe un 15% o más y deposítelo en su fondo de emergencia. En próximos post hablaremos de un plan detallado para lograr sus objetivos.";
//		
//		author = "Federico Dirazar";
//		
//		pages.insertPage(title, text, author);
		
	}
	
	
}
