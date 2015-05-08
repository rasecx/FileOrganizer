package fileorganizer;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	private final static String TAG = "HardwareAddress";
	private final static String REQ = "select vendor from oui where mac=?";
	// 0x1 is HW Type: Ethernet (10Mb) [JBP]
	// 0x2 is ARP Flag: completed entry (ha valid)
	private final static String MAC_RE = "^%s\\s+0x1\\s+0x2\\s+([:0-9a-fA-F]+)\\s+\\*\\s+\\w+$";
	private static String[] arrayUnidades = { "B", "KB", "MB", "GB", "TB" };
	private final static int BUF = 8 * 1024;
	static Boolean ascendente = false;

	public Utils() {
		// TODO Auto-generated constructor stub
	}

	public static String removeExtensao(String nomeArquivo) {
		String ret = "";
		String ext = "";
		if (nomeArquivo.lastIndexOf(".") > 0)
			ext = nomeArquivo.substring(nomeArquivo.lastIndexOf("."),
					nomeArquivo.length());

		if (ext.length() == 4) {
			ret = nomeArquivo.substring(0, nomeArquivo.length() - ext.length());
		} else {
			ret = nomeArquivo;
		}
		return ret;
	}


	public static String converterBytes(long valor) {
		int potencia = 0;
		int proxima = 0;
		boolean cond1;
		boolean cond2;
		if (valor > 0) {
			NumberFormat ftTamamho = NumberFormat.getIntegerInstance();
			Double tamanho = new Double(0.00);
			String strTamanhoFormatado;
			proxima = potencia + 1;
			cond1 = (Math.pow(2, potencia * 10) <= valor);
			cond2 = (valor < Math.pow(2, proxima * 10));
			potencia++;

			while (!(cond1 && cond2)) {
				proxima = potencia + 1;
				cond1 = (Math.pow(2, potencia * 10) <= valor);
				cond2 = (valor < Math.pow(2, proxima * 10));
				potencia++;
			}
			potencia--;
			tamanho = (valor / Math.pow(2, potencia * 10));
			ftTamamho.setMaximumFractionDigits(2);
			strTamanhoFormatado = ftTamamho.format(tamanho);
			return strTamanhoFormatado + "" + arrayUnidades[potencia];
		} else {
			return "0 MB";
		}
	}
	
	public static String convertLongToDate(long value){
		SimpleDateFormat df= new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date(value);
		
		return df.format(d);
		
	}
	
	public static void printConsole(File f,boolean raiz){
		if(raiz)
			System.out.println(f.getName()+" "+converterBytes(f.length())+" "
    			+convertLongToDate(f.lastModified())); 
		else
			System.out.println("    "+f.getName()+" - "+converterBytes(f.length())+" - "
	    			+convertLongToDate(f.lastModified()));
	}

}
