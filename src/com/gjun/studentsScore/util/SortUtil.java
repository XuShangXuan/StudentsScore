package com.gjun.studentsScore.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.gjun.studentsScore.model.StudentsReport;
import com.gjun.studentsScore.model.StudentsScore;

public class SortUtil {

	public static void sortStudentsScoreByAverage(StudentsReport studentsScoreDatas) {

		Comparator<StudentsScore> comparestudentsScore = new Comparator<StudentsScore>() {// 實作排序的規則
			@Override
			public int compare(StudentsScore s1, StudentsScore s2) {

				if (s2.getSumScore().compareTo(s1.getSumScore()) != 0) {// 主排序:總分
					return s2.getSumScore().compareTo(s1.getSumScore());
				}

				if (s2.getScore().get(0).compareTo(s1.getScore().get(0)) != 0) {// 次排序:國文(如果總成績相同，就用國文成績進行排序)
					return s2.getScore().get(0).compareTo(s1.getScore().get(0));
				}

				if (s2.getScore().get(3).compareTo(s1.getScore().get(3)) != 0) {// 次次排序:數學(如果國文成績也相同，就用數學成績進行排序)
					return s2.getScore().get(3).compareTo(s1.getScore().get(3));
				}

				return 0;

			}
		};

		Collections.sort(studentsScoreDatas.getStudentsScoreData(), comparestudentsScore);// 需要排序的資料及排序的規則

	}

}
