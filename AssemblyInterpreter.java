package Miko;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AssemblyInterpreter {

	private static Map<String, Integer> registers = new HashMap<>();

	public static void executeInstruction(String instruction) {
		String[] parts = instruction.split("[,\\s#]+");
		String opcode = parts[0];

		if (opcode.equals("MV")) {
			int value = Integer.parseInt(parts[2]);		
			registers.put(parts[1], value);
		} 
		else if (opcode.equals("ADD")) {
			String register1 = parts[1];
			String operand = parts[2];
			int value;

			if(registers.containsKey(parts[2])) {
				value = registers.get(operand);
				registers.put(register1, registers.get(register1) + value);
			}else {
				value = Integer.parseInt(operand);
				registers.put(register1, registers.get(register1) + value);
			}
			
		} 
		else if (opcode.equals("SHOW")) {
			String register = "REG1";
			System.out.println(registers.get(register));
		}
	}

	public static void executeProgram(String[] program) {

		for (String instruction : program) {
			instruction = instruction.trim();

			if (!instruction.isEmpty()) {
				executeInstruction(instruction);
			}
		}
	}

	public static void main(String[] args) {

		String filePath = "C:\\Users\\sswat\\eclipse-workspace\\Assesments\\src\\Miko\\input.txt";
		StringBuilder content = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		} catch (IOException e) {
			System.out.println("Error reading the file: " + e.getMessage());
			return;
		}

		String[] inputArray = content.toString().split("\n");

		executeProgram(inputArray);
	}
}
