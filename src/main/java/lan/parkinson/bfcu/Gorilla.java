package lan.parkinson.bfcu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Marvin Parkinson
 * 
 *         Parse student data this is an implementation of a TestGorilla
 *         interview question -- to best of my memory
 * 
 *         Parse student data (name and grades) and output the the student's
 *         name and average grade Grade information can be delimited with any
 *         non-numeric character
 */
public class Gorilla {
	private static final Class<Gorilla> ME = Gorilla.class;
	private static final Logger LAGER = Logger.getLogger(ME.getName());
	private static final Pattern bracketMatcher = Pattern.compile("\\[([a-zA-Z ]+)([^\\]]*)\\]");
	private static final Pattern numberMatcher = Pattern.compile("(\\d+)");

	/**
	 * @param input
	 * @return Map<Student>
	 */
	public Map<String, Integer> parseStudents(String input) {
		String method = "parseStudents";
		LAGER.entering(ME.getName(), method, input);
		List<Student> students = new ArrayList<>();
		Matcher m = bracketMatcher.matcher(input);
		while (m.find()) {
			Student s = new Student(m.group(1), m.group(2));
			LAGER.info(s.toString());
			students.add(s);
		}
		Map<String, Integer> results = new HashMap<>();
		for (Student s : students) {
			results.put(s.name, s.getAverageGrade());
		}
		return results;
	}

	class Student {
		private String name = null;
		private Integer[] grades;

		/**
		 * Constructor
		 * 
		 * @param name
		 * @param grades
		 */
		private Student(String name, String grades) {
			this.name = name.trim();
			Matcher m = numberMatcher.matcher(grades);
			List<Integer> l = new ArrayList<>();
			while (m.find()) {
				l.add(Integer.parseInt(m.group()));
			}
			this.grades = l.toArray(new Integer[l.size()]);
		}

		public Integer getAverageGrade() {
			if (this.grades == null || this.grades.length == 0)
				return 0;
			int sum = 0;
			for (int g : this.grades) {
				sum += g;
			}
			return Integer.valueOf(sum / this.grades.length);
		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", grades=" + Arrays.toString(grades) + "]";
		}

	}

}
