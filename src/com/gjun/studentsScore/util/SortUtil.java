package com.gjun.studentsScore.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.gjun.studentsScore.model.StudentsScore;

public class SortUtil {

	public static void sortStudentsScoreByAverage(List<StudentsScore> studentsScoreDatas) {
		// 主排序:總分
		// 次排序:國文
		// 次次排序:數學

		Comparator<StudentsScore> comparestudentsScore = new Comparator<StudentsScore>() {
			@Override
			public int compare(StudentsScore s1, StudentsScore s2) {

				s1.getSumScore().compareTo(s2.getSumScore());

				if (s2.getSumScore().compareTo(s1.getSumScore()) != 0) {
					return s2.getSumScore().compareTo(s1.getSumScore());
				}

				if (s2.getScore().get(0).compareTo(s1.getScore().get(0)) != 0) {
					return s2.getScore().get(0).compareTo(s1.getScore().get(0));
				}

				if (s2.getScore().get(3).compareTo(s1.getScore().get(3)) != 0) {
					return s2.getScore().get(3).compareTo(s1.getScore().get(3));
				}

				return 0;
			}
		};

		Collections.sort(studentsScoreDatas, comparestudentsScore);

	}

}
