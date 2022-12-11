package com.gjun.studentsScore.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.gjun.studentsScore.model.StudentsScore;

public class StudentsScoreService {

	/**
	 * Step1:使用File輸入串流將來源檔資料讀出StudentsScoreData.csv <br/>
	 * Step2:計算每一筆學生成績的總分與平均,並存入collection
	 * 
	 * @param fileData
	 * @return List<StudentsScore>
	 * @throws IOException
	 */
	public List<StudentsScore> inputStudentsScoreData(Path filePath) throws IOException {

		List<StudentsScore> studentsScoreData = new ArrayList();
		
		// 總分、平均(四捨五入小數點第一位)

		Charset cs = Charset.defaultCharset();

		List<String> lines = Files.readAllLines(filePath, cs);

		for (int i = 1; i < lines.size(); i++) {

			StudentsScore stu = new StudentsScore();
			String[] column = lines.get(i).split(",");

			stu.setStudentNo(column[0]);
			stu.setName(column[1]);

			int sum = 0;
			
			List<String> scoreData = new ArrayList<>();

			for (int j = 2; j < column.length; j++) {

				sum += Integer.parseInt(column[j]);

				scoreData.add(column[j]);
			}
			
			double ave=(double)sum/(column.length-2);
			ave=Math.round(ave * 10.0) / 10.0;
			
			stu.setScore(scoreData);
			stu.setSumScore(String.valueOf(sum));
			stu.setAverage(String.valueOf(ave));

			studentsScoreData.add(stu);

		}

		// 計算總分跟平均還沒做

		return studentsScoreData;
	}

	/**
	 * info:將處理好的collection使用File輸出串流,將資料產出至結果檔StudentsScoreResulet.csv
	 * 
	 * @param studentsScoreDatas
	 * @throws IOException
	 */
	public void outputStudentsScoreData(Path reportDir, List<StudentsScore> studentsScoreDatas) throws IOException {

		List<StringBuilder> lines = new ArrayList<>();

		for (int i = 0; i < studentsScoreDatas.size(); i++) {

			StringBuilder line = new StringBuilder();
			line.append(studentsScoreDatas.get(i).getStudentNo());
			line.append(",");
			line.append(studentsScoreDatas.get(i).getName());
			line.append(",");
			for (int j = 0; j < studentsScoreDatas.get(i).getScore().size(); j++) {
				line.append(studentsScoreDatas.get(i).getScore().get(j));
				line.append(",");
			}
			line.append(studentsScoreDatas.get(i).getSumScore());
			line.append(",");
			line.append(studentsScoreDatas.get(i).getAverage());

			lines.add(line);

		}

		Charset cs = Charset.defaultCharset();

		Files.write(reportDir, lines, cs, StandardOpenOption.CREATE);

	}

}
