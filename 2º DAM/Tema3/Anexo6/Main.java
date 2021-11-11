package Anexo6;

public class Main {

	public static void main(String[] args) {

		BD bd = new BD();
		
		bd.conectar();
		
		bd.columnasMetaData("pedidos", "detalles_pedido");
		
		bd.desconectar();
	}

}