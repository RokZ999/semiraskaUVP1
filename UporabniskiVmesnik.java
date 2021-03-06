import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class UporabniskiVmesnik {
	static ArrayList<Uporabnik> uporabniki =  new ArrayList<Uporabnik>();
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public final static String pot  = "uporabniki.txt";
	public final static String pot2 = "pocitnice.txt";
	public static boolean flag_load = true;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		if(flag_load){
			IORazred_mainVmesnik.preberi(pot, uporabniki);
			TuristicnaAgencija.nalozi();
			flag_load=false;
		}
		

		if( args !=null && args.length > 0 && Boolean.parseBoolean(args[0])==true ) br.readLine(); //neka brezvezna resitev...
				
		char opcija = ' ';
		
		while(true) 
		{
			System.out.printf("%s%n*%36s%13s%n%s%n",new String(new char[50]).replace("\0", "*"),"-->Turisticna agencija<--","*",new String(new char[50]).replace("\0", "*"));
			System.out.println("Opcije programa: ");
			System.out.println("a -> Registriraj novega uporabnika.");
			System.out.println("b -> Izpis uporabnikov.");
			System.out.println("c -> Prijava v vmesnik.");
			System.out.println("y -> Zakljuci/Terminiraj program.");
			System.out.print("Vnesite opcijo programa kot znak (npr. y): ");
			opcija = (char) br.read();
			switch (opcija)
			{
				case 'a': 
					IORazred_mainVmesnik.registriraj(uporabniki,false);
					IORazred_mainVmesnik.shrani(pot, uporabniki);
					br.readLine(); //Beli znak
					break;
				case 'b':
					IORazred_mainVmesnik.izpisUporabnikov(uporabniki);
					br.readLine(); //Beli znak
					break;
				case 'c':
					IORazred_mainVmesnik.prijava(uporabniki);
					br.readLine();//Beli znak
					break;
				case 'y':
					TuristicnaAgencija.shrani();
					IORazred_mainVmesnik.shrani(pot, uporabniki);
					IORazred_mainVmesnik.terminirajProgram();
					break;
				case 'g':
					generatePocitnice();
					generateAdmin_and_normal();
					br.readLine(); //Beli znak
					break;
				default:
					System.out.println( args !=null &&Boolean.parseBoolean(args[0])==true ?  "" : "NAPACNA OPCIJA." + "->" + opcija + "<-");
			}
		}
	}
	
	public static void generateAdmin_and_normal()  throws IOException, ClassNotFoundException{
		if(!IORazred_mainVmesnik.aliObstaja(uporabniki,"admin") ) {
			uporabniki.add(new Uporabnik("admin","admin","admin","admin",true));
			uporabniki.add(new Uporabnik("rok","wow","rokz","rokz",true));
			uporabniki.add(new Uporabnik("nejc","toman","ntoman","ntoman",false));
			
		IORazred_mainVmesnik.shrani(pot, uporabniki);
		}
	}
	
	public static void generatePocitnice() throws ClassNotFoundException, IOException {

	
		
		for(int i=1;i<7;i++)
			TuristicnaAgencija.pocitnice.add(new Pocitnice(i*1, i*2, i*1, i*100, "slo", i*1000, rezervacijeGenerator(), null));


		TuristicnaAgencija.pocitnice.add(new Pocitnice_krizarjanje(1, 7, 9, 7, "ang", 10000, rezervacijeGenerator(), null,false,false));
		TuristicnaAgencija.pocitnice.add(new Pocitnice_kampiranje(3, 9, 2, 4, "ang", 10000, rezervacijeGenerator(), null,true,false));
		TuristicnaAgencija.pocitnice.add(new Pocitnice_smucanje(2, 1, 6, 5, "ang", 10000, rezervacijeGenerator(), null,false,true));
		TuristicnaAgencija.pocitnice.add(new Pocitnice_potovanje(4, 2, 1, 3, "ang", 10000, rezervacijeGenerator(), null,true,false));
		
		TuristicnaAgencija.shrani();

	}
	
	public static ArrayList<Termin> rezervacijeGenerator(){
		ArrayList<Termin> lista = new ArrayList<>();
		Random rnd = new Random();
		
		lista.add(new Termin(rnd.nextInt(10) + 1 , rnd.nextInt(12) + 1, rnd.nextInt(24) + 1, rnd.nextInt(24) + 1));
		lista.add(new Termin(rnd.nextInt(10) + 1 , rnd.nextInt(12) + 1, rnd.nextInt(24) + 1, rnd.nextInt(24) + 1));
		lista.add(new Termin(rnd.nextInt(10) + 1 , rnd.nextInt(12) + 1, rnd.nextInt(24) + 1, rnd.nextInt(24) + 1));

		return lista;
	}
	
}
