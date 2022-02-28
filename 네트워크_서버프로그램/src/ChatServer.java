import java.io.*;
import java.net.*;

public class ChatServer {
    int port = 7070;
    // 포트를 지정한다
    ServerSocket server;
    // 서버 소켓 객체를 생성한다.
    Socket socket;
    public ChatServer(int port){
        this.port = port;
        // 넘겨받은 포트를 현재 클래스의 인스턴스에 값을 넘겨준다
        System.out.println(">>> 서버를 시작합니다");
        try {
            server = new  ServerSocket(port);
            // 서버 소켓을 생성한다

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void communicate(){

        System.out.println(">>클라이언트가 접속하기를 기다리고있습니다.");
        String inputa="";
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        //  입력받을 버퍼드 reader를 선언한다
        try {
            // 클라이언트가 서버로 접속하기를 응답을 대기한다
            socket = server.accept();
            // 서버가 클라이언트 응답을 수락한다.
            printInfo();
            //  출력을 담당하는 메소드를 호출한다
                System.out.println("클라이언트에게 전달할 메시지를 입력하세요 .");
                inputa = read.readLine();
                // 사용자의 정보를 입력받는다
                OutputStream out = socket.getOutputStream();
                // 접속한 socket의 getoutputstream) 메서드를 이용해 구현한다.
                DataOutputStream dos = new DataOutputStream(out);
                // dos 객체를 생성하여 문자열을 출력한다
                dos.writeUTF(inputa);
                // 입력받을 문자열을 클라이언트로 전송한다
                System.out.println("메시지를 보냈습니다");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public void close(){
        try{
            socket.close();
            // 소켓을 닫아준다
            server.close();
            // 서버 소켓을 닫아준다
        } catch (IOException e) {
            e.printStackTrace();
        }
}
public void printInfo(){
        // 출력을 담당하는 메소드이다.
        System.out.println(">>클라이언트가 성공적으로 접속에 성공하였습니다.");
        System.out.println("서버 포트번호 : "+ socket.getLocalPort());
        System.out.println("클라이언트 주소 :" + socket.getInetAddress());
        System.out.println("클라이언트 포트번호:"+socket.getPort());
        System.out.println("\n");
        System.out.println("클라이언트에 전달할 메시지를 쓰고 엔터를 누르세요."+'\n');
}

    public static void main(String[] args){
        int port =7070;
        // 포트를 선언한다

            ChatServer myserver = new ChatServer(port);
            // 클래스의 객체를 생성한다 기본 메소드로 port를 넘겨준다
            myserver.communicate();
            // 객체에서 커뮤니케이션메소드를 호출한다
            myserver.close();;
            //통신을 종료할수있도록 객체를 호출하여 close()메소드를 이용 닫아준다 .

    }
}
