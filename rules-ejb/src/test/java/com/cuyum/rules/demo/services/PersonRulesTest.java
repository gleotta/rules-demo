package com.cuyum.rules.demo.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import au.com.bytecode.opencsv.CSVReader;

import com.cuyum.rules.demo.domain.DocumentType;
import com.cuyum.rules.demo.domain.Person;
import com.cuyum.rules.demo.exceptions.ValidationException;
import com.cuyum.rules.demo.services.impl.PersonRulesImpl;

@RunWith(Parameterized.class)
public class PersonRulesTest {
	
	private static Logger log = Logger.getLogger(PersonRulesTest.class);

	@Parameters
	public static Collection<Object[]> data() {
		try {
			String docBase = System.getProperty("rules.demo.docbase",".");
			File file = new File(docBase,"isAdult.csv");
			log.info("Inicializando test con datos de "+file.getCanonicalPath());
			FileReader fr = new FileReader(file);
			CSVReader reader = new CSVReader(fr, ';', '\'', 1);
			List<String[]> rows = reader.readAll();
			Object[][] ret = new Object[rows.size()][2];
			int i = 0;
			for (String[] row : rows) {
				Person p = new Person();
				p.setDocumentNumber(row[3]);
				p.setDocumentType(DocumentType.valueOf(row[2]));
				try {
					p.setAge(Integer.valueOf(row[4]));
				} catch (NumberFormatException e) {
					p.setAge(null);
				}
				p.setFirstName(row[0]);
				p.setLastName(row[1]);
			
				
				String result = row[5];
				ret[i][0] = p;
				ret[i][1] = result;
				for (int j = 0; j < row.length; j++) {
					System.out.print(row[j]+"\t");
				}
				System.out.println();
				i++;
			}
			log.info("Inicializacion correcta "+file.getCanonicalPath());
			return Arrays.asList(ret);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("No se puede comenzar test", e);
		}
		
		
//		Object[][] val = {
//				{ new Person("111", DNI, 8, "Bart", "Simpson"), "true" },
//				{ new Person("222", DNI, 7, "Liza", "Simpson"), "false" },
//				{ new Person("333", DNI, null, "Magie", "Simpson"),
//						"ValidationException" },
//				{ new Person("444", DNI, 34, "March", "Simpson"), "true" },
//				{ new Person("555", DNI, 36, "Homer", "Simpson"), "false" }
//
//		};
//		return Arrays.asList(val);
	}

	private PersonRules personRules;

	private Person person;

	private String resultExpected;

	public PersonRulesTest(Person p, String result) {
		person = p;
		resultExpected = result;
	}

	@Before
	public void setUp() throws Exception {
		personRules = new PersonRulesImpl();
	}

	@Test
	public final void testIsAdult() {
		log.info("Persona: "+person);
		log.info("Resultado esperado:"+resultExpected);
		try {
			boolean res = personRules.isAdult(person);
			if (resultExpected.equals("true"))
				assertTrue(
						"Se esperaba persona adulta con edad "
								+ person.getAge(), res);
			else if (resultExpected.equals("false"))
				assertFalse(
						"Se esperaba persona no adulta con edad "
								+ person.getAge(), res);
			else
				fail("Respuesta inesperada para persona con edad "
						+ person.getAge() + ": " + resultExpected);

		} catch (ValidationException e) {
			if (!resultExpected.equals(e.getClass().getSimpleName())) {
				fail("Respuesta inesperada:" + e.getMessage());
			}
		}

	}
	
	public static void main(String[] args) {
		
		FileReader fr = null;
		try {
			fr = new FileReader(new File("isAdult.csv"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException("No se puede comenzar test", e);
		}
		CSVReader reader = new CSVReader(fr, ';', '\'', 1);
		try {
			List<String[]> rows = reader.readAll();
			for (String[] row : rows) {
				for (int i = 0; i < row.length; i++) {
					System.out.print(row[i]+"\t");
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
