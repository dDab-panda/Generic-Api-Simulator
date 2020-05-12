package com.simulator.webapi;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;  
import java.net.URL; 

import com.github.fge.jsonschema.core.exceptions.ProcessingException;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.jsonschema2pojo.DefaultGenerationConfig;  
import org.jsonschema2pojo.GenerationConfig;  
import org.jsonschema2pojo.Jackson2Annotator;  
import org.jsonschema2pojo.SchemaGenerator;  
import org.jsonschema2pojo.SchemaMapper;  
import org.jsonschema2pojo.SchemaStore;  
import org.jsonschema2pojo.SourceType;  
import org.jsonschema2pojo.rules.RuleFactory;  
import com.sun.codemodel.JCodeModel;  

@SpringBootApplication
public class WebApiSimulatorApplication {
	
	public static void main(String[] args) throws IOException, ParseException, ProcessingException {
		
		// loading data from file into JSON object		
		//	JSONParser parser = new JSONParser();
		
		//	FileReader reader = new FileReader("/Mastercard/emp/src/main/resources/EmployeeData.json");
		
		//Object obj = parser.parse(reader);
		
		//	JSONObject empjsonobj = (JSONObject)obj;
		
		// Validating the JSON object
		File schemaFile = new File("src/main/resources/schema.json");
		File jsonFile = new File("src/main/resources/config.json");
		
		if (ValidationUtils.isJsonValid(schemaFile, jsonFile)){
			System.out.println("Valid Configuration!");
			
			String packageName = "com.simulator.pojo";  
			File inputJson = new File("src/main/resources/config.json");  
			File outputPojoDirectory = new File("src/main/java");  
			outputPojoDirectory.mkdirs();  
			try {  
				new WebApiSimulatorApplication().convert2JSON(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));  
			} catch (IOException e) {  
				// TODO Auto-generated catch block  
				System.out.println("Encountered issue while converting to pojo: " + e.getMessage());  
				e.printStackTrace();  
			} 
			SpringApplication.run(WebApiSimulatorApplication.class, args);
		} else {
			System.out.println("Input configuration file is not valid!");
		}
	}
	
	public void convert2JSON(URL inputJson, File outputPojoDirectory, String packageName, String className) throws IOException{  
		JCodeModel codeModel = new JCodeModel();  
		URL source = inputJson;  
		GenerationConfig config = new DefaultGenerationConfig() {  
			@Override  
			public boolean isGenerateBuilders() { // set config option by overriding method  
				return true;  
			}  
			public SourceType getSourceType(){  
				return SourceType.JSON;  
			}  
		};  
		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());  
		mapper.generate(codeModel, className, packageName, source);  
		codeModel.build(outputPojoDirectory);  
	}  
}
