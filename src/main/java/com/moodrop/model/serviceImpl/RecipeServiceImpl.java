package com.moodrop.model.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moodrop.MoodropApplication;
import com.moodrop.model.dao.RecipeDao;
import com.moodrop.model.dto.NotesDto;
import com.moodrop.model.dto.UserRecipeDto;
import com.moodrop.model.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final MoodropApplication moodropApplication;

	
	@Autowired
	RecipeDao dao;

    RecipeServiceImpl(MoodropApplication moodropApplication) {
        this.moodropApplication = moodropApplication;
    }

    /*
     * userId를 이용해서 Recipe를 가져온다.
     * */
	@Override
	public List<UserRecipeDto> getUserRecipe(String userId) {
		
		List<UserRecipeDto> userRecipeList = dao.selectUserRecipe(userId);
		
		return userRecipeList;
	}
	
	/*
	 * 사용자의 레시피를 작성한다.
	 * userRecipe 및 composition이 insert돼야 commit되게 한다.
	 * 그렇지 않으면 Rollback. 
	 * 추가 구현 사항: userId 포함, perfumeName 동일 시 rollBack 및 오류 메시지, rating_table 만들어서 평균 값 가져오기 
	 * */
	@Override
	@Transactional
	public int createUserRecipe(UserRecipeDto userRecipe) {
		// 사용자 userId 받을 시(UserRecipeDto userRecipe, userId)
		// userRecipe.setUserId(userId)
		int resultStatus = dao.insertUserRecipe(userRecipe);
		int userPerfumeId = userRecipe.getUserPerfumeId();
		if( userPerfumeId == 0) {
			throw new IllegalStateException("userPerfume을 가져오는 데 실패했습니다.");
		}
		
		if(userRecipe.getComposition() != null) {			
			dao.insertCompositionsInRecipe(userPerfumeId, userRecipe.getComposition());
		}
		
		return userPerfumeId;
	}
	
	// recipeId로 레시피를 조회한다.
	@Override
	public UserRecipeDto selectUserRecipe(int recipeId) {
		UserRecipeDto result = dao.selectRecipeById(recipeId);
		return result;
	}

	@Override
	public int deleteUserRecipe(int recipeId) {
		// TODO Auto-generated method stub
		
		int result = dao.deleteRecipeById(recipeId);
		return result;
	}
	

	
	
	
}


//		ObjectMapper mapper = new ObjectMapper();
//		
//		// 기본적으로 JSON은 문자열이기 때문에, Iterable하게 만들기 위해 mapper를 써야 한다.
//		for(UserRecipeDto ur: userRecipeList) {
//			String compositionJson = ur.getComposition(); 
//			List<Map<String, Object>> compositionList =
//			        mapper.readValue(compositionJson, new TypeReference<List<Map<String, Object>>>() {});
//			
//			for (Map<String, Object> item : compositionList) {
//			    System.out.println(item.get("note"));   
//			    System.out.println(item.get("type"));
//			    System.out.println(item.get("weight"));
//			}
//			String composition = ur.getComposition();	
//			System.out.println(composition);
//			
//		}

////note name -> note id로 바꿔서 넣기
//List<NotesDto> userCompositions = userRecipe.getComposition();
//List<Integer> userNoteIds = dao.selectNoteIdByName(userCompositions);
//System.out.println(userNoteIds);
//userRecipe.setCompositionNoteIds(userNoteIds);