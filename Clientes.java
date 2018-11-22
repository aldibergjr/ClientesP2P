import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
public class Clientes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		ClienteSafado josu = new ClienteSafado(777);
		Thread a = new ReceiveMassage(josu.porta());
		a.start();
		ClienteSafado brunin = new ClienteSafado(6969);
		Thread b = new ReceiveMassage(brunin.porta());
		b.start();

		System.out.println("portas disponiveis são as dos clientes instanciados, 777 e 6969. escreva porta de destino e mensagem em seguida.");
		Thread test;
		while(in.hasNext()) {
			int portas = in.nextInt();
			in.nextLine();
			String msg = in.nextLine();
			test = new SendMassage(msg,portas);
			test.start();
		}
		
		
		/*int port = 7777; //porta do servidor
		String address = "localhost";
		try {
			Socket socket = new Socket (address,port);
			while(true) {
				DataOutputStream saida = new DataOutputStream(socket.getOutputStream()); // Fluxo de saida
				saida.writeUTF("raffamoreiramano skrskr \n");
			}
			
		}catch(ConnectException e){
			System.out.println("Deu ruim no destino");
		}catch(Exception e){
			e.printStackTrace();
		}
	*/	
	}
}

class ClienteSafado{
	private int port;
	public ClienteSafado(int port) {
		this.port = port;
	}
	public int porta() {
		return this.port;
	}
}

class SendMassage extends Thread{
	private String address = "localhost";
	private String msg;
	private int port;
	public SendMassage(String msg, int port){
		this.msg = msg;
		this.port = port;
	}
	
	public void run() {
		try {
			Socket socket = new Socket(address,port);
			DataOutputStream saida = new DataOutputStream(socket.getOutputStream()); // Fluxo de saida
			saida.writeUTF(msg+ '\n');
			
		}catch(ConnectException e){
			System.out.println("Deu ruim no destino");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

class ReceiveMassage extends Thread{
	private int port;
	public ReceiveMassage (int port) {
		this.port = port;
	}
	public void run()
	{
		try {
			ServerSocket tmpsocket = new ServerSocket(port);
			//socket.setSoTimeout(10000);
			//Thread.sleep(10000);
				while(true)
				{
					Socket socket = tmpsocket.accept();
					long elapsed = System.currentTimeMillis();
					DataInputStream entrada= new DataInputStream(socket.getInputStream());
					entrada= new DataInputStream(socket.getInputStream());
					String s = entrada.readUTF();
					System.out.println("EU sou o brother da porta "+ port + " e foi confirmado, eu confirmo: " + s);
					//socket.close();
				}
			}catch(BindException e){
				System.out.print("Endereço em uso");
			}catch(Exception e){
				System.out.println("Deu ruim" + e);
			}
	}
}
