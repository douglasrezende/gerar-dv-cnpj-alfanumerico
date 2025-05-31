package com.douglas.dv.cnpj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.text.MaskFormatter;
import com.douglas.dv.cnpj.properties.PropertiesCNPJ;

public class CNPJ {

	public static final int TAMANHO_CNPJ_SEM_DV = 12; 

	public static final int TAMANHO_CNPJ_COM_PRIMEIRO_DV = 13;

	public static void main(String[] args) throws Exception {

		//12ABC34501DE
		//Resultado final: 12.ABC.345/01DE-35

		List<Long> resultadosMultiplicacao = new ArrayList<>();
		String cnpj = " ";
		Scanner scanner = new Scanner(System.in);

		while (cnpj.length() < 12 
				|| cnpj.length() > 12) {
			
			System.out.print("Informe os 12 primeiros valores do CNPJ: ");
			cnpj = scanner.next();
			
		}
		
		cnpj = cnpj.replaceAll("[^a-zA-Z0-9 ]", "");

		String primeiroDV = calcularPrimeiroDV(resultadosMultiplicacao, cnpj);

		String cnpjComPrimeiroDV = cnpj + primeiroDV;
		String segundoDV = calcularSegundoDV(resultadosMultiplicacao, cnpjComPrimeiroDV);

		System.out.println(cnpj.substring(0,1)); // indice 1
		System.out.println(cnpj.substring(1,2)); // indice 2
		System.out.println(cnpj.substring(2,3)); // indice 3

		scanner.close();

		System.out.println("CNPJ com DV: " + formatarCNPJAlfanumerico(cnpjComPrimeiroDV + segundoDV));

	}

	public static String calcularPrimeiroDV(List<Long> resultadosMultiplicacao, String cnpj) throws Exception {

		int primeiroDV = 0;
		Long valor = 0L;
		Long peso = 0L;
		String pesoStr = null;
		String valorStr =null;
		String caracterCNPJ = null;
		int posInicial = 0;
		int posFinal = 1;
		int chavePeso = 0;

		for (int i = 0 ; i <= 12; i++) {

			if (posFinal > TAMANHO_CNPJ_SEM_DV ) {

				break;

			}

			caracterCNPJ = cnpj.substring(posInicial, posFinal);

			valorStr = PropertiesCNPJ.getProperty(caracterCNPJ, "tabela.properties");
			valor = valorStr != null ? Long.valueOf(valorStr) : null;

			pesoStr = PropertiesCNPJ.getProperty(String.valueOf(chavePeso), "peso_primeiro_dv.properties");
			peso = pesoStr != null ? Long.valueOf(pesoStr) : null;

			if (valor != null
					&& peso != null) {


				resultadosMultiplicacao.add(valor * peso);


			}


			posInicial +=1;
			posFinal +=1;
			chavePeso +=1;

		}

		Long soma = 0L;
		for (Long resultado : resultadosMultiplicacao) {
			soma += Long.valueOf(resultado);
		}

		int restoDivisao = soma.intValue() % 11;
		if (restoDivisao == 1 
				|| restoDivisao == 0) {

			primeiroDV = 0; 

		} else {

			primeiroDV = 11 - restoDivisao;
		}

		return String.valueOf(primeiroDV);
	}

	public static String calcularSegundoDV(List<Long> resultadosMultiplicacao, String cnpj) throws Exception {

		int segundoDV = 0;
		Long valor = 0L;
		Long peso = 0L;
		String pesoStr = null;
		String valorStr =null;
		String caracterCNPJ = null;
		int posInicial = 0;
		int posFinal = 1;
		int chavePeso = 0;
		resultadosMultiplicacao = new ArrayList<>();

		for (int i = 0 ; i <= 13; i++) {

			if (posFinal > TAMANHO_CNPJ_COM_PRIMEIRO_DV ) {

				break;

			}

			caracterCNPJ = cnpj.substring(posInicial, posFinal);

			valorStr = PropertiesCNPJ.getProperty(caracterCNPJ, "tabela.properties");
			valor = valorStr != null ? Long.valueOf(valorStr) : null;

			pesoStr = PropertiesCNPJ.getProperty(String.valueOf(chavePeso), "peso_segundo_dv.properties");
			peso = pesoStr != null ? Long.valueOf(pesoStr) : null;

			if (valor != null
					&& peso != null) {


				resultadosMultiplicacao.add(valor * peso);


			}


			posInicial +=1;
			posFinal +=1;
			chavePeso +=1;

		}

		Long soma = 0L;
		for (Long resultado : resultadosMultiplicacao) {
			soma += Long.valueOf(resultado);
		}

		int restoDivisao = soma.intValue() % 11;
		if (restoDivisao == 1 
				|| restoDivisao == 0) {

			segundoDV = 0; 

		} else {

			segundoDV = 11 - restoDivisao;
		}

		return String.valueOf(segundoDV);
	}

	public static String formatarCnpj(String cnpj) throws Exception {

		try {

			MaskFormatter mask = new MaskFormatter("###.###.###/####-##");
			mask.setValueContainsLiteralCharacters(false);
			System.out.println("CNPJ : " + mask.valueToString(cnpj));

			return mask.valueToString(cnpj);

		} catch (Exception ex) {
			throw new Exception("Falha ao formatar CNPJ: " + ex);
		}

	}

	public static String formatarCNPJAlfanumerico(String cnpj) {
		// Remove quaisquer caracteres que não sejam letras ou números
		cnpj = cnpj.replaceAll("[^a-zA-Z0-9]", "");

		// Aplica a máscara: 2 . 3 . 3 / 4 - 2
		return cnpj.replaceFirst("(.{2})(.{3})(.{3})(.{4})(.{2})", "$1.$2.$3/$4-$5");
	}
}
