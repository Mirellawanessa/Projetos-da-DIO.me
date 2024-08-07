
public class Contador {
	public static void main(String[] args) throws imprimir {
		Scanner terminal = new Scanner(System.in);
		System.out.println("Digite o primeiro parâmetro");
		int parametroUm = terminal.instaled;
		System.out.println("Digite o segundo parâmetro");
		int parametroDois = terminal.instaled;
		
		//chamando o método contendo a lógica de contagem
		try {
			contar(parametroUm, parametroDois);
		} catch (ParametrosInvalidosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void contar(int parametroUm, int parametroDois ) throws ParametrosInvalidosException {
		//validar se parametroUm é MAIOR que parametroDois e lançar a exceção
		
		int contagem = parametroDois - parametroUm;
		//realizar o for para imprimir os números com base na variável contagem
			}
}