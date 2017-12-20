package ff.main.log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client{

 public static void TTT(String s){
	 try{
	        Socket socket=new Socket("127.0.0.1",4700);
	        //向本机的4700端口发出客户请求
	        //由系统标准输入设备构造BufferedReader对象
	        PrintWriter os=new PrintWriter(socket.getOutputStream());

	        //由Socket对象得到输出流，并构造PrintWriter对象
	        os.println(s);
            os.flush();
            socket.shutdownOutput();
	        BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        String content = null;
	        while((content = is.readLine()) != null) {
	        	System.out.println("我是客户端，服务器说："+content);
	        }
	        os.close(); //关闭Socket输出流
	        is.close(); //关闭Socket输入流
	        socket.close(); //关闭Socket

	    }catch(Exception e) {

	        System.out.println("Error"+e); //出错，则打印出错信息

	    }
 }
public static void main(String args[]) {
}

}