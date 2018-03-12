package util.json;

import java.io.BufferedOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JacksonUtil {

	public static String javaToJson() throws Exception {
		User user = new User();
		user.setEmail("zhangsan@163.com");
		user.setAge(20);

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		user.setBirthday(dateformat.parse("1996-10-01"));
		System.out.println(dateformat.parse("1996-10-01").getTime());

		/**
		 * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
		 * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
		 * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
		 * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
		 * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
		 * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
		 */
		ObjectMapper mapper = new ObjectMapper();
		
		//如果属性没有值，那么Json是会处理的，int类型为0，String类型为null，数组为[]，设置这个特性可以忽略空值属性 
//		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY); 
		
		//忽视对象不包含的字段
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		//让Json可以缩进，可读性更好，一般用在测试和开发阶段。
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true); 
		
		//to allow serialization of "empty" POJOs (no properties to serialize)
		//without this setting, an exception is thrown in those cases
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		
		// to write java.util.Date, Calendar as number (timestamp):
		// defult : 将date转化成long 
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		// to allow coercion of JSON empty String ("") to null Object value:
		// 字符串赋值为""时，作null来处理
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		
		//让map的key按自然顺序排列
		mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
		
		//日期输出格式 
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	    mapper.setDateFormat(outputFormat); 
	    
	    // 默认情况下java类属性名就是输出的json字段名，但是可以采用注解的方式修改,如果这个类不能被修改(第三方类里的)，则需要重写下面的方法
	    mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {  
	    	
			private static final long serialVersionUID = 1L;

			// 如果属性是public修饰的
            @Override  
            public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {  
                if (field.getFullName().equals("Artist#name"))  
                    return "Artist-Name";  
                return super.nameForField(config, field, defaultName);  
            }  
  
            // 如果get方法是public修饰的
            @Override  
            public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {  
                if (method.getAnnotated().getDeclaringClass().equals(User.class) && defaultName.equals("email"))  
                    return "myemail";  
                return super.nameForGetterMethod(config, method, defaultName);  
            }  
        });  
		
	    
	    
		// User类 转JSON
		// 输出结果：{"name":"zhangsan","age":20,"birthday":844099200000,"email":"zhangsan@163.com"}
		String json = mapper.writeValueAsString(user);
		System.out.println(json);

		// Java集合 转JSON
		// 输出结果：[{"name":"zhangsan","age":20,"birthday":844099200000,"email":"zhangsan@163.com"}]
		List<User> users = new ArrayList<User>();
		users.add(user);
		String jsonlist = mapper.writeValueAsString(users);
		System.out.println(jsonlist);

		return null;
	}
	
	
	// List 和   Map
	public static void jsonToJava() {
//		String json = "{\"name\":\"zhangsan\",\"age\":20,\"birthday\":844099200000,\"email\":\"zhangsan@163.com\"}";
//       String json = "{\"age\":20,\"birthday\":\"1996-09-30 16-00-00\",\"myname\":\"zhangsan\"}";
       String json = "{\"age\":20,\"birthday\":\"1996-09-30 16-00-00\",\"email\":\"zhangsan@163.com\",\"myname\":\"zhangsan\"}";
		/**
         * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
         */
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
		try {
			user = mapper.readValue(json, User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        System.out.println(user);
        
        String json1 = "[{\"name\":\"zhangsan\",\"age\":20,\"birthday\":844099200000,\"email\":\"zhangsan@163.com\"}]";
        List<User> beanList = null;
		try {
//			Map<String, ResultValue> results = mapper.readValue(jsonSource,
//					   new TypeReference<Map<String, ResultValue>>() { } );

			beanList = mapper.readValue(json1, new TypeReference<List<User>>() {});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        System.out.println(beanList);
        
        
	}
	
	
	public static void Streaming() {
		// 生成
        JsonFactory factory = new JsonFactory();  
        JsonGenerator generator = null;
        try {
        	generator = factory.createGenerator(new BufferedOutputStream(System.out));  
        	 generator.writeStartObject();//{  
             generator.writeFieldName("title");  
             generator.writeString("Free Music Archive - Albums");  
             generator.writeFieldName("dataset");  
             generator.writeStartArray();//[  
             generator.writeStartObject();//{  
             generator.writeStringField("album_title", "A.B.A.Y.A.M");  
             generator.writeEndObject();//}  
             generator.writeEndArray();//]  
             generator.writeEndObject();//}  
         //输出{"title":"Free Music Archive - Albums","dataset":[{"album_title":"A.B.A.Y.A.M"}]}  
       
             generator.close();  
		} catch (Exception e) {
		}
	}
	
	// json生成
	public static void treeJavaToJson() {
		try {
			//创建一个提供所有节点的JsonNodeFactory,false表示不创建DecimalNode  
			JsonNodeFactory factory = new JsonNodeFactory(false);  
			  
	        JsonFactory jsonFactory = new JsonFactory();  
	        JsonGenerator generator = jsonFactory.createGenerator(System.out);  
	        ObjectMapper mapper = new ObjectMapper();  
	  
	        //创建节点和数据,一个ObjectNode代表一个节点对象  
	        ObjectNode node1 = factory.objectNode();  
	        ObjectNode node2 = factory.objectNode();  
	        node1.put("A", "a");  
	        node1.put("B", "b");  
	        node2.set("C", node1);  
	  
	        // 根节点  
	        ObjectNode root = factory.objectNode();  
	        root.put("root", "root");  
	        root.set("children", node2);  
	        
	        mapper.writeTree(generator, root);
		} catch (Exception e) {
		}
		
	}
	
	public static void treeJsonToJava() {
		try {
			ObjectMapper mapper = new ObjectMapper();  
	        // 读取json，将整个json作为根节点  
	        JsonNode node = mapper.readTree("{\"root\":\"root\",\"children\":{\"C\":{\"A\":\"a\",\"B\":\"b\"}}}");  
	        // 看看根节点的类型  
	        System.out.println("node JsonNodeType:" + node.getNodeType());//OBJECT  
	        // 是不是一个ContainerNode（array and object nodes）  
	        System.out.println("node is containerNode ? " + node.isContainerNode());//true  
	        // 得到所有node节点的直接子节点名称  
	        Iterator<String> fieldNames = node.fieldNames();  
	        while (fieldNames.hasNext()) {  
	            String fieldName = fieldNames.next();  
	            System.out.println(fieldName + " ");//root children  
	        }  
	        System.out.println(node.get("root").isContainerNode());//false 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		try {
			javaToJson();
//			jsonToJava();
//			Streaming();
			
//			treeJavaToJson();
//			treeJsonToJava();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
