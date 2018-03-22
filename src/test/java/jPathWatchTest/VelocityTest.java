package jPathWatchTest;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VelocityTest {

	public static void main(String[] args) {
		VelocityEngine ve = new VelocityEngine();
		Template t = ve.getTemplate("conf/Velocity.vm");
		 VelocityContext ctx = new VelocityContext();
		 ctx.put("name", "Rcode3QpiDch11-8f@3456%\\$#*&%(^#@)e1qcr\n");	
		 ctx.put("date", (new Date()).toString());
		 List temp = new ArrayList();
		 temp.add("1");
		 temp.add("2");
		 ctx.put("list", temp);
		 StringWriter sw = new StringWriter();
		 t.merge(ctx, sw);
		 System.out.println(sw.toString());
	}

}
