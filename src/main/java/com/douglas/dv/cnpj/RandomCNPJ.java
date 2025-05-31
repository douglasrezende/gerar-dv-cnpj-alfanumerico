package com.douglas.dv.cnpj;

import java.util.ArrayList;
import java.util.List;

public class RandomCNPJ {
	
	
	public static void main(String[] args) throws Exception {
		
		List<Long> resultadosMultiplicacao = new ArrayList<>();
		String cnpjSemDV = RandomStringGenerator.generateCustomRandomString();
		String primeiroDV = CNPJ.calcularPrimeiroDV(resultadosMultiplicacao, cnpjSemDV);
		
		String cnpjComPrimeiroDV = cnpjSemDV + primeiroDV;
		String segundoDV = CNPJ.calcularSegundoDV(resultadosMultiplicacao, cnpjComPrimeiroDV);
		
		String cnpjAlfnumerico = cnpjComPrimeiroDV + segundoDV;
		System.out.println("CNPJ gerado: " + CNPJ.formatarCNPJAlfanumerico(cnpjAlfnumerico));
		
	}

}
