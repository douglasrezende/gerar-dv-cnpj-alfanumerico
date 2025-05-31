package com.douglas.dv.cnpj;

import java.security.SecureRandom;

public class RandomStringGenerator {
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	//private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final int LENGTH = 12;
	private static final SecureRandom random = new SecureRandom();

	// Método para gerar uma parte da string com o tamanho especificado
	private static String generatePart(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(index));
		}
		return sb.toString();
	}

	public static String generateCustomRandomString() {
		String part1 = generatePart(8); // Primeiras 8 posições
		String part2 = generatePart(4); // Próximas 4 posições
		return part1 + part2;
	}


	public static String generateRandomString() {
		StringBuilder sb = new StringBuilder(LENGTH);
		for (int i = 0; i < LENGTH; i++) {
			int index = random.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(index));
		}
		return sb.toString().toUpperCase();
	}

	public static void main(String[] args) {
		String randomString = generateRandomString();
		System.out.println("String aleatória: " + randomString);

		randomString = generateCustomRandomString();
		System.out.println("String aleatória: " + randomString);
	}
}
