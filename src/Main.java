
class Main {
	
	public static void main (String args[]){
		
		// simula a entrada
		double Xc[] = {0.0,1.0};
		double Yc[] = {0.0,0.0};
		double R[] = {1.0,1.0};
		decide(Xc,Yc,R);
		
		// captura a entrada
		
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
		
		System.out.println("("+ InterX +","+ InterY +")");
		
	}
	
	// resolucao para duas interseccoes
	// levando em conta o desenho e método proposto por Paul Bourke em artigo escrito em 1997 
	// e comentado no relatório que acompanha este trabalho
	// Vale observar que esse metodo esta sendo usado apenas para resolver o problema de duas intersecções.
	// Já que as outras possibilidades foram cobertas em testes anteriores à chamada do método a seguir
	static void TWOintersec(double[] Xc, double[] Yc, double[] R, double distancia){
		
		System.out.println("resolucao para duas interseccoes");
		 
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
		System.out.print("("+ x3_1 +","+ y3_1 +")");
		System.out.println("("+ x3_2 +","+ y3_2 +")");
		
		
	}

}














