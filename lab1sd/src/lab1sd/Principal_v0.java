
package lab1sd;

/**
 *
 * @author Usuário
 */
import java.io.*;
import java.nio.file.*;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Random;

public class Principal_v0 {

	public final static Path path = Paths			
			.get("src\\fortune-br.txt");
	private int NUM_FORTUNES = 0;

	public class FileReader {

		public int countFortunes() throws FileNotFoundException {

			int lineCount = 0;

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();

				}// fim while

				System.out.println(lineCount);
			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
			return lineCount;
		}

		public void parser(HashMap<Integer, String> hm)
				throws FileNotFoundException {

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is,"UTF8"))) {//se windows usar ISO-8859-1

				int lineCount = 0;

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();
					StringBuffer fortune = new StringBuffer();
					while (!(line == null) && !line.equals("%")) {
						fortune.append(line + "\n");
						line = br.readLine();
						// System.out.print(lineCount + ".");
					}

					hm.put(lineCount, fortune.toString());
//					System.out.println(fortune.toString());

//					System.out.println(lineCount);
				}// fim while

			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
		}

		public void read(HashMap<Integer, String> hm)
				throws FileNotFoundException {
                    
                    //SEU CODIGO AQUI
                    Random gerador = new Random();
                    int valorAleatorio = gerador.nextInt(NUM_FORTUNES);
                    System.out.println(valorAleatorio);
//                    System.out.println("Aqui começa o teste");
                    System.out.println(hm.get(valorAleatorio));
                    
		}

		public void write(HashMap<Integer, String> hm)
				throws FileNotFoundException {

                    //SEU CODIGO AQUI
                    String fortuna = "\nFortuna Inserida!\n%";
                    byte data[] = fortuna.getBytes();
                    try (OutputStream out = new BufferedOutputStream(
                    Files.newOutputStream(path, CREATE, APPEND))){
                        out.write(data,0,data.length);
                    }catch (IOException x){
                        System.err.println(x);
                    }
		}
	}

	public void iniciar() {

		FileReader fr = new FileReader();
		try {
			NUM_FORTUNES = fr.countFortunes();
			HashMap hm = new HashMap<Integer, String>();
			fr.parser(hm);
			fr.read(hm);
			fr.write(hm);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Principal_v0().iniciar();
	}

}

