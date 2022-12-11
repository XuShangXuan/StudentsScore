package com.gjun.studentsScore.job;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import com.gjun.studentsScore.model.StudentsScore;
import com.gjun.studentsScore.service.StudentsScoreService;
import com.gjun.studentsScore.util.SortUtil;

public class StudentsScoreJob {

	public static void main(String[] args) {

		String fileStudentsScoreDataPath = System.getProperty("user.dir")+ "/src/com/gjun/studentsScore/StudentsScoreData.csv";
		Path filePath = Paths.get(fileStudentsScoreDataPath);
		
		String fileStudentsScoreResuletPath = System.getProperty("user.dir")+ "/src/com/gjun/studentsScore/StudentsScoreResulet.csv";
		Path targetPath = Paths.get(fileStudentsScoreResuletPath);

		// Step1:使用File輸入串流將來源檔資料讀出
		if (Files.exists(filePath)) {

			StudentsScoreService service = new StudentsScoreService();

			List<StudentsScore> studentsScoreDatas;
			try {
				
				studentsScoreDatas = service.inputStudentsScoreData(filePath);
				// Step2:計算每一筆學生成績的總分與平均,並存入collection

				if (studentsScoreDatas != null && studentsScoreDatas.size() > 0) {
					// Step3:將collection裡的元素依平均分數降幕排序
					SortUtil.sortStudentsScoreByAverage(studentsScoreDatas);
					// Step4:將處理好的collection使用File輸出串流,將資料產出至結果檔StudentsScoreResulet.csv
					service.outputStudentsScoreData(targetPath, studentsScoreDatas);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
