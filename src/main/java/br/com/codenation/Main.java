package br.com.codenation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/answer.json"));
		String jsonContent = "";

		while (br.ready())
			jsonContent += br.readLine();

		br.close();
		
		ObjectMapper mapper = new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(jsonContent, Map.class);
		
		String cifrado = (String) map.get("cifrado");
		int numeroCasas = (Integer)map.get("numero_casas");
		
		String decrypted = Utils.decrypt(numeroCasas, cifrado);
		
		String signature;
		try {
			signature = Utils.generateSHA1Signature(decrypted);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			signature = "Erro";
		}
			
		
		map.put("decifrado",decrypted);
		map.put("resumo_criptografico",signature);
		
		RestClient client = new RestClient();
		
		client.sendResult(mapper.writeValueAsString(map), (String) map.get("token"));
		
		System.out.println(mapper.writeValueAsString(map));
	}
	

}
