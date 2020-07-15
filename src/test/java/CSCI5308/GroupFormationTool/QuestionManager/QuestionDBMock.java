package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.SurveyManager.Survey;

import java.util.ArrayList;
import java.util.List;

public class QuestionDBMock implements IQuestionPersistence 
{
	List<IQuestion> questions;
	private QuestionAbstractFactory questionFactory = QuestionAbstractFactory.getFactory();
	
	@Override
	public List<IQuestion> loadQuestionsSortedByTitle(String bannerId) 
	{
		questions = new ArrayList<IQuestion>();
		if(bannerId.equals("B-000000")) 
		{
			IQuestion q = questionFactory.createQuestion();
			q.setId(1);
			q.setTitle("Test Title");
			q.setText("Test Question");
			q.setType(QuestionType.TEXT);
			questions.add(q);
			
			q = questionFactory.createQuestion();
			q.setId(1);
			q.setTitle("Test Title 2");
			q.setText("Test Question");
			q.setType(QuestionType.TEXT);
			questions.add(q);
		}
		return questions;
	}

	@Override
	public List<IQuestion> loadSortedQuestionsSortedByDate(String bannerId) 
	{
		questions = new ArrayList<IQuestion>();
		if(bannerId.equals("B-000000")) 
		{
			IQuestion q = questionFactory.createQuestion();
			q.setId(1);
			q.setTitle("Test Title 2");
			q.setText("Test Question");
			q.setType(QuestionType.TEXT);
			questions.add(q);
			
			q = questionFactory.createQuestion();
			q.setId(1);
			q.setTitle("Test Title");
			q.setText("Test Question");
			q.setType(QuestionType.TEXT);
			questions.add(q);
		}
		return questions;
	}

	public boolean deleteQuestionByQuestionId(long questionId) 
	{
		IQuestion q = questionFactory.createQuestion();
		q.setId(1);
		q.setTitle("Test Title");
		q.setText("Test Question");
		q.setType(QuestionType.TEXT);
		
		if(questionId>-1) 
		{
			q.setDefaults();
			return true;
		}
		return false;
	}

	@Override
	public long createQuestion(IQuestion question, String bannerID) 
	{
		if(question.getId()>-1) 
		{
			return 1;
		}
		return 0;
	}

	@Override
	public boolean createQuestionOption(IOptionValue option, int order, long questionID) 
	{
		if(questionID == -1 || isStringEmpty(option.getText()) || isStringEmpty(option.getStoredAs())) 
		{
			return false;
		}
		return true;
	}
	
	public boolean isStringEmpty(String s) 
	{
		return s.replaceAll(" ","").length() == 0;
	}

}
