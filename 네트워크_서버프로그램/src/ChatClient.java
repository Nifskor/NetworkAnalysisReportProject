import java.io.*;
import java.net.Socket;

public class ChatClient {
Socket socket;

public ChatClient(String ip, int port){
    try {
        // 소켓 객체를 생성 ip와 포트를 입력받음
        socket = new Socket(ip,port);
        // 디스플레이 출력하는 함수를 호출함
        printInfo();
    }
    catch (IOException e) {
        // 입출력에 대한 예외 발생시 처리하는 구문
        e.printStackTrace();
    }
}
public void communicate()  {
    // 서버와 통신을 하기위한 커뮤니케이트
    try {

        InputStream is = socket.getInputStream();
        // 소켓을통해 들어오는 문자단위로 수신
        DataInputStream dis = new DataInputStream(is);
        //데이터의 input stream 수신 및 dis 객체 생성
        OutputStream out = socket.getOutputStream();
        // stream객체를 이용하여 메시지를 주고받는다 outputstream객체를 구성하여 전송함
        DataOutputStream dout = new DataOutputStream((out));
        // 출력할 문자구문을 문자단위로 stream 하고 dout객체를 생성한다
        System.out.println("서버로부터 전달 받은 메시지 입니다.");

        System.out.println(dis.readUTF());
        //서버가 전송한 문자를 수신한다

        dis.close();
        // datainputStream을 소켓을 닫는다
        dout.close();
        // 사용이 완료된 소켓은 close() 메서드를 이용하여 종료 처리한다.

    } catch (IOException e) {
        e.printStackTrace();
    }
}
public void close(){
    try{
        socket.close();
     // 소켓도 스트림처럼 사용후 닫아준다.
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void printInfo(){
    // 포트와 ip를 출력해주며 현재 클라이언트 상태를 알려준다.
    System.out.println(">>서버에 접속에 성공하였습니다.");
    System.out.println("클라이언트 포트번호" + socket.getLocalPort());
    System.out.println("서버주소 " + socket.getInetAddress());
    System.out.println("서버 포트번호:" + socket.getPort() + "\n");
}
    public static void main(String[] args){
        ChatClient client = new ChatClient("127.0.0.1",7070);
        // 클래스의 객체를 client 생성하여 생성함과 동시에 매개변수가있는 기본생성자를 호출하여 ip port 값을 전송하여준다.
        client.communicate();
        //client 객체에서 communicate 메소드를 호출한다.
    }
}
