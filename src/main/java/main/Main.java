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
//		resume = "Seg�n Robert Kiyosaki...";
//		text = "Seg� Robert Kiyosaki, el cuadrante del flujo de dinero explica los 4 caminos posibles para generar ingresos. Tenemos la posibilidad de ser Empleados (Employee), Especialistas (Specialists), Emprendedores (Business owner) e Inversores (Investor). Contrario al saber popular, las rutas de empleado y especialista son las que tienen m�s impuestos, y por ello las m�s dif�ciles para alcanzar la riqueza. Ser un emprendedor (M�s de 500 empleados) y ser un inversor tienen menos impuestos y m�s potencial de ganancia, pero requieren tomar m�s riesgos o conocer las personas indicadas.";
//		author = "Federico Dirazar";
//		
//		posts.insertPost(title, resume, text, tags, relatedLinks, author);
		
		//-----Page-----
//		
//		title = "�Por qu� el dinero no trae la felicidad?";
//		
//		text = "Tanto el tener poco como tener mucho dinero generan dolores de cabeza. Uno puede pensar �Qu� problema tiene una persona con exceso de dinero? La respuesta es una palabra que todos los latinoamericanos conocemos bien: \\\"Inflaci�n\\\". La inflaci�n es un fen�meno monetario, cuyo origen var�a seg�n el autor. B�sicamente la inflaci�n ocurre porque los bancos centrales de distintos pa�ses pueden imprimir dinero sin un respaldo como el oro; as� crece la oferta de dinero y la demanda siempre es la misma, por lo tanto los precios suben. Ve�moslo con un ejemplo: Si yo vendo un kilo de manzanas a 100 pesos y el banco central imprime dinero, al cabo de los a�os, el precio del kilo de manzanas aumentar� a 200 pesos, porque hay m�s pesos en circulaci�n, por lo tanto cada peso vale menos. Es decir, el dinero que tanto nos cuesta conseguir, pierde valor con el paso del tiempo.\\nPor su parte, el no tener dinero es el problema m�s obvio y creer�a que el m�s com�n tambi�n. En este caso, debemos organizar nuestros h�bitos de consumo para hacer rendir el dinero y progresar econ�micamente. Por ejemplo, aplicando el concepto de p�guese primero, es decir, cuando llegue su sueldo, separe un 15% o m�s y depos�telo en su fondo de emergencia. En pr�ximos post hablaremos de un plan detallado para lograr sus objetivos.";
//		
//		author = "Federico Dirazar";
//		
//		pages.insertPage(title, text, author);
		
	}
	
	
}
