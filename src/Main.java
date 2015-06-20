import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String args[]){
		
		double Xc[] = new double[2];
		double Yc[] = new double[2];
		double R[] = new double[2];

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// captura a entrada
		try {
			while(in.ready()){
				
				String[] numeros1 = in.readLine().split(" ");
				
				Xc[0] = Double.parseDouble(numeros1[0]);
				Yc[0] = Double.parseDouble(numeros1[1]);
				R[0] = Double.parseDouble(numeros1[2]);
				
				
				String[] numeros2 = in.readLine().split(" ");
				
				Xc[1] = Double.parseDouble(numeros2[0]);
				Yc[1] = Double.parseDouble(numeros2[1]);
				R[1] = Double.parseDouble(numeros2[2]);
				
				decide(Xc,Yc,R);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// decide qual resolucao deve ser aplicada
	static void decide(double[] Xc, double[] Yc, double[] R){
		
		// calcula a distancia euclidiana entre os dois pontos
		double distancia = Math.sqrt((Xc[1] - Xc[0]) * (Xc[1] - Xc[0]) + (Yc[1] - Yc[0]) * (Yc[1] - Yc[0]));
		
		// se forem a mesma circunferência
		if(distancia == 0 && R[0] == R[1]){
			System.out.println("THE CIRCLES ARE THE SAME");
			return;
		}
		
		// se uma circunferencia esta completamente dentro da outra
		if(distancia < Math.abs(R[0]-R[1])){
			Nintersec();
			return;
		}
		
		// se as circunferências estao distantes demais uma da outra para se tocarem
		if(distancia > (R[0]+R[1])){
			Nintersec();
			return;
		}
		
		// se as circunferências se tocam em apenas um ponto
		if(distancia == (R[0]+R[1])){
			ONEintersec(Xc, Yc, R, distancia);
			return;
		}
		
		// se as circunferências se tocam em dois pontos
		if(distancia < (R[0]+R[1])){
			TWOintersec(Xc, Yc, R, distancia);
			return;
		}
		
		return;
		
	}
	
	// resolucao para nenhuma interseccao
	static void Nintersec(){
		System.out.println("NO INTERSECTION");
	}
	
	// resolucao para uma interseccao
	static void ONEintersec(double[] Xc, double[] Yc, double[] R, double distancia){
		
		double Blinha = Math.abs(Xc[1]-Xc[0]);
		double Alinha = Math.abs(Yc[1]-Yc[0]);
		
		// por semelhança de triangulos, temos que
		double A = (R[0]*Alinha)/(R[0]+R[1]);
		double B = (R[0]*Blinha)/(R[0]+R[1]);
		
		// o metodo aplicado descobre as distancias X e Y do ponto de interseccao a partir do centro da primeira circunferencia
		// agora, eh necessario descobrir os sinais dessas distancias no plano cartesiano
		double sinalX;
		double sinalY;
		
		if(Xc[0] < Xc[1])
			sinalX = 1;
		else
			sinalX = -1;
		
		if(Yc[0] < Yc[1])
			sinalY = 1;
		else
			sinalY = -1;
		
		// encontra as coordenadas do ponto de interseccao
		double InterX = Xc[0] + sinalX*B;
		double InterY = Yc[0] + sinalY*A;
		
		System.out.format("(%.3f,%.3f)\n", InterX,InterY);
		
	}
	
	// resolucao para duas interseccoes
	// levando em conta o desenho e método proposto por Paul Bourke em artigo escrito em 1997 
	// e comentado no relatório que acompanha este trabalho
	// Vale observar que esse metodo esta sendo usado apenas para resolver o problema de duas intersecções.
	// Já que as outras possibilidades foram cobertas em testes anteriores à chamada do método a seguir
	static void TWOintersec(double[] Xc, double[] Yc, double[] R, double distancia){
		 
		double a = ((R[0]*R[0]) - (R[1]*R[1]) + (distancia*distancia))/(2*distancia); 
		double h = Math.sqrt((R[0]*R[0]) - (a*a));
		
		double x2 = Xc[0] + (a*(Xc[1] - Xc[0]))/distancia;
		double y2 = Yc[0] + (a*(Yc[1] - Yc[0]))/distancia;
		
		// encontro as coordenadas dos dois pontos de instersecção (P3)
		double escapeX = -1*(h*(Yc[1] - Yc[0])/distancia);
		double escapeY = h*(Xc[1] - Xc[0])/distancia;
		
		double x3_1 = x2 + escapeX;
		double x3_2 = x2 - escapeX;
		
		double y3_1 = y2 + escapeY;
		double y3_2 = y2 - escapeY;

		if(x3_1 > x3_2 || (x3_1 == x3_2 && y3_1 > y3_2)){
			System.out.format("(%.3f,%.3f)", x3_2,y3_2);
			System.out.format("(%.3f,%.3f)\n", x3_1,y3_1);
		}
		else{
			System.out.format("(%.3f,%.3f)", x3_1,y3_1);
			System.out.format("(%.3f,%.3f)\n", x3_2,y3_2);
		}
		
	}


}